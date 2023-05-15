#pragma once
// Switches & Buttons
#define POP1_b A2
#define POP2_b A3
#define POP3_b A4
#define LBMP_b A6
#define RBMP_b A5
#define START_b A7

//Solenoids
#define POP1_s 2
#define POP2_s 3
#define POP3_s 4
#define LBMP_s 6
#define RBMP_s 5

//Hall Effect Sensors
#define HE1 14
#define HE2 15
#define HE3 16

#define FLIPPER_EOT_PWM 64 // 1/4 power

// IMU
#define GRAVITY 9.8
#define CHEAT_TOLERANCE 1.05

// Scheduling behavior defines (before include)
#define _TASK_INTERVAL 5
#define _TASK_SLEEP_ON_IDLE_RUN
#define _TASK_PRIORITY
#define _TASK_WDT_IDS
#define _TASK_TIMECRITICAL
