package com.skd.playeranimationcore.neoforge.event;

import com.skd.playeranimationcore.animation.AnimationController;
import com.skd.playeranimationcore.molang.MolangLoader;
import com.skd.playeranimationcore.molang.QueryBinding;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import net.neoforged.bus.api.Event;
import team.unnamed.mocha.MochaEngine;

public class MolangEvent extends Event {
   private final AnimationController controller;
   private final MochaEngine<AnimationController> engine;
   private final QueryBinding<AnimationController> queryBinding;

   public MolangEvent(AnimationController controller, MochaEngine<AnimationController> engine, QueryBinding<AnimationController> queryBinding) {
      this.controller = controller;
      this.engine = engine;
      this.queryBinding = queryBinding;
   }

   public AnimationController getAnimationController() {
      return this.controller;
   }

   public MochaEngine<AnimationController> getRuntimeBuilder() {
      return this.engine;
   }

   public boolean setDoubleQuery(String name, ToDoubleFunction<AnimationController> value) {
      return MolangLoader.setDoubleQuery(this.queryBinding, name, value);
   }

   public boolean setBoolQuery(String name, Function<AnimationController, Boolean> value) {
      return MolangLoader.setBoolQuery(this.queryBinding, name, value);
   }
}
