# Graph Report - 26.1.2  (2026-08-02)

## Corpus Check
- 121 files · ~79,207 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 1321 nodes · 2945 edges · 85 communities (74 shown, 11 thin omitted)
- Extraction: 96% EXTRACTED · 4% INFERRED · 0% AMBIGUOUS · INFERRED: 108 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `bc5679e1`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- AnimationData
- QueryBinding
- PlayerAnimationController.java
- EasingType
- AnimationController
- LevelRendererMixin.java
- BezierEasing
- PlayerAnimatorLoader.java
- ExtraAnimationData
- ItemInHandLayerMixin.java
- AbstractFadeModifier
- ParticleKeyframeData
- AnimationBinary
- AnimationLoader.java
- IAnimation
- Player Animation Core - Developer Documentation
- FirstPersonConfiguration
- RawAnimation
- ModifierLayer
- PlayerAnimBone
- Animation
- Keyframe
- LegacyAnimationBinary
- JsonUtil
- Player Animation Core - Changelog
- PlayerModelMixin.java
- CapeLayerMixin.java
- EasingType.java
- PlayerCapeModelMixin.java
- AnimationController.java
- ToggleablePlayerAnimBone
- ItemInHandRendererMixin.java
- AbstractModifier
- AdvancedPlayerAnimBone
- AvatarAnimManager
- BoneChannel
- IAvatarAnimationState
- PlayerAnimCommands.java
- PlayerAnimResources.java
- FirstPersonMode
- Flujo de trabajo — Player Animation Core (NeoForge)
- RenderUtil.java
- Override
- KeyframeStack
- KeyFrameLoader.java
- UniversalAnimLoader.java
- PlayerAnimationFactory.java
- LoopType
- ItemInHandLayerMixin.java
- HeaderFlag
- MochaMathExtensions.java
- LivingEntityRendererMixin.java
- PlayerAnimCore
- Matrix4f
- BoneAnimation
- .handleAnimation
- SoundKeyframeData
- AvatarRendererMixin.java
- PlayerRawAnimationBuilder
- ElytraLayerMixin.java
- KeyframeFlag.java
- PlayerAnimCoreService
- KeyFrameData
- Player Animation Core
- AnimationSnapshot
- MirrorModifier
- AnimationArgumentProvider.java
- CurseForge — Variables del proyecto
- HumanoidAnimationController
- apply
- State
- MathHelper
- CLAUDE.md — player_animation_core (26.1.2)
- Player Animation Core v0.0.0-beta.17 — Render Layer Hotfix
- Player Animation Core v0.0.0-beta.18 — HANDS_ONLY_ARM
- Player Animation Core v0.0.0-beta.19 — Configurable Arm Rotation Scale
- Player Animation Core v0.0.0-beta.20 — Hand Position from Arm Rotation
- gradlew
- Player Animation Core v0.0.0-beta.21
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
- `AvatarMixin` --implements--> `IAnimatedAvatar`  [EXTRACTED]
  src/main/java/com/skd/mixin/AvatarMixin.java → src/main/java/com/skd/accessors/IAnimatedAvatar.java
- `PlayerModelMixin` --implements--> `IBoneUpdater`  [EXTRACTED]
  src/main/java/com/skd/mixin/PlayerModelMixin.java → src/main/java/com/skd/accessors/IBoneUpdater.java
- `Animation` --references--> `ExtraAnimationData`  [EXTRACTED]
  src/main/java/com/skd/animation/Animation.java → src/main/java/com/skd/animation/ExtraAnimationData.java
- `Animation` --references--> `BoneAnimation`  [EXTRACTED]
  src/main/java/com/skd/animation/Animation.java → src/main/java/com/skd/animation/keyframe/BoneAnimation.java

## Import Cycles
- None detected.

## Communities (85 total, 11 thin omitted)

### Community 0 - "AnimationData"
Cohesion: 0.05
Nodes (13): AnimationData, AnimationContainer, NotNull, Nullable, Override, AdjustmentModifier, Override, PartModifier (+5 more)

### Community 1 - "QueryBinding"
Cohesion: 0.06
Nodes (30): ExecutionContext, MutableObjectBinding, ParseException, AnimationRegister, Avatar, FunctionalInterface, Identifier, Nullable (+22 more)

