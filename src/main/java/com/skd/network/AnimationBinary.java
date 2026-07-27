package com.skd.playeranimationcore.network;

import com.skd.playeranimationcore.animation.Animation;
import com.skd.playeranimationcore.animation.ExtraAnimationData;
import com.skd.playeranimationcore.animation.keyframe.BoneAnimation;
import com.skd.playeranimationcore.animation.keyframe.Keyframe;
import com.skd.playeranimationcore.animation.keyframe.KeyframeStack;
import com.skd.playeranimationcore.animation.keyframe.event.data.CustomInstructionKeyframeData;
import com.skd.playeranimationcore.animation.keyframe.event.data.ParticleKeyframeData;
import com.skd.playeranimationcore.animation.keyframe.event.data.SoundKeyframeData;
import com.skd.playeranimationcore.easing.EasingType;
import com.skd.playeranimationcore.enums.AnimationFormat;
import com.skd.playeranimationcore.enums.TransformType;
import com.skd.playeranimationcore.loading.PlayerAnimatorLoader;
import com.skd.playeranimationcore.math.Vec3f;
import com.skd.playeranimationcore.molang.MolangLoader;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import team.unnamed.mocha.parser.ast.Expression;
import team.unnamed.mocha.parser.ast.FloatExpression;
import team.unnamed.mocha.runtime.IsConstantExpression;
import team.unnamed.mocha.util.ExprBytesUtils;
import team.unnamed.mocha.util.network.ProtocolUtils;
import team.unnamed.mocha.util.network.VarIntUtils;

public final class AnimationBinary {
   public static int getCurrentVersion() {
      return 6;
   }

   public static void write(ByteBuf buf, Animation animation) {
      write(buf, getCurrentVersion(), animation);
   }

   public static void write(ByteBuf buf, int version, Animation animation) {
      if (version >= 6) {
         AnimationBinaryV6.write(buf, version, animation);
      } else {
         Map<String, Object> data = animation.data().data();
         boolean applyBendToOtherBones = (Boolean)data.getOrDefault("applyBendToOtherBones", false);
         if (version < 3
            && applyBendToOtherBones
            && animation.boneAnimations().containsKey("torso")
            && !animation.boneAnimations().get("torso").bendKeyFrames().isEmpty()) {
            applyBendToOtherBones = false;
         }

         buf.writeFloat(animation.length());
         boolean shouldPlayAgain = animation.loopType().shouldPlayAgain(null, animation);
         buf.writeBoolean(shouldPlayAgain);
         if (shouldPlayAgain) {
            if (animation.loopType() == Animation.LoopType.HOLD_ON_LAST_FRAME) {
               buf.writeBoolean(true);
            } else {
               buf.writeBoolean(false);
               buf.writeFloat(animation.loopType().restartFromTick(null, animation));
            }
         }

         buf.writeByte(((AnimationFormat)data.getOrDefault("format", AnimationFormat.GECKOLIB)).id);
         buf.writeFloat((Float)data.getOrDefault("beginTick", Float.NaN));
         buf.writeFloat((Float)data.getOrDefault("endTick", Float.NaN));
         if (version > 1) {
            buf.writeBoolean(applyBendToOtherBones);
            buf.writeBoolean((Boolean)data.getOrDefault("easeBeforeKeyframe", true));
         }

         NetworkUtils.writeUuid(buf, animation.uuid());
         VarIntUtils.writeVarInt(buf, animation.boneAnimations().size());

         for (Entry<String, BoneAnimation> entry : animation.boneAnimations().entrySet()) {
            ProtocolUtils.writeString(buf, entry.getKey());
            writeBoneAnimation(
               buf, entry.getValue(), version < 4 && entry.getKey().equals("body"), version < 5 && LegacyAnimationBinary.ITEM_BONE.test(entry.getKey())
            );
         }

         writeEventKeyframes(buf, animation.keyFrames());
         NetworkUtils.writeMap(buf, animation.bones(), ProtocolUtils::writeString, NetworkUtils::writeVec3f);
         NetworkUtils.writeMap(buf, animation.parents(), ProtocolUtils::writeString, ProtocolUtils::writeString);
      }
   }

   public static void writeBoneAnimation(ByteBuf buf, BoneAnimation bone, boolean isBody, boolean isItem) {
      writeKeyframeStack(buf, bone.rotationKeyFrames(), isBody, false, TransformType.ROTATION);
      writeKeyframeStack(buf, bone.positionKeyFrames(), isBody, isItem, TransformType.POSITION);
      writeKeyframeStack(buf, bone.scaleKeyFrames(), false, false, TransformType.SCALE);
      ProtocolUtils.writeList(buf, bone.bendKeyFrames(), AnimationBinary::writeKeyframe);
   }

