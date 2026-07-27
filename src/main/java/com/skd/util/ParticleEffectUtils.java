package com.skd.playeranimationcore.util;

import com.google.gson.JsonObject;
import com.skd.playeranimationcore.PlayerAnimCore;

public class ParticleEffectUtils {
   public static String parseIdentifier(String raw) {
      return getIdentifier((JsonObject)PlayerAnimCore.GSON.fromJson(raw, JsonObject.class));
   }

   public static String getIdentifier(JsonObject obj) {
      return obj.getAsJsonObject("particle_effect").getAsJsonObject("description").get("identifier").getAsString();
   }
}
