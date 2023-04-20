#include "bovee_multissd.h"
#include "Arduino.h"

multi_SSD::multi_SSD(int a, int b, int c, int d, int e, int f, int g, int h, int d0, int d1, int d2, int d3)
  {
  _a = a, _b = b, _c = c, _d = d, _e = e, _f = f, _g = g, _h = h;
  _d0 = d0, _d1 = d1, _d2 = d2, _d3 = d3;
}

void multi_SSD::begin() {
  pinMode(_a, OUTPUT);
  pinMode(_b, OUTPUT);
  pinMode(_c, OUTPUT);
  pinMode(_d, OUTPUT);
  pinMode(_e, OUTPUT);
  pinMode(_f, OUTPUT);
  pinMode(_g, OUTPUT);
  pinMode(_h, OUTPUT);

  pinMode(_d0, OUTPUT);
  pinMode(_d1, OUTPUT);
  pinMode(_d2, OUTPUT);
  pinMode(_d3, OUTPUT);
}


void multi_SSD::display(int *vals, bool *dps) {
  for (int i = 0; i < 4; i++) {
    // set_display(-1, false);
    digitalWrite(_d0, HIGH);
    digitalWrite(_d1, HIGH);
    digitalWrite(_d2, HIGH);
    digitalWrite(_d3, HIGH);
    delay(2);
    digitalWrite(display_pin(i), LOW);
    set_display(vals[i], dps[i]);
    delay(2);
  }
}

void multi_SSD::set_display(int val, bool dp){
    boolean N = val & 0b10000000;   // grab negative bit
    boolean W = val & 0b1000;
    boolean X = val & 0b0100;
    boolean Y = val & 0b0010;
    boolean Z = val & 0b0001;
    // digitalWrite(_a, !N && (Y || W || !Z && !X || X && Z));
    // digitalWrite(_b, !N && (W || !Y && !Z || Y && Z || !X));
    // digitalWrite(_c, !N && (!Y || Z || X));
    // digitalWrite(_d, !N && (Y && !Z || X && Z && !Y || !X && !Z || !W && !X && Y));
    // digitalWrite(_e, !N && (!X && !Z || Y && !Z));
    // digitalWrite(_f, !N && (W || !Y && !Z || Z && !Y && X || !Z && Y && X));
    // digitalWrite(_g, !N && (Y && !Z || W || !W && !X && Y || X && !Y));
    // digitalWrite(_h, !N && (dp));    
    digitalWrite(_a, (Y || W || !Z && !X || X && Z));
    digitalWrite(_b, (W || !Y && !Z || Y && Z || !X));
    digitalWrite(_c, (!Y || Z || X));
    digitalWrite(_d, (Y && !Z || X && Z && !Y || !X && !Z || !W && !X && Y));
    digitalWrite(_e, (!X && !Z || Y && !Z));
    digitalWrite(_f, (W || !Y && !Z || Z && !Y && X || !Z && Y && X));
    digitalWrite(_g, (Y && !Z || W || !W && !X && Y || X && !Y));
    digitalWrite(_h, (dp));
  
}

int multi_SSD::display_pin(int target) {
  switch (target) {
    case 0:
      return _d0;
    case 1:
      return _d1;
    case 2:
      return _d2;
    case 3:
      return _d3;
  }
  return -1;  // OOB check
}