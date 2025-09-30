package com.optiflex.opti;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.eventbus.api.IEventBus; // <-- ESTE IMPORT FALTABA
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Opti.MODID);

    public static final RegistryObject<CreativeModeTab> OPTIFLEX_TAB =
            CREATIVE_TABS.register("optiflex_tab", () -> CreativeModeTab.builder()
                    .title(Component.literal("Optiflex")) // Nombre del tab
                    .icon(() -> new ItemStack(ModItems.CUSTOM_BOOK.get())) // Icono del tab
                    .displayItems((params, output) -> { // Items que aparecerán
                        output.accept(ModItems.CUSTOM_BOOK.get());
                        output.accept(ModItems.CENTINEL.get());
                    })
                    .build());

    public static void register(IEventBus bus) {
        CREATIVE_TABS.register(bus);
    }
}
