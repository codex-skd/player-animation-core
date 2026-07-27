# Player Animation Core - Developer Documentation

## Adding the Dependency

Add to your `build.gradle`:

```groovy
repositories {
    flatDir { dir 'libs' }
}

dependencies {
    implementation files("libs/player_animation_core-<version>.jar")
}
```

Place `player_animation_core-<version>.jar` and its embedded JARs in your mod's `libs/` folder.

---

## Creating Animation Files

Place animation JSON files in your mod resources under:
```
assets/<your_mod_id>/player_animations/<animation_name>.json
```

### EmoteCraft Format (recommended)

```json
{
  "name": "attack_slash_right",
  "author": "YourName",
  "description": "One-handed slash attack",
  "emote": {
    "isLoop": false,
    "returnTick": 2,
    "beginTick": 8,
    "endTick": 20,
    "stopTick": 30,
    "degrees": false,
    "moves": [
      {
        "tick": 8,
        "easing": "EASEINOUTQUAD",
        "turn": 0,
        "right_arm": { "pitch": 0.0, "yaw": 0.0, "roll": 0.0 },
        "body": { "yaw": -20.0 }
      },
      {
        "tick": 12,
        "easing": "EASEINOUTQUAD",
        "turn": 0,
        "right_arm": { "pitch": -90.0, "yaw": 30.0, "roll": 45.0 },
        "body": { "yaw": 30.0 }
      }
    ]
  }
}
```

### Bone Names

| Bone | Description |
|------|-------------|
| `head` | Player head |
| `body` | Torso |
| `right_arm` | Right arm |
| `left_arm` | Left arm |
| `right_leg` | Right leg |
| `left_leg` | Left leg |
| `right_item` | Right hand item |
| `left_item` | Left hand item |

### Easing Types

`LINEAR`, `CONSTANT`, `STEP`, `EASEINSINE`, `EASEOUTSINE`, `EASEINOUTSINE`, `EASEINQUAD`, `EASEOUTQUAD`, `EASEINOUTQUAD`, `EASEINCUBIC`, `EASEOUTCUBIC`, `EASEINOUTCUBIC`, `BEZIER`, `CATMULLROM`

---

## Registering Animations

### Method 1: PlayerAnimationFactory (clean API)

```java
import com.skd.playeranimationcore.api.PlayerAnimationFactory;
import com.skd.playeranimationcore.animation.PlayerAnimationController;
import com.skd.playeranimationcore.enums.PlayState;
import net.minecraft.resources.Identifier;

// Register during mod initialization
PlayerAnimationFactory.ANIMATION_DATA_FACTORY.registerFactory(
    Identifier.of("your_mod_id", "factory"),
    1000,  // priority (higher = overrides lower)
    player -> new PlayerAnimationController(player, (controller, anim, state) -> PlayState.STOP)
);
```

### Method 2: NeoForge Event

```java
import com.skd.playeranimationcore.api.PlayerAnimationAccess;
import com.skd.playeranimationcore.neoforge.event.PlayerAnimationRegisterEvent;
import net.neoforged.bus.api.SubscribeEvent;

@SubscribeEvent
public void onAnimationRegister(PlayerAnimationRegisterEvent event) {
    var player = event.player();
    var manager = event.animationStack();
    // Add your animation layer here
}
```

---

## Triggering Animations

