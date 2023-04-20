byte byte0;
char c;
String output;

void setup() {
  Serial.begin(115200);
}

void loop() {
  // assume we are receiving a 4 byte float/double
  while (Serial.available() > 0) {
    byte0 = Serial.read();  //read in the new byte
    if (byte0 == 0x0A) { //newline
      Serial.print("ESP8266:");
      Serial.println(output);
      output = "";
    } else {
      output += (char)byte0;  //add it to the string
    }
  }
}
