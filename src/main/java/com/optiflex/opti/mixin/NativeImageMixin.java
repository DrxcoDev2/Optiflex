package com.optiflex.opti.mixin;

import com.mojang.blaze3d.platform.NativeImage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.IOException;
import java.io.InputStream;

@Mixin(NativeImage.class)
public abstract class NativeImageMixin {

    @Inject(method = "read(Ljava/io/InputStream;)Lcom/mojang/blaze3d/platform/NativeImage;",
            at = @At("RETURN"), cancellable = true)
    private static void onRead(InputStream stream, CallbackInfoReturnable<NativeImage> cir) throws IOException {
        NativeImage original = cir.getReturnValue();
        if (original != null) {
            int newWidth = Math.max(1, original.getWidth() / 4);   // reducir a la mitad
            int newHeight = Math.max(1, original.getHeight() / 4);

            NativeImage scaled = new NativeImage(newWidth, newHeight, true);
            original.resizeSubRectTo(0, 0, original.getWidth(), original.getHeight(), scaled);

            cir.setReturnValue(scaled);
            original.close();
        }
    }
}
