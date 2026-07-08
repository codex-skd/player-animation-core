package com.skd.playeranimationcore.animation;

import com.skd.playeranimationcore.accessors.IAnimatedAvatar;
import com.skd.playeranimationcore.util.RenderUtil;
import com.skd.playeranimationcore.animation.AnimationData;
import com.skd.playeranimationcore.animation.layered.AnimationStack;
import com.skd.playeranimationcore.animation.layered.IAnimation;
import com.skd.playeranimationcore.bones.PlayerAnimBone;
import it.unimi.dsi.fastutil.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.ApiStatus.Internal;

public class AvatarAnimManager extends AnimationStack {
   private final Avatar avatar;
   private float lastUpdateTime;
   private boolean isFirstTick = true;
   private float tickDelta;

   public AvatarAnimManager(Avatar avatar) {
      this.avatar = avatar;
   }

   public void tickAnimation(AnimationStack playerAnimManager, AnimationData state) {
      playerAnimManager.getLayers().removeIf(pairx -> pairx.right() == null || ((IAnimation)pairx.right()).canRemove());

      for (Pair<Integer, IAnimation> pair : playerAnimManager.getLayers()) {
         IAnimation animation = (IAnimation)pair.right();
         if (animation.isActive()) {
            animation.setupAnim(state.copy());
         }
      }

      this.finishFirstTick();
   }

   public float getLastUpdateTime() {
      return this.lastUpdateTime;
   }

   public void updatedAt(float updateTime) {
      this.lastUpdateTime = updateTime;
   }

   public boolean isFirstTick() {
      return this.isFirstTick;
   }

   protected void finishFirstTick() {
      this.isFirstTick = false;
   }

   public float getTickDelta() {
      return this.tickDelta;
   }

   @Internal
   public void setTickDelta(float tickDelta) {
      this.tickDelta = tickDelta;
   }

   public void updatePart(ModelPart part, PlayerAnimBone bone) {
      PartPose initialPose = part.getInitialPose();
      this.get3DTransform(bone);
      RenderUtil.translatePartToBone(part, bone, initialPose);
   }

   public void handleAnimations(float partialTick, boolean fullTick, boolean isFirstPersonPass) {
      Vec3 velocity = this.avatar.getDeltaMovement();
      AvatarAnimManager animatableManager = ((IAnimatedAvatar) this.avatar).playerAnimLib$getAnimManager();
      int currentTick = this.avatar.tickCount;
      float currentFrameTime = (float)currentTick + partialTick;
      AnimationData animationData = new AnimationData((float)((Math.abs(velocity.x) + Math.abs(velocity.z)) / 2.0), partialTick, isFirstPersonPass);
      if (fullTick) {
         animatableManager.tick(animationData.copy());
      }

      if (animatableManager.isFirstTick() || currentFrameTime != animatableManager.getLastUpdateTime()) {
         if (!Minecraft.getInstance().isPaused()) {
            animatableManager.updatedAt(currentFrameTime);
         }

         this.tickAnimation(animatableManager, animationData);
      }
   }

   public Avatar getAvatar() {
      return this.avatar;
   }
}
