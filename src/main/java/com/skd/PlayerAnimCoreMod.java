package com.skd.playeranimationcore;

import com.skd.playeranimationcore.animation.PlayerAnimationController;
import com.skd.playeranimationcore.animation.keyframe.event.builtin.AutoPlayingSoundKeyframeHandler;
import com.skd.playeranimationcore.api.PlayerAnimationFactory;
import com.skd.playeranimationcore.molang.MolangQueries;
import com.skd.playeranimationcore.PlayerAnimCore;
import com.skd.playeranimationcore.animation.keyframe.event.CustomKeyFrameEvents;
import com.skd.playeranimationcore.enums.PlayState;
import com.skd.playeranimationcore.event.MolangEvent;
import net.minecraft.resources.Identifier;

public abstract class PlayerAnimCoreMod extends PlayerAnimCore {
   public static final Identifier ANIMATION_LAYER_ID = id("factory");

   public static Identifier id(String name) {
      return Identifier.fromNamespaceAndPath("player_animation_core", name);
   }

   protected void init() {
      PlayerAnimationFactory.ANIMATION_DATA_FACTORY
         .registerFactory(ANIMATION_LAYER_ID, 1000, player -> new PlayerAnimationController(player, (var0x, var1, var2) -> PlayState.STOP));
      MolangEvent.MOLANG_EVENT.register((var0, var1, queryBinding) -> MolangQueries.setDefaultQueryValues(queryBinding));
      CustomKeyFrameEvents.SOUND_KEYFRAME_EVENT.register(new AutoPlayingSoundKeyframeHandler());
   }
}
