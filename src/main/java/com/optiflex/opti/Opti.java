package com.optiflex.opti;

import com.optiflex.opti.config.OptiConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

@Mod(Opti.MODID)
public class Opti {
    public static final String MODID = "optiflex";

    public Opti() {
        // Registrar config del cliente
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, OptiConfig.CLIENT_CONFIG);
        System.out.println("[OptiFlex] Configuración registrada correctamente.");
    }
}
