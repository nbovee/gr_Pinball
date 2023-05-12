## Graduate Embedded Project Proposal: Pinball

<img align="right" src="/docs/sapphire.jpg">

Design and build of a half-scale pinball table. Modern pinball tables use 48V systems with 27mm steel balls. We will reduce our power to 24V for easier sourcing of components, and reduce the size of the steel ball to 19mm (~½ mass of 27mm) to maintain the best approximation of game feel. The approximate layout will match the screenshot on the right of Pokemon Pinball : Sapphire’s table, but may not remain Pokemon themed. To reduce overall complexity, features 2, 4, 5,  8, & 10-13 are intended to be removed.

Mechanical design components will be largely laser cut frames, with acrylic and 3D printed parts as necessary for cosmetic and detail work as needed. Solenoid assemblies require some mechanical level design and may be waterjet aluminum to eliminate potential issues in the timescale of this project. A poster print may be requested to serve as the art for the pinball board. AC and 24V components added by Nick will be soldered and covered for safety.
While individual sensors may be considered trivial, the greater embedded programming challenge is in the implementation of a large state machine with numerous inputs and feedback methods. Unless time cost is trivial, all components active during gamestate must be non blocking to ensure high speed play is responsive.

## Details

### Implemented Components

| Name                | Components                 | Implemented | Notes                                           |
| ------------------- | -------------------------- | ----------- | ----------------------------------------------- |
| Flipper Assembly    | Solenoid, PWM, FET         | Yes         | Risk of burning out solenoids                   |
| Pop Bumper Assembly | Solenoid, FET, Drop Switch | Yes         | Switch design extremely challenging             |
| Arcade Buttons      | 2x 30mm 1x 24mm Sanwa OBSN | Yes         | Barely worth mentioning                         |
| Ball Detection      | Hall Effect sensors        | Not Yet     | Would like to have on i2c bus but not practical |
| Screen              | i2c LCD                    | Not Yet     |
| Tilt Detection      | IMU                        | Not Yet     |

### Removed Components

| Name               | Components                          | Implemented | Justification                    |
| ------------------ | ----------------------------------- | ----------- | ------------------------- |
| Name Input         | Hex Keypad                          | No          |
| Slingshot Assembly | Solenoid, FET, Prox Switch          | No          | Switch design challenging |
| Lights             | Addressable LED                     | No          |
| Sounds             | Speakers, SD reader and playback IC | No          | Need to find sfx library  |

### C4 Context Diagram

```mermaid
    C4Context
      title System Context diagram for Internet Banking System
      Enterprise_Boundary(b0, "BankBoundary0") {
        Person(customerA, "Banking Customer A", "A customer of the bank, with personal bank accounts.")
        Person(customerB, "Banking Customer B")
        Person_Ext(customerC, "Banking Customer C", "desc")

        Person(customerD, "Banking Customer D", "A customer of the bank, <br/> with personal bank accounts.")

        System(SystemAA, "Internet Banking System", "Allows customers to view information about their bank accounts, and make payments.")

        Enterprise_Boundary(b1, "BankBoundary") {

          SystemDb_Ext(SystemE, "Mainframe Banking System", "Stores all of the core banking information about customers, accounts, transactions, etc.")

          System_Boundary(b2, "BankBoundary2") {
            System(SystemA, "Banking System A")
            System(SystemB, "Banking System B", "A system of the bank, with personal bank accounts. next line.")
          }

          System_Ext(SystemC, "E-mail system", "The internal Microsoft Exchange e-mail system.")
          SystemDb(SystemD, "Banking System D Database", "A system of the bank, with personal bank accounts.")

          Boundary(b3, "BankBoundary3", "boundary") {
            SystemQueue(SystemF, "Banking System F Queue", "A system of the bank.")
            SystemQueue_Ext(SystemG, "Banking System G Queue", "A system of the bank, with personal bank accounts.")
          }
        }
      }

      BiRel(customerA, SystemAA, "Uses")
      BiRel(SystemAA, SystemE, "Uses")
      Rel(SystemAA, SystemC, "Sends e-mails", "SMTP")
      Rel(SystemC, customerA, "Sends e-mails to")

      UpdateElementStyle(customerA, $fontColor="red", $bgColor="grey", $borderColor="red")
      UpdateRelStyle(customerA, SystemAA, $textColor="blue", $lineColor="blue", $offsetX="5")
      UpdateRelStyle(SystemAA, SystemE, $textColor="blue", $lineColor="blue", $offsetY="-10")
      UpdateRelStyle(SystemAA, SystemC, $textColor="blue", $lineColor="blue", $offsetY="-40", $offsetX="-50")
      UpdateRelStyle(SystemC, customerA, $textColor="red", $lineColor="red", $offsetX="-50", $offsetY="20")

      UpdateLayoutConfig($c4ShapeInRow="3", $c4BoundaryInRow="1")
```

### Libraries

Main non-default library that i did not write is the Scheduler Library from (GITHUB LINK HERE)
They look like all MIT licenses.

## Team & Qualifications

Nick is an experienced engineer and computer scientist with a large background in robotics and electromechanical design. His work in Engineering in the creation of similar equipment and guiding student embedded design projects, is highly relevant, and he has work experience with industrial and home robotics. As a student he retrofit a drive by wire systems into a golf cart for undergraduate senior design, and is currently performing research in robotics and AI with Dr. Ho. Recent personal projects include custom fighting game controllers, keyboards, and an MTG card sorting machine.
