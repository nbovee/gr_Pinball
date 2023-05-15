
#include "Safe_Solenoid.h"

Safe_Solenoid::Safe_Solenoid(int pin_switch, int pin_solenoid, int safe_PWM)
{
  _pin_switch = pin_switch;
  // if (digitalPinHasPWM(_pin_solenoid)) // just in case
  // {
  this->_pin_solenoid = pin_solenoid;
  // }
  _safe_PWM = safe_PWM;
}

Safe_Solenoid::Safe_Solenoid(int pin_switch, int pin_solenoid)
{
  Safe_Solenoid(pin_switch, pin_solenoid, Safe_Solenoid_eot_PWM);
}

void Safe_Solenoid::begin()
{
  pinMode(this->_pin_switch, INPUT_PULLUP);
  pinMode(this->_pin_solenoid, OUTPUT);
  analogWrite(this->_pin_solenoid, 0);
  this->_input_state = !digitalRead(this->_pin_switch);
}

int Safe_Solenoid::step()
{
  bool current_input_state = !digitalRead(_pin_switch);
  if (current_input_state && (current_input_state != this->_input_state))
  {
    this->_active_time = millis();
  }

  int writeVal = 0;
  if (current_input_state)
  {
    if ((millis() - this->_active_time) < Safe_Solenoid_max_on_ms)
    {
      writeVal = Safe_Solenoid_max_PWM;
    }
    else
    {
      writeVal = this->_safe_PWM;
    }
  }
  this->_input_state = current_input_state;
  analogWrite(this->_pin_solenoid, writeVal);
  return current_input_state;
}
