#include <SPI.h>
#include <Ethernet.h>

/*
Pinos digitais 13, 12, 11 e 10 são utilizados no ethernet shield
Pino digital 4 é utilizado pelo SD reader
*/

//pino analógico 0 LDR
//pino analógico 1 Temp
//pino digital 2 Led LDR
//pino digital 3 Led Temp
//pino digital 4 Push button

int analogLdr = 0;
int analogTemp = 1;
int digitalLedLdr = 2;
int digitalLedTemp = 3;

int button = 4;
boolean changeState = 0;//estado da luz ON - OFF

//vai servir para concatenar os caractéres recebidos via http ao ter um cliente
String readString;

byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
IPAddress ip(192,168,0,177);

// Initialize the Ethernet client library
// with the IP address and port of the server 
// that you want to connect to (port 80 is default for HTTP):
EthernetClient clientToWS;
char serverToClient[15] = {"192.168.0.101"};//ip do servidor
// Initialize the Ethernet server library
// with the IP address and port you want to use 
// (port 80 is default for HTTP):
EthernetServer server(80);

void setup() {
  // start the Ethernet connection and the server:
  Ethernet.begin(mac, ip);
  server.begin();
  
  //Coloca o pino digital 2 como saída
  pinMode(digitalLedLdr, OUTPUT);
  pinMode(digitalLedTemp, OUTPUT);
  //Pino digital 4 como entrada
  pinMode(button, INPUT);
  
  // Open serial communications and wait for port to open:
  Serial.begin(9600);
}

void loop() {
  int state = digitalRead(button);//ler o estado do botão
  if (state == HIGH) {
    changeState = (changeState) ? 0 : 1;//switch de estado ON - OFF
    digitalWrite(digitalLedLdr, HIGH);
    delay(500);
  }
  
  int sensorReadingLdr = analogRead(analogLdr); // ler o valor do sensor LDR
  
  //verifica se a luz foi apagada
  if (!changeState) {
    //0 - 1023 |0 = Muito escuro, 1023 = Muito claro|
    (sensorReadingLdr < 300) ? digitalWrite(digitalLedLdr, HIGH) : digitalWrite(digitalLedLdr, LOW);
  }
  
  int sensorReadingTemp = analogRead(analogTemp); //analog pin 1
  sensorReadingTemp = (sensorReadingTemp * 0.00488) * 100; //cálculo para º célcius
  //Temperatura superior acende o led
  (sensorReadingTemp > 33) ? digitalWrite(digitalLedTemp, HIGH) : digitalWrite(digitalLedTemp, LOW);

  // listen for incoming clients
  EthernetClient client = server.available();

  if (client) {
    // an http request ends with a blank line
    boolean currentLineIsBlank = true;
    while (client.connected()) {      
      if (client.available()) {
        char c = client.read();
        
        if (readString.length() < 100) {
          readString += c;              
        }
        
        // if you've gotten to the end of the line (received a newline
        // character) and the line is blank, the http request has ended,
        // so you can send a reply
        if (c == '\n' && currentLineIsBlank) {
          // send a standard http response header
          client.println("HTTP/1.1 200 OK");
          client.println("Content-Type: text/html");
          client.println("Connection: close");  // the connection will be closed after completion of the response
	  client.println("Refresh: 2");  // refresh the page automatically every x seconds
          client.println();
          client.println("<!DOCTYPE HTML>");
          client.println("<html>");

          client.print("A0:");
          client.print(sensorReadingLdr);
          client.print("|A1:");
          client.print(sensorReadingTemp);
          client.print("|D2:");
          client.print(digitalRead(digitalLedLdr));
          client.print("|D3:");
          client.print(digitalRead(digitalLedTemp));
          
          client.println("</html>");
          break;
        }
        if (c == '\n') {
          // you're starting a new line
          currentLineIsBlank = true;
        } 
        else if (c != '\r') {
          // you've gotten a character on the current line
          currentLineIsBlank = false;
        }
      }
    }
    // give the web browser time to receive the data
    delay(1);
    // close the connection:
    client.stop();
  }
  /*|||***CLIENT***|||*/
  
  // if you get a connection, report back via serial:
  if (clientToWS.connect(serverToClient, 80)) {
    Serial.println("connected");
    
    String data = "parametro=valor";
    /*data += "A0:" + sensorReadingLdr;
    data += "|A1:" + sensorReadingTemp;
    data += "|D2:" + digitalRead(digitalLedLdr);
    data += "|D3:" + digitalRead(digitalLedTemp);*/
    data += "&submit=Submit";
    
    // Make a HTTP request:
    clientToWS.println("POST /teste/index.php HTTP/1.1");
    //String host = "Host: %c", serverToClient;
    clientToWS.println("Host: 192.168.0.101");
    clientToWS.println("Content-Type: application/x-www-form-urlencoded");
    clientToWS.println("Connection: close");
    clientToWS.print("Content-Length: ");
    clientToWS.println(data.length());
    clientToWS.println();
    clientToWS.print(data);
    clientToWS.println();
  }
  else {
    // if you didn't get a connection to the server:
    //Serial.println("connection failed");
  }
  
  // if there are incoming bytes available 
  // from the server, read them and print them:
  if (clientToWS.available()) {
    char c = clientToWS.read();
    Serial.print(c);
    client.print(c);
  }

  // if the server's disconnected, stop the client:
  if (!clientToWS.connected()) {
    Serial.println();
    Serial.println("disconnecting.");
    clientToWS.stop();

    // do nothing forevermore:
    //while(true);
  }
}

