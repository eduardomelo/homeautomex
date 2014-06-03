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
#include <Servo.h>

byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };

/*
String ipString = "192.168.0.116";
byte ip[] = { 192, 168, 0, 116 };
char servidor[] = "192.168.0.100";
const unsigned short int porta = 80;
String url = "/ws/index.php";
*/

String ipString = "192.168.0.116";
byte ip[] = { 192, 168, 0, 116 };
char servidor[] = "192.168.0.102";
const unsigned short int porta = 80;
String url = "/meuwebservice/HomeAutomexWS.asmx/StatusArduino";

boolean usarDHCP = false;

EthernetClient cliente;

String readString;

//dispositivos
const unsigned short int dispositivoA = 2;
const unsigned short int dispositivoB = 3;
const unsigned short int dispositivoC = 4;
const unsigned short int dispositivoD = 5;
const unsigned short int dispositivoE = 6;

//interruptores digitais
boolean interruptorA;
boolean interruptorB;
boolean interruptorC;
boolean interruptorD;
boolean interruptorE;

Thread threadConectar = Thread();
Thread threadTarefas = Thread();

const unsigned short int intervaloConectar = 1300;
const unsigned short int intervaloTarefas = 20;

ThreadController threadControle = ThreadController();

Servo meuServo;
unsigned short int posicao = 80;

void setup() {
  pinMode(dispositivoA, OUTPUT);
  pinMode(dispositivoB, OUTPUT);
  pinMode(dispositivoC, OUTPUT);
  pinMode(dispositivoD, OUTPUT);
  pinMode(dispositivoE, OUTPUT);

  Serial.begin(9600);

  if (usarDHCP) {
    if (Ethernet.begin(mac) == 0) {
      Serial.println("Failed to configure Ethernet using DHCP");
      Ethernet.begin(mac, ip);
    }
  }
  else {
    Ethernet.begin(mac, ip);
  }
  // give the Ethernet shield a time to initialize:
  delay(1200);
  Serial.println("Arduino IP:");
  Serial.println(Ethernet.localIP());
  Serial.println();

  threadConectar.onRun(conectar);
  threadConectar.setInterval(intervaloConectar);
  
  threadTarefas.onRun(executarTarefas);
  threadTarefas.setInterval(intervaloTarefas);
  
  threadControle.add(&threadConectar);
  threadControle.add(&threadTarefas);
}

void loop() {
  if (cliente.available()) {
    char c = cliente.read();

    if (readString.length() < 250) {
      readString += c;
    }

    Serial.print(c);
  }

  if (!cliente.connected()) {
    Serial.println();
    Serial.println("Desconectado.");
    cliente.stop();
  }

  /*
  Run ThreadController
   this will check every thread inside ThreadController,
   if it should run. If yes, he will run it;
   */
  threadControle.run();
}

void conectar() {
  Serial.println("Conectando...");

  if (cliente.connect(servidor, porta)) {
    Serial.println("Conectado");

    cliente.println("POST " + url + " HTTP/1.1");
    cliente.print("Host: ");
    cliente.println(String(servidor));
    cliente.println("Content-Type: application/x-www-form-urlencoded");
    cliente.println("User-Agent: Arduino/1.0");
    cliente.println("Connection: close");

    String data = "status=";
    data += "D2:";
    data += interruptorA;
    data += "|D3:";
    data += interruptorB;
    data += "|D4:";
    data += interruptorC;
    data += "|D5:";
    data += interruptorD;
    data += "|D6:";
    data += interruptorE;
    data += "&ip=";
    data += ipString;
    data += "&submit=Submit";

    cliente.print("Content-Length: ");
    cliente.println(data.length());
    cliente.println();
    cliente.println(data);
    cliente.println();
  }
  else {
    Serial.println("Falha na conexão");
  }
}

void executarTarefas() {
  /*
  if (digitalRead(botaoA)) {
    interruptorA = (interruptorA) ? false : true;
    delay(intervaloBotao);
  }
  else {
  */
    if (readString.indexOf("D2:1") > 0) {
      interruptorA = true;
    }
    else if (readString.indexOf("D2:0") > 0) {
      interruptorA = false;
    }
  //}

  /*
  if (digitalRead(botaoB)) {
    interruptorB = (interruptorB) ? false : true;
    delay(intervaloBotao);
  }
  else {
  */
    if (readString.indexOf("D3:1") > 0) {
      interruptorB = true;
    }
    else if (readString.indexOf("D3:0") > 0) {
      interruptorB = false;
    }
  //}

  /*
  if (digitalRead(botaoC)) {
    interruptorC = (interruptorC) ? false : true;
    delay(intervaloBotao);
  }
  else {
  */
    if (readString.indexOf("D4:1") > 0) {
      interruptorC = true;
    }
    else if (readString.indexOf("D4:0") > 0) {
      interruptorC = false;
    }
  //}

  /*
  if (digitalRead(botaoD)) {
    interruptorD = (interruptorD) ? false : true;
    delay(intervaloBotao);
  }
  else {
  */
    if (readString.indexOf("D5:1") > 0) {
      interruptorD = true;
    }
    else if (readString.indexOf("D5:0") > 0) {
      interruptorD = false;
    }
  //}
    if (readString.indexOf("D6:1") > 0) {
      interruptorE = true;
    }
    else if (readString.indexOf("D6:0") > 0) {
      interruptorE = false;
    }

  //-1 == string não encontrada.
  //if (readString.indexOf("HTTP/1.1 200") != -1) {
  (interruptorA) ? digitalWrite(dispositivoA, HIGH) : digitalWrite(dispositivoA, LOW);
  (interruptorB) ? digitalWrite(dispositivoB, HIGH) : digitalWrite(dispositivoB, LOW);
  (interruptorC) ? digitalWrite(dispositivoC, HIGH) : digitalWrite(dispositivoC, LOW);
  (interruptorD) ? digitalWrite(dispositivoD, HIGH) : digitalWrite(dispositivoD, LOW);
  (interruptorE) ? digitalWrite(dispositivoE, HIGH) : digitalWrite(dispositivoE, LOW);
  //}
  readString = "";
}

