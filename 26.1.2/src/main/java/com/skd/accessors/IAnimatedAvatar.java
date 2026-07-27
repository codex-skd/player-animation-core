package com.skd.playeranimationcore.accessors;

import com.skd.playeranimationcore.animation.AvatarAnimManager;
import com.skd.playeranimationcore.animation.layered.IAnimation;
import net.minecraft.resources.Identifier;

public interface IAnimatedAvatar {
   AvatarAnimManager playerAnimLib$getAnimManager();

   IAnimation playerAnimLib$getAnimation(Identifier var1);
}
