package org.kitowashere.boiled_witchcraft.server.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.server.Main;
import net.minecraft.server.commands.FillCommand;
import net.minecraft.server.level.ServerLevel;
import org.kitowashere.boiled_witchcraft.core.blood.ITitanBloodHandler;

import java.util.function.Consumer;

import static org.kitowashere.boiled_witchcraft.core.blood.BloodDensityProvider.TITAN_BLOOD_HANDLER;

public class TitanBloodCommand {

    public TitanBloodCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> header = Commands.literal("titan_blood");

        dispatcher.register(
                header.requires(c -> c.hasPermission(2))
                    .then(Commands.literal("add")).executes(this::add)
                    /*.then(Commands.literal("sub")).executes(this::sub)
                    .then(Commands.literal("set")).executes(this::set)*/
                    .then(Commands.argument("position", BlockPosArgument.blockPos()))
                    .then(Commands.argument("value", IntegerArgumentType.integer()))
        );
    }

    private int command(CommandContext<CommandSourceStack> context, Consumer<ITitanBloodHandler> action) {
        context.getSource().getLevel().getChunkAt(BlockPosArgument.getBlockPos(context, "position"))
                .getCapability(TITAN_BLOOD_HANDLER).ifPresent(action::accept);

        return 1;
    }

    private int set(CommandContext<CommandSourceStack> context) {
        return command(context, handler -> handler.set(IntegerArgumentType.getInteger(context, "value")));
    }

    private int sub(CommandContext<CommandSourceStack> context) {
        return command(context, handler -> handler.sub(IntegerArgumentType.getInteger(context, "value")));
    }

    private int add(CommandContext<CommandSourceStack> context) {
        return command(context, handler -> handler.add(IntegerArgumentType.getInteger(context, "value")));
    }
}
