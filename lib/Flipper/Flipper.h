/*
  IMU.h - Small handler for IMU results.
  Created by Nick Bovee, April 24, 2023.
*/
#ifndef Flippers_h
#define Flippers_h

#include "Arduino.h"

class Flipper
{
  public:
    Flipper(int pin_in, int pin_out);
    void begin();
  private:
    int _pin;
    int _pin_out;
};

#endif