### Community 2 - "PlayerAnimationController.java"
Cohesion: 0.08
Nodes (25): AddClientReloadListenersEvent, EquipmentSlot, FMLClientSetupEvent, IEventBus, Mod, RegisterClientCommandsEvent, Avatar, Identifier (+17 more)

### Community 3 - "EasingType"
Cohesion: 0.05
Nodes (36): EasingType, BEZIER, CATMULLROM, CONSTANT, EASE_IN_BACK, EASE_IN_BOUNCE, EASE_IN_CIRC, EASE_IN_CUBIC (+28 more)

### Community 4 - "AnimationController"
Cohesion: 0.09
Nodes (4): AnimationController, MochaEngine, Nullable, KeyframeLocation

### Community 5 - "LevelRendererMixin.java"
Cohesion: 0.11
Nodes (21): DeltaTracker, Entity, Frustum, LevelRenderState, ModifyExpressionValue, CallbackInfo, Camera, EntityRenderState (+13 more)

### Community 6 - "BezierEasing"
Cohesion: 0.11
Nodes (16): FloatFunction3, BezierEasing, Expression, Float2FloatFunction, MochaEngine, Override, CatmullRomEasing, Expression (+8 more)

### Community 7 - "PlayerAnimatorLoader.java"
Cohesion: 0.13
Nodes (12): TransformType, BEND, POSITION, ROTATION, SCALE, Expression, Gson, JsonDeserializationContext (+4 more)

### Community 8 - "ExtraAnimationData"
Cohesion: 0.11
Nodes (12): ByteBuffer, ExtraAnimationData, JsonElement, JsonObject, NotNull, Nullable, Override, AnimationFormat (+4 more)

### Community 9 - "ItemInHandLayerMixin.java"
Cohesion: 0.15
Nodes (20): ArmedEntityRenderState, EntityType, LivingEntity, AvatarMixin, Identifier, Level, Mixin, Override (+12 more)

### Community 10 - "AbstractFadeModifier"
Cohesion: 0.13
Nodes (7): AbstractFadeModifier, EasingFunction, FunctionalInterface, Override, FadeType, FADE_IN, FADE_OUT

### Community 11 - "ParticleKeyframeData"
Cohesion: 0.10
Nodes (12): CustomKeyFrameEvents, CustomKeyFrameHandler, FunctionalInterface, ResetKeyFramesHandler, CustomInstructionKeyframeData, Override, Override, ParticleKeyframeData (+4 more)

### Community 12 - "AnimationBinary"
Cohesion: 0.19
Nodes (4): AnimationBinary, ByteBuf, ByteBuf, NetworkUtils

### Community 13 - "AnimationLoader.java"
Cohesion: 0.21
Nodes (8): FloatObjectPair, AnimationLoader, Expression, JsonArray, JsonDeserializationContext, JsonElement, JsonObject, Type

### Community 14 - "IAnimation"
Cohesion: 0.15
Nodes (7): Pair, IAnimatedAvatar, Identifier, AnimationStack, NotNull, Override, IAnimation

### Community 15 - "Player Animation Core - Developer Documentation"
Cohesion: 0.09
Nodes (22): Adding the Dependency, Bone Names, Complete Example: Combat Animation Mod, Creating Animation Files, Easing Types, EmoteCraft Format (recommended), Fade Modifiers (transitions between animations), First-Person Mode (THIRD_PERSON_MODEL) (+14 more)

### Community 17 - "RawAnimation"
Cohesion: 0.17
Nodes (3): Override, RawAnimation, Stage

### Community 18 - "ModifierLayer"
Cohesion: 0.15
Nodes (4): NotNull, Nullable, Override, ModifierLayer

### Community 19 - "PlayerAnimBone"
Cohesion: 0.19
Nodes (4): Internal, Override, PlayerAnimBone, Vector3f

### Community 20 - "Animation"
Cohesion: 0.18
Nodes (5): Animation, NotNull, Override, restartFromTick(), shouldPlayAgain()

### Community 21 - "Keyframe"
Cohesion: 0.19
Nodes (6): Expression, Override, Keyframe, Expression, AnimationBinaryV6, ByteBuf

### Community 23 - "JsonUtil"
Cohesion: 0.27
Nodes (6): Contract, JsonArray, JsonElement, JsonObject, Nullable, JsonUtil

