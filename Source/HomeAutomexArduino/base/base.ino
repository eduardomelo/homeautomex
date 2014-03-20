#include <SPI.h>
#include <Ethernet.h>

byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
//IPAddress ip(10,1,2,21);
IPAddress ip(192,168,0,108);
//char server[] = "10.1.2.6";
char server[] = "192.168.0.101";

EthernetClient client;

boolean fazerConexao = true;

void setup() {
  Serial.begin(9600);

  //try use DHCP
  if (Ethernet.begin(mac) == 0) {
    Serial.println("Failed to configure Ethernet using DHCP");
    Ethernet.begin(mac, ip);
  }
  //sem DHCP
  //Ethernet.begin(mac, ip);
  
  // give the Ethernet shield a second to initialize:
  delay(1000);
  Serial.println("Arduino IP:");
  Serial.println(Ethernet.localIP());
  Serial.println();
}

void loop() {
  if (fazerConexao) {
    conectar();
    fazerConexao = false;
  }
  
  //Tem cliente disponível?
  if (client.available()) {
    char c = client.read();
    Serial.print(c);
  }

  //o cliente foi desconectado?
  if (!client.connected()) {
    Serial.println();
    Serial.println("disconnecting.");
    client.stop();
    //espera 2 segundos para conectar novamente
    delay(2000);
    
    fazerConexao = true;
  }
}

void conectar() {
  Serial.println("connecting...");
  
  if (client.connect(server, 80)) {
    Serial.println("connected");
    
    client.println("POST /homeautomex/HomeAutomexWS.asmx HTTP/1.1");
    client.print("Host: ");
    client.println(String(server));
    //client.println("Content-Type: application/x-www-form-urlencoded");
    //client.println("User-Agent: Arduino/1.0");
    client.println("Connection: close");
    
    //String data = "param=valor&submit=Submit";
    
    //client.print("Content-Length: ");
    //client.println(data.length());
    client.println("Content-Length: 0");
    //client.println();
    //client.println(data);
    client.println();
  } 
  else {
    Serial.println("connection failed");
  }
}
