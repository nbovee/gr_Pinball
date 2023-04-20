#include "song.h"
#define buzzerPin 49

void setup() {
  pinMode(buzzerPin, OUTPUT);
  Serial.begin(9600);
}

void loop() {
  Serial.println("playing");
  for(int i=0; i< NUM_ELEMENTS_0; i++){
    int h = pgm_read_word_near(track_hz0 + i);
    Serial.println(h);
    tone(buzzerPin, h, 1000/FILE_SAMPLE_RATE);
    delay(1000/FILE_SAMPLE_RATE);
  }
  // for(int i=0; i< NUM_ELEMENTS_1; i++){
  //   int h = pgm_read_word_near(track_hz1 + i);
  //   Serial.println(h);
  //   tone(buzzerPin, h, 1000/FILE_SAMPLE_RATE);
  //   delay(1000/FILE_SAMPLE_RATE);
  // }
  // for(int i=0; i< NUM_ELEMENTS_2; i++){
  //   int h = pgm_read_word_near(track_hz2 + i);
  //   Serial.println(h);
  //   tone(buzzerPin, h, 1000/FILE_SAMPLE_RATE);
  //   delay(1000/FILE_SAMPLE_RATE);
  // }
  exit(0);
}
