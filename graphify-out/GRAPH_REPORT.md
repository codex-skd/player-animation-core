# Graph Report - 26.2  (2026-08-06)

## Corpus Check
- 121 files · ~79,298 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 1315 nodes · 2939 edges · 68 communities (61 shown, 7 thin omitted)
- Extraction: 96% EXTRACTED · 4% INFERRED · 0% AMBIGUOUS · INFERRED: 108 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `c93146f2`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- UniversalAnimLoader.java
- PlayerAnimationController.java
- AnimationData
- ItemInHandRendererMixin.java
- ElytraLayerMixin.java
- EasingType
- AnimationController
- KeyframeStack
- AnimationBinary
- LevelRendererMixin.java
- BezierEasing
- Animation
- BoneAnimation
- PlayerAnimBone
- Keyframe
- EasingType.java
- RawAnimation
- Player Animation Core - Developer Documentation
- QueryBinding
- FirstPersonConfiguration
- ExtraAnimationData
- IAnimation
- AnimationLoader.java
- AbstractFadeModifier
- ModifierLayer
- PlayerAnimResources.java
- AvatarAnimManager
- Event
- CapeLayerMixin.java
- AnimationController.java
- ToggleablePlayerAnimBone
- CurseForge — Variables del proyecto
- AdvancedPlayerAnimBone
- Vec3f
- IAvatarAnimationState
- AnimationContainer
- ItemInHandLayerMixin.java
- AvatarMixin.java
- PlayerAnimCommands.java
- Flujo de trabajo — Player Animation Core (NeoForge)
- Override
- HumanoidArmorLayerMixin.java
- PlayerAnimationFactory.java
- FirstPersonMode
- HeaderFlag
- MochaMathExtensions.java
- Matrix4f
- AvatarRendererMixin.java
- KeyframeFlag.java
- PlayerAnimCoreService
- MolangLoader.java
- Player Animation Core
- MathHelper
- .getCurrentAnimationInstance
- [0.0.0-beta.1] - 2026-08-02
- CLAUDE.md — player_animation_core (26.2)
- gradlew
- apply
- fromJson

## God Nodes (most connected - your core abstractions)
1. `AnimationController` - 127 edges
2. `PlayerAnimBone` - 77 edges
3. `Animation` - 63 edges
4. `EasingType` - 60 edges
5. `AnimationData` - 55 edges
6. `AvatarAnimManager` - 45 edges
7. `Vec3f` - 37 edges
8. `Keyframe` - 36 edges
9. `IAnimation` - 36 edges
10. `FirstPersonConfiguration` - 33 edges

## Surprising Connections (you probably didn't know these)
- `AvatarMixin` --implements--> `IAnimatedAvatar`  [EXTRACTED]
  src/main/java/com/skd/mixin/AvatarMixin.java → src/main/java/com/skd/accessors/IAnimatedAvatar.java
- `PlayerCapeModelMixin` --implements--> `IBoneUpdater`  [EXTRACTED]
  src/main/java/com/skd/mixin/PlayerCapeModelMixin.java → src/main/java/com/skd/accessors/IBoneUpdater.java
- `PlayerModelMixin` --implements--> `IBoneUpdater`  [EXTRACTED]
  src/main/java/com/skd/mixin/PlayerModelMixin.java → src/main/java/com/skd/accessors/IBoneUpdater.java
- `Animation` --references--> `ExtraAnimationData`  [EXTRACTED]
  src/main/java/com/skd/animation/Animation.java → src/main/java/com/skd/animation/ExtraAnimationData.java
- `Animation` --references--> `BoneAnimation`  [EXTRACTED]
  src/main/java/com/skd/animation/Animation.java → src/main/java/com/skd/animation/keyframe/BoneAnimation.java

## Import Cycles
- None detected.

## Communities (68 total, 7 thin omitted)

### Community 0 - "UniversalAnimLoader.java"
Cohesion: 0.07
Nodes (26): FloatObjectPair, JsonDeserializer, Pattern, AnimationLoader, Expression, JsonArray, JsonDeserializationContext, JsonElement (+18 more)

### Community 1 - "PlayerAnimationController.java"
Cohesion: 0.08
Nodes (18): Keyframes, restartFromTick(), shouldPlayAgain(), AutoPlayingSoundKeyframeHandler, CustomKeyFrameEvents, CustomKeyFrameHandler, FunctionalInterface, ResetKeyFramesHandler (+10 more)

### Community 2 - "AnimationData"
Cohesion: 0.11
Nodes (3): AnimationData, Override, SpeedModifier

