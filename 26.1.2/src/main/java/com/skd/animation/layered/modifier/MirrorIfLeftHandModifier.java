package com.skd.playeranimationcore.animation.layered.modifier;

import com.skd.playeranimationcore.animation.PlayerAnimationController;
import com.skd.playeranimationcore.animation.layered.modifier.MirrorModifier;
import com.skd.playeranimationcore.bones.PlayerAnimBone;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.HumanoidArm;
import org.jetbrains.annotations.NotNull;

public class MirrorIfLeftHandModifier extends MirrorModifier {
   @Override
   public void get3DTransform(@NotNull PlayerAnimBone bone) {
      if (this.getController() instanceof PlayerAnimationController controller
         && controller.getAvatar() == Minecraft.getInstance().player
         && Minecraft.getInstance().options.mainHand().get() == HumanoidArm.LEFT) {
         return;
      }

      super.get3DTransform(bone);
   }
}
