package com.skd.playeranimationcore.molang;

import com.skd.playeranimationcore.PlayerAnimCore;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.unnamed.mocha.parser.ast.Expression;
import team.unnamed.mocha.runtime.ExecutionContext;
import team.unnamed.mocha.runtime.value.Function;
import team.unnamed.mocha.runtime.value.MutableObjectBinding;
import team.unnamed.mocha.runtime.value.ObjectProperty;
import team.unnamed.mocha.runtime.value.Value;

public class QueryBinding<T> extends MutableObjectBinding implements ExecutionContext<T> {
   private final T entity;

   public QueryBinding(T entity) {
      this.entity = entity;
   }

   @Nullable
   public ObjectProperty getProperty(@NotNull String name) {
      ObjectProperty property = super.getProperty(name);
      if (property == null) {
         return null;
      } else {
         if (property.value() instanceof Function function && !property.constant()) {
            try {
               return ObjectProperty.property(Objects.requireNonNull(function.evaluate(this)), false);
            } catch (Throwable var5) {
               PlayerAnimCore.LOGGER.warn("Failed to evaluate function property '{}'", name, var5);
            }
         }

         return property;
      }
   }

   public T entity() {
      return this.entity;
   }

   @Nullable
   public Value eval(@NotNull Expression expression) {
      throw new UnsupportedOperationException();
   }

   @Nullable
   public Object flag() {
      throw new UnsupportedOperationException();
   }

   public void flag(@Nullable Object flag) {
      throw new UnsupportedOperationException();
   }
}
