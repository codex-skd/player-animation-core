package com.skd.playeranimationcore.bones;

import com.skd.playeranimationcore.animation.Animation;
import com.skd.playeranimationcore.animation.keyframe.BoneAnimation;
import com.skd.playeranimationcore.animation.keyframe.Keyframe;
import com.skd.playeranimationcore.animation.keyframe.KeyframeStack;
import com.skd.playeranimationcore.easing.EasingType;
import com.skd.playeranimationcore.enums.Axis;
import com.skd.playeranimationcore.enums.TransformType;
import java.util.List;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.joml.Vector3f;

public class PlayerAnimBone {
   public final String name;
   public final Vector3f position;
   public final Vector3f rotation;
   public final Vector3f scale;
   @Deprecated(
      forRemoval = true
   )
   public float bend;

   public PlayerAnimBone(String name) {
      this.name = name;
      this.position = new Vector3f();
      this.rotation = new Vector3f();
      this.scale = new Vector3f(1.0F);
   }

   public PlayerAnimBone(PlayerAnimBone bone) {
      this.name = bone.getName();
      this.position = new Vector3f(bone.position);
      this.rotation = new Vector3f(bone.rotation);
      this.scale = new Vector3f(bone.scale);
      this.bend = bone.bend;
   }

   public String getName() {
      return this.name;
   }

   public void setToInitialPose() {
      this.position.set(0.0F, 0.0F, 0.0F);
      this.rotation.set(0.0F, 0.0F, 0.0F);
      this.scale.set(1.0F, 1.0F, 1.0F);
      this.bend = 0.0F;
   }

   public PlayerAnimBone scale(float value) {
      this.position.mul(value);
      this.rotation.mul(value);
      this.scale.mul(value);
      this.bend *= value;
      return this;
   }

   public PlayerAnimBone add(PlayerAnimBone bone) {
      this.position.add(bone.position);
      this.rotation.add(bone.rotation);
      this.scale.add(bone.scale);
      this.bend = this.bend + bone.bend;
      return this;
   }

   public PlayerAnimBone applyOtherBone(PlayerAnimBone bone) {
      this.position.add(bone.position);
      this.rotation.add(bone.rotation);
      this.scale.mul(bone.scale);
      this.bend = this.bend + bone.bend;
      return this;
   }

   public PlayerAnimBone copyOtherBone(PlayerAnimBone bone) {
      this.position.set(bone.position);
      this.rotation.set(bone.rotation);
      this.scale.set(bone.scale);
      this.bend = bone.bend;
      return this;
   }

   public PlayerAnimBone copyOtherBoneIfNotDisabled(PlayerAnimBone bone) {
      if (bone instanceof ToggleablePlayerAnimBone toggleableBone) {
         if (toggleableBone.isPositionXEnabled()) {
            this.position.x = bone.position.x;
         }

         if (toggleableBone.isPositionYEnabled()) {
            this.position.y = bone.position.y;
         }

         if (toggleableBone.isPositionZEnabled()) {
            this.position.z = bone.position.z;
         }

         if (toggleableBone.isRotXEnabled()) {
            this.rotation.x = bone.rotation.x;
         }

         if (toggleableBone.isRotYEnabled()) {
            this.rotation.y = bone.rotation.y;
         }

         if (toggleableBone.isRotZEnabled()) {
            this.rotation.z = bone.rotation.z;
         }

         if (toggleableBone.isScaleXEnabled()) {
            this.scale.x = bone.scale.x;
         }

         if (toggleableBone.isScaleYEnabled()) {
            this.scale.y = bone.scale.y;
         }

         if (toggleableBone.isScaleZEnabled()) {
            this.scale.z = bone.scale.z;
         }

         if (toggleableBone.isBendEnabled()) {
            this.bend = bone.bend;
         }

         return this;
      } else {
         return this.copyOtherBone(bone);
      }
   }

