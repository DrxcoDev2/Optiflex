package com.optiflex.opti;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.ServerLifecycleHooks;

@Mod.EventBusSubscriber(modid = "optiflex")
public class ItemLimiter {

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        for (ServerLevel level : ServerLifecycleHooks.getCurrentServer().getAllLevels()) {
            AABB worldBox = new AABB(-30000000, 0, -30000000, 30000000, 256, 30000000);

            for (ItemEntity item : level.getEntitiesOfClass(ItemEntity.class, worldBox)) {

                // Elimina ítems que llevan mucho tiempo
                if (item.tickCount > 600) {
                    item.remove(ItemEntity.RemovalReason.DISCARDED);
                    System.out.println("[Optiflex] Ítem eliminado por tiempo: " + item.getItem().getHoverName());
                    continue;
                }

                // Limita la cantidad de ítems iguales cerca
                long countNearby = level.getEntitiesOfClass(ItemEntity.class, item.getBoundingBox().inflate(1))
                        .stream()
                        .filter(i -> i.getItem() == item.getItem()) // ← comparación correcta
                        .count();

                if (countNearby > 20) {
                    item.remove(ItemEntity.RemovalReason.DISCARDED);
                    System.out.println("[Optiflex] Ítem eliminado por exceso: " + item.getItem().getHoverName());
                }
            }
        }
    }
}
