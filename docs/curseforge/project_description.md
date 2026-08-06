<h1 align="center">🎬 Player Animation Core</h1>

<p align="center"><strong>Keyframe-based animation library for NeoForge — powering custom player animations in Minecraft.</strong></p>

<br>

---

<br>

<h2>✨ Overview</h2>

<p>Player Animation Core (PAC) is a library mod for NeoForge that provides a flexible, layered animation system for player models. It powers custom animations for mods like <strong>Dinamyc Combat</strong>, allowing smooth, keyframe-based animations for combat, movement, and more — with full first-person support.</p>

<br>

<h2>🎯 Features</h2>

<h3>🎞️ Keyframe Animation System</h3>
<p>Define animations in JSON using GeckoLib-style keyframes. Full control over bone transforms: position, rotation, scale per keyframe with configurable easing.</p>

<h3>🧩 Layered Animation Stacking</h3>
<p>Multiple animations run simultaneously with priority layering. Higher-priority animations override lower ones per-bone, enabling complex blends like walking + attacking.</p>

<h3>👁️ Four First-Person Modes</h3>
<p><strong>THIRD_PERSON_MODEL</strong> — Full 3D player model rendered in first person with configurable part visibility.<br>
<strong>HANDS_ONLY</strong> — Item bone transforms applied on top of vanilla first-person hands.<br>
<strong>HANDS_ONLY_ARM</strong> — Arm bone rotations (configurable scale) applied to vanilla first-person item rendering. Matches third-person arm swing without extra keyframes.<br>
<strong>VANILLA</strong> — Standard Minecraft first-person view.</p>

<h3>⚙️ FirstPersonConfiguration</h3>
<p>Control visibility of arms, items, and armor per-animation. Configurable arm rotation scale, arm length, and pitch factor for per-weapon tuning.</p>

<h3>🦴 Bone Transform API</h3>
<p>Query and apply transforms to any body part: head, torso, arms, legs, right/left item. Automatically mirror animations for left-hand rendering with <code>MirrorIfLeftHandModifier</code>.</p>

<h3>📦 Lightweight</h3>
<p>Designed as a library — zero visual changes on its own. Only affects rendering when another mod triggers an animation. Perfect dependency for combat, parkour, emote, or any animation mod.</p>

<br>

<h2>📋 Requirements</h2>

<table>
<tr><td><strong>Minecraft</strong></td><td>26.2</td></tr>
<tr><td><strong>NeoForge</strong></td><td>26.2.0.32-beta+</td></tr>
</table>

<br>

<h2>🎮 For Mod Developers</h2>

<p>Add PAC as a dependency in your <code>build.gradle</code>:</p>

<pre><code>repositories {
    maven {
        url "https://gitlab.com/api/v4/projects/stalking-dragons%2Fminecraft%2Fplayer-animation-core/packages/maven"
    }
}

dependencies {
    implementation "com.skd:player_animation_core:0.0.0-beta.1"
}
</code></pre>

<p>Register an animation factory:</p>

<pre><code>PlayerAnimationFactory.ANIMATION_DATA_FACTORY.registerFactory(
    Identifier.of("yourmod", "combat"), 1000, YourController::new);
</code></pre>

<p>Trigger animations:</p>

<pre><code>var layer = PlayerAnimationAccess.getPlayerAnimationLayer(avatar, FACTORY_ID);
if (layer instanceof PlayerAnimationController controller) {
    controller.triggerAnimation(animId);
}
</code></pre>

<p>Override first-person config:</p>

<pre><code>@Override
public FirstPersonMode getFirstPersonMode() {
    return FirstPersonMode.HANDS_ONLY_ARM;
}

@Override
public FirstPersonConfiguration getFirstPersonConfiguration() {
    return new FirstPersonConfiguration()
        .setArmRotationScale(1.0f)
        .setShowArmor(true);
}
</code></pre>

<p>See the <a href="https://gitlab.com/stalking-dragons/minecraft/player-animation-core/-/blob/production/docs/DEVELOPER.md">full API reference</a> for details.</p>

<br>

---

<br>

<h2>🔧 Compatible Mods</h2>

<p><strong>Dinamyc Combat</strong> — Custom weapon attack animations with first-person support.<br>
<em>Your mod here — PAC is designed for integration.</em></p>

<br>

<h2>🙏 Credits</h2>

<p>Based on <strong>Player Animation Lib</strong> by <em>Kosmx</em>.<br>
Ported and extended for NeoForge 26.2 by <strong>Stalking Dragons</strong>.</p>

<br>
<br>

<p align="center">
  <a href="https://codex.skdragons.com/" target="_blank">
    <img src="https://node-files.skdragons.com/logo_codex_stalking_dragons.png" alt="Codex Stalking Dragons" width="200">
  </a>
  <br>
  <a href="https://codex.skdragons.com/">https://codex.skdragons.com/</a>
  <br>
  <em>Codex Stalking Dragons — Minecraft Modding</em>
</p>
