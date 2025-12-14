AirStrike Game – How to Run

This project is a 2D Air Strike game developed using Java AWT and Swing only.

---

## Requirements

1. Java JDK 8 or above installed
2. Command Prompt / PowerShell
3. Windows OS

Check Java installation:
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
│   └── (audio files)
│
└── bin
└── (compiled class files)

---

## METHOD 1: Run from Terminal

Step 1: Open Command Prompt / PowerShell

Step 2: Go to project root folder
Example:
cd C:\Users\shriv\OneDrive\Desktop\AirStrikeGame

Step 3: Compile the project
javac -d bin src\airstrike*.java

Step 4: Run the game
java -cp bin airstrike.AirStrikeGame

---

## METHOD 2: Run using Executable (.jar) File

Step 1: Compile the project (if not already compiled)
javac -d bin src\airstrike*.java

Step 2: Create executable JAR file
jar cfe AirStrikeGame.jar airstrike.AirStrikeGame -C bin .

Step 3: Run the executable JAR
Double-click AirStrikeGame.jar

OR run from terminal:
java -jar AirStrikeGame.jar

---

## Controls

Arrow Keys  - Move player
Spacebar   - Fire missile
Esc         - Exit game

---

## Common Issues

1. Game does not open when double-clicking JAR:

   * Java must be installed
   * Try running using: java -jar AirStrikeGame.jar

2. Images or audio not loading:

   * Ensure images and audio folders are in the same directory as the JAR file

3. Class not found error:

   * Ensure correct package name: airstrike

---

## Notes

1. Only Java AWT and Swing are used
2. No external libraries
3. Suitable for academic labs and viva

---

