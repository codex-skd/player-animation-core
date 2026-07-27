package com.skd.playeranimationcore.util;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.PoseStack.Pose;
import com.skd.playeranimationcore.bones.PlayerAnimBone;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;

public final class RenderUtil {
   public static void rotateMatrixAroundBone(PoseStack poseStack, PlayerAnimBone bone) {
      rotateZYX(poseStack.last(), bone.rotation.z, bone.rotation.y, bone.rotation.x);
   }

   public static void translatePartToCape(ModelPart part, PlayerAnimBone bone, PartPose initialPose) {
      part.x = bone.position.x + initialPose.x();
      part.y = -(bone.position.y + initialPose.y());
      part.z = bone.position.z + initialPose.z();
      part.xRot = bone.rotation.x;
      part.yRot = bone.rotation.y;
      part.zRot = bone.rotation.z;
      part.xScale = bone.scale.x;
      part.yScale = bone.scale.y;
      part.zScale = bone.scale.z;
   }

   public static void translatePartToBone(ModelPart part, PlayerAnimBone bone, PartPose initialPose) {
      part.x = bone.position.x + initialPose.x();
      part.y = -bone.position.y + initialPose.y();
      part.z = bone.position.z + initialPose.z();
      part.xRot = bone.rotation.x;
      part.yRot = bone.rotation.y + initialPose.yRot();
      part.zRot = bone.rotation.z;
      part.xScale = bone.scale.x;
      part.yScale = bone.scale.y;
      part.zScale = bone.scale.z;
   }

   public static void translateMatrixToBone(PoseStack poseStack, PlayerAnimBone bone) {
      poseStack.translate(bone.position.x / 16.0F, bone.position.y / 16.0F, bone.position.z / 16.0F);
      rotateMatrixAroundBone(poseStack, bone);
      poseStack.scale(bone.scale.x, bone.scale.y, bone.scale.z);
   }

   public static PlayerAnimBone copyVanillaPart(ModelPart part, PlayerAnimBone bone) {
      PartPose initialPose = part.getInitialPose();
      bone.position.set(part.x - initialPose.x(), -(part.y - initialPose.y()), part.z - initialPose.z());
      bone.rotation.set(part.xRot, part.yRot, part.zRot);
      bone.scale.set(part.xScale, part.yScale, part.zScale);
      bone.bend = 0.0F;
      return bone;
   }

   public static void rotateZYX(Pose matrices, float angleZ, float angleY, float angleX) {
      matrices.pose().rotateZYX(angleZ, angleY, angleX);
      matrices.normal().rotateZYX(angleZ, angleY, angleX);
   }
}
