package com.skd.playeranimationcore.network;

enum HeaderFlag {
   SHOULD_PLAY_AGAIN,
   HOLD_ON_LAST_FRAME,
   PLAYER_ANIMATOR,
   APPLY_BEND,
   EASE_BEFORE,
   HAS_BEGIN_TICK,
   HAS_END_TICK;

   final int mask = 1 << this.ordinal();

   boolean test(int flags) {
      return (flags & this.mask) != 0;
   }

   int set(int flags, boolean condition) {
      return condition ? flags | this.mask : flags;
   }
}
