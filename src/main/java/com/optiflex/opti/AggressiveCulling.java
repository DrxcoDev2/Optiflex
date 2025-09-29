package com.optiflex.opti;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientChunkCache;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "optiflex", value = Dist.CLIENT)
public class AggressiveCulling {

    // ✅ Se ejecuta en el renderizado del mundo
    @SubscribeEvent
    public static void onRenderWorld(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_SOLID_BLOCKS) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;

        ClientChunkCache chunkCache = mc.level.getChunkSource();
        int viewDistance = mc.options.getEffectiveRenderDistance();

        BlockPos playerPos = mc.player.blockPosition();
        int playerChunkX = playerPos.getX() >> 4;
        int playerChunkZ = playerPos.getZ() >> 4;

        // 🔍 Recorremos solo los chunks dentro de la distancia de renderizado
        for (int cx = playerChunkX - viewDistance; cx <= playerChunkX + viewDistance; cx++) {
            for (int cz = playerChunkZ - viewDistance; cz <= playerChunkZ + viewDistance; cz++) {
                LevelChunk chunk = chunkCache.getChunk(cx, cz, false);
                if (chunk == null) continue; // si no está cargado, lo ignoramos

                AABB chunkBox = new AABB(
                        chunk.getPos().getMinBlockX(), 0, chunk.getPos().getMinBlockZ(),
                        chunk.getPos().getMaxBlockX(), mc.level.getMaxBuildHeight(), chunk.getPos().getMaxBlockZ()
                );

                if (!isVisible(mc.player, chunkBox)) {
                    // Mensaje en consola (no spam al jugador en pantalla)
                    System.out.println("[OptiFlex] Chunk oculto en " + chunk.getPos());
                }
            }
        }
    }

    // ✅ Test de visibilidad del chunk con raycast
    private static boolean isVisible(Player player, AABB box) {
        Vec3 eye = player.getEyePosition(1.0F);

        // Revisamos el centro y dos esquinas del bounding box
        Vec3[] points = {
            box.getCenter(),
            new Vec3(box.minX, box.minY, box.minZ),
            new Vec3(box.maxX, box.maxY, box.maxZ)
        };

        for (Vec3 point : points) {
            HitResult hit = player.level().clip(new ClipContext(
                    eye, point,
                    ClipContext.Block.COLLIDER,
                    ClipContext.Fluid.NONE,
                    player
            ));
            if (hit.getType() == HitResult.Type.MISS) {
                return true; // al menos un punto es visible
            }
        }
        return false; // todo tapado → no visible
    }
}