### Community 3 - "ItemInHandRendererMixin.java"
Cohesion: 0.07
Nodes (21): ItemDisplayContext, NotNull, FirstPersonConfiguration, ItemInHandLayerMixin, CallbackInfo, HumanoidArm, Inject, ItemStack (+13 more)

### Community 4 - "ElytraLayerMixin.java"
Cohesion: 0.06
Nodes (37): CameraRenderState, HumanoidModel, PartPose, Pose, ModelPart, ElytraLayerMixin, CallbackInfo, HumanoidRenderState (+29 more)

### Community 5 - "EasingType"
Cohesion: 0.05
Nodes (36): EasingType, BEZIER, CATMULLROM, CONSTANT, EASE_IN_BACK, EASE_IN_BOUNCE, EASE_IN_CIRC, EASE_IN_CUBIC (+28 more)

### Community 6 - "AnimationController"
Cohesion: 0.09
Nodes (3): AnimationController, Nullable, QueuedAnimation

### Community 8 - "AnimationBinary"
Cohesion: 0.15
Nodes (6): AnimationBinary, ByteBuf, AnimationBinaryV6, ByteBuf, ByteBuf, NetworkUtils

### Community 9 - "LevelRendererMixin.java"
Cohesion: 0.11
Nodes (21): DeltaTracker, Entity, Frustum, LevelRenderState, ModifyExpressionValue, CallbackInfo, Camera, EntityRenderState (+13 more)

### Community 10 - "BezierEasing"
Cohesion: 0.11
Nodes (16): FloatFunction3, BezierEasing, Expression, Float2FloatFunction, MochaEngine, Override, CatmullRomEasing, Expression (+8 more)

### Community 11 - "Animation"
Cohesion: 0.07
Nodes (11): Animation, FunctionalInterface, JsonElement, NotNull, Override, LoopType, Identifier, PlayerRawAnimationBuilder (+3 more)

### Community 13 - "PlayerAnimBone"
Cohesion: 0.15
Nodes (7): Override, MirrorIfLeftHandModifier, Override, MirrorModifier, Override, PlayerAnimBone, Vector3f

### Community 14 - "Keyframe"
Cohesion: 0.11
Nodes (10): Expression, Override, Keyframe, Internal, Axis, X, Y, Z (+2 more)

### Community 15 - "EasingType.java"
Cohesion: 0.17
Nodes (11): back(), bounce(), easeIn(), easeInOut(), easeOut(), elastic(), fromId(), Float2FloatFunction (+3 more)

### Community 16 - "RawAnimation"
Cohesion: 0.14
Nodes (11): TransformType, BEND, POSITION, ROTATION, SCALE, Expression, Gson, JsonDeserializationContext (+3 more)

### Community 17 - "Player Animation Core - Developer Documentation"
Cohesion: 0.09
Nodes (22): Adding the Dependency, Bone Names, Complete Example: Combat Animation Mod, Creating Animation Files, Easing Types, EmoteCraft Format (recommended), Fade Modifiers (transitions between animations), First-Person Mode (THIRD_PERSON_MODEL) (+14 more)

### Community 18 - "QueryBinding"
Cohesion: 0.07
Nodes (24): ExecutionContext, MutableObjectBinding, ParseException, Event, Invoker, FunctionalInterface, FunctionalInterface, MochaEngine (+16 more)

### Community 19 - "FirstPersonConfiguration"
Cohesion: 0.24
Nodes (9): EntityType, LivingEntity, AvatarMixin, Identifier, Level, Mixin, Override, SuppressWarnings (+1 more)

### Community 20 - "ExtraAnimationData"
Cohesion: 0.16
Nodes (7): ByteBuffer, ExtraAnimationData, JsonElement, JsonObject, NotNull, Nullable, Override

### Community 21 - "IAnimation"
Cohesion: 0.18
Nodes (5): Pair, AnimationStack, NotNull, Override, IAnimation

### Community 22 - "AnimationLoader.java"
Cohesion: 0.21
Nodes (5): AnimationFormat, GECKOLIB, PLAYER_ANIMATOR, fromId(), Expression

### Community 23 - "AbstractFadeModifier"
Cohesion: 0.24
Nodes (4): Nullable, BoneAnimation, Override, JsonElement

### Community 24 - "ModifierLayer"
Cohesion: 0.05
Nodes (19): AnimationContainer, NotNull, Nullable, Override, NotNull, AbstractModifier, Nullable, Override (+11 more)

### Community 25 - "PlayerAnimResources.java"
Cohesion: 0.18
Nodes (11): BoneChannel, BEND, POSITION_X, POSITION_Y, POSITION_Z, ROTATION_X, ROTATION_Y, ROTATION_Z (+3 more)

