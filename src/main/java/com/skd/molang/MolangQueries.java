package com.skd.playeranimationcore.molang;

import com.skd.playeranimationcore.animation.PlayerAnimationController;
import com.skd.playeranimationcore.animation.AnimationController;
import com.skd.playeranimationcore.molang.MolangLoader;
import com.skd.playeranimationcore.molang.QueryBinding;
import java.util.Arrays;
import java.util.Optional;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.ClientMannequin;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentUser;
import net.minecraft.world.entity.Leashable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.MoonPhase;

public final class MolangQueries {
   public static final String ACTOR_COUNT = "actor_count";
   public static final String BLOCKING = "blocking";
   public static final String BODY_X_ROTATION = "body_x_rotation";
   public static final String BODY_Y_ROTATION = "body_y_rotation";
   public static final String CARDINAL_FACING = "cardinal_facing";
   public static final String CARDINAL_FACING_2D = "cardinal_facing_2d";
   public static final String CARDINAL_PLAYER_FACING = "cardinal_player_facing";
   public static final String DAY = "day";
   public static final String DEATH_TICKS = "death_ticks";
   public static final String DISTANCE_FROM_CAMERA = "distance_from_camera";
   public static final String EQUIPMENT_COUNT = "equipment_count";
   public static final String FRAME_ALPHA = "frame_alpha";
   public static final String GET_ACTOR_INFO_ID = "get_actor_info_id";
   public static final String GROUND_SPEED = "ground_speed";
   public static final String HAS_CAPE = "has_cape";
   public static final String HAS_COLLISION = "has_collision";
   public static final String HAS_GRAVITY = "has_gravity";
   public static final String HAS_HEAD_GEAR = "has_head_gear";
   public static final String HAS_OWNER = "has_owner";
   public static final String HAS_PLAYER_RIDER = "has_player_rider";
   public static final String HAS_RIDER = "has_rider";
   public static final String HEAD_X_ROTATION = "head_x_rotation";
   public static final String HEAD_Y_ROTATION = "head_y_rotation";
   public static final String HEALTH = "health";
   public static final String HURT_TIME = "hurt_time";
   public static final String INVULNERABLE_TICKS = "invulnerable_ticks";
   public static final String IS_ALIVE = "is_alive";
   public static final String IS_ANGRY = "is_angry";
   public static final String IS_BABY = "is_baby";
   public static final String IS_BREATHING = "is_breathing";
   public static final String IS_FIRE_IMMUNE = "is_fire_immune";
   public static final String IS_FIRST_PERSON = "is_first_person";
   public static final String IS_IN_CONTACT_WITH_WATER = "is_in_contact_with_water";
   public static final String IS_IN_LAVA = "is_in_lava";
   public static final String IS_IN_WATER = "is_in_water";
   public static final String IS_IN_WATER_OR_RAIN = "is_in_water_or_rain";
   public static final String IS_INVISIBLE = "is_invisible";
   public static final String IS_LEASHED = "is_leashed";
   public static final String IS_MOVING = "is_moving";
   public static final String IS_ON_FIRE = "is_on_fire";
   public static final String IS_ON_GROUND = "is_on_ground";
   public static final String IS_RIDING = "is_riding";
   public static final String IS_SADDLED = "is_saddled";
   public static final String IS_SILENT = "is_silent";
   public static final String IS_SLEEPING = "is_sleeping";
   public static final String IS_SNEAKING = "is_sneaking";
   public static final String IS_SPRINTING = "is_sprinting";
   public static final String IS_SWIMMING = "is_swimming";
   public static final String IS_USING_ITEM = "is_using_item";
   public static final String IS_WALL_CLIMBING = "is_wall_climbing";
   public static final String LIFE_TIME = "life_time";
   public static final String LIMB_SWING = "limb_swing";
   public static final String LIMB_SWING_AMOUNT = "limb_swing_amount";
   public static final String MAIN_HAND_ITEM_MAX_DURATION = "main_hand_item_max_duration";
   public static final String MAIN_HAND_ITEM_USE_DURATION = "main_hand_item_use_duration";
   public static final String MAX_HEALTH = "max_health";
   public static final String MOON_BRIGHTNESS = "moon_brightness";
   public static final String MOON_PHASE = "moon_phase";
   public static final String MOVEMENT_DIRECTION = "movement_direction";
   public static final String PLAYER_LEVEL = "player_level";
   public static final String RIDER_BODY_X_ROTATION = "rider_body_x_rotation";
   public static final String RIDER_BODY_Y_ROTATION = "rider_body_y_rotation";
   public static final String RIDER_HEAD_X_ROTATION = "rider_head_x_rotation";
   public static final String RIDER_HEAD_Y_ROTATION = "rider_head_y_rotation";
   public static final String SCALE = "scale";
   public static final String SLEEP_ROTATION = "sleep_rotation";
   public static final String TIME_OF_DAY = "time_of_day";
   public static final String TIME_STAMP = "time_stamp";
   public static final String VERTICAL_SPEED = "vertical_speed";
   public static final String YAW_SPEED = "yaw_speed";

