/*
  IMU.h - Small handler for IMU results.
  Created by Nick Bovee, April 24, 2023.
*/
#ifndef Flipper_h
#define Flipper_h
#define flipper_max_PWM 1023
#define flipper_eot_PWM 128
#define flipper_on_time_ms 100
#include "Arduino.h"

class Flipper
{
  public:
    Flipper(int pin_switch, int pin_solenoid);
    void begin();
    void update_inputs();
    void update_outputs();
  private:
    int _pin_switch;
    int _pin_solenoid;
};

#endif