```java
import com.skd.playeranimationcore.animation.PlayerAnimResources;
import com.skd.playeranimationcore.animation.Animation;
import com.skd.playeranimationcore.api.PlayerAnimationAccess;

// Get the controller for a player
var controller = (PlayerAnimationController) PlayerAnimationAccess
    .getPlayerAnimationLayer(player, Identifier.of("player_animation_core", "factory"));

// Trigger by Identifier (auto-loads from resources)
controller.triggerAnimation(Identifier.of("your_mod_id", "attack_slash_right"));

// Trigger with Animation object
Animation anim = PlayerAnimResources.getAnimation(Identifier.of("your_mod_id", "attack"));
controller.triggerAnimation(anim, 5.0f); // start at tick 5.0

// Trigger with RawAnimation (sequence of animations)
import com.skd.playeranimationcore.animation.RawAnimation;
controller.triggerAnimation(
    RawAnimation.begin()
        .thenPlay(anim1)
        .thenWait(10)  // wait 10 ticks between
        .thenPlay(anim2)
);

// Replace with fade transition
import com.skd.playeranimationcore.animation.layered.modifier.AbstractFadeModifier;
import com.skd.playeranimationcore.easing.EasingType;

controller.replaceAnimationWithFade(
    AbstractFadeModifier.standardFadeIn(10, EasingType.EASE_IN_OUT_SINE),
    Identifier.of("your_mod_id", "new_animation"),
    true  // fadeFromNothing
);
```

---

## First-Person Mode (THIRD_PERSON_MODEL)

Render the third-person model in first-person view.

```java
import com.skd.playeranimationcore.api.firstPerson.FirstPersonConfiguration;
import com.skd.playeranimationcore.api.firstPerson.FirstPersonMode;

// In your animation controller, override getFirstPersonMode()
@Override
public FirstPersonMode getFirstPersonMode() {
    return isAnimating() ? FirstPersonMode.THIRD_PERSON_MODEL : FirstPersonMode.NONE;
}

// Configure what to show in first person
@Override
public FirstPersonConfiguration getFirstPersonConfiguration() {
    return new FirstPersonConfiguration()
        .setShowRightArm(true)
        .setShowLeftArm(false)
        .setShowRightItem(true)
        .setShowLeftItem(false)
        .setShowArmor(true);  // show armor in first person
}
```

---

## Using Modifiers

Modifiers transform animation output in the layer stack.

### Speed Modifier

```java
import com.skd.playeranimationcore.animation.layered.modifier.SpeedModifier;

// Play animation at 2x speed
controller.animation.getModifierLayer(0)
    .addModifierBefore(new SpeedModifier(2.0f));
```

### Fade Modifiers (transitions between animations)

```java
import com.skd.playeranimationcore.animation.layered.modifier.AbstractFadeModifier;
import com.skd.playeranimationcore.easing.EasingType;

// Fade in over 10 ticks with easing
var fadeIn = AbstractFadeModifier.standardFadeIn(10, EasingType.EASE_IN_OUT_SINE);
controller.replaceAnimationWithFade(fadeIn, newAnimation);

// Fade out over 15 ticks
var fadeOut = AbstractFadeModifier.standardFadeOut(15, EasingType.EASE_IN_OUT_QUAD);
controller.replaceAnimationWithFade(fadeOut, null); // fade to nothing
```

### Mirror Modifier

```java
import com.skd.playeranimationcore.animation.layered.modifier.MirrorModifier;

var mirror = new MirrorModifier();
controller.animation.getModifierLayer(0).addModifierBefore(mirror);
```

### Mirror If Left Hand

```java
import com.skd.playeranimationcore.animation.layered.modifier.MirrorIfLeftHandModifier;

// Only mirrors if player's main hand is right (keeps left-handed animations as-is)
var mirror = new MirrorIfLeftHandModifier();
controller.animation.getModifierLayer(0).addModifierBefore(mirror);
```

---

## Molang Queries

Animation JSON files can use Molang expressions. Available queries:

