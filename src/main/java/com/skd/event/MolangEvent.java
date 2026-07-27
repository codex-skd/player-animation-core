package com.skd.playeranimationcore.event;

import com.skd.playeranimationcore.animation.AnimationController;
import com.skd.playeranimationcore.molang.QueryBinding;
import team.unnamed.mocha.MochaEngine;

public class MolangEvent {
   public static final Event<MolangEvent.MolangEventInterface> MOLANG_EVENT = new Event<>(listeners -> (controller, engine, queryBinding) -> {
         for (MolangEvent.MolangEventInterface listener : listeners) {
            listener.registerMolangQueries(controller, engine, queryBinding);
         }
      });

   @FunctionalInterface
   public interface MolangEventInterface {
      void registerMolangQueries(AnimationController var1, MochaEngine<AnimationController> var2, QueryBinding<AnimationController> var3);
   }
}
