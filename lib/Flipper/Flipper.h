/*
  Flipper.h - Small handler for Flipper control.
  Created by Nick Bovee, April 24, 2023.
*/

#ifndef flipper_h
#define flipper_h
#define flipper_max_PWM 1023
#define flipper_eot_PWM 128
#define flipper_max_on_ms 100
#include "Arduino.h"

class Flipper
{
  public:
    Flipper( int pin_switch, int pin_solenoid);
    void begin();
    void input_call();
    void output_call();
  private:
    int _pin_switch;
    int _pin_solenoid;
    bool _input_state;
    long _active_time;
};
#endif
