package com.skd.playeranimationcore.animation.keyframe.event.builtin;

import com.skd.playeranimationcore.animation.PlayerAnimationController;
import com.skd.playeranimationcore.util.ClientUtil;
import com.skd.playeranimationcore.animation.AnimationController;
import com.skd.playeranimationcore.animation.AnimationData;
import com.skd.playeranimationcore.animation.keyframe.event.CustomKeyFrameEvents;
import com.skd.playeranimationcore.animation.keyframe.event.data.SoundKeyframeData;
import com.skd.playeranimationcore.event.EventResult;
import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.core.Holder.Reference;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.phys.Vec3;

public class AutoPlayingSoundKeyframeHandler implements CustomKeyFrameEvents.CustomKeyFrameHandler<SoundKeyframeData> {
   public EventResult handle(float animationTick, AnimationController controller, SoundKeyframeData keyFrameData, AnimationData animationData) {
      Vec3 position = controller instanceof PlayerAnimationController player ? player.getAvatar().position() : null;
      if (position == null) {
         return EventResult.PASS;
      } else {
         String[] segments = keyFrameData.getSound().split("\\|");
         Identifier rl = Identifier.tryParse(segments[0]);
         if (rl == null) {
            return EventResult.PASS;
         } else {
            Optional<Reference<SoundEvent>> soundEvent = BuiltInRegistries.SOUND_EVENT.get(rl);
            if (soundEvent.isEmpty()) {
               return EventResult.PASS;
            } else {
               float volume = segments.length > 1 ? Float.parseFloat(segments[1]) : 1.0F;
               float pitch = segments.length > 2 ? Float.parseFloat(segments[2]) : 1.0F;
               ClientUtil.getLevel().playSound(null, position.x, position.y, position.z, (Holder)soundEvent.get(), SoundSource.PLAYERS, volume, pitch);
               return EventResult.SUCCESS;
            }
         }
      }
   }
}