   public static void setDefaultQueryValues(QueryBinding<AnimationController> binding) {
      MolangLoader.setDoubleQuery(binding, "actor_count", actor -> (double)Minecraft.getInstance().levelRenderer.levelRenderState.entityRenderStates.size());
      MolangLoader.setDoubleQuery(binding, "cardinal_player_facing", actor -> (double)((PlayerAnimationController)actor).getAvatar().getDirection().ordinal());
      MolangLoader.setDoubleQuery(binding, "day", actor -> (double)((PlayerAnimationController)actor).getAvatar().level().getGameTime() / 24000.0);
      MolangLoader.setDoubleQuery(binding, "frame_alpha", actor -> (double)actor.getAnimationData().getPartialTick());
      MolangLoader.setBoolQuery(binding, "has_cape", actor -> {
         Avatar avatar = ((PlayerAnimationController)actor).getAvatar();
         if (avatar instanceof AbstractClientPlayer player) {
            return player.getSkin().cape() != null;
         } else {
            return avatar instanceof ClientMannequin mannequin ? mannequin.getSkin().cape() != null : false;
         }
      });
      MolangLoader.setBoolQuery(binding, "is_first_person", actor -> {
         if (((PlayerAnimationController)actor).getAvatar() instanceof AbstractClientPlayer player && player.isLocalPlayer()) {
            return Minecraft.getInstance().options.getCameraType() == CameraType.FIRST_PERSON;
         }

         return false;
      });
      MolangLoader.setDoubleQuery(binding, "life_time", actor -> actor.isActive() ? (double)actor.getAnimationTime() : 0.0);
      MolangLoader.setDoubleQuery(binding, "moon_brightness", actor -> {
         Avatar avatar = ((PlayerAnimationController)actor).getAvatar();
         return (double)((Float)avatar.level().environmentAttributes().getValue(EnvironmentAttributes.STAR_BRIGHTNESS, avatar.position())).floatValue();
      });
      MolangLoader.setDoubleQuery(binding, "moon_phase", actor -> {
         Avatar avatar = ((PlayerAnimationController)actor).getAvatar();
         return (double)((MoonPhase)avatar.level().environmentAttributes().getValue(EnvironmentAttributes.MOON_PHASE, avatar.position())).index();
      });
      MolangLoader.setDoubleQuery(
         binding,
         "player_level",
         actor -> ((PlayerAnimationController)actor).getAvatar() instanceof AbstractClientPlayer player ? (double)player.experienceLevel : 0.0
      );
      MolangLoader.setDoubleQuery(
         binding, "time_of_day", actor -> (double)((PlayerAnimationController)actor).getAvatar().level().getDefaultClockTime() / 24000.0
      );
      MolangLoader.setDoubleQuery(binding, "time_stamp", actor -> (double)((PlayerAnimationController)actor).getAvatar().level().getGameTime());
      setDefaultEntityQueryValues(binding);
      setDefaultLivingEntityQueryValues(binding);
   }

