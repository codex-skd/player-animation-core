package com.skd.playeranimationcore.easing;

import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import team.unnamed.mocha.MochaEngine;
import team.unnamed.mocha.parser.ast.Expression;
import team.unnamed.mocha.runtime.standard.MochaMath;
import team.unnamed.mocha.runtime.value.ObjectValue.FloatFunction3;

@FunctionalInterface
public interface EasingTypeTransformer extends FloatFunction3 {
   Float2FloatFunction buildTransformer(@Nullable Float var1);

   default float apply(
      MochaEngine<?> env, float startValue, float endValue, float transitionLength, float lerpValue, @Nullable List<List<Expression>> easingArgs
   ) {
      if (lerpValue >= 1.0F) {
         return endValue;
      } else if (Float.isNaN(lerpValue)) {
         return startValue;
      } else {
         Float easingVariable = null;
         if (easingArgs != null && !easingArgs.isEmpty()) {
            easingVariable = env.eval(easingArgs.getFirst());
         }

         return this.apply(startValue, endValue, easingVariable, lerpValue);
      }
   }

   default float apply(float startValue, float endValue, float lerpValue) {
      return this.apply(startValue, endValue, null, lerpValue);
   }

   default float apply(float startValue, float endValue, @Nullable Float easingValue, float lerpValue) {
      return MochaMath.lerp(startValue, endValue, (Float)this.buildTransformer(easingValue).apply(lerpValue));
   }
}
