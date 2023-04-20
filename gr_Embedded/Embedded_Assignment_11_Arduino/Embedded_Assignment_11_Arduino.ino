#define b_pin 10

void setup() {
  pinMode(b_pin, INPUT_PULLUP);
  Serial.begin(9600);
  Serial1.begin(115200);
}

void loop() {
  if(digitalRead(b_pin) == 0) {
    Serial1.write("button!\n");
    delay(100);
  }
  if (Serial1.available()) {
    int inByte = Serial1.read();
    Serial.write(inByte);
  }
}