| Query | Type | Description |
|-------|------|-------------|
| `q.anim_time` | float | Current animation time |
| `q.is_first_person` | bool | Is camera in first person |
| `q.is_sprinting` | bool | Player is sprinting |
| `q.is_sneaking` | bool | Player is crouching |
| `q.is_on_ground` | bool | Player on ground |
| `q.is_moving` | bool | Player is moving |
| `q.ground_speed` | float | Horizontal speed |
| `q.vertical_speed` | float | Vertical speed |
| `q.head_x_rotation` | float | Head pitch |
| `q.head_y_rotation` | float | Head yaw |
| `q.limb_swing` | float | Walk animation position |
| `q.health` | float | Current health |
| `q.max_health` | float | Max health |
| `q.hurt_time` | float | Hurt time with partial tick |
| `q.is_blocking` | bool | Is using shield |
| `q.player_level` | int | XP level |
| `q.day` | float | Day time (0.0 to 1.0) |
| `q.time_of_day` | float | Clock time |
| `q.moon_phase` | int | Moon phase (0-7) |

Add custom queries:

```java
import com.skd.playeranimationcore.molang.MolangLoader;

// In your animation layer setup
MolangLoader.setBoolQuery(queryBinding, "is_charging", controller -> {
    return controller instanceof YourController c && c.isCharging();
});

MolangLoader.setDoubleQuery(queryBinding, "charge_progress", controller -> {
    return controller instanceof YourController c ? c.getChargeProgress() : 0.0;
});
```

---

## Keyframe Events (Sounds, Particles)

### Sound Effects

In your animation JSON:
```json
{
  "name": "attack",
  "emote": {
    "moves": [...],
    "sound_effects": {
      "0.0": { "effect": "minecraft:entity.player.attack.sweep" },
      "0.4": { "effect": "minecraft:entity.player.attack.sweep|0.5|1.2" }
    }
  }
}
```
Format: `"sound_id|volume|pitch"`

### Particle Effects

```json
{
  "emote": {
    "particle_effects": {
      "0.5": { "effect": "minecraft:crit", "locator": "right_arm", "pre_effect_script": "" }
    }
  }
}
```

---

## Complete Example: Combat Animation Mod

```java
package com.example.combatmod;

import com.skd.playeranimationcore.animation.PlayerAnimationController;
import com.skd.playeranimationcore.animation.Animation;
import com.skd.playeranimationcore.animation.PlayerAnimResources;
import com.skd.playeranimationcore.animation.RawAnimation;
import com.skd.playeranimationcore.api.PlayerAnimationAccess;
import com.skd.playeranimationcore.api.PlayerAnimationFactory;
import com.skd.playeranimationcore.enums.PlayState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Avatar;

public class CombatAnimations {
    private static final Identifier ANIM_SLASH = Identifier.of("combatmod", "slash_right");
    private static final Identifier ANIM_STAB = Identifier.of("combatmod", "stab");

    // Called during mod init
    public static void init() {
        // Register animation layer
        PlayerAnimationFactory.ANIMATION_DATA_FACTORY.registerFactory(
            Identifier.of("combatmod", "combat"),
            1000,
            CombatController::new
        );
    }

    // Trigger an attack animation on a player
    public static void playSlash(Avatar player) {
        var controller = (PlayerAnimationController) PlayerAnimationAccess
            .getPlayerAnimationLayer(player, Identifier.of("combatmod", "combat"));
        if (controller != null) {
            controller.triggerAnimation(ANIM_SLASH);
        }
    }

    // Custom controller
    public static class CombatController extends PlayerAnimationController {
        public CombatController(Avatar avatar) {
            super(avatar, CombatController::handleAnimationState);
        }

        private static PlayState handleAnimationState(
            PlayerAnimationController controller,
            Animation anim,
            PlayState state
        ) {
            // Animation finished - can trigger follow-up here
            return PlayState.CONTINUE;
        }
    }
}
```

---

## Tips

- **Priority system:** Higher priority layers execute on top (override lower layers)
- **Non-blocking:** Triggering a new animation while one is playing replaces it; use `replaceAnimationWithFade` for smooth transitions
- **Animation files** support both EmoteCraft format and Bedrock/GeckoLib format automatically
- **Resource reload:** Animations are reloaded when resources reload (F3+T), no restart needed
- **Client-only:** All animation logic runs on the client side; the mod is safe to install on dedicated servers
