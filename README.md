# com.dotgears.flappybird

Deobfuscation and recompilation project of the original Flappy Bird game.

## Source code differences
Few changes were made to make testing and debugging easier.  

1. Build System: The original project was likely written in Eclipse without Gradle. This version uses Gradle to make the project more accessible to modern developers. If you're interested in creating an Eclipse-compatible version, pull requests are welcome! You can get started with Eclipse-Android using scoop install eclipse-android.
2. SDK Targets: Updated targetSdk and minSdk values for modern Android compatibility.
3. Development Notes: Added TODO comments throughout the source code highlighting areas that are slightly altered. Notably uv mapping.

## Whats next
Planned upgrades to the original source code in a separate branch:
- Landscape support  
- Gamepad support
- A separate branch porting the game to sdl and possibly c++  
- Fixing online capabilities
