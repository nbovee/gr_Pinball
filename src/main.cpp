// required since we are in PlatformIO, not Arduino IDE
#include <Arduino.h>

// defines kept here to avoid clutter
#include <IOdef.h>

// external
#include <TaskScheduler.h>
#include <LiquidCrystal_I2C.h>

// internal
#include <Safe_Solenoid.h>

// tiered Scheduling allows inputs and logic to run before settung outputs
Scheduler r;

// Game Objects
Safe_Solenoid l_flipper = Safe_Solenoid(LBMP_b, LBMP_s, FLIPPER_EOT_PWM);
Safe_Solenoid r_flipper = Safe_Solenoid(RBMP_b, RBMP_s, FLIPPER_EOT_PWM);
Safe_Solenoid pop_bumper1 = Safe_Solenoid(POP1_b, POP1_s);
Safe_Solenoid pop_bumper2 = Safe_Solenoid(POP2_b, POP2_s);
Safe_Solenoid pop_bumper3 = Safe_Solenoid(POP3_b, POP3_s);

// void wrappers
void flippers() {
  l_flipper.step();
  r_flipper.step();
}

// input Tasks
Task flip(_TASK_INTERVAL, TASK_FOREVER, &flippers, &r);

// game logic Tasks

// output Tasks

void setup() {
  // all tasks update at the same interval, and we'll let the priority system sort it out.
  l_flipper.begin();
  r_flipper.begin();
  // standard_runner.setHighPriorityScheduler(&middle_runner); 
  // middle_runner.setHighPriorityScheduler(&priority_runner); 
  r.enableAll(true); // this will recursively enable the higher priority tasks as well
}

void loop() {
  r.execute();
}

