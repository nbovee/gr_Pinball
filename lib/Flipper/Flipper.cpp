
#include "Flipper.h"

Flipper::Flipper(int pin_switch, int pin_solenoid)
{
  _pin_switch = pin_switch;
  if(digitalPinHasPWM(_pin_solenoid)){
    _pin_solenoid = pin_solenoid;
  }
}

void Flipper::begin() {
  pinMode(_pin_switch, INPUT_PULLUP);
  analogWrite(_pin_solenoid, 0);
}

void Flipper::input_call() {
  bool current_input_state = !digitalRead(_pin_switch);
  if(current_input_state != _input_state && current_input_state) {
    _active_time = millis();
  }
  _input_state = current_input_state;
}

void Flipper::output_call() {
  int writeVal = 0;
  if(_input_state) {
    if((millis() - _active_time) < flipper_max_on_ms) {
      writeVal = flipper_max_PWM;
  }
  else {
    writeVal = flipper_eot_PWM;
  }
    analogWrite(_pin_solenoid, writeVal);
  }
}