# Graph Report - 26.2  (2026-08-02)

## Corpus Check
- 118 files · ~79,034 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 1305 nodes · 2932 edges · 78 communities (67 shown, 11 thin omitted)
- Extraction: 96% EXTRACTED · 4% INFERRED · 0% AMBIGUOUS · INFERRED: 108 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `6d2fa444`
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
- PlayerRawAnimationBuilder
- AbstractModifier
- Override
- HumanoidArmorLayerMixin.java
- PlayerAnimationFactory.java
- PlayerAnimCoreModNeo.java
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
- ItemInHandLayerMixin.java
- AnimationArgumentProvider.java
- HumanoidAnimationController
- MirrorModifier
- AnimationSnapshot
- .getCurrentAnimationInstance
- .getCurrentAnimationInstance
- .getCurrentKeyFrameLocation
- ParticleEffectUtils.java
- [0.0.0-beta.1] - 2026-08-02
- CLAUDE.md — player_animation_core (26.2)
- gradlew
- apply
- State
- MolangEvent.java
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
- `PlayerAnimCoreMod` --inherits--> `PlayerAnimCore`  [EXTRACTED]
  src/main/java/com/skd/PlayerAnimCoreMod.java → src/main/java/com/skd/PlayerAnimCore.java
- `PlayerAnimCoreModNeo` --inherits--> `PlayerAnimCoreMod`  [EXTRACTED]
  src/main/java/com/skd/neoforge/PlayerAnimCoreModNeo.java → src/main/java/com/skd/PlayerAnimCoreMod.java
- `Animation` --references--> `ExtraAnimationData`  [EXTRACTED]
  src/main/java/com/skd/animation/Animation.java → src/main/java/com/skd/animation/ExtraAnimationData.java
- `Animation` --references--> `BoneAnimation`  [EXTRACTED]
  src/main/java/com/skd/animation/Animation.java → src/main/java/com/skd/animation/keyframe/BoneAnimation.java
- `Animation` --references--> `Vec3f`  [EXTRACTED]
  src/main/java/com/skd/animation/Animation.java → src/main/java/com/skd/math/Vec3f.java

## Import Cycles
- None detected.

## Communities (78 total, 11 thin omitted)

### Community 0 - "UniversalAnimLoader.java"
Cohesion: 0.05
Nodes (46): FloatObjectPair, JsonDeserializer, Logger, Pattern, Keyframes, Axis, X, Y (+38 more)

### Community 1 - "PlayerAnimationController.java"
Cohesion: 0.05
Nodes (25): AutoPlayingSoundKeyframeHandler, CustomKeyFrameEvents, CustomKeyFrameHandler, FunctionalInterface, ResetKeyFramesHandler, CustomInstructionKeyframeData, Override, Override (+17 more)

### Community 2 - "AnimationData"
Cohesion: 0.28
Nodes (3): AnimationBinary, ByteBuf, Expression

### Community 4 - "ElytraLayerMixin.java"
Cohesion: 0.06
Nodes (39): CameraRenderState, HumanoidModel, PartPose, Pose, IBoneUpdater, ModelPart, ModelPart, ElytraLayerMixin (+31 more)

### Community 5 - "EasingType"
Cohesion: 0.05
Nodes (36): EasingType, BEZIER, CATMULLROM, CONSTANT, EASE_IN_BACK, EASE_IN_BOUNCE, EASE_IN_CIRC, EASE_IN_CUBIC (+28 more)

### Community 6 - "AnimationController"
Cohesion: 0.09
Nodes (4): AnimationController, MochaEngine, MochaEngine, MolangEvent

### Community 7 - "KeyframeStack"
Cohesion: 0.14
Nodes (12): TransformType, BEND, POSITION, ROTATION, SCALE, Expression, Gson, JsonDeserializationContext (+4 more)

### Community 8 - "AnimationBinary"
Cohesion: 0.22
Nodes (4): AnimationBinaryV6, ByteBuf, ByteBuf, NetworkUtils

### Community 9 - "LevelRendererMixin.java"
Cohesion: 0.11
Nodes (21): DeltaTracker, Entity, Frustum, LevelRenderState, ModifyExpressionValue, CallbackInfo, Camera, EntityRenderState (+13 more)

