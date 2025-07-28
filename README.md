# Bitcoin Catcher

A simple Java game where you catch falling bitcoins. The game keeps track of your score and personal high score.

## Features
- Player profiles with persistent high scores
- Increasing difficulty as you catch more coins
- Restart option when the coin hits the ground
- Smooth animation using a Swing timer
- Coins start from random positions for variety
- High scores are saved under a `scores` folder

## Building and Running
1. Compile the sources:
   ```bash
   javac src/bitcoinGUI/*.java
   ```
2. Run the game:
   ```bash
   java -cp src bitcoinGUI.Main
   ```

## Creating a Windows `.exe`
The project is pure Java, so it normally runs via the JVM. If you want a Windows executable, you can create one using `jpackage` (requires JDK 14+ and must be run on Windows):

```bash
jpackage --type exe --input src --main-class bitcoinGUI.Main \
         --name BitcoinCatcher --main-jar BitcoinCatcher.jar
```

First create `BitcoinCatcher.jar` containing the compiled classes, then run the above command on a Windows machine. Tools such as Launch4J can also wrap the JAR into an executable.
