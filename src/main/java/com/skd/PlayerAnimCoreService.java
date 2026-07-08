package com.skd.playeranimationcore;

import org.redlance.common.services.AdvancedService;
import org.redlance.common.services.ServiceUtils;

public interface PlayerAnimCoreService extends AdvancedService {
   PlayerAnimCoreService INSTANCE = (PlayerAnimCoreService)ServiceUtils.loadService(PlayerAnimCoreService.class);

   boolean isModLoaded(String var1);
}
