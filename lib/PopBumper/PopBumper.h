/*
  PopBumper.h - Small handler for individual pop bumpers.
  Created by Nick Bovee, April 24, 2023.
*/
#ifndef PopBumper_h
#define PopBumper_h

#include "Arduino.h"

class PopBumper
{
  public:
    PopBumper(int pin_input, int pin_trigger);
    void begin();
  private:
    int _pin_in;
    int _pin_trig;
};

#endif