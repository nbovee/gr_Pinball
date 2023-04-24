/*
  IMU.h - Small handler for IMU results.
  Created by Nick Bovee, April 24, 2023.
*/
#ifndef IMU_h
#define IMU_h

#include "Arduino.h"

class IMU
{
  public:
    IMU(int pin);
    void begin();
  private:
    int _pin;
};

#endif