### Community 24 - "Player Animation Core - Changelog"
Cohesion: 0.11
Nodes (17): 0.0.0-beta.10 (2026-07-09), 0.0.0-beta.12 (2026-07-09), 0.0.0-beta.13 (2026-07-09), 0.0.0-beta.14 (2026-07-09), 0.0.0-beta.15 (2026-07-09), 0.0.0-beta.16 (2026-07-09), 0.0.0-beta.17 (2026-07-09), 0.0.0-beta.18 (2026-07-09) (+9 more)

### Community 25 - "PlayerModelMixin.java"
Cohesion: 0.23
Nodes (10): HumanoidModel, AvatarRenderState, CallbackInfo, Inject, Mixin, ModelPart, Override, PoseStack (+2 more)

### Community 26 - "CapeLayerMixin.java"
Cohesion: 0.19
Nodes (14): RenderLayer, CapeLayerMixin, AvatarRenderState, CallbackInfo, Inject, Mixin, PoseStack, SubmitNodeCollector (+6 more)

### Community 27 - "EasingType.java"
Cohesion: 0.17
Nodes (11): back(), bounce(), easeIn(), easeInOut(), easeOut(), elastic(), fromId(), Float2FloatFunction (+3 more)

### Community 28 - "PlayerCapeModelMixin.java"
Cohesion: 0.20
Nodes (9): IBoneUpdater, ModelPart, AvatarRenderState, CallbackInfo, Inject, Mixin, ModelPart, Override (+1 more)

### Community 29 - "AnimationController.java"
Cohesion: 0.19
Nodes (4): MochaEngine, PivotBone, Override, Vec3f

### Community 31 - "ItemInHandRendererMixin.java"
Cohesion: 0.36
Nodes (10): ItemDisplayContext, ItemInHandRendererMixin, CallbackInfo, Inject, ItemStack, LivingEntity, LocalPlayer, Mixin (+2 more)

### Community 32 - "AbstractModifier"
Cohesion: 0.18
Nodes (4): Internal, AbstractModifier, Nullable, Override

### Community 34 - "AvatarAnimManager"
Cohesion: 0.23
Nodes (4): AvatarAnimManager, Avatar, Avatar, PlayerAnimationRegisterEvent

### Community 35 - "BoneChannel"
Cohesion: 0.12
Nodes (15): Axis, X, Y, Z, BoneChannel, BEND, POSITION_X, POSITION_Y (+7 more)

### Community 36 - "IAvatarAnimationState"
Cohesion: 0.19
Nodes (5): IAvatarAnimationState, AvatarRenderStateMixin, Mixin, NotNull, Override

### Community 37 - "PlayerAnimCommands.java"
Cohesion: 0.32
Nodes (5): CommandBuildContext, CommandDispatcher, CommandSourceStack, CommandContext, PlayerAnimCommands

### Community 38 - "PlayerAnimResources.java"
Cohesion: 0.26
Nodes (8): PreparationBarrier, ResourceManager, ResourceManagerReloadListener, SharedState, Identifier, NotNull, Nullable, PlayerAnimResources

### Community 39 - "FirstPersonMode"
Cohesion: 0.16
Nodes (8): NotNull, FirstPersonMode, DISABLED, HANDS_ONLY, HANDS_ONLY_ARM, NONE, THIRD_PERSON_MODEL, VANILLA

### Community 40 - "Flujo de trabajo — Player Animation Core (NeoForge)"
Cohesion: 0.15
Nodes (12): Buenas prácticas, Commits (Conventional Commits), Convenciones de nomenclatura, Específico del mod, Estructura del proyecto, Flujo de trabajo — Player Animation Core (NeoForge), Flujo por tarea, Idioma (+4 more)

### Community 41 - "RenderUtil.java"
Cohesion: 0.27
Nodes (6): PartPose, Pose, ModelPart, ModelPart, PoseStack, RenderUtil

### Community 42 - "Override"
Cohesion: 0.21
Nodes (3): InternalAnimationAccessor, NotNull, Override

### Community 44 - "KeyFrameLoader.java"
Cohesion: 0.33
Nodes (7): JsonDeserializer, Keyframes, JsonDeserializationContext, JsonElement, JsonObject, Type, KeyFrameLoader

### Community 45 - "UniversalAnimLoader.java"
Cohesion: 0.32
Nodes (6): Pattern, JsonDeserializationContext, JsonElement, JsonObject, Type, UniversalAnimLoader