### Community 27 - "Event"
Cohesion: 0.33
Nodes (6): AnimationRegister, Avatar, FunctionalInterface, Identifier, Nullable, PlayerAnimationAccess

### Community 28 - "CapeLayerMixin.java"
Cohesion: 0.19
Nodes (14): RenderLayer, CapeLayerMixin, AvatarRenderState, CallbackInfo, Inject, Mixin, PoseStack, SubmitNodeCollector (+6 more)

### Community 29 - "AnimationController.java"
Cohesion: 0.15
Nodes (9): AnimationSetter, AnimationStateHandler, FunctionalInterface, Internal, MochaEngine, KeyframeLocation, PlayState, CONTINUE (+1 more)

### Community 30 - "ToggleablePlayerAnimBone"
Cohesion: 0.10
Nodes (5): AnimationSnapshot, NotNull, Override, Deprecated, ToggleablePlayerAnimBone

### Community 31 - "CurseForge — Variables del proyecto"
Cohesion: 0.12
Nodes (15): Changelog, Claves parseables por el script genérico, CurseForge — Variables del proyecto, Descripcion del proyecto, Estructura del changelog (HTML), Flujo completo, IDs de `gameVersions` para 26.2, Parámetros del upload (+7 more)

### Community 33 - "Vec3f"
Cohesion: 0.15
Nodes (6): HumanoidAnimationController, MochaEngine, Override, PivotBone, Override, Vec3f

### Community 34 - "IAvatarAnimationState"
Cohesion: 0.19
Nodes (5): IAvatarAnimationState, AvatarRenderStateMixin, Mixin, NotNull, Override

### Community 35 - "AnimationContainer"
Cohesion: 0.19
Nodes (3): Override, PlayerAnimationFrame, PlayerBone

### Community 36 - "ItemInHandLayerMixin.java"
Cohesion: 0.35
Nodes (11): ArmedEntityRenderState, ItemInHandLayerMixin, CallbackInfo, HumanoidArm, Inject, ItemStack, ItemStackRenderState, LocalBooleanRef (+3 more)

### Community 38 - "PlayerAnimCommands.java"
Cohesion: 0.05
Nodes (39): AddClientReloadListenersEvent, CommandBuildContext, CommandDispatcher, CommandSourceStack, FMLClientSetupEvent, IEventBus, Logger, Mod (+31 more)

### Community 39 - "Flujo de trabajo — Player Animation Core (NeoForge)"
Cohesion: 0.15
Nodes (12): Buenas prácticas, Commits (Conventional Commits), Convenciones de nomenclatura, Específico del mod, Estructura del proyecto, Flujo de trabajo — Player Animation Core (NeoForge), Flujo por tarea, Idioma (+4 more)

### Community 42 - "Override"
Cohesion: 0.14
Nodes (7): InternalAnimationAccessor, NotNull, Override, State, PAUSED, RUNNING, STOPPED

### Community 43 - "HumanoidArmorLayerMixin.java"
Cohesion: 0.30
Nodes (10): EquipmentSlot, HumanoidArmorLayerMixin, CallbackInfo, HumanoidModel, HumanoidRenderState, Inject, ItemStack, Mixin (+2 more)

### Community 44 - "PlayerAnimationFactory.java"
Cohesion: 0.36
Nodes (7): DataHolder, FactoryHolder, Avatar, Identifier, Internal, Nullable, PlayerAnimationFactory

### Community 46 - "FirstPersonMode"
Cohesion: 0.16
Nodes (6): IBoneUpdater, ModelPart, AvatarAnimManager, Avatar, Avatar, PlayerAnimationRegisterEvent

### Community 47 - "HeaderFlag"
Cohesion: 0.18
Nodes (8): HeaderFlag, APPLY_BEND, EASE_BEFORE, HAS_BEGIN_TICK, HAS_END_TICK, HOLD_ON_LAST_FRAME, PLAYER_ANIMATOR, SHOULD_PLAY_AGAIN

### Community 48 - "MochaMathExtensions.java"
Cohesion: 0.38
Nodes (6): Binding, ObjectValue, Nullable, ObjectProperty, Value, MochaMathExtensions

### Community 51 - "AvatarRendererMixin.java"
Cohesion: 0.31
Nodes (7): Internal, AvatarRendererMixin, Avatar, AvatarRenderState, CallbackInfo, Inject, Mixin

