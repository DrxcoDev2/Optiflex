package com.optiflex.opti;

import com.optiflex.opti.items.CustomBookItem;
import com.optiflex.opti.items.CentinelItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Opti.MODID);

    public static final RegistryObject<Item> CUSTOM_BOOK = ITEMS.register("ram",
            () -> new CustomBookItem(new Item.Properties())
    );

    public static final RegistryObject<Item> CENTINEL = ITEMS.register("centinel",
            () -> new CentinelItem(new Item.Properties())
    );
}
