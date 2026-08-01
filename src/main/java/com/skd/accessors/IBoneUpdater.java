package com.skd.playeranimationcore.accessors;

import com.skd.playeranimationcore.animation.AvatarAnimManager;
import com.skd.playeranimationcore.bones.PlayerAnimBone;
import net.minecraft.client.model.geom.ModelPart;
import org.jetbrains.annotations.Nullable;

public interface IBoneUpdater {
   default void pal$updatePart(AvatarAnimManager emote, ModelPart part, PlayerAnimBone bone) {
   }

   default void pal$resetAll(@Nullable AvatarAnimManager emote) {
   }
}
