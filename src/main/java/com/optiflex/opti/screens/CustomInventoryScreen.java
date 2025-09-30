package com.optiflex.opti.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.optiflex.opti.Opti;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class CustomInventoryScreen extends InventoryScreen {

   private static final ResourceLocation CUSTOM_GUI = new ResourceLocation(Opti.MODID, "textures/gui/custom_slots.png");


    public CustomInventoryScreen(Inventory playerInventory, Component title) {
        super(playerInventory.player);
    }

    @Override
    protected void renderBg(net.minecraft.client.gui.GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        super.renderBg(guiGraphics, partialTicks, mouseX, mouseY);

        // Activamos la textura personalizada
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        guiGraphics.blit(CUSTOM_GUI, leftPos + 100, topPos + 10, 0, 0, 18, 18); // posición y tamaño de la casilla
    }
}
