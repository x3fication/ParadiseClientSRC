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

public class CloudSync extends Command {
    public CloudSync(MinecraftClient minecraftClient) {
        super("cloudsync", "Executes CloudSync Commands", minecraftClient);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> build() {
        return (CloudSync.literal(this.getName()).executes(this::build)).then((CloudSync.argument("player", StringArgumentType.word()).executes(this::build)).then(CloudSync.argument("command", StringArgumentType.greedyString()).executes(this::buildL)));
    }

    private int buildL(CommandContext<?> context) {
        String playerName = context.getArgument("player", String.class);
        String command = context.getArgument("command", String.class);
        Helper.sendPacket(new CustomPayloadC2SPacket(new CloudSyncWriter(playerName, command)));
        Helper.printChatMessage("CloudSync payload sent!");
        return 1;
    }

    private int build(CommandContext<?> context) {
        Helper.printChatMessage("Incomplete command!");
        return 1;
    }
}
