package com.optiflex.opti.items;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;

public class CustomBookItem extends Item {

    public CustomBookItem(Properties properties) {
        super(properties.stacksTo(1)); // solo stacksTo, no tab()
    }

    @Override
    public Component getName(ItemStack stack) {
        return Component.literal("Libro Mágico de Nilx").withStyle(ChatFormatting.GOLD);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        tooltip.add(Component.literal("Help you for your Optiflex config").withStyle(ChatFormatting.AQUA));
        tooltip.add(Component.literal("Opti Addon ~ Optiflex").withStyle(ChatFormatting.LIGHT_PURPLE));
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack = new ItemStack(this);

        CompoundTag tag = new CompoundTag();
        tag.putString("title", "Libro Mágico");
        tag.putString("author", "Nilx S.A.");

        ListTag pages = new ListTag();
        pages.add(StringTag.valueOf("{\"text\":\"¡Hola! Bienvenido a mi libro custom.\"}"));
        pages.add(StringTag.valueOf("{\"text\":\"Segunda página con más contenido.\"}"));
        tag.put("pages", pages);

        stack.setTag(tag);
        return stack;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.openItemGui(stack, hand);
        return InteractionResultHolder.success(stack);
    }
}