   @Internal
   public void beginOrEndTickLerp(AdvancedPlayerAnimBone bone, float animTime, Animation animation) {
      if (bone.positionXEnabled) {
         this.position.x = this.beginOrEndTickLerp(
            this.position.x, bone.position.x, bone.positionXTransitionLength, animTime, animation, TransformType.POSITION, Axis.X
         );
      }

      if (bone.positionYEnabled) {
         this.position.y = this.beginOrEndTickLerp(
            this.position.y, bone.position.y, bone.positionYTransitionLength, animTime, animation, TransformType.POSITION, Axis.Y
         );
      }

      if (bone.positionZEnabled) {
         this.position.z = this.beginOrEndTickLerp(
            this.position.z, bone.position.z, bone.positionZTransitionLength, animTime, animation, TransformType.POSITION, Axis.Z
         );
      }

      if (bone.rotXEnabled) {
         this.rotation.x = this.beginOrEndTickLerp(
            this.rotation.x, bone.rotation.x, bone.rotXTransitionLength, animTime, animation, TransformType.ROTATION, Axis.X
         );
      }

      if (bone.rotYEnabled) {
         this.rotation.y = this.beginOrEndTickLerp(
            this.rotation.y, bone.rotation.y, bone.rotYTransitionLength, animTime, animation, TransformType.ROTATION, Axis.Y
         );
      }

      if (bone.rotZEnabled) {
         this.rotation.z = this.beginOrEndTickLerp(
            this.rotation.z, bone.rotation.z, bone.rotZTransitionLength, animTime, animation, TransformType.ROTATION, Axis.Z
         );
      }

      if (bone.scaleXEnabled) {
         this.scale.x = this.beginOrEndTickLerp(this.scale.x, bone.scale.x, bone.scaleXTransitionLength, animTime, animation, TransformType.SCALE, Axis.X);
      }

      if (bone.scaleYEnabled) {
         this.scale.y = this.beginOrEndTickLerp(this.scale.y, bone.scale.y, bone.scaleYTransitionLength, animTime, animation, TransformType.SCALE, Axis.Y);
      }

      if (bone.scaleZEnabled) {
         this.scale.z = this.beginOrEndTickLerp(this.scale.z, bone.scale.z, bone.scaleZTransitionLength, animTime, animation, TransformType.SCALE, Axis.Z);
      }

      if (bone.bendEnabled) {
         this.bend = this.beginOrEndTickLerp(this.bend, bone.bend, bone.bendTransitionLength, animTime, animation, TransformType.BEND, Axis.Y);
      }
   }

   private float beginOrEndTickLerp(
      float startValue, float endValue, Float transitionLength, float animTime, Animation animation, TransformType type, Axis axis
   ) {
      EasingType easingType = EasingType.EASE_IN_OUT_SINE;
      if (animation != null) {
         float temp = startValue;
         startValue = endValue;
         endValue = temp;
         if (transitionLength == null) {
            transitionLength = animation.length() - (Float)animation.data().getRaw("endTick");
         }

         if (animation.data().has("easeBeforeKeyframe") && !(Boolean)animation.data().getRaw("easeBeforeKeyframe")) {
            BoneAnimation boneAnimation = animation.getBone(this.getName());
            KeyframeStack var10000;
            if (boneAnimation == null) {
               var10000 = null;
            } else {
               switch (type) {
                  case BEND:
                     List<Keyframe> bendKeyFrames = boneAnimation.bendKeyFrames();
                     if (!bendKeyFrames.isEmpty()) {
                        easingType = bendKeyFrames.getLast().easingType();
                     }

                     var10000 = null;
                     break;
                  case ROTATION:
                     var10000 = boneAnimation.rotationKeyFrames();
                     break;
                  case SCALE:
                     var10000 = boneAnimation.scaleKeyFrames();
                     break;
                  case POSITION:
                     var10000 = boneAnimation.positionKeyFrames();
                     break;
                  default:
                     throw new MatchException(null, null);
               }
            }

            KeyframeStack keyframeStack = var10000;
            if (keyframeStack != null) {
               List<Keyframe> keyFrames = keyframeStack.getKeyFramesForAxis(axis);
               if (!keyFrames.isEmpty()) {
                  easingType = keyFrames.getLast().easingType();
               }
            }
         }

         if (easingType == EasingType.BEZIER || easingType == EasingType.CATMULLROM) {
            easingType = EasingType.EASE_IN_OUT_SINE;
         }
      }

      return transitionLength == null ? endValue : easingType.apply(startValue, endValue, animTime / transitionLength);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else {
         return obj != null && this.getClass() == obj.getClass() ? this.hashCode() == obj.hashCode() : false;
      }
   }

   @Override
   public int hashCode() {
      return this.getName().hashCode();
   }
}
