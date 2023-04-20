
#include <LiquidCrystal.h>
#include <String.h>

const int rs = 2, en = 3, d0 = 4, d1 = 5, d2 = 6, d3 = 7, d4 = 8, d5 = 9, d6 = 10, d7 = 11;
LiquidCrystal lcd(rs, en, d0, d1, d2, d3, d4, d5, d6, d7);
const int b0 = 22, b1 = 23, b2 = 24, b3 = 13;
const int debounce = 100;

String l1 = "SNEEDs FEED&SEED";
String l2 = "formerly Chucks ";

String allowed = "abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ";

const int num_chars = 6;
int row_loc = 0;
int col_loc = 0;
unsigned long last_b_press = 0;
bool ad_mode = false;

void setup() {
  Serial.begin(9600);
  // set up the LCD's number of columns and rows:
  lcd.begin(16, 2);
  pinMode(b0, INPUT_PULLUP);  // scroll characters
  pinMode(b1, INPUT_PULLUP);  // cursor left
  pinMode(b2, INPUT_PULLUP);  // cursor right
  pinMode(b3, INPUT_PULLUP);  // toggle ad_mode
  randomSeed(analogRead(0));
  Serial.println(allowed.length());
}

void loop() {
  check_inputs();
  display_billboard();
  if (ad_mode) {
    lcd.noCursor();
    subliminal(random(0, 3));
    delay(250);
  } else {
    lcd.cursor();
    lcd.setCursor(row_loc, col_loc);
    delay(250);
  }
}

void check_inputs() {
  if (digitalRead(b3) == LOW && (millis() - debounce) > last_b_press) {
    ad_mode = !ad_mode;
  }
  if ((digitalRead(b1) == LOW && (millis() - debounce) > last_b_press)) {
    row_loc -= 1;
  }
  if ((digitalRead(b2) == LOW && (millis() - debounce) > last_b_press)) {
    row_loc += 1;
  }
  if (row_loc < 0) {
    row_loc = 16;
    if (col_loc == 1) {
      col_loc = 0;
    } else {
      col_loc = 1;
    }
  } else if (row_loc > 16) {
    row_loc = 0;
    if (col_loc == 1) {
      col_loc = 0;
    } else {
      col_loc = 1;
    }
  }
  if ((digitalRead(b0) == LOW && (millis() - debounce) > last_b_press)) {
    switch (col_loc) {
      case 0:
        int _current = allowed.indexOf(l1.charAt(row_loc));
        int _next = (_current + 1) % (allowed.length());
        Serial.print(allowed.charAt(_current));
        Serial.print(" ");
        Serial.print(_next);
        Serial.println(allowed.charAt(_next));
        l1.setCharAt(row_loc, allowed.charAt(_next));
        break;
      case 1:
        l2.setCharAt(row_loc, (allowed.charAt(allowed.indexOf(l2.charAt(row_loc)) + 1) % allowed.length() ));
        break;
    }
  }
}

void subliminal(int i) {
  switch (i) {
    case 0:
      lcd.setCursor(random(1, 11), random(0, 2));
      lcd.print(" BUY ");
      return;
    case 1:
      lcd.setCursor(random(1, 7), random(0, 2));
      lcd.print(" CONSUME ");
      return;
    case 2:
      lcd.setCursor(random(1, 7), random(0, 2));
      lcd.print(" GIVE A+ ");
      return;
  }
}

void display_billboard() {
  lcd.setCursor(0, 0);
  lcd.print(l1);
  lcd.setCursor(0, 2);
  lcd.print(l2);
}