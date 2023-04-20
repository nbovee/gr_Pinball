#include <LiquidCrystal_I2C.h>
#include <dht_nonblocking.h>
#define DHT_SENSOR_TYPE DHT_TYPE_11
#define DHT_SENSOR_PIN 12

DHT_nonblocking dht_sensor(DHT_SENSOR_PIN, DHT_SENSOR_TYPE);

LiquidCrystal_I2C lcd(0x27, 20, 4);  // set the LCD address to 0x27 for a 16 chars and 2 line display

void setup() {
  Serial.begin(9600);
  lcd.init();
  lcd.backlight();
}


void loop() {
  lcd.setCursor(0, 0);
  lcd.print(dht_sensor.get_temperature());
  lcd.setCursor(0, 1);
  lcd.print(dht_sensor.get_humidity());
  delay(1000);
}