package com.optiflex.opti.items;
import java.util.List;
import net.minecraft.world.item.TooltipFlag;


import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CentinelItem extends Item {

    public CentinelItem(Properties properties) {
        super(properties.stacksTo(1)); // solo 1 por stack
    }

    @Override
    public Component getName(ItemStack stack) {
        return Component.literal("Centinel").withStyle(ChatFormatting.GOLD);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        tooltip.add(Component.literal("Run more fast with this artifact").withStyle(ChatFormatting.AQUA));
        tooltip.add(Component.literal("Opti Addon ~ Optiflex").withStyle(ChatFormatting.LIGHT_PURPLE));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, net.minecraft.world.entity.Entity entity, int slot, boolean selected) {
        if (!world.isClientSide && entity instanceof Player player) {
            // Si el item está en la mano secundaria
            if (player.getOffhandItem() == stack) {
                // Le damos velocidad nivel 1 (duración 40 ticks = 2s)
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 2, false, false));
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