### Community 46 - "PlayerAnimationFactory.java"
Cohesion: 0.36
Nodes (7): DataHolder, FactoryHolder, Avatar, Identifier, Internal, Nullable, PlayerAnimationFactory

### Community 47 - "LoopType"
Cohesion: 0.20
Nodes (4): FunctionalInterface, JsonElement, LoopType, QueuedAnimation

### Community 48 - "ItemInHandLayerMixin.java"
Cohesion: 0.33
Nodes (9): ItemInHandLayerMixin, CallbackInfo, HumanoidArm, Inject, ItemStack, ItemStackRenderState, Mixin, PoseStack (+1 more)

### Community 49 - "HeaderFlag"
Cohesion: 0.18
Nodes (8): HeaderFlag, APPLY_BEND, EASE_BEFORE, HAS_BEGIN_TICK, HAS_END_TICK, HOLD_ON_LAST_FRAME, PLAYER_ANIMATOR, SHOULD_PLAY_AGAIN

### Community 50 - "MochaMathExtensions.java"
Cohesion: 0.38
Nodes (6): Binding, ObjectValue, Nullable, ObjectProperty, Value, MochaMathExtensions

### Community 51 - "LivingEntityRendererMixin.java"
Cohesion: 0.33
Nodes (7): CameraRenderState, CallbackInfo, Inject, Mixin, PoseStack, SubmitNodeCollector, LivingEntityRendererMixin

### Community 52 - "PlayerAnimCore"
Cohesion: 0.31
Nodes (6): Logger, Gson, Type, PlayerAnimCore, JsonObject, ParticleEffectUtils

### Community 54 - "BoneAnimation"
Cohesion: 0.27
Nodes (4): Nullable, BoneAnimation, Override, getKeyframes()

### Community 55 - ".handleAnimation"
Cohesion: 0.27
Nodes (6): AnimationSetter, AnimationStateHandler, FunctionalInterface, PlayState, CONTINUE, STOP

### Community 56 - "SoundKeyframeData"
Cohesion: 0.24
Nodes (3): AutoPlayingSoundKeyframeHandler, Override, SoundKeyframeData

### Community 57 - "AvatarRendererMixin.java"
Cohesion: 0.31
Nodes (7): Internal, AvatarRendererMixin, Avatar, AvatarRenderState, CallbackInfo, Inject, Mixin

### Community 59 - "ElytraLayerMixin.java"
Cohesion: 0.33
Nodes (7): ElytraLayerMixin, CallbackInfo, HumanoidRenderState, Inject, Mixin, PoseStack, SubmitNodeCollector

### Community 60 - "KeyframeFlag.java"
Cohesion: 0.27
Nodes (9): flagBitsForVersion(), KeyframeFlag, HAS_EASING_ARGS, IS_CONSTANT, LENGTH_ONE, LENGTH_ZERO, pack(), unpackEasing() (+1 more)

### Community 61 - "PlayerAnimCoreService"
Cohesion: 0.28
Nodes (4): AdvancedService, Override, PlayerAnimCoreServiceImpl, PlayerAnimCoreService

### Community 63 - "Player Animation Core"
Cohesion: 0.29
Nodes (6): Bug fixes, Features, License, Links, Player Animation Core, Version: 0.0.0-beta.1

### Community 65 - "AnimationSnapshot"
Cohesion: 0.43
Nodes (3): AnimationSnapshot, NotNull, Override

### Community 66 - "MirrorModifier"
Cohesion: 0.33
Nodes (4): Override, MirrorIfLeftHandModifier, Override, MirrorModifier

### Community 67 - "AnimationArgumentProvider.java"
Cohesion: 0.48
Nodes (5): AnimationArgumentProvider, CommandContext, SuggestionProvider, Suggestions, SuggestionsBuilder

### Community 68 - "CurseForge — Variables del proyecto"
Cohesion: 0.33
Nodes (5): CurseForge — Variables del proyecto, Parámetros del upload, Proyecto, Tokens, Versión actual

### Community 70 - "apply"
Cohesion: 0.40
Nodes (6): apply(), buildTransformer(), Expression, MochaEngine, Override, lerpWithOverride()

### Community 71 - "State"
Cohesion: 0.33
Nodes (4): State, PAUSED, RUNNING, STOPPED

