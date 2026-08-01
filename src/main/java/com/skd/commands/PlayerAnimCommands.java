package com.skd.playeranimationcore.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.skd.playeranimationcore.PlayerAnimCoreMod;
import com.skd.playeranimationcore.animation.PlayerAnimResources;
import com.skd.playeranimationcore.api.PlayerAnimationAccess;
import com.skd.playeranimationcore.animation.Animation;
import com.skd.playeranimationcore.animation.AnimationController;
import com.skd.playeranimationcore.animation.RawAnimation;
import com.skd.playeranimationcore.network.AnimationBinary;
import com.skd.playeranimationcore.network.LegacyAnimationBinary;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.IdentifierArgument;
import net.minecraft.commands.arguments.UuidArgument;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Avatar;

public class PlayerAnimCommands {
   public static <T> void register(CommandDispatcher<T> dispatcher, CommandBuildContext registryAccess) {
      dispatcher.register(
         (LiteralArgumentBuilder)Commands.literal("testPlayerAnimation")
            .then(Commands.argument("animationID", IdentifierArgument.id()).suggests(new AnimationArgumentProvider()).executes(PlayerAnimCommands::execute))
      );
      dispatcher.register(
         (LiteralArgumentBuilder)Commands.literal("testLegacyAnimationBinary")
            .then(
               Commands.argument("animationID", IdentifierArgument.id())
                  .suggests(new AnimationArgumentProvider())
                  .then(
                     Commands.argument("version", IntegerArgumentType.integer(1, LegacyAnimationBinary.getCurrentVersion()))
                        .executes(PlayerAnimCommands::executeLegacy)
                  )
            )
      );
      dispatcher.register(
         (LiteralArgumentBuilder)Commands.literal("testAnimationBinary")
            .then(
               Commands.argument("animationID", IdentifierArgument.id())
                  .suggests(new AnimationArgumentProvider())
                  .then(
                     Commands.argument("version", IntegerArgumentType.integer(1, AnimationBinary.getCurrentVersion()))
                        .executes(PlayerAnimCommands::executeBinary)
                  )
            )
      );
      dispatcher.register(
         (LiteralArgumentBuilder)Commands.literal("testMannequin")
            .then(
               Commands.argument("animationID", IdentifierArgument.id())
                  .suggests(new AnimationArgumentProvider())
                  .then(Commands.argument("mannequin", UuidArgument.uuid()).executes(PlayerAnimCommands::executeMannequin))
            )
      );
   }

   private static int execute(CommandContext<CommandSourceStack> context) {
      Identifier animation = IdentifierArgument.getId(context, "animationID");
      return playAnimation(PlayerAnimResources.getAnimation(animation));
   }

   private static int executeLegacy(CommandContext<CommandSourceStack> context) {
      Animation animation = Objects.requireNonNull(PlayerAnimResources.getAnimation(IdentifierArgument.getId(context, "animationID")));
      int version = IntegerArgumentType.getInteger(context, "version");
      ByteBuf byteBuffer = Unpooled.buffer(LegacyAnimationBinary.calculateSize(animation, version));
      LegacyAnimationBinary.write(animation, byteBuffer, version);

      int e;
      try {
         e = playAnimation(LegacyAnimationBinary.read(byteBuffer, version));
      } catch (IOException var8) {
         throw new RuntimeException(var8);
      } finally {
         byteBuffer.release();
      }

      return e;
   }

   private static int executeBinary(CommandContext<CommandSourceStack> context) {
      Animation animation = Objects.requireNonNull(PlayerAnimResources.getAnimation(IdentifierArgument.getId(context, "animationID")));
      int version = IntegerArgumentType.getInteger(context, "version");
      ByteBuf byteBuf = Unpooled.buffer();
      AnimationBinary.write(byteBuf, version, animation);
      return playAnimation(AnimationBinary.read(byteBuf, version));
   }

   private static int playAnimation(Animation animation) {
      AnimationController controller = (AnimationController)PlayerAnimationAccess.getPlayerAnimationLayer(
         Objects.requireNonNull(Minecraft.getInstance().player), PlayerAnimCoreMod.ANIMATION_LAYER_ID
      );
      if (controller == null) {
         return 0;
      } else {
         controller.triggerAnimation(RawAnimation.begin().thenPlay(animation));
         return 1;
      }
   }

   private static int executeMannequin(CommandContext<CommandSourceStack> context) {
      Animation animation = Objects.requireNonNull(PlayerAnimResources.getAnimation(IdentifierArgument.getId(context, "animationID")));
      Avatar avatar = (Avatar) Objects.requireNonNull(Minecraft.getInstance().level.getEntity(UuidArgument.getUuid(context, "mannequin")));
      AnimationController controller = (AnimationController)PlayerAnimationAccess.getPlayerAnimationLayer(avatar, PlayerAnimCoreMod.ANIMATION_LAYER_ID);
      if (controller == null) {
         return 0;
      } else {
         controller.triggerAnimation(RawAnimation.begin().thenPlay(animation));
         return 1;
      }
   }
}
