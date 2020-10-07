package me.github.thirtyFirst.mixins.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(EndermanEntity.class)
public abstract class MixinEndermanEntity extends HostileEntity implements Angerable {

	public MixinEndermanEntity (EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "damage", at = @At(value = "HEAD", target = "Lnet/minecraft/entity/mob/EndermanEntity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"), locals = LocalCapture.CAPTURE_FAILSOFT)
	private void injectDamage (DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		amount *= 2; // Haha...
	}

	/*@Inject(method = "initGoals", at = @At(value = "TAIL", target = ""))
	protected void redirectGoals (CallbackInfo ci) {

	}*/

	// Make it so Enderman cannot DROWN when going into water
	@Inject(method = "hurtByWater", at = @At(value = "RETURN"), cancellable = true)
	public void isHurtByWater (CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(false);
	}
}
