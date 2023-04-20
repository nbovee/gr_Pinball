#include <RTClib.h>
RTC_DS1307 rtc;
#define IR_pin 3
#define red 8
#define green 9
bool last_ir;
void setup() {
  Serial.begin(9600);
  pinMode(IR_pin, INPUT);
  pinMode(red, OUTPUT);
  pinMode(green, OUTPUT);
  if (!rtc.begin()) {
    Serial.println("No RTC component detected");
    while (1) {};
  }
  rtc.adjust(DateTime(F(__DATE__), F(__TIME__)));
  last_ir = 1;
}

void loop() {

  bool current_ir = digitalRead(IR_pin);
  if (current_ir != last_ir) {
    if (current_ir) {
      Serial.print("Motion Detected: ");
      digitalWrite(red, LOW);
      digitalWrite(green, HIGH);
    } else {
      Serial.print("Motion Lost: ");
      digitalWrite(red, HIGH);
      digitalWrite(green, LOW);
    }
    DateTime now = rtc.now();
    Serial.print(now.hour());
    Serial.print(":");
    Serial.print(now.minute());
    Serial.print(":");
    Serial.println(now.second());
  }
  last_ir = current_ir;
}
