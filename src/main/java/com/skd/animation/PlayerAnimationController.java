package com.skd.playeranimationcore.animation;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.skd.playeranimationcore.PlayerAnimCoreMod;
import com.skd.playeranimationcore.util.RenderUtil;
import com.skd.playeranimationcore.animation.AnimationController;
import com.skd.playeranimationcore.animation.HumanoidAnimationController;
import com.skd.playeranimationcore.animation.layered.modifier.AbstractFadeModifier;
import com.skd.playeranimationcore.math.Vec3f;
import com.skd.playeranimationcore.molang.MolangLoader;
import java.util.function.Function;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.unnamed.mocha.MochaEngine;

public class PlayerAnimationController extends HumanoidAnimationController {
   protected final Avatar avatar;

   public PlayerAnimationController(Avatar avatar, AnimationController.AnimationStateHandler animationHandler) {
      this(avatar, animationHandler, MolangLoader::createNewEngine);
   }

   public PlayerAnimationController(
      Avatar avatar, AnimationController.AnimationStateHandler animationHandler, Function<AnimationController, MochaEngine<AnimationController>> molangRuntime
   ) {
      super(animationHandler, molangRuntime);
      this.avatar = avatar;
   }

   public Avatar getAvatar() {
      return this.avatar;
   }

   public boolean triggerAnimation(Identifier newAnimation, float startAnimFrom) {
      if (PlayerAnimResources.hasAnimation(newAnimation)) {
         this.triggerAnimation(PlayerAnimResources.getAnimation(newAnimation), startAnimFrom);
         return true;
      } else {
         PlayerAnimCoreMod.LOGGER.error("Could not find animation with the name:" + newAnimation);
         return false;
      }
   }

   public boolean triggerAnimation(Identifier newAnimation) {
      return this.triggerAnimation(newAnimation, 0.0F);
   }

   public boolean replaceAnimationWithFade(@NotNull AbstractFadeModifier fadeModifier, @Nullable Identifier newAnimation, boolean fadeFromNothing) {
      if (PlayerAnimResources.hasAnimation(newAnimation)) {
         this.replaceAnimationWithFade(fadeModifier, PlayerAnimResources.getAnimation(newAnimation), fadeFromNothing);
         return true;
      } else {
         return false;
      }
   }

   public boolean replaceAnimationWithFade(@NotNull AbstractFadeModifier fadeModifier, @Nullable Identifier newAnimation) {
      return this.replaceAnimationWithFade(fadeModifier, newAnimation, true);
   }

   @Nullable
   public PoseStack getBoneWorldPositionPoseStack(String name, float tickDelta, Vec3 cameraPos) {
      if (!this.activeBones.containsKey(name)) {
         return null;
      } else {
         PoseStack poseStack = new PoseStack();
         Vec3f pivot = this.getBonePosition(name);
         Vec3 position = this.avatar.getPosition(tickDelta).subtract(cameraPos).add((double)pivot.x(), (double)pivot.y(), (double)pivot.z());
         poseStack.translate(position.x(), position.y(), position.z());
         poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - Mth.lerp(tickDelta, this.avatar.yBodyRotO, this.avatar.yBodyRot)));
         RenderUtil.translateMatrixToBone(poseStack, this.activeBones.get(name));
         return poseStack;
      }
   }
}
