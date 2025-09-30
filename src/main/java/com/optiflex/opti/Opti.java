package com.optiflex.opti;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Opti.MODID)
public class Opti {
    public static final String MODID = "optiflex";

    public Opti() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        // Registrar ítems
        ModItems.ITEMS.register(bus);

        // Registrar creative tabs
        ModCreativeTabs.register(bus);

        // Si en el futuro agregas bloques:
        // ModBlocks.BLOCKS.register(bus);
    }
}
