package me.github.thirtyFirst.mixins.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Random;

@Mixin(EndermanEntity.class)
public abstract class MixinEndermanEntity extends HostileEntity implements Angerable {

	public MixinEndermanEntity (EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "damage", at = @At(value = "HEAD", target = "Lnet/minecraft/entity/mob/EndermanEntity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"), locals = LocalCapture.CAPTURE_FAILSOFT)
	private void injectDamage (DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		amount *= 3; // Haha...
	}

	// Make it so Enderman cannot DROWN when going into water
	@Inject(method = "hurtByWater", at = @At(value = "RETURN"), cancellable = true)
	public void isHurtByWater (CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(false);
	}

	@Inject(method = "isPlayerStaring", at = @At(value = "TAIL", target = "Lnet/minecraft/entity/mob/EndermanEntity;isPlayerStaring(Lnet/minecraft/entity/player/PlayerEntity;)Z"))
	private void stareEnderman (PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
		Entity enderMan = (EndermanEntity) (Object) this;
		if (!player.isCreative()) {
			int random = new Random().nextInt(50);
			if (random == 1) {
				enderMan.setInvisible(true);
			}
		}
	}
}
