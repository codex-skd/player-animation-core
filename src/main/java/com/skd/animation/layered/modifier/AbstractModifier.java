package com.skd.playeranimationcore.animation.layered.modifier;

import com.skd.playeranimationcore.animation.AnimationController;
import com.skd.playeranimationcore.animation.layered.AnimationContainer;
import com.skd.playeranimationcore.animation.layered.IAnimation;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractModifier extends AnimationContainer<IAnimation> {
   protected IAnimation host;

   public void setHost(IAnimation host) {
      this.host = host;
   }

   @Nullable
   protected AnimationController getController() {
      IAnimation var2 = this.host;
      return var2 instanceof AnimationController ? (AnimationController)var2 : null;
   }

   @Override
   public boolean canRemove() {
      return false;
   }
}
