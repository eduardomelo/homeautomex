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
String ipString = "192.168.0.115";
byte ip[] = { 192, 168, 0, 115 };
char servidor[] = "192.168.0.100";
const unsigned short int porta = 80;
String url = "/ws/index.php";

//IPAddress ip(10,1,2,21);
//char servidor[] = "10.1.2.1";
//const unsigned short int porta = 8080;
//String url = "/ws/HomeAutomexWS.asmx";

boolean usarDHCP = false;

EthernetClient cliente;

String readString;

//dispositivos
const unsigned short int dispositivoA = 2;
const unsigned short int dispositivoB = 3;

//botoes
const unsigned short int botaoA = 4;
const unsigned short int botaoB = 5;

const unsigned short int intervaloBotao = 150;

//interruptores digitais
boolean interruptorA;
boolean interruptorB;

Thread threadConectar = Thread();
Thread threadTarefas = Thread();

const unsigned short int intervaloTarefas = 10;
const unsigned short int intervaloConectar = 1000;

ThreadController threadControle = ThreadController();

void setup() {
  pinMode(dispositivoA, OUTPUT);
  pinMode(dispositivoB, OUTPUT);
  pinMode(botaoA, INPUT);
  pinMode(botaoB, INPUT);

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
  // give the Ethernet shield a second to initialize:
  delay(1000);
  Serial.println("Arduino IP:");
  Serial.println(Ethernet.localIP());
  Serial.println();

  threadTarefas.onRun(executarTarefas);
  threadTarefas.setInterval(intervaloTarefas);

  threadConectar.onRun(conectar);
  threadConectar.setInterval(intervaloConectar);

  threadControle.add(&threadTarefas);
  threadControle.add(&threadConectar);
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
  if (digitalRead(botaoA)) {
    interruptorA = (interruptorA) ? false : true;
    delay(intervaloBotao);
  }
  else {
    if (readString.indexOf("D2:1") > 0) {
      interruptorA = true;
    }
    else if (readString.indexOf("D2:0") > 0) {
      interruptorA = false;
    }
  }

  if (digitalRead(botaoB)) {
    interruptorB = (interruptorB) ? false : true;
    delay(intervaloBotao);
  }
  else {
    if (readString.indexOf("D3:1") > 0) {
      interruptorB = true;
    }
    else if (readString.indexOf("D3:0") > 0) {
      interruptorB = false;
    }
  }

  //-1 == string não encontrada.
  //if (readString.indexOf("HTTP/1.1 200") != -1) {
  (interruptorA) ? digitalWrite(dispositivoA, HIGH) : digitalWrite(dispositivoA, LOW);
  (interruptorB) ? digitalWrite(dispositivoB, HIGH) : digitalWrite(dispositivoB, LOW);
  //}
  readString = "";
}

