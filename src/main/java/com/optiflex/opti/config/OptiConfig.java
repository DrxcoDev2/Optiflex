package com.optiflex.opti.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class OptiConfig {
    public static final ForgeConfigSpec CLIENT_CONFIG;

    public static final ForgeConfigSpec.IntValue freezeDistance;
    public static final ForgeConfigSpec.IntValue skipDistance;
    public static final ForgeConfigSpec.IntValue skipTicks;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("optimization");

        freezeDistance = builder
                .comment("Distancia en bloques a la que las entidades se congelan completamente")
                .defineInRange("freezeDistance", 64, 16, 256);

        skipDistance = builder
                .comment("Distancia en bloques a la que las entidades se actualizan con saltos de ticks")
                .defineInRange("skipDistance", 32, 8, 256);

        skipTicks = builder
                .comment("Número de ticks que se saltan las entidades lejanas")
                .defineInRange("skipTicks", 50, 1, 200);

        builder.pop();
        CLIENT_CONFIG = builder.build();
    }
}
