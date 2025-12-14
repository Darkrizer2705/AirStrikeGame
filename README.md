
AirStrike Game – How to Run from Terminal

This project is a simple 2D Air Strike game developed using Java AWT and Swing only.

---

## Requirements

1. Java JDK 8 or above must be installed
2. Terminal / Command Prompt / PowerShell
3. Windows OS (commands given for Windows)

To check Java installation:
java -version
javac -version

---

## Project Structure

AirStrikeGame
│
├── src
│   └── airstrike
│       ├── AirStrikeGame.java
│       ├── GamePanel.java
│       ├── Player.java
│       ├── Enemy.java
│       ├── EnemyManager.java
│       ├── Missile.java
│       ├── MissileDrop.java
│       ├── CollisionManager.java
│       ├── AudioManager.java
│       ├── ScoreManager.java
│       ├── FloatingText.java
│       ├── KillEffect.java
│       └── KeyHandler.java
│
├── images
│   ├── player.png
│   ├── enemy.png
│   └── background.png
│
├── audio
│   └── (audio files if any)
│
└── bin
└── (compiled class files)

---

## Steps to Compile the Project

Step 1: Open Command Prompt / PowerShell

Step 2: Go to the project root folder

Step 3: Compile all Java files
javac -d bin src\airstrike*.java

This command compiles all source files and stores the .class files in the bin folder.

---

## Steps to Run the Game

After compilation, run the following command:

java -cp bin airstrike.AirStrikeGame

The game window will open if compilation is successful.

---

## Controls

Arrow Keys  - Move the player
Spacebar   - Fire missile
Esc         - Exit game

---

## Common Issues

1. If images do not load:

   * Make sure the images folder is present in the project root
   * Check image file names and paths

2. If audio does not play:

   * Ensure audio files are in the audio folder
   * Prefer .wav format

3. Class not found error:

   * Run commands from the project root directory
   * Ensure bin folder exists

---

## Notes

1. No external libraries are used
2. Only Java AWT and Swing packages are used
3. Project is suitable for academic labs and viva exams

---


