class Ultrasonic {
public:
  Ultrasonic(int _echo, int _trig);
  void begin();
  void update();
  void setMode(bool _is_inches);
  double getReading();
  static Ultrasonic *instance;
  static double conv_factor();
  static void isr();
  bool is_inches;
  int echo;
  int trig;
  volatile double latest_dist;
  volatile unsigned long time;
};

Ultrasonic *Ultrasonic::instance;

Ultrasonic::Ultrasonic(int _echo, int _trig) {
  echo = _echo;
  trig = _trig;
  Ultrasonic::instance = this;
  latest_dist = NULL;
}

void Ultrasonic::setMode(bool _is_inches) {
  is_inches = _is_inches;
}
void Ultrasonic::begin() {
  pinMode(echo, INPUT);
  pinMode(trig, OUTPUT);
  digitalWrite(trig, LOW);
  attachInterrupt(digitalPinToInterrupt(echo), isr, CHANGE);
}



void Ultrasonic::update() {
  digitalWrite(trig, HIGH);
  delayMicroseconds(10);
  digitalWrite(trig, LOW);
}

static void Ultrasonic::isr() {
  switch (digitalRead(Ultrasonic::instance->echo)) {
    case HIGH:
      Ultrasonic::instance->time = micros();
      break;
    case LOW:
      Ultrasonic::instance->latest_dist = (micros() - Ultrasonic::instance->time) * conv_factor() * 1e-4;  //to cm
      break;
  }
}

double Ultrasonic::getReading() {
  return latest_dist;
}

static double Ultrasonic::conv_factor() {
  if (instance->is_inches == true) {
    return 67.52;  //inches
  }
  return 171.5;  // sound(m/s)/2
}
#define echo_pin 21
#define trig_pin 20

Ultrasonic sensor(echo_pin, trig_pin);

bool in = false;
void setup() {
  Serial.begin(9600);
  sensor.begin();
  pinMode(33, INPUT_PULLUP);
}

void loop() {
  sensor.update();
  delay(100);
  Serial.println(sensor.getReading());
  if (digitalRead(33) == LOW) {
    in = !in;
    delay(10);
    if (digitalRead(33) == LOW) {
      sensor.setMode(in);
    }
  }
}