   public static void writeKeyframeStack(ByteBuf buf, KeyframeStack stack, boolean isBody, boolean isItem, TransformType type) {
      ProtocolUtils.writeList(buf, isBody ? negateKeyframes(stack.xKeyframes()) : stack.xKeyframes(), AnimationBinary::writeKeyframe);
      ProtocolUtils.writeList(
         buf, !isItem && (!isBody || type != TransformType.ROTATION) ? stack.yKeyframes() : negateKeyframes(stack.yKeyframes()), AnimationBinary::writeKeyframe
      );
      ProtocolUtils.writeList(buf, stack.zKeyframes(), AnimationBinary::writeKeyframe);
   }

   public static void writeKeyframe(Keyframe keyframe, ByteBuf buf) {
      buf.writeFloat(keyframe.length());
      ExprBytesUtils.writeExpressions(keyframe.endValue(), buf);
      buf.writeByte(keyframe.easingType().id);
      ProtocolUtils.writeList(buf, keyframe.easingArgs(), ExprBytesUtils::writeExpressions);
   }

   static void writeEventKeyframes(ByteBuf buf, Animation.Keyframes keyFrames) {
      VarIntUtils.writeVarInt(buf, keyFrames.sounds().length);

      for (SoundKeyframeData soundKeyframe : keyFrames.sounds()) {
         buf.writeFloat(soundKeyframe.getStartTick());
         ProtocolUtils.writeString(buf, soundKeyframe.getSound());
      }

      VarIntUtils.writeVarInt(buf, keyFrames.particles().length);

      for (ParticleKeyframeData particleKeyframe : keyFrames.particles()) {
         buf.writeFloat(particleKeyframe.getStartTick());
         ProtocolUtils.writeString(buf, particleKeyframe.getEffect());
         ProtocolUtils.writeString(buf, particleKeyframe.getLocator());
         ProtocolUtils.writeString(buf, particleKeyframe.script());
      }

      VarIntUtils.writeVarInt(buf, keyFrames.customInstructions().length);

      for (CustomInstructionKeyframeData instructionKeyframe : keyFrames.customInstructions()) {
         buf.writeFloat(instructionKeyframe.getStartTick());
         ProtocolUtils.writeString(buf, instructionKeyframe.getInstructions());
      }
   }

   public static Animation read(ByteBuf buf) {
      return read(buf, getCurrentVersion());
   }

   public static Animation read(ByteBuf buf, int version) {
      if (version >= 6) {
         return AnimationBinaryV6.read(buf, version);
      } else {
         float length = buf.readFloat();
         Animation.LoopType loopType = Animation.LoopType.PLAY_ONCE;
         if (buf.readBoolean()) {
            if (buf.readBoolean()) {
               loopType = Animation.LoopType.HOLD_ON_LAST_FRAME;
            } else {
               loopType = Animation.LoopType.returnToTickLoop(buf.readFloat());
            }
         }

         ExtraAnimationData data = new ExtraAnimationData();
         AnimationFormat format = AnimationFormat.fromId(buf.readByte());
         data.put("format", format);
         float beginTick = buf.readFloat();
         float endTick = buf.readFloat();
         if (!Float.isNaN(beginTick)) {
            data.put("beginTick", beginTick);
         }

         if (!Float.isNaN(endTick)) {
            data.put("endTick", endTick);
         }

         if (version > 1) {
            boolean applyBendToOtherBones = buf.readBoolean();
            boolean easeBefore = buf.readBoolean();
            if (applyBendToOtherBones) {
               data.put("applyBendToOtherBones", true);
            }

            if (!easeBefore) {
               data.put("easeBeforeKeyframe", false);
            }
         } else {
            data.put("applyBendToOtherBones", true);
         }

         data.put("uuid", NetworkUtils.readUuid(buf));
         Map<String, BoneAnimation> boneAnimations = NetworkUtils.readMap(
            buf, ProtocolUtils::readString, buf1 -> readBoneAnimation(buf1, format == AnimationFormat.PLAYER_ANIMATOR)
         );
         if (version < 4 && boneAnimations.containsKey("body")) {
            BoneAnimation body = boneAnimations.get("body");
            body.positionKeyFrames().xKeyframes().replaceAll(AnimationBinary::negateKeyframeExpressions);
            body.rotationKeyFrames().xKeyframes().replaceAll(AnimationBinary::negateKeyframeExpressions);
            body.rotationKeyFrames().yKeyframes().replaceAll(AnimationBinary::negateKeyframeExpressions);
         }

         if (version < 5) {
            if (boneAnimations.containsKey("right_item")) {
               boneAnimations.get("right_item").positionKeyFrames().yKeyframes().replaceAll(AnimationBinary::negateKeyframeExpressions);
            }

            if (boneAnimations.containsKey("left_item")) {
               boneAnimations.get("left_item").positionKeyFrames().yKeyframes().replaceAll(AnimationBinary::negateKeyframeExpressions);
            }
         }

         Animation.Keyframes keyFrames = readEventKeyframes(buf);
         Map<String, Vec3f> pivotBones = NetworkUtils.readMap(buf, ProtocolUtils::readString, NetworkUtils::readVec3f);
         Map<String, String> parents = NetworkUtils.readMap(buf, ProtocolUtils::readString, ProtocolUtils::readString);
         return new Animation(data, length, loopType, boneAnimations, keyFrames, pivotBones, parents);
      }
   }

