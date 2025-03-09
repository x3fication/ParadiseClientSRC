package io.github.spigotrce.paradiseclientprivate.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.CommandSource;
import net.minecraft.MinecraftClient;

public abstract class Command extends io.github.spigotrce.paradiseclientfabric.command.Command {
    public Command(String name, String description, MinecraftClient minecraftClient) {
        super(name, description, minecraftClient);
    }

    public abstract LiteralArgumentBuilder<CommandSource> build();
}
