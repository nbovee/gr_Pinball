
#include "Flipper.h"

Flipper::Flipper(int pin_switch, int pin_solenoid)
{
  _pin_switch = pin_switch;
  _pin_solenoid = pin_solenoid;
  if(!digitalPinHasPWM(_pin_solenoid)){
    throw "Check PWM pins: PWM not enabled for given pin.";
  }
}

void Flipper::begin() {
    pinMode(_pin_switch, INPUT_PULLUP);
    analogWrite(_pin_solenoid, 0);
}