### Community 10 - "BezierEasing"
Cohesion: 0.11
Nodes (16): FloatFunction3, BezierEasing, Expression, Float2FloatFunction, MochaEngine, Override, CatmullRomEasing, Expression (+8 more)

### Community 11 - "Animation"
Cohesion: 0.11
Nodes (9): Animation, FunctionalInterface, JsonElement, NotNull, Override, LoopType, restartFromTick(), shouldPlayAgain() (+1 more)

### Community 12 - "BoneAnimation"
Cohesion: 0.23
Nodes (3): ByteBuf, Expression, LegacyAnimationBinary

### Community 13 - "PlayerAnimBone"
Cohesion: 0.20
Nodes (4): Internal, Override, PlayerAnimBone, Vector3f

### Community 14 - "Keyframe"
Cohesion: 0.18
Nodes (7): Nullable, BoneAnimation, Override, Expression, Override, Keyframe, getKeyframes()

### Community 15 - "EasingType.java"
Cohesion: 0.17
Nodes (11): back(), bounce(), easeIn(), easeInOut(), easeOut(), elastic(), fromId(), Float2FloatFunction (+3 more)

### Community 16 - "RawAnimation"
Cohesion: 0.17
Nodes (3): Override, RawAnimation, Stage

### Community 17 - "Player Animation Core - Developer Documentation"
Cohesion: 0.09
Nodes (22): Adding the Dependency, Bone Names, Complete Example: Combat Animation Mod, Creating Animation Files, Easing Types, EmoteCraft Format (recommended), Fade Modifiers (transitions between animations), First-Person Mode (THIRD_PERSON_MODEL) (+14 more)

### Community 18 - "QueryBinding"
Cohesion: 0.20
Nodes (8): ExecutionContext, MutableObjectBinding, MolangQueries, Expression, Nullable, ObjectProperty, Value, QueryBinding

### Community 19 - "FirstPersonConfiguration"
Cohesion: 0.15
Nodes (9): NotNull, NotNull, FirstPersonMode, DISABLED, HANDS_ONLY, HANDS_ONLY_ARM, NONE, THIRD_PERSON_MODEL (+1 more)

### Community 20 - "ExtraAnimationData"
Cohesion: 0.16
Nodes (7): ByteBuffer, ExtraAnimationData, JsonElement, JsonObject, NotNull, Nullable, Override

### Community 21 - "IAnimation"
Cohesion: 0.19
Nodes (5): Pair, AnimationStack, NotNull, Override, IAnimation

### Community 22 - "AnimationLoader.java"
Cohesion: 0.50
Nodes (4): AnimationFormat, GECKOLIB, PLAYER_ANIMATOR, fromId()

### Community 23 - "AbstractFadeModifier"
Cohesion: 0.20
Nodes (4): Internal, AbstractModifier, Nullable, Override

### Community 24 - "ModifierLayer"
Cohesion: 0.15
Nodes (3): Nullable, Override, ModifierLayer

### Community 25 - "PlayerAnimResources.java"
Cohesion: 0.21
Nodes (9): PreparationBarrier, ResourceManager, ResourceManagerReloadListener, SharedState, Identifier, Identifier, NotNull, Nullable (+1 more)

### Community 26 - "AvatarAnimManager"
Cohesion: 0.22
Nodes (4): AnimationContainer, NotNull, Nullable, Override

### Community 27 - "Event"
Cohesion: 0.33
Nodes (6): AnimationRegister, Avatar, FunctionalInterface, Identifier, Nullable, PlayerAnimationAccess

### Community 28 - "CapeLayerMixin.java"
Cohesion: 0.19
Nodes (14): RenderLayer, CapeLayerMixin, AvatarRenderState, CallbackInfo, Inject, Mixin, PoseStack, SubmitNodeCollector (+6 more)

### Community 29 - "AnimationController.java"
Cohesion: 0.21
Nodes (6): AnimationSetter, AnimationStateHandler, FunctionalInterface, PlayState, CONTINUE, STOP