   private static void setDefaultEntityQueryValues(QueryBinding<AnimationController> binding) {
      MolangLoader.setDoubleQuery(
         binding, "body_x_rotation", actor -> (double)((PlayerAnimationController)actor).getAvatar().getViewXRot(actor.getAnimationData().getPartialTick())
      );
      MolangLoader.setDoubleQuery(
         binding,
         "body_y_rotation",
         actor -> {
            Avatar patt0$temp = ((PlayerAnimationController)actor).getAvatar();
            return patt0$temp instanceof LivingEntity
               ? (double)Mth.lerp(actor.getAnimationData().getPartialTick(), patt0$temp.yBodyRotO, patt0$temp.yBodyRot)
               : (double)((PlayerAnimationController)actor).getAvatar().getViewYRot(actor.getAnimationData().getPartialTick());
         }
      );
      MolangLoader.setDoubleQuery(binding, "cardinal_facing", actor -> (double)((PlayerAnimationController)actor).getAvatar().getDirection().get3DDataValue());
      MolangLoader.setDoubleQuery(binding, "cardinal_facing_2d", actor -> {
         int directionId = ((PlayerAnimationController)actor).getAvatar().getDirection().get3DDataValue();
         return directionId < 2 ? 6.0 : (double)directionId;
      });
      MolangLoader.setDoubleQuery(
         binding,
         "distance_from_camera",
         actor -> Minecraft.getInstance().gameRenderer.mainCamera().position().distanceTo(((PlayerAnimationController)actor).getAvatar().position())
      );
      MolangLoader.setDoubleQuery(binding, "get_actor_info_id", actor -> (double)((PlayerAnimationController)actor).getAvatar().getId());
      MolangLoader.setDoubleQuery(
         binding,
         "equipment_count",
         actor -> (double)(
               ((PlayerAnimationController)actor).getAvatar() instanceof EquipmentUser armorable
                  ? Arrays.stream(EquipmentSlot.values()).filter(EquipmentSlot::isArmor).filter(slot -> !armorable.getItemBySlot(slot).isEmpty()).count()
                  : 0L
            )
      );
      MolangLoader.setBoolQuery(binding, "has_collision", actor -> !((PlayerAnimationController)actor).getAvatar().noPhysics);
      MolangLoader.setBoolQuery(binding, "has_gravity", actor -> !((PlayerAnimationController)actor).getAvatar().isNoGravity());
      MolangLoader.setBoolQuery(binding, "has_owner", actor -> {
         if (((PlayerAnimationController)actor).getAvatar() instanceof OwnableEntity ownable && ownable.getOwnerReference() != null) {
            return true;
         }

         return false;
      });
      MolangLoader.setBoolQuery(binding, "has_player_rider", actor -> ((PlayerAnimationController)actor).getAvatar().hasPassenger(Player.class::isInstance));
      MolangLoader.setBoolQuery(binding, "has_rider", actor -> ((PlayerAnimationController)actor).getAvatar().isVehicle());
      MolangLoader.setBoolQuery(binding, "is_alive", actor -> ((PlayerAnimationController)actor).getAvatar().isAlive());
      MolangLoader.setBoolQuery(binding, "is_angry", actor -> {
         if (((PlayerAnimationController)actor).getAvatar() instanceof NeutralMob neutralMob && neutralMob.isAngry()) {
            return true;
         }

         return false;
      });
      MolangLoader.setBoolQuery(
         binding,
         "is_breathing",
         actor -> ((PlayerAnimationController)actor).getAvatar().getAirSupply() >= ((PlayerAnimationController)actor).getAvatar().getMaxAirSupply()
      );
      MolangLoader.setBoolQuery(binding, "is_fire_immune", actor -> ((PlayerAnimationController)actor).getAvatar().getType().fireImmune());
      MolangLoader.setBoolQuery(binding, "is_invisible", actor -> ((PlayerAnimationController)actor).getAvatar().isInvisible());
      MolangLoader.setBoolQuery(binding, "is_in_contact_with_water", actor -> ((PlayerAnimationController)actor).getAvatar().isInWaterOrRain());
      MolangLoader.setBoolQuery(binding, "is_in_lava", actor -> ((PlayerAnimationController)actor).getAvatar().isInLava());
      MolangLoader.setBoolQuery(binding, "is_in_water", actor -> ((PlayerAnimationController)actor).getAvatar().isInWater());
      MolangLoader.setBoolQuery(binding, "is_in_water_or_rain", actor -> ((PlayerAnimationController)actor).getAvatar().isInWaterOrRain());
      MolangLoader.setBoolQuery(binding, "is_leashed", actor -> {
         if (((PlayerAnimationController)actor).getAvatar() instanceof Leashable leashable && leashable.isLeashed()) {
            return true;
         }

         return false;
      });
      MolangLoader.setBoolQuery(binding, "is_moving", actor -> actor.getAnimationData().isMoving());
      MolangLoader.setBoolQuery(binding, "is_on_fire", actor -> ((PlayerAnimationController)actor).getAvatar().isOnFire());
      MolangLoader.setBoolQuery(binding, "is_on_ground", actor -> ((PlayerAnimationController)actor).getAvatar().onGround());
      MolangLoader.setBoolQuery(binding, "is_riding", actor -> ((PlayerAnimationController)actor).getAvatar().isPassenger());
      MolangLoader.setBoolQuery(binding, "is_saddled", actor -> {
         if (((PlayerAnimationController)actor).getAvatar() instanceof EquipmentUser saddleable && !saddleable.getItemBySlot(EquipmentSlot.SADDLE).isEmpty()) {
            return true;
         }

         return false;
      });
      MolangLoader.setBoolQuery(binding, "is_silent", actor -> ((PlayerAnimationController)actor).getAvatar().isSilent());
      MolangLoader.setBoolQuery(binding, "is_sneaking", actor -> ((PlayerAnimationController)actor).getAvatar().isCrouching());
      MolangLoader.setBoolQuery(binding, "is_sprinting", actor -> ((PlayerAnimationController)actor).getAvatar().isSprinting());
      MolangLoader.setBoolQuery(binding, "is_swimming", actor -> ((PlayerAnimationController)actor).getAvatar().isSwimming());
      MolangLoader.setDoubleQuery(
         binding,
         "movement_direction",
         actor -> actor.getAnimationData().isMoving()
               ? (double)Direction.getApproximateNearest(((PlayerAnimationController)actor).getAvatar().getDeltaMovement()).get3DDataValue()
               : 6.0
      );
      MolangLoader.setDoubleQuery(
         binding,
         "rider_body_x_rotation",
         actor -> ((PlayerAnimationController)actor).getAvatar().isVehicle()
               ? (
                  ((PlayerAnimationController)actor).getAvatar().getFirstPassenger() instanceof LivingEntity
                     ? 0.0
                     : (double)((PlayerAnimationController)actor).getAvatar().getFirstPassenger().getViewXRot(actor.getAnimationData().getPartialTick())
               )
               : 0.0
      );
      MolangLoader.setDoubleQuery(
         binding,
         "rider_body_y_rotation",
         actor -> ((PlayerAnimationController)actor).getAvatar().isVehicle()
               ? (
                  ((PlayerAnimationController)actor).getAvatar().getFirstPassenger() instanceof LivingEntity living
                     ? (double)Mth.lerp(actor.getAnimationData().getPartialTick(), living.yBodyRotO, living.yBodyRot)
                     : (double)((PlayerAnimationController)actor).getAvatar().getFirstPassenger().getViewYRot(actor.getAnimationData().getPartialTick())
               )
               : 0.0
      );
      MolangLoader.setDoubleQuery(
         binding,
         "rider_head_x_rotation",
         actor -> ((PlayerAnimationController)actor).getAvatar().getFirstPassenger() instanceof LivingEntity living
               ? (double)living.getViewXRot(actor.getAnimationData().getPartialTick())
               : 0.0
      );
      MolangLoader.setDoubleQuery(
         binding,
         "rider_head_y_rotation",
         actor -> ((PlayerAnimationController)actor).getAvatar().getFirstPassenger() instanceof LivingEntity living
               ? (double)living.getViewYRot(actor.getAnimationData().getPartialTick())
               : 0.0
      );
      MolangLoader.setDoubleQuery(binding, "vertical_speed", actor -> ((PlayerAnimationController)actor).getAvatar().getDeltaMovement().y);
      MolangLoader.setDoubleQuery(
         binding,
         "yaw_speed",
         actor -> (double)(((PlayerAnimationController)actor).getAvatar().getYRot() - ((PlayerAnimationController)actor).getAvatar().yRotO)
      );
   }

