#include "bovee_ssd.h"
#include "Arduino.h"

static int _a, _b, _c, _d, _e, _f, _g, _h;


SSD::SSD(int a, int b, int c, int d, int e, int f, int g, int h) {
  // lazy way
  static int _a, _b, _c, _d, _e, _f, _g, _h;

  _a = a, _b = b, _c = c, _d = d, _e = e, _f = f, _g = g, _h = h;
  // const int _pin_iter[] = {&_a, _b, _c, _d, _e, _f, _g, _h};
}

void SSD::begin() {
  // for (int i = 0; i < 8; i++) {
  //   Serial.print("Setting pin to output");
  //   Serial.println(_pin_iter[i]);
  //   pinMode(_pin_iter[i], OUTPUT);
  // }
    Serial.println(_b);
    pinMode(_a, OUTPUT);
    pinMode(_b, OUTPUT);
    pinMode(_c, OUTPUT);
    pinMode(_d, OUTPUT);
    pinMode(_e, OUTPUT);
    pinMode(_f, OUTPUT);
    pinMode(_g, OUTPUT);
    pinMode(_h, OUTPUT);

}
void SSD::set_display(int val) {  //short to ensure we have a 2 byte number for bit operations
  boolean N = val & 0b10000000;   // grab negative bit
  boolean B = val > 9;
  boolean C = val > 19;
  int temp_val = val;  // used for printout below
  val %= 10;       // reduce to ones place, since B & C catch higher numbers
  boolean W = val & 0b1000;
  boolean X = val & 0b0100;
  boolean Y = val & 0b0010;
  boolean Z = val & 0b0001;
  char buffer[40];
  if(Serial.available()){
    sprintf(buffer, "%d: W%d X%d Y%d Z%d N%d B%d C%d", temp_val, W, X, Y, Z, N, B, C);
    Serial.print(buffer);
  }
  
  digitalWrite(_a, !N && !C && (Y || W || !Z && !X || X && Z));
  digitalWrite(_b, !N && !C && (W || !Y && !Z || Y && Z || !X));
  digitalWrite(_c, !N && !C && (!Y || Z || X));
  digitalWrite(_d, !N && !C && (Y && !Z || X && Z && !Y || !X && !Z || !W && !X && Y));
  digitalWrite(_e, !N && !C && (!X && !Z || Y && !Z));
  digitalWrite(_f, !N && !C && (W || !Y && !Z || Z && !Y && X || !Z && Y && X));
  digitalWrite(_g, !N && !C && (Y && !Z || W || !W && !X && Y || X && !Y));
  digitalWrite(_h, !N && (B));
}