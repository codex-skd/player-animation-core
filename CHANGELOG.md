# Player Animation Core - Changelog

## 0.0.0-beta.15 (2026-07-09)
- Fix: brazos extra en primera persona — `ItemInHandRendererMixin` ya no requiere `isActive()` para cancelar vanilla hands; basta con `getFirstPersonMode() == THIRD_PERSON_MODEL`
- Fix: armadura invisible en primera persona — `HumanoidArmorLayerMixin` siempre muestra armadura durante first-person pass, sin consultar `isShowArmor()`
- Fix: animaciones de ataque no se reproducían en el primer golpe — `AnimationController.hasAnimationFinished()` retorna `true` si `currentRawAnimation` es `null`

## 0.0.0-beta.14 (2026-07-09)
- Removed `isActive()` check in `AnimationStack.getFirstPersonMode()` and `getFirstPersonConfiguration()` — now returns config from first applicable layer regardless of active state
- Removed `isActive()` check in `ClientUtil.shouldBeFirstPersonPass()` — `getFirstPersonMode() == THIRD_PERSON_MODEL` is now sufficient

## 0.0.0-beta.13 (2026-07-09)
- Fixed runtime ClassCastException en ElytraLayerMixin y CapeLayerMixin: `WingsLayer`/`CapeLayer` extienden `RenderLayer`, no implementan `RenderLayerParent`. Cambiado cast a `((RenderLayer<?, ?>)(Object)this).getParentModel()`

## 0.0.0-beta.12 (2026-07-09)
- Fixed CapeLayerMixin: replaced `@Shadow getParentModel()` with cast to `RenderLayerParent` (mismo patrón que ElytraLayerMixin — el método está heredado de `RenderLayer`, no declarado en `CapeLayer`)

## 0.0.0-beta.10 (2026-07-09)
- Fixed ElytraLayerMixin: replaced `@Shadow getParentModel()` with cast to `RenderLayerParent` (`getParentModel()` no está declarado en `WingsLayer`, solo heredado de `RenderLayer`)
- Fixed firstPerson.HumanoidArmorLayerMixin: added missing `CallbackInfo ci` parameter in `@Inject` method

## 0.0.0-beta.9 (2026-07-09)
- Fixed AvatarMixin circular dependency: `AvatarMixin` extendía `LivingEntity` mientras targeteaba `LivingEntity.class`. Separado el `tick()` a un mixin independiente (`LivingEntityTickMixin`) que targetea `LivingEntity.class` sin extenderlo. `AvatarMixin` ahora solo targetea `Avatar.class`.
- Added `LivingEntityTickMixin` to `player_animation_core.mixins.json`

## 0.0.0-beta.3 (2026-07-08)
- Fixed HumanoidArmorLayerMixin: updated @Inject method from "renderArmorPiece" to "submit" (method renamed in MC 1.21.5 rendering overhaul)
- Armor visibility fix now properly applies in MC 1.21.5 NeoForge 26.1.2

## 0.0.0-beta.2 (2026-07-08)
- Added developer documentation (DOCS.md) with full API reference and integration examples

## 0.0.0-beta.1 (2026-07-08)
- Initial beta release
- Forked from Player Animation Library, rebranded as Player Animation Core
- Fixed armor visibility bug: armor no longer disappears when using FirstPersonMode.THIRD_PERSON_MODEL
- All original packages renamed to com.skd.playeranimationcore
- NeoForge 26.1.2+ support
