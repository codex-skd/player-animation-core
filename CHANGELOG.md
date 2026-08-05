# Player Animation Core - Changelog

Todas las versiones notables de Player Animation Core (26.2) están documentadas aquí.

El formato está basado en [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
y este proyecto adhiere a [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

Para el historial completo de la versión 26.1.2, ver `player_animation_core/26.1.2/CHANGELOG.md` (rama `minecraft/26.1.2/neoforge-26.1.2.78/production`).
---

## [1.0.2] - 2026-08-05

### Change

- **Recompilado contra NeoForge `26.2.0.37-beta`**: bump de `neo_version` en `gradle.properties` (`26.2.0.32-beta` -> `26.2.0.37-beta`). Verificado con `runServer` (arranque sin errores).

## [1.0.1] - 2026-08-02

### Refactor
- Clase principal `PlayerAnimCoreModNeo` → `PlayerAnimationCore` y `mod_group_id` corregido a `com.skd.playeranimationcore` (residuo del fork "Player Animation Library"). Sin cambios de funcionalidad.

## [1.0.0] - 2026-08-02

### Added
- Primera versión estable de Player Animation Core para Minecraft 26.2 / NeoForge 26.2.0.32-beta.

### Fixed
- Incluye los arreglos de las betas previas: mixin `ItemInHandRendererMixin` apuntando a `submitHandsWithItems` y `LevelRendererMixin` reorientado a `LevelExtractor`.

## [0.0.0-beta.3] - 2026-08-02

### Fixed
- Crash al iniciar: `LevelRendererMixin` apuntaba a `LevelRenderer.extractVisibleEntities`, método movido a la nueva clase `net.minecraft.client.renderer.extract.LevelExtractor` en el refactor de renderizado de Minecraft 26.2 (InvalidInjectionException).

## [0.0.0-beta.2] - 2026-08-02

### Fixed
- Crash al iniciar: el mixin `ItemInHandRendererMixin` apuntaba a `renderHandsWithItems`, método renombrado a `submitHandsWithItems` en Minecraft 26.2 (InvalidInjectionException "could not find any targets").

## [0.0.0-beta.1] - 2026-08-02

### Added
- Port inicial a Minecraft 26.2 / NeoForge 26.2.0.32-beta, partiendo del estado estable de la versión 26.1.2 (`1.0.0`): sistema de animaciones por keyframes, primer-paso con cuatro modos, Molang, stacking por capas y API para mods.
- `GameRenderer.getMainCamera()` eliminado en 26.2 → reemplazado por `GameRenderer.mainCamera()` en `ItemInHandRendererMixin`, `MolangQueries` y `ClientUtil`.
- Build actualizado a `net.neoforged.moddev` `2.0.142`; el JAR final ya no incluye la carpeta `templates/` sin resolver (solo el `neoforge.mods.toml` generado).
