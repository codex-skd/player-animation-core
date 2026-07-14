package com.skd.playeranimationcore.api.firstPerson;

public class FirstPersonConfiguration {
   boolean showRightArm = false;
   boolean showLeftArm = false;
   boolean showRightItem = true;
   boolean showLeftItem = true;
   boolean showArmor = false;
   float armRotationScale = 1.0f;
   float armLength = 8.0f;
   float pitchFactor = 0.6f;

   public FirstPersonConfiguration() {
   }

   public FirstPersonConfiguration(boolean showRightArm, boolean showLeftArm, boolean showRightItem, boolean showLeftItem) {
      this(showRightArm, showLeftArm, showRightItem, showLeftItem, false);
   }

   public FirstPersonConfiguration(boolean showRightArm, boolean showLeftArm, boolean showRightItem, boolean showLeftItem, boolean showArmor) {
      this.showRightArm = showRightArm;
      this.showLeftArm = showLeftArm;
      this.showRightItem = showRightItem;
      this.showLeftItem = showLeftItem;
      this.showArmor = showArmor;
   }

   public boolean isShowArmor() {
      return this.showArmor;
   }

   public boolean isShowLeftItem() {
      return this.showLeftItem;
   }

   public boolean isShowRightItem() {
      return this.showRightItem;
   }

   public boolean isShowLeftArm() {
      return this.showLeftArm;
   }

   public boolean isShowRightArm() {
      return this.showRightArm;
   }

   public FirstPersonConfiguration setShowRightItem(boolean showRightItem) {
      this.showRightItem = showRightItem;
      return this;
   }

   public FirstPersonConfiguration setShowArmor(boolean showArmor) {
      this.showArmor = showArmor;
      return this;
   }

   public FirstPersonConfiguration setShowLeftItem(boolean showLeftItem) {
      this.showLeftItem = showLeftItem;
      return this;
   }

   public FirstPersonConfiguration setShowLeftArm(boolean showLeftArm) {
      this.showLeftArm = showLeftArm;
      return this;
   }

   public FirstPersonConfiguration setShowRightArm(boolean showRightArm) {
      this.showRightArm = showRightArm;
      return this;
   }

   public float getArmRotationScale() {
      return this.armRotationScale;
   }

   public FirstPersonConfiguration setArmRotationScale(float armRotationScale) {
      this.armRotationScale = armRotationScale;
      return this;
   }

   public float getArmLength() {
      return this.armLength;
   }

   public FirstPersonConfiguration setArmLength(float armLength) {
      this.armLength = armLength;
      return this;
   }

   public float getPitchFactor() {
      return this.pitchFactor;
   }

   public FirstPersonConfiguration setPitchFactor(float pitchFactor) {
      this.pitchFactor = pitchFactor;
      return this;
   }
}
