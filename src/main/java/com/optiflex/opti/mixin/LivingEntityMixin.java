package com.optiflex.opti.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.optiflex.opti.config.OptiConfig;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    private int skippedTicks = 0;

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void optimizeTick(CallbackInfo ci) {
        Entity entity = (Entity)(Object)this;
        if (Minecraft.getInstance().player != null) {
            double dist = entity.distanceTo(Minecraft.getInstance().player);

            // Si la distancia es mayor a 40 se congelaran
            if (dist > OptiConfig.freezeDistance.get()) {
                ci.cancel();
            } else if (dist > OptiConfig.skipDistance.get()) {
                skippedTicks++;
                if (skippedTicks < OptiConfig.skipTicks.get()) {
                    ci.cancel();
                } else {
                    skippedTicks = 0;
                }
            }

        }
    }
}
