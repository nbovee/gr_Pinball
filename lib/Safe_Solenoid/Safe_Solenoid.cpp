
#include "Safe_Solenoid.h"

Safe_Solenoid::Safe_Solenoid(int pin_switch, int pin_solenoid, int safe_PWM)
{
  _pin_switch = pin_switch;
  if(digitalPinHasPWM(_pin_solenoid)){
    _pin_solenoid = pin_solenoid;
  }
  _safe_PWM = safe_PWM;
}

Safe_Solenoid::Safe_Solenoid(int pin_switch, int pin_solenoid)
{
  Safe_Solenoid(pin_switch, pin_solenoid, Safe_Solenoid_eot_PWM);
}

void Safe_Solenoid::begin() {
  pinMode(_pin_switch, INPUT_PULLUP);
  analogWrite(_pin_solenoid, 0);
}

void Safe_Solenoid::step() {
  bool current_input_state = !digitalRead(_pin_switch);
  if(current_input_state != _input_state && current_input_state) {
    _active_time = millis();
  }
  _input_state = current_input_state;

  int writeVal = 0;
  if(_input_state) {
    if((millis() - _active_time) < Safe_Solenoid_max_on_ms) {
      writeVal = Safe_Solenoid_max_PWM;
  }
  else {
    writeVal = _safe_PWM;
  }
    analogWrite(_pin_solenoid, writeVal);
  }
}