### Community 31 - "CurseForge — Variables del proyecto"
Cohesion: 0.12
Nodes (15): Changelog, Claves parseables por el script genérico, CurseForge — Variables del proyecto, Descripcion del proyecto, Estructura del changelog (HTML), Flujo completo, IDs de `gameVersions` para 26.2, Parámetros del upload (+7 more)

### Community 33 - "Vec3f"
Cohesion: 0.21
Nodes (4): MochaEngine, PivotBone, Override, Vec3f

### Community 34 - "IAvatarAnimationState"
Cohesion: 0.14
Nodes (7): IAvatarAnimationState, AvatarAnimManager, Avatar, AvatarRenderStateMixin, Mixin, NotNull, Override

### Community 35 - "AnimationContainer"
Cohesion: 0.21
Nodes (3): Override, PlayerAnimationFrame, PlayerBone

### Community 36 - "ItemInHandLayerMixin.java"
Cohesion: 0.15
Nodes (23): ArmedEntityRenderState, ItemDisplayContext, ItemInHandRendererMixin, CallbackInfo, Inject, ItemStack, LivingEntity, LocalPlayer (+15 more)

### Community 37 - "AvatarMixin.java"
Cohesion: 0.21
Nodes (9): EntityType, LivingEntity, IAnimatedAvatar, Identifier, AvatarMixin, Identifier, Level, Mixin (+1 more)

### Community 38 - "PlayerAnimCommands.java"
Cohesion: 0.36
Nodes (5): CommandBuildContext, CommandDispatcher, CommandSourceStack, CommandContext, PlayerAnimCommands

### Community 39 - "Flujo de trabajo — Player Animation Core (NeoForge)"
Cohesion: 0.15
Nodes (12): Buenas prácticas, Commits (Conventional Commits), Convenciones de nomenclatura, Específico del mod, Estructura del proyecto, Flujo de trabajo — Player Animation Core (NeoForge), Flujo por tarea, Idioma (+4 more)

### Community 41 - "AbstractModifier"
Cohesion: 0.20
Nodes (5): Event, Invoker, FunctionalInterface, Avatar, PlayerAnimationRegisterEvent

### Community 42 - "Override"
Cohesion: 0.21
Nodes (3): InternalAnimationAccessor, NotNull, Override

### Community 43 - "HumanoidArmorLayerMixin.java"
Cohesion: 0.30
Nodes (10): EquipmentSlot, HumanoidArmorLayerMixin, CallbackInfo, HumanoidModel, HumanoidRenderState, Inject, ItemStack, Mixin (+2 more)

### Community 44 - "PlayerAnimationFactory.java"
Cohesion: 0.36
Nodes (7): DataHolder, FactoryHolder, Avatar, Identifier, Internal, Nullable, PlayerAnimationFactory

### Community 45 - "PlayerAnimCoreModNeo.java"
Cohesion: 0.27
Nodes (6): AddClientReloadListenersEvent, FMLClientSetupEvent, IEventBus, Mod, RegisterClientCommandsEvent, PlayerAnimCoreModNeo

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
Cohesion: 0.30
Nodes (7): ParseException, Contract, Expression, JsonElement, MochaEngine, Value, MolangLoader

### Community 55 - "Player Animation Core"
Cohesion: 0.25
Nodes (7): Building, Features, License, Links, Player Animation Core, Requirements, Version: 0.0.0-beta.1

### Community 57 - "ItemInHandLayerMixin.java"
Cohesion: 0.33
Nodes (9): ItemInHandLayerMixin, CallbackInfo, HumanoidArm, Inject, ItemStack, ItemStackRenderState, Mixin, PoseStack (+1 more)

### Community 58 - "AnimationArgumentProvider.java"
Cohesion: 0.48
Nodes (5): AnimationArgumentProvider, CommandContext, SuggestionProvider, Suggestions, SuggestionsBuilder

### Community 60 - "MirrorModifier"
Cohesion: 0.31
Nodes (4): Override, MirrorIfLeftHandModifier, Override, MirrorModifier

### Community 61 - "AnimationSnapshot"
Cohesion: 0.43
Nodes (3): AnimationSnapshot, NotNull, Override

