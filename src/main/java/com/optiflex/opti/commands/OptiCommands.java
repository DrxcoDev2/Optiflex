package com.optiflex.opti.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.optiflex.opti.config.OptiConfig;
import net.minecraft.network.chat.Component;

@Mod.EventBusSubscriber
public class OptiCommands {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        dispatcher.register(
            Commands.literal("checkculling")
                .executes(OptiCommands::checkCulling)
        );
    }

    private static int checkCulling(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();

        int freeze = OptiConfig.freezeDistance.get();
        int skip = OptiConfig.skipDistance.get();
        int ticks = OptiConfig.skipTicks.get();

        source.sendSuccess(() -> Component.literal(
            "[OptiFlex] Config actual:\n" +
            " FreezeDistance: " + freeze + "\n" +
            " SkipDistance: " + skip + "\n" +
            " SkipTicks: " + ticks
        ), false);

        return 1;
    }
}
