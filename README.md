# Player Animation Core

A NeoForge library that allows mods to animate the player without conflicts between mods.

## Features

- **Non-conflicting player animations**: Multiple mods can animate the player simultaneously
- **First-person model support**: THIRD_PERSON_MODEL mode for first-person animations
- **Molang engine**: Full support for Molang expressions in animations
- **Keyframe system**: Advanced keyframe-based animation system with easing support
- **Layered animation stack**: Priority-based animation layering with fade modifiers
- **Mod API**: Simple API for other mods to register and trigger animations

## Requirements

- **NeoForge** 26.2.0.32-beta or later
- **Minecraft** 26.2

## Version: 0.0.0-beta.1

Initial 26.2 beta release, ported from the stable 26.1.2 state (1.0.0). Based on Player Animation Library, rebranded and fixed.

## Building

```bash
./gradlew.bat build
```

The JAR is generated at `build/libs/player_animation_core-26.2-neoforge-<version>.jar`.

## Links
- GitLab: https://gitlab.com/stalking-dragons/minecraft/player-animation-core

## License
All Rights Reserved
