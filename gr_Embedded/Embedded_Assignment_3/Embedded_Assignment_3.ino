  #define C 2
  #define D 3
  #define E 4
  #define F 5 
  #define G 6
  

void setup() {
  pinMode(C, OUTPUT);
  pinMode(D, OUTPUT);
  pinMode(E, OUTPUT);
  pinMode(F, OUTPUT);
  pinMode(G, OUTPUT);
}

void loop() {
  note(C, 1000);
  note(D, 1000);
  note(E, 1000);
  note(G, 1000);
  note(F, 1000);
  note(D, 1000);
  note(C, 2000);
  exit(0);
}

void note(int pin, int ms_offset){
  digitalWrite(pin, HIGH);
  delay(ms_offset);
  digitalWrite(pin, LOW);
}
