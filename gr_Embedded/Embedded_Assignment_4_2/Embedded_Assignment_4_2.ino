#define r_led 2
#define g_led 3
#define b_led 4
#define r_pin 8
#define g_pin 9
#define b_pin 10

void setup() {
  pinMode(r_led, OUTPUT);
  pinMode(g_led, OUTPUT);
  pinMode(b_led, OUTPUT);
  pinMode(r_pin, INPUT_PULLUP);
  pinMode(g_pin, INPUT_PULLUP);
  pinMode(b_pin, INPUT_PULLUP);
}

void loop() {
  digitalWrite(r_led, not(digitalRead(r_pin)));
  digitalWrite(g_led, not(digitalRead(g_pin)));
  digitalWrite(b_led, not(digitalRead(b_pin)));
}
