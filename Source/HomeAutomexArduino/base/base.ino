#include <SPI.h>
#include <Ethernet.h>

byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };

char server[] = "10.1.2.6";    // name address for Google (using DNS)

IPAddress ip(10,1,2,21);

EthernetClient client;

void setup() {
  Serial.begin(9600);

  if (Ethernet.begin(mac) == 0) {
    Serial.println("Failed to configure Ethernet using DHCP");
    Serial.println(Ethernet.localIP());
    Ethernet.begin(mac, ip);
  }
  // give the Ethernet shield a second to initialize:
  delay(1000);
  Serial.println("connecting...");

  if (client.connect(server, 54090)) {
    Serial.println("connected");
    Serial.println(Ethernet.localIP());
    
    client.println("POST /HomeAutomexWS.asmx/ConsutarTodosUsuarios HTTP/1.1");
    client.print("Host: ");
    client.println(String(server));
    client.println("Content-Type: application/x-www-form-urlencoded");
    //client.println("User-Agent: Arduino/1.0");
    client.println("Connection: close");
    
    String data = "param=valor&submit=Submit";
    
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

void loop()
{
  if (client.available()) {
    char c = client.read();
    Serial.print(c);
  }

  if (!client.connected()) {
    Serial.println();
    Serial.println("disconnecting.");
    client.stop();
  }
}
