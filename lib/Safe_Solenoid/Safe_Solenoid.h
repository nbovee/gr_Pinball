/*
  Safe_Solenoid.h - Small handler for Safe_Solenoid control.
  Created by Nick Bovee, April 24, 2023.
*/

#ifndef Safe_Solenoid_h
#define Safe_Solenoid_h
#define Safe_Solenoid_max_PWM 1023
#define Safe_Solenoid_eot_PWM 0
#define Safe_Solenoid_max_on_ms 100
#include "Arduino.h"

class Safe_Solenoid
{
  public:
    Safe_Solenoid( int pin_switch, int pin_solenoid, int safe_PWM);
    Safe_Solenoid( int pin_switch, int pin_solenoid);
    void begin();
    void step();
  private:
    int _pin_switch;
    int _pin_solenoid;
    int _safe_PWM;
    bool _input_state;
    long _active_time;
};
#endif
