// required since we are in PlatformIO, not Arduino IDE
#include <Arduino.h>

// defines kept here to avoid clutter
#include <IOdef.h>

// external
#include <TaskScheduler.h>

// internal
#include <PopBumper.h>
#include <Flipper.h>
#include <IMU.h>

// tiered Scheduling allows inputs and logic to run before settung outputs
Scheduler priority_runner; // input control
Scheduler middle_runner; // game logic control
Scheduler standard_runner; // output control

// Game Objects
Flipper l_flipper = Flipper(LBMP_b, LBMP_s);
Flipper r_flipper = Flipper(RBMP_b, RBMP_s);

// Task definitions
void l_flip_in() {l_flipper.input_call();}
void l_flip_out() {l_flipper.output_call();}
void r_flip_in() {r_flipper.input_call();}
void r_flip_out() {r_flipper.output_call();}


// input Tasks
Task i1(TASK_INTERVAL, TASK_FOREVER, &l_flip_in, &priority_runner);
Task i2(TASK_INTERVAL, TASK_FOREVER, &r_flip_in, &priority_runner);

// game logic Tasks

// output Tasks
Task o1(TASK_INTERVAL, TASK_FOREVER, &l_flip_out, &standard_runner);
Task o2(TASK_INTERVAL, TASK_FOREVER, &r_flip_out, &standard_runner);

void setup() {
  // all tasks update at the same interval, and we'll let the priority system sort it out.
  l_flipper.begin();
  r_flipper.begin();
  standard_runner.setHighPriorityScheduler(&middle_runner); 
  middle_runner.setHighPriorityScheduler(&priority_runner); 
  standard_runner.enableAll(true); // this will recursively enable the higher priority tasks as well
}

void loop() {
  standard_runner.execute();
}

