package com.skd.playeranimationcore.animation.keyframe.event;

import com.skd.playeranimationcore.animation.AnimationController;
import com.skd.playeranimationcore.animation.AnimationData;
import com.skd.playeranimationcore.animation.keyframe.event.data.CustomInstructionKeyframeData;
import com.skd.playeranimationcore.animation.keyframe.event.data.KeyFrameData;
import com.skd.playeranimationcore.animation.keyframe.event.data.ParticleKeyframeData;
import com.skd.playeranimationcore.animation.keyframe.event.data.SoundKeyframeData;
import com.skd.playeranimationcore.event.Event;
import com.skd.playeranimationcore.event.EventResult;
import java.util.Set;

public class CustomKeyFrameEvents {
   public static final Event<CustomKeyFrameEvents.CustomKeyFrameHandler<CustomInstructionKeyframeData>> CUSTOM_INSTRUCTION_KEYFRAME_EVENT = new Event<>(
      listeners -> (animationTick, controller, eventKeyFrame, animationData) -> {
            for (CustomKeyFrameEvents.CustomKeyFrameHandler<CustomInstructionKeyframeData> listener : listeners) {
               EventResult result = listener.handle(animationTick, controller, eventKeyFrame, animationData);
               if (result == EventResult.FAIL) {
                  return result;
               }
            }

            return EventResult.PASS;
         }
   );
   public static final Event<CustomKeyFrameEvents.CustomKeyFrameHandler<ParticleKeyframeData>> PARTICLE_KEYFRAME_EVENT = new Event<>(
      listeners -> (animationTick, controller, eventKeyFrame, animationData) -> {
            for (CustomKeyFrameEvents.CustomKeyFrameHandler<ParticleKeyframeData> listener : listeners) {
               EventResult result = listener.handle(animationTick, controller, eventKeyFrame, animationData);
               if (result == EventResult.FAIL) {
                  return result;
               }
            }

            return EventResult.PASS;
         }
   );
   public static final Event<CustomKeyFrameEvents.CustomKeyFrameHandler<SoundKeyframeData>> SOUND_KEYFRAME_EVENT = new Event<>(
      listeners -> (animationTick, controller, eventKeyFrame, animationData) -> {
            for (CustomKeyFrameEvents.CustomKeyFrameHandler<SoundKeyframeData> listener : listeners) {
               EventResult result = listener.handle(animationTick, controller, eventKeyFrame, animationData);
               if (result == EventResult.FAIL) {
                  return result;
               }
            }

            return EventResult.PASS;
         }
   );
   public static final Event<CustomKeyFrameEvents.ResetKeyFramesHandler> RESET_KEYFRAMES_EVENT = new Event<>(listeners -> (controller, eventKeyFrames) -> {
         for (CustomKeyFrameEvents.ResetKeyFramesHandler listener : listeners) {
            listener.handle(controller, eventKeyFrames);
         }
      });

   @FunctionalInterface
   public interface CustomKeyFrameHandler<T extends KeyFrameData> {
      EventResult handle(float var1, AnimationController var2, T var3, AnimationData var4);
   }

   @FunctionalInterface
   public interface ResetKeyFramesHandler {
      void handle(AnimationController var1, Set<KeyFrameData> var2);
   }
}