### Community 63 - ".getCurrentAnimationInstance"
Cohesion: 0.05
Nodes (13): AnimationData, AbstractFadeModifier, EasingFunction, FunctionalInterface, Override, AdjustmentModifier, Override, PartModifier (+5 more)

### Community 66 - "[0.0.0-beta.1] - 2026-08-02"
Cohesion: 0.25
Nodes (7): [0.0.0-beta.1] - 2026-08-02, [0.0.0-beta.2] - 2026-08-02, [0.0.0-beta.3] - 2026-08-02, Added, Fixed, Fixed, Player Animation Core - Changelog

### Community 67 - "CLAUDE.md — player_animation_core (26.2)"
Cohesion: 0.50
Nodes (3): CLAUDE.md — player_animation_core (26.2), Prioridad de instrucciones, Workflow del mod

### Community 68 - "gradlew"
Cohesion: 0.83
Nodes (3): gradlew script, die(), warn()

### Community 73 - "apply"
Cohesion: 0.40
Nodes (6): apply(), buildTransformer(), Expression, MochaEngine, Override, lerpWithOverride()

### Community 74 - "State"
Cohesion: 0.33
Nodes (4): State, PAUSED, RUNNING, STOPPED

### Community 75 - "MolangEvent.java"
Cohesion: 0.47
Nodes (4): FunctionalInterface, MochaEngine, MolangEvent, MolangEventInterface

### Community 76 - "fromJson"
Cohesion: 0.67
Nodes (3): fromJson(), fromString(), JsonElement

## Knowledge Gaps
- **132 isolated node(s):** `NONE`, `VANILLA`, `THIRD_PERSON_MODEL`, `HANDS_ONLY`, `HANDS_ONLY_ARM` (+127 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **11 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `AnimationController` connect `AnimationController` to `PlayerAnimationController.java`, `ItemInHandRendererMixin.java`, `EasingType`, `Animation`, `PlayerAnimBone`, `RawAnimation`, `QueryBinding`, `FirstPersonConfiguration`, `IAnimation`, `AbstractFadeModifier`, `AnimationController.java`, `AdvancedPlayerAnimBone`, `Vec3f`, `PlayerAnimCommands.java`, `Override`, `MolangLoader.java`, `HumanoidAnimationController`, `.getCurrentAnimationInstance`, `.getCurrentAnimationInstance`, `.getCurrentKeyFrameLocation`, `State`, `MolangEvent.java`?**
  _High betweenness centrality (0.228) - this node is a cross-community bridge._
- **Why does `PlayerAnimBone` connect `PlayerAnimBone` to `AdvancedPlayerAnimBone`, `Vec3f`, `ItemInHandRendererMixin.java`, `ElytraLayerMixin.java`, `AnimationContainer`, `AnimationController`, `ItemInHandLayerMixin.java`, `Override`, `Matrix4f`, `FirstPersonConfiguration`, `IAnimation`, `ModifierLayer`, `AvatarAnimManager`, `MirrorModifier`, `AnimationSnapshot`, `ToggleablePlayerAnimBone`, `.getCurrentAnimationInstance`?**
  _High betweenness centrality (0.198) - this node is a cross-community bridge._
- **Why does `EasingType` connect `EasingType` to `UniversalAnimLoader.java`, `Vec3f`, `AnimationController`, `KeyframeStack`, `apply`, `BezierEasing`, `fromJson`, `Keyframe`, `EasingType.java`, `FirstPersonMode`, `.getCurrentAnimationInstance`?**
  _High betweenness centrality (0.124) - this node is a cross-community bridge._
- **What connects `NONE`, `VANILLA`, `THIRD_PERSON_MODEL` to the rest of the system?**
  _132 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `UniversalAnimLoader.java` be split into smaller, more focused modules?**
  _Cohesion score 0.05183861082737487 - nodes in this community are weakly interconnected._
- **Should `PlayerAnimationController.java` be split into smaller, more focused modules?**
  _Cohesion score 0.05367231638418079 - nodes in this community are weakly interconnected._
- **Should `ItemInHandRendererMixin.java` be split into smaller, more focused modules?**
  _Cohesion score 0.1067193675889328 - nodes in this community are weakly interconnected._