package com.skd.playeranimationcore.neoforge;

import com.skd.playeranimationcore.PlayerAnimCoreService;
import net.neoforged.fml.loading.FMLLoader;

public final class PlayerAnimCoreServiceImpl implements PlayerAnimCoreService {
   public boolean isServiceActive() {
      try {
         Class.forName("net.neoforged.fml.loading.FMLLoader");
         return true;
      } catch (Exception var2) {
         return false;
      }
   }

   @Override
   public boolean isModLoaded(String id) {
      return FMLLoader.getCurrent().getLoadingModList().getModFileById(id) != null;
   }
}