### Community 73 - "CLAUDE.md — player_animation_core (26.1.2)"
Cohesion: 0.50
Nodes (3): CLAUDE.md — player_animation_core (26.1.2), Prioridad de instrucciones, Workflow del mod

### Community 74 - "Player Animation Core v0.0.0-beta.17 — Render Layer Hotfix"
Cohesion: 0.50
Nodes (3): Fixes, New, Player Animation Core v0.0.0-beta.17 — Render Layer Hotfix

### Community 75 - "Player Animation Core v0.0.0-beta.18 — HANDS_ONLY_ARM"
Cohesion: 0.50
Nodes (3): FirstPerson modes summary, New, Player Animation Core v0.0.0-beta.18 — HANDS_ONLY_ARM

### Community 76 - "Player Animation Core v0.0.0-beta.19 — Configurable Arm Rotation Scale"
Cohesion: 0.50
Nodes (3): Changes, Player Animation Core v0.0.0-beta.19 — Configurable Arm Rotation Scale, Quick example

### Community 77 - "Player Animation Core v0.0.0-beta.20 — Hand Position from Arm Rotation"
Cohesion: 0.50
Nodes (3): Changes, Example, Player Animation Core v0.0.0-beta.20 — Hand Position from Arm Rotation

### Community 78 - "gradlew"
Cohesion: 0.83
Nodes (3): gradlew script, die(), warn()

### Community 80 - "fromJson"
Cohesion: 0.67
Nodes (3): fromJson(), fromString(), JsonElement

## Knowledge Gaps
- **143 isolated node(s):** `NONE`, `VANILLA`, `THIRD_PERSON_MODEL`, `HANDS_ONLY`, `HANDS_ONLY_ARM` (+138 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **11 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `AnimationController` connect `AnimationController` to `AnimationData`, `QueryBinding`, `PlayerAnimationController.java`, `EasingType`, `AbstractFadeModifier`, `ParticleKeyframeData`, `IAnimation`, `FirstPersonConfiguration`, `RawAnimation`, `PlayerAnimBone`, `Animation`, `AnimationController.java`, `AbstractModifier`, `AdvancedPlayerAnimBone`, `PlayerAnimCommands.java`, `FirstPersonMode`, `Override`, `LoopType`, `.handleAnimation`, `SoundKeyframeData`, `KeyFrameData`, `.processCurrentAnimation`, `HumanoidAnimationController`, `State`?**
  _High betweenness centrality (0.220) - this node is a cross-community bridge._
- **Why does `PlayerAnimBone` connect `PlayerAnimBone` to `AnimationData`, `AnimationController`, `ItemInHandLayerMixin.java`, `AbstractFadeModifier`, `IAnimation`, `FirstPersonConfiguration`, `ModifierLayer`, `PlayerModelMixin.java`, `PlayerCapeModelMixin.java`, `AnimationController.java`, `ToggleablePlayerAnimBone`, `ItemInHandRendererMixin.java`, `AdvancedPlayerAnimBone`, `FirstPersonMode`, `RenderUtil.java`, `Override`, `LivingEntityRendererMixin.java`, `Matrix4f`, `ElytraLayerMixin.java`, `AnimationSnapshot`, `MirrorModifier`, `HumanoidAnimationController`?**
  _High betweenness centrality (0.190) - this node is a cross-community bridge._
- **Why does `EasingType` connect `EasingType` to `AnimationController`, `BezierEasing`, `apply`, `PlayerAnimatorLoader.java`, `AbstractFadeModifier`, `AnimationLoader.java`, `fromJson`, `Keyframe`, `EasingType.java`, `AnimationController.java`?**
  _High betweenness centrality (0.120) - this node is a cross-community bridge._
- **What connects `NONE`, `VANILLA`, `THIRD_PERSON_MODEL` to the rest of the system?**
  _143 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `AnimationData` be split into smaller, more focused modules?**
  _Cohesion score 0.05081585081585081 - nodes in this community are weakly interconnected._
- **Should `QueryBinding` be split into smaller, more focused modules?**
  _Cohesion score 0.059027777777777776 - nodes in this community are weakly interconnected._
- **Should `PlayerAnimationController.java` be split into smaller, more focused modules?**
  _Cohesion score 0.08084163898117387 - nodes in this community are weakly interconnected._