   public static BoneAnimation readBoneAnimation(ByteBuf buf, boolean shouldStartFromDefault) {
      KeyframeStack rotationKeyFrames = readKeyframeStack(buf, shouldStartFromDefault, false);
      KeyframeStack positionKeyFrames = readKeyframeStack(buf, shouldStartFromDefault, false);
      KeyframeStack scaleKeyFrames = readKeyframeStack(buf, shouldStartFromDefault, true);
      List<Keyframe> bendKeyFrames = readKeyframeList(buf, shouldStartFromDefault, false);
      return new BoneAnimation(rotationKeyFrames, positionKeyFrames, scaleKeyFrames, bendKeyFrames);
   }

   public static KeyframeStack readKeyframeStack(ByteBuf buf, boolean shouldStartFromDefault, boolean isScale) {
      List<Keyframe> xKeyframes = readKeyframeList(buf, shouldStartFromDefault, isScale);
      List<Keyframe> yKeyframes = readKeyframeList(buf, shouldStartFromDefault, isScale);
      List<Keyframe> zKeyframes = readKeyframeList(buf, shouldStartFromDefault, isScale);
      return new KeyframeStack(xKeyframes, yKeyframes, zKeyframes);
   }

   public static List<Keyframe> readKeyframeList(ByteBuf buf, boolean shouldStartFromDefault, boolean isScale) {
      int count = VarIntUtils.readVarInt(buf);
      List<Keyframe> list = new ArrayList<>(count);

      for (int i = 0; i < count; i++) {
         float length = buf.readFloat();
         List<Expression> endValue = ExprBytesUtils.readExpressions(buf);
         List<Expression> startValue = list.isEmpty()
            ? (shouldStartFromDefault ? (isScale ? PlayerAnimatorLoader.ONE : PlayerAnimatorLoader.ZERO) : endValue)
            : list.getLast().endValue();
         EasingType easingType = EasingType.fromId(buf.readByte());
         List<List<Expression>> easingArgs = ProtocolUtils.readList(buf, ExprBytesUtils::readExpressions);
         list.add(new Keyframe(length, startValue, endValue, easingType, easingArgs));
      }

      return list;
   }

   static Animation.Keyframes readEventKeyframes(ByteBuf buf) {
      int soundCount = VarIntUtils.readVarInt(buf);
      SoundKeyframeData[] sounds = new SoundKeyframeData[soundCount];

      for (int i = 0; i < soundCount; i++) {
         float startTick = buf.readFloat();
         String sound = ProtocolUtils.readString(buf);
         sounds[i] = new SoundKeyframeData(startTick, sound);
      }

      int particleCount = VarIntUtils.readVarInt(buf);
      ParticleKeyframeData[] particles = new ParticleKeyframeData[particleCount];

      for (int i = 0; i < particleCount; i++) {
         float startTick = buf.readFloat();
         String effect = ProtocolUtils.readString(buf);
         String locator = ProtocolUtils.readString(buf);
         String script = ProtocolUtils.readString(buf);
         particles[i] = new ParticleKeyframeData(startTick, effect, locator, script);
      }

      int customInstructionCount = VarIntUtils.readVarInt(buf);
      CustomInstructionKeyframeData[] customInstructions = new CustomInstructionKeyframeData[customInstructionCount];

      for (int i = 0; i < customInstructionCount; i++) {
         float startTick = buf.readFloat();
         String instructions = ProtocolUtils.readString(buf);
         customInstructions[i] = new CustomInstructionKeyframeData(startTick, instructions);
      }

      return new Animation.Keyframes(sounds, particles, customInstructions);
   }

   private static List<Keyframe> negateKeyframes(List<Keyframe> keyframes) {
      List<Keyframe> var1 = new ArrayList<>(keyframes);
      var1.replaceAll(AnimationBinary::negateKeyframeExpressions);
      return var1;
   }

   private static Keyframe negateKeyframeExpressions(Keyframe keyframe) {
      keyframe = new Keyframe(
         keyframe.length(), new ArrayList<>(keyframe.startValue()), new ArrayList<>(keyframe.endValue()), keyframe.easingType(), keyframe.easingArgs()
      );
      negateKeyframeExpressions(keyframe.startValue());
      negateKeyframeExpressions(keyframe.endValue());
      return keyframe;
   }

   private static void negateKeyframeExpressions(List<Expression> expressions) {
      if (expressions.size() == 1 && IsConstantExpression.test(expressions.getFirst())) {
         expressions.set(0, FloatExpression.of(-MolangLoader.MOCHA_ENGINE.eval(expressions)));
      }
   }
}
