package com.skd.playeranimationcore.animation.layered;

import com.skd.playeranimationcore.animation.AnimationData;
import com.skd.playeranimationcore.api.firstPerson.FirstPersonConfiguration;
import com.skd.playeranimationcore.api.firstPerson.FirstPersonMode;
import com.skd.playeranimationcore.bones.PlayerAnimBone;
import it.unimi.dsi.fastutil.Pair;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class AnimationStack implements IAnimation {
   protected final List<Pair<Integer, IAnimation>> layers = new ArrayList<>();

   public List<Pair<Integer, IAnimation>> getLayers() {
      return this.layers;
   }

   @Override
   public boolean isActive() {
      for (Pair<Integer, IAnimation> layer : this.layers) {
         if (((IAnimation)layer.right()).isActive()) {
            return true;
         }
      }

      return false;
   }

   @Override
   public void tick(AnimationData state) {
      for (Pair<Integer, IAnimation> layer : this.layers) {
         ((IAnimation)layer.right()).tick(state);
      }
   }

   @Override
   public void get3DTransform(@NotNull PlayerAnimBone bone) {
      for (Pair<Integer, IAnimation> layer : this.layers) {
         if (((IAnimation)layer.right()).isActive()) {
            ((IAnimation)layer.right()).get3DTransform(bone);
         }
      }
   }

   @Override
   public void setupAnim(AnimationData state) {
      for (Pair<Integer, IAnimation> layer : this.layers) {
         if (((IAnimation)layer.right()).isActive()) {
            ((IAnimation)layer.right()).setupAnim(state);
         }
      }
   }

   public void addAnimLayer(int priority, IAnimation layer) {
      int search = 0;

      while (this.layers.size() > search && this.layers.get(search).left() < priority) {
         search++;
      }

      this.layers.add(search, Pair.of(priority, layer));
   }

   public boolean removeLayer(IAnimation layer) {
      return this.layers.removeIf(integerIAnimationPair -> integerIAnimationPair.right() == layer);
   }

   public boolean removeLayer(int layerLevel) {
      return this.layers.removeIf(integerIAnimationPair -> (Integer)integerIAnimationPair.left() == layerLevel);
   }

   @NotNull
   @Override
   public FirstPersonMode getFirstPersonMode() {
      int i = this.layers.size();

      while (i > 0) {
         Pair<Integer, IAnimation> layer = this.layers.get(--i);
         if (((IAnimation)layer.right()).isActive()) {
            FirstPersonMode mode = ((IAnimation)layer.right()).getFirstPersonMode();
            if (mode != FirstPersonMode.NONE) {
               return mode;
            }
         }
      }

      return FirstPersonMode.NONE;
   }

   @NotNull
   @Override
   public FirstPersonConfiguration getFirstPersonConfiguration() {
      int i = this.layers.size();

      while (i > 0) {
         Pair<Integer, IAnimation> layer = this.layers.get(--i);
         if (((IAnimation)layer.right()).isActive()) {
            FirstPersonMode mode = ((IAnimation)layer.right()).getFirstPersonMode();
            if (mode != FirstPersonMode.NONE) {
               return ((IAnimation)layer.right()).getFirstPersonConfiguration();
            }
         }
      }

      return IAnimation.super.getFirstPersonConfiguration();
   }

   public int getPriority() {
      int priority = 0;

      for (int i = this.layers.size() - 1; i >= 0; i--) {
         Pair<Integer, IAnimation> layer = this.layers.get(i);
         if (((IAnimation)layer.right()).isActive()) {
            priority = (Integer)layer.left();
            break;
         }
      }

      return priority;
   }

   @Override
   public String toString() {
      return "AnimationStack{layers=" + this.layers + "}";
   }
}
