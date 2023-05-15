/*
  Safe_Solenoid.h - Small handler for Safe_Solenoid control.
  Created by Nick Bovee, April 24, 2023.
*/

#ifndef Safe_Solenoid_h
#define Safe_Solenoid_h
#define Safe_Solenoid_max_PWM 255
#define Safe_Solenoid_eot_PWM 0
#define Safe_Solenoid_max_on_ms 250
#include "Arduino.h"

class Safe_Solenoid
{
  public:
    // Instantiation
    Safe_Solenoid( int pin_switch, int pin_solenoid, int safe_PWM);
    Safe_Solenoid( int pin_switch, int pin_solenoid);
    // Initializes
    void begin();
    // Checks inputs and sets outputs. Steps output down after a certain amount of time.
    // Does not start a timer and must be iterated over with a scheduler for the planned safety.
    int step();
  private:
    int _pin_switch;
    int _pin_solenoid;
    int _safe_PWM;
    bool _input_state;
    long _active_time;
};
#endif
