# Player Animation Core

Player Animation Core (PAC) is a library mod for NeoForge that provides a flexible animation system for player models in Minecraft. It powers custom animations for mods like Dinamyc Combat, allowing smooth, keyframe-based animations for combat, movement, and more.

## Features

- **Keyframe animation system** — define animations in JSON using GeckoLib-style keyframes
- **Layered animation stacking** — multiple animations can run simultaneously with priority layering
- **First-person modes** — four modes to control how first-person rendering works:
  - `THIRD_PERSON_MODEL` — renders the full 3D player model in first person (arms, body, head)
  - `HANDS_ONLY` — applies item bone transforms on top of vanilla first-person hands
  - `HANDS_ONLY_ARM` — applies arm bone rotations (configurable scale) to vanilla first-person item rendering
  - `VANILLA` — standard Minecraft first-person view
- **FirstPersonConfiguration** — control visibility of arms, items, and armor per-animation
- **Bone transform API** — query and apply transforms to any body part (head, torso, arms, legs, items)
- **MirrorIfLeftHandModifier** — automatically mirror animations for left-hand rendering

## Requirements

- Minecraft 1.21.5+
- NeoForge 26.1.2+

## Integration for Mod Developers

Add PAC as a dependency in your `build.gradle`:

```gradle
repositories {
    maven {
        url "https://gitlab.com/api/v4/projects/stalking-dragons%2Fminecraft%2Fplayer-animation-core/packages/maven"
    }
}

dependencies {
    implementation "com.skd:player_animation_core:0.0.0-beta.19"
}
```

Register an animation factory:

```java
PlayerAnimationFactory.ANIMATION_DATA_FACTORY.registerFactory(
    Identifier.of("yourmod", "combat"), 1000, YourController::new);
```

Trigger animations:

```java
var layer = PlayerAnimationAccess.getPlayerAnimationLayer(avatar, FACTORY_ID);
if (layer instanceof PlayerAnimationController controller) {
    controller.triggerAnimation(animId);
}
```

Override first-person config:

```java
@Override
public FirstPersonMode getFirstPersonMode() {
    return FirstPersonMode.HANDS_ONLY_ARM;
}

@Override
public FirstPersonConfiguration getFirstPersonConfiguration() {
    return new FirstPersonConfiguration()
        .setArmRotationScale(1.0f)
        .setShowArmor(true);
}
```

See [DOCS.md](https://gitlab.com/stalking-dragons/minecraft/player-animation-core/-/blob/main/DOCS.md) for full API reference.