   private static void setDefaultLivingEntityQueryValues(QueryBinding<AnimationController> binding) {
      MolangLoader.setBoolQuery(binding, "blocking", actor -> ((PlayerAnimationController)actor).getAvatar().isBlocking());
      MolangLoader.setDoubleQuery(
         binding,
         "death_ticks",
         actor -> ((PlayerAnimationController)actor).getAvatar().deathTime == 0
               ? 0.0
               : (double)((float)((PlayerAnimationController)actor).getAvatar().deathTime + actor.getAnimationData().getPartialTick())
      );
      MolangLoader.setDoubleQuery(binding, "ground_speed", actor -> ((PlayerAnimationController)actor).getAvatar().getDeltaMovement().horizontalDistance());
      MolangLoader.setBoolQuery(binding, "has_head_gear", actor -> !((PlayerAnimationController)actor).getAvatar().getItemBySlot(EquipmentSlot.HEAD).isEmpty());
      MolangLoader.setDoubleQuery(
         binding, "head_x_rotation", actor -> (double)((PlayerAnimationController)actor).getAvatar().getViewXRot(actor.getAnimationData().getPartialTick())
      );
      MolangLoader.setDoubleQuery(
         binding, "head_y_rotation", actor -> (double)((PlayerAnimationController)actor).getAvatar().getViewYRot(actor.getAnimationData().getPartialTick())
      );
      MolangLoader.setDoubleQuery(binding, "health", actor -> (double)((PlayerAnimationController)actor).getAvatar().getHealth());
      MolangLoader.setDoubleQuery(
         binding,
         "hurt_time",
         actor -> ((PlayerAnimationController)actor).getAvatar().hurtTime == 0
               ? 0.0
               : (double)((float)((PlayerAnimationController)actor).getAvatar().hurtTime - actor.getAnimationData().getPartialTick())
      );
      MolangLoader.setDoubleQuery(
         binding,
         "invulnerable_ticks",
         actor -> ((PlayerAnimationController)actor).getAvatar().invulnerableTime == 0
               ? 0.0
               : (double)((float)((PlayerAnimationController)actor).getAvatar().invulnerableTime - actor.getAnimationData().getPartialTick())
      );
      MolangLoader.setBoolQuery(binding, "is_baby", actor -> ((PlayerAnimationController)actor).getAvatar().isBaby());
      MolangLoader.setBoolQuery(binding, "is_sleeping", actor -> ((PlayerAnimationController)actor).getAvatar().isSleeping());
      MolangLoader.setBoolQuery(binding, "is_using_item", actor -> ((PlayerAnimationController)actor).getAvatar().isUsingItem());
      MolangLoader.setBoolQuery(binding, "is_wall_climbing", actor -> ((PlayerAnimationController)actor).getAvatar().onClimbable());
      MolangLoader.setDoubleQuery(binding, "limb_swing", actor -> (double)((PlayerAnimationController)actor).getAvatar().walkAnimation.position());
      MolangLoader.setDoubleQuery(
         binding,
         "limb_swing_amount",
         actor -> (double)((PlayerAnimationController)actor).getAvatar().walkAnimation.speed(actor.getAnimationData().getPartialTick())
      );
      MolangLoader.setDoubleQuery(
         binding,
         "main_hand_item_max_duration",
         actor -> (double)((PlayerAnimationController)actor).getAvatar().getMainHandItem().getUseDuration(((PlayerAnimationController)actor).getAvatar())
      );
      MolangLoader.setDoubleQuery(
         binding,
         "main_hand_item_use_duration",
         actor -> ((PlayerAnimationController)actor).getAvatar().getUsedItemHand() == InteractionHand.MAIN_HAND
               ? (double)((PlayerAnimationController)actor).getAvatar().getTicksUsingItem() / 20.0 + (double)actor.getAnimationData().getPartialTick()
               : 0.0
      );
      MolangLoader.setDoubleQuery(binding, "max_health", actor -> (double)((PlayerAnimationController)actor).getAvatar().getMaxHealth());
      MolangLoader.setDoubleQuery(binding, "scale", actor -> (double)((PlayerAnimationController)actor).getAvatar().getScale());
      MolangLoader.setDoubleQuery(
         binding,
         "sleep_rotation",
         actor -> (double)Optional.ofNullable(((PlayerAnimationController)actor).getAvatar().getBedOrientation())
               .<Float>map(Direction::toYRot)
               .orElse(0.0F)
               .floatValue()
      );
   }
}
