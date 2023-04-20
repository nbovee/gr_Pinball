#include "bovee_multissd.h"
#include <Arduino.h>

#define a 7
#define b 9
#define c 5
#define d 3
#define e 2
#define f 8
#define g 6
#define dp 4

#define d0 10
#define d1 11
#define d2 12
#define d3 13

#define MODE_BUTTON 18  // ISR capable
#define INCR_BUTTON 19  // ISR capable

multi_SSD my_display = multi_SSD(a, b, c, d, e, f, g, dp, d0, d1, d2, d3);  // covers all displays

boolean in_hour_mode = true;
boolean am = false;
unsigned long last_button;

int hours = 11;
int minutes = 45;
int disp[4];  // this will be modifed to always be the display digits

void setup() {
  last_button = millis();
  // put your setup code here, to run once:
  Serial.begin(9600);
  Serial.println("Start");
  my_display.begin();
  pinMode(MODE_BUTTON, INPUT_PULLUP);
  pinMode(INCR_BUTTON, INPUT_PULLUP);
}

void loop() {
  // put your main code here, to run repeatedly:
  for (int s = 0; s < 60; s++) {  // loop over seconds
    unsigned long t1 = millis();
    process_time();  //process updates to display array
    bool dps[] = { in_hour_mode, !am, !in_hour_mode, false };
    while ((millis() - t1) < 1000) {
      check_input();
      my_display.display(disp, dps);
    }
  }
  incr_min();  // 60 iterations = 1 min
}

void check_input() {  
  if(digitalRead(MODE_BUTTON) == 0 && millis() > (last_button + 500)) {
    in_hour_mode = !in_hour_mode;
    last_button = millis();
  }
  if(digitalRead(INCR_BUTTON) == 0 && millis() > (last_button + 500)) {
    last_button = millis();
    if(in_hour_mode) {
      incr_hours();
      Serial.println("INCR HR");
    }
    else {
      incr_min();
      Serial.println("INCR MN");
    }
  }
}

void process_time() {
  disp[0] = hours / 10;
  disp[1] = hours % 10;
  disp[2] = minutes / 10;
  disp[3] = minutes % 10;
}

void incr_hours() {
  hours = hours + 1;
  if (hours == 12) {
    am = !am;  // flip AM or PM boolean
  }
  if (hours > 12) {
    hours %= 12;
  }
}

void incr_min() {
  minutes = minutes + 1;
  if (minutes >= 60) {
    incr_hours();
    minutes %= 60;
  }
}
