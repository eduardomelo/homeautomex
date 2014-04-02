/*
Ethernet shield w5100
Pinos digitais 13, 12, 11 e 10 são utilizados
Pino digital 4 é do SD reader mas como não vai ser usado, está disponível
*/

//pino digital 2 Relê 1
//pino digital 3 Relê 2
//pino digital 4 Push button p/ Relê 1
//pino digital 5 Push button p/ Relê 2

#include <SPI.h>
#include <Ethernet.h>
#include <Thread.h>
#include <ThreadController.h>

byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
//IPAddress ip(10,1,2,21);
IPAddress ip(192,168,0,108);
//char server[] = "10.1.2.1";
char server[] = "192.168.0.101";

EthernetClient client;

String readString;

const unsigned short int dispositivoA = 2;
const unsigned short int dispositivoB = 3;

//const unsigned short int botaoA = 4;
//const unsigned short int botaoB = 5;

boolean interruptorA;
boolean interruptorB;

Thread threadConectar = Thread();
Thread threadTarefas = Thread();

ThreadController threadControle = ThreadController();

void setup() {
  pinMode(dispositivoA, OUTPUT);
  pinMode(dispositivoB, OUTPUT);
  //pinMode(botaoA, INPUT);
  //pinMode(botaoB, INPUT);
  Serial.begin(9600);

  //try use DHCP
  /*if (Ethernet.begin(mac) == 0) {
    Serial.println("Failed to configure Ethernet using DHCP");
    Ethernet.begin(mac, ip);
  }*/
  //sem DHCP
  Ethernet.begin(mac, ip);
  
  // give the Ethernet shield a second to initialize:
  delay(1000);
  Serial.println("Arduino IP:");
  Serial.println(Ethernet.localIP());
  Serial.println();
  
  threadTarefas.onRun(executarTarefas);
  threadTarefas.setInterval(20);
  
  threadConectar.onRun(conectar);
  threadConectar.setInterval(2000);
  
  threadControle.add(&threadTarefas);
  threadControle.add(&threadConectar);
}

void loop() {
  //Tem cliente disponível?
  if (client.available()) {
    char c = client.read();
    
    if (readString.length() < 250) {
      readString += c;
    }
    
    Serial.print(c);
  }

  //o cliente foi desconectado?
  if (!client.connected()) {
    Serial.println();
    Serial.println("disconnecting.");
    client.stop();
  }
  
  /*
  Run ThreadController
  this will check every thread inside ThreadController,
  if it should run. If yes, he will run it;
  */
  threadControle.run();
}

void conectar() {
  Serial.println("connecting...");
  
  //if (client.connect(server, 8080)) {
  if (client.connect(server, 80)) {
    Serial.println("connected");
    
    //client.println("POST /ws/HomeAutomexWS.asmx HTTP/1.1");
    client.println("POST /homeautomex/index.php HTTP/1.1");
    client.print("Host: ");
    client.println(String(server));
    client.println("Content-Type: application/x-www-form-urlencoded");
    client.println("User-Agent: Arduino/1.0");
    client.println("Connection: close");
    
    String data = "params=";
    data += "D2:";
    data += digitalRead(dispositivoA);
    //data += interruptorA;
    data += "|D3:";
    data += digitalRead(dispositivoB);
    //data += interruptorB;
    data += "&submit=Submit";
    
    client.print("Content-Length: ");
    client.println(data.length());
    client.println();
    client.println(data);
    client.println();
  }
  else {
    Serial.println("connection failed");
  }
}

void executarTarefas() {
  /*if (digitalRead(botaoA)) {
    interruptorA = (interruptorA) ? LOW : HIGH;
    delay(100);
  }
  else {
    */
    if (readString.indexOf("D2:1") > 0) {
      interruptorA = HIGH;
    }
    else if (readString.indexOf("D2:0") > 0) {
      interruptorA = LOW;
    }
  //}
  
  if (readString.indexOf("D3:1") > 0 && interruptorB == LOW) {
    interruptorB = HIGH;
  }
  else if (readString.indexOf("D3:0") > 0 && interruptorB == HIGH) {
    interruptorB = LOW;
  }
  /*else if (digitalRead(botaoB)) {
    interruptorB = (interruptorB) ? LOW : HIGH;
    delay(100);
  }*/
  
  //-1 == string não encontrada. indexOf retorna a posição da string
  if (readString.indexOf("HTTP/1.1 200") != -1) {
    (interruptorA) ? digitalWrite(dispositivoA, HIGH) : digitalWrite(dispositivoA, LOW);
    (interruptorB) ? digitalWrite(dispositivoB, HIGH) : digitalWrite(dispositivoB, LOW);
  }
  //Serial.println(readString);
  readString = "";
}
