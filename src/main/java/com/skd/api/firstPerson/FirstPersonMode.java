package com.skd.playeranimationcore.api.firstPerson;

public enum FirstPersonMode {
   NONE(false),
   VANILLA(true),
   THIRD_PERSON_MODEL(true),
   HANDS_ONLY(true),
   HANDS_ONLY_ARM(true),
   DISABLED(false);

   private final boolean enabled;

   public boolean isEnabled() {
      return this.enabled;
   }

   private FirstPersonMode(boolean enabled) {
      this.enabled = enabled;
   }
}