### Community 52 - "KeyframeFlag.java"
Cohesion: 0.27
Nodes (9): flagBitsForVersion(), KeyframeFlag, HAS_EASING_ARGS, IS_CONSTANT, LENGTH_ONE, LENGTH_ZERO, pack(), unpackEasing() (+1 more)

### Community 53 - "PlayerAnimCoreService"
Cohesion: 0.28
Nodes (4): AdvancedService, Override, PlayerAnimCoreServiceImpl, PlayerAnimCoreService

### Community 54 - "MolangLoader.java"
Cohesion: 0.24
Nodes (3): AdjustmentModifier, Override, PartModifier

### Community 55 - "Player Animation Core"
Cohesion: 0.25
Nodes (7): Building, Features, License, Links, Player Animation Core, Requirements, Version: 0.0.0-beta.1

### Community 63 - ".getCurrentAnimationInstance"
Cohesion: 0.13
Nodes (7): AbstractFadeModifier, EasingFunction, FunctionalInterface, Override, FadeType, FADE_IN, FADE_OUT

### Community 66 - "[0.0.0-beta.1] - 2026-08-02"
Cohesion: 0.13
Nodes (14): [0.0.0-beta.1] - 2026-08-02, [0.0.0-beta.2] - 2026-08-02, [0.0.0-beta.3] - 2026-08-02, [1.0.0] - 2026-08-02, [1.0.1] - 2026-08-02, [1.0.2] - 2026-08-05, Added, Added (+6 more)

### Community 67 - "CLAUDE.md — player_animation_core (26.2)"
Cohesion: 0.50
Nodes (3): CLAUDE.md — player_animation_core (26.2), Prioridad de instrucciones, Workflow del mod

### Community 68 - "gradlew"
Cohesion: 0.83
Nodes (3): gradlew script, die(), warn()

### Community 73 - "apply"
Cohesion: 0.40
Nodes (6): apply(), buildTransformer(), Expression, MochaEngine, Override, lerpWithOverride()

### Community 76 - "fromJson"
Cohesion: 0.67
Nodes (3): fromJson(), fromString(), JsonElement

## Knowledge Gaps
- **136 isolated node(s):** `NONE`, `VANILLA`, `THIRD_PERSON_MODEL`, `HANDS_ONLY`, `HANDS_ONLY_ARM` (+131 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **7 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `AnimationController` connect `AnimationController` to `AdvancedPlayerAnimBone`, `PlayerAnimationController.java`, `AnimationData`, `Vec3f`, `ItemInHandRendererMixin.java`, `EasingType`, `PlayerAnimCommands.java`, `Override`, `Animation`, `PlayerAnimBone`, `Keyframe`, `QueryBinding`, `IAnimation`, `MolangLoader.java`, `ModifierLayer`, `AvatarAnimManager`, `AnimationController.java`, `.getCurrentAnimationInstance`?**
  _High betweenness centrality (0.223) - this node is a cross-community bridge._
- **Why does `PlayerAnimBone` connect `PlayerAnimBone` to `AdvancedPlayerAnimBone`, `Vec3f`, `ItemInHandRendererMixin.java`, `ElytraLayerMixin.java`, `AnimationContainer`, `AnimationController`, `ItemInHandLayerMixin.java`, `Override`, `FirstPersonMode`, `Keyframe`, `Matrix4f`, `IAnimation`, `MolangLoader.java`, `ModifierLayer`, `AnimationController.java`, `ToggleablePlayerAnimBone`, `.getCurrentAnimationInstance`?**
  _High betweenness centrality (0.192) - this node is a cross-community bridge._
- **Why does `EasingType` connect `EasingType` to `UniversalAnimLoader.java`, `AnimationController`, `KeyframeStack`, `apply`, `BezierEasing`, `fromJson`, `Keyframe`, `EasingType.java`, `RawAnimation`, `AnimationLoader.java`, `AbstractFadeModifier`, `AnimationController.java`, `.getCurrentAnimationInstance`?**
  _High betweenness centrality (0.122) - this node is a cross-community bridge._
- **What connects `NONE`, `VANILLA`, `THIRD_PERSON_MODEL` to the rest of the system?**
  _136 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `UniversalAnimLoader.java` be split into smaller, more focused modules?**
  _Cohesion score 0.07236544549977386 - nodes in this community are weakly interconnected._
- **Should `PlayerAnimationController.java` be split into smaller, more focused modules?**
  _Cohesion score 0.08232118758434548 - nodes in this community are weakly interconnected._
- **Should `AnimationData` be split into smaller, more focused modules?**
  _Cohesion score 0.11396011396011396 - nodes in this community are weakly interconnected._