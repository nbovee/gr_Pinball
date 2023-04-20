#include "bovee_ssd.h"
#include <Arduino.h>

#define a 4
#define b 5
#define c 8
#define d 7
#define e 6
#define f 3
#define g 2
#define h 9
#define SEQ_BUTTON 18  // ISR capable
#define RNG_BUTTON 19  // ISR capable

int displayVals[] = { 4, -1, 18, 20 };
SSD my_display(a, b, c, d, e, f, g, h);

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  my_display.begin();
  // the setup function runs once when you press reset or power the board

  randomSeed(analogRead(0));
  pinMode(SEQ_BUTTON, INPUT_PULLUP);
  pinMode(RNG_BUTTON, INPUT_PULLUP);

  for (int i = 0; i < 4; i++) {
    my_display.set_display(displayVals[i]);
    Serial.println(displayVals[i]);
    delay(1000);
  }
}

// the loop function runs over and over again forever
void loop() {
  // I don't want to bother with ISR on this
  Serial.println("loop");
  Serial.println(digitalRead(RNG_BUTTON));
  Serial.println(digitalRead(SEQ_BUTTON));
  while (digitalRead(RNG_BUTTON) && digitalRead(SEQ_BUTTON)) {
    delay(10);
  }
  // escape and run

  if (!digitalRead(RNG_BUTTON)) {
    Serial.println("random");
    roullete(true, 1, 10, 20);
  } else {
    Serial.println("sequential");
    roullete(false, 1, 10, 20);
  }
}

void roullete(boolean is_random, int timeStep, int dt, int upperRange) {
  int winningNumber = random(upperRange);
  int nextNumber, displayNumber = -1;
  while (timeStep < 1500) {
    if (is_random) {
      do {
        nextNumber = random(upperRange);
      } while (nextNumber == displayNumber);
      displayNumber = nextNumber;
    } else {
      displayNumber += 1;
      displayNumber %= upperRange;
    }
    my_display.set_display(displayNumber);
    Serial.println(displayNumber);
    delay(timeStep);
    timeStep += dt;
    dt *= 1.1;
  }
  if (is_random) {
    my_display.set_display(winningNumber);
    Serial.println(winningNumber);
  }
}
