package com.skd.playeranimationcore.enums;

public enum State {
   RUNNING(true),
   PAUSED(true),
   STOPPED(false);

   private final boolean isActive;

   public boolean isActive() {
      return this.isActive;
   }

   private State(boolean isActive) {
      this.isActive = isActive;
   }
}
