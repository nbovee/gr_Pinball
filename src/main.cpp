// required since we are in PlatformIO, not Arduino IDE
#include <Arduino.h>

// defines kept here to avoid clutter
#include <IOdef.h>

// external
#include <TaskScheduler.h>
#include <LiquidCrystal_I2C.h>
#include <Adafruit_MPU6050.h>
#include <Adafruit_Sensor.h>

// internal
#include <Safe_Solenoid.h>

// Scheduler allows for microsleeps to save energy, and avoids messy logic for us.
Scheduler r;

// Checks acceleration to look for cheaters.
Adafruit_MPU6050 imu = Adafruit_MPU6050();

// Displays score.
LiquidCrystal_I2C lcd = LiquidCrystal_I2C(0x27, 16, 2);

long score;
bool cheat_flag;
bool end_flag;

// Game Objects
Safe_Solenoid l_flipper = Safe_Solenoid(LBMP_b, LBMP_s, FLIPPER_EOT_PWM);
Safe_Solenoid r_flipper = Safe_Solenoid(RBMP_b, RBMP_s, FLIPPER_EOT_PWM);
Safe_Solenoid pop_1 = Safe_Solenoid(POP1_b, POP1_s);
Safe_Solenoid pop_2 = Safe_Solenoid(POP2_b, POP2_s);
Safe_Solenoid pop_3 = Safe_Solenoid(POP3_b, POP3_s);

// void task wrappers
void flippers()
{
  Serial.print("T:Flippers");
  Serial.print(" ");
  Serial.print(l_flipper.step());
  Serial.print(" ");
  Serial.println(r_flipper.step());
}

void bumpers()
{
  Serial.println("T:Bumpers");
  Serial.print(pop_1.step());
  Serial.print(pop_2.step());
  Serial.println(pop_3.step());
  if (pop_1._input_state || pop_2._input_state || pop_3._input_state)
  { // bumper has been triggered somehow.
    score += 100;
  }
}

void hall_effects()
{
  Serial.println("T:Hall Effects");
  bool h1 = !digitalRead(HE1);
  bool h2 = !digitalRead(HE2);
  bool h3 = !digitalRead(HE3);
  // ball left playing area
  if (h1)
  {
    end_flag = true;
  }
  // ball entered or exit loop
  if (h2 || h3)
  {
    score += 500;
  }
}

void find_cheater()
{
  Serial.println("T:IMU");
  sensors_event_t a, g, temp;
  imu.getEvent(&a, &g, &temp);
  float xy_accel = sqrt(a.acceleration.x * a.acceleration.x + a.acceleration.y * a.acceleration.y);
  float xyz_accel = sqrt(xy_accel * xy_accel + a.acceleration.z * a.acceleration.z);
  if (xyz_accel > GRAVITY * CHEAT_TOLERANCE)
  {
    cheat_flag = true;
  }
}

void show_score()
{
  Serial.println("T:LCD");
  lcd.clear();
  lcd.setCursor(0, 0);
  lcd.print(score);
}

void start()
{
  if (!digitalRead(START_b))
  {
    r.enableAll(true);
  }
}

// all disabled by default until the start input is received
Task flip(_TASK_INTERVAL, TASK_FOREVER, &flippers, &r, false);
Task bump(_TASK_INTERVAL, TASK_FOREVER, &bumpers, &r, false);
Task hall(_TASK_INTERVAL, TASK_FOREVER, &hall_effects, &r, false);
Task cheat_test(_TASK_INTERVAL, TASK_FOREVER, &find_cheater, &r, false);
Task lcd_disp(_TASK_INTERVAL, TASK_FOREVER, &show_score, &r, false);
// by being the only enabled task, nothing will run until this task enables everything.
Task starter(_TASK_INTERVAL, TASK_FOREVER, &start, &r, true);

/*
  Initializes the program
*/
void setup()
{
  // all tasks update at the same interval
  // we'll assume game logic is robust enough to not a need priority system
  Serial.begin(9600);
  l_flipper.begin();
  r_flipper.begin();
  imu.begin();
  lcd.init();
  lcd.backlight();
  lcd.print("Waiting for Start.");
  score = 0;
  pinMode(START_b, INPUT_PULLUP);
  pinMode(HE1, INPUT_PULLUP);
  pinMode(HE2, INPUT_PULLUP);
  pinMode(HE3, INPUT_PULLUP);
}

/*
  Iterates through the scheduler
*/
void loop()
{
  r.execute();
  if (cheat_flag)
  {
    lcd.setCursor(0, 0);
    lcd.print("Cheat detected!");
    exit(1);
  }
  if (end_flag)
  {
    exit(0);
  }
}
