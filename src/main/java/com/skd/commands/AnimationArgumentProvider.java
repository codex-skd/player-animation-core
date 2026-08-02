package com.skd.playeranimationcore.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.skd.playeranimationcore.animation.PlayerAnimResources;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.resources.Identifier;

public class AnimationArgumentProvider<S> implements SuggestionProvider<S> {
   public CompletableFuture<Suggestions> getSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
      List<String> suggestions = new LinkedList<>();

      for (Identifier animation : PlayerAnimResources.getAnimations().keySet()) {
         suggestions.add(animation.toString());
      }

      return SharedSuggestionProvider.suggest(suggestions, builder);
   }
}
