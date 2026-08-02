package com.skd.playeranimationcore.neoforge;

import com.skd.playeranimationcore.PlayerAnimCoreMod;
import com.skd.playeranimationcore.animation.PlayerAnimResources;
import com.skd.playeranimationcore.api.PlayerAnimationAccess;
import com.skd.playeranimationcore.commands.PlayerAnimCommands;
import com.skd.playeranimationcore.neoforge.event.PlayerAnimationRegisterEvent;
import com.skd.playeranimationcore.event.MolangEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.client.event.AddClientReloadListenersEvent;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.jetbrains.annotations.NotNull;

@Mod("player_animation_core")
public final class PlayerAnimationCore extends PlayerAnimCoreMod {
   public PlayerAnimationCore(IEventBus bus) {
      bus.addListener(this::onAddClientReloadListeners);
      if (!FMLLoader.getCurrent().isProduction() || ModList.get().getModFileById("player_animation_core").versionString().contains("dev")) {
         NeoForge.EVENT_BUS.addListener(this::onRegisterCommands);
      }

      bus.addListener(this::onClientInit);
   }

   public void onAddClientReloadListeners(@NotNull AddClientReloadListenersEvent event) {
      event.addListener(PlayerAnimResources.KEY, new PlayerAnimResources());
   }

   public void onRegisterCommands(RegisterClientCommandsEvent event) {
      PlayerAnimCommands.register(event.getDispatcher(), event.getBuildContext());
   }

   public void onClientInit(FMLClientSetupEvent event) {
      event.enqueueWork(
         () -> {
            super.init();
            PlayerAnimationAccess.REGISTER_ANIMATION_EVENT
               .register((player, manager) -> NeoForge.EVENT_BUS.post(new PlayerAnimationRegisterEvent(player, manager)));
            MolangEvent.MOLANG_EVENT
               .register((controller, builder, q) -> NeoForge.EVENT_BUS.post(new com.skd.playeranimationcore.neoforge.event.MolangEvent(controller, builder, q)));
         }
      );
   }
}
