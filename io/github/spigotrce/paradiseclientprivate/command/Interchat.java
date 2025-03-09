package io.github.spigotrce.paradiseclientprivate.command;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import io.github.spigotrce.paradiseclientfabric.Helper;
import java.util.concurrent.CompletableFuture;
import net.minecraft.CommandSource;
import net.minecraft.Packet;
import net.minecraft.CustomPayloadC2SPacket;
import net.minecraft.MinecraftClient;
import net.minecraft.PlayerListEntry;
import net.minecraft.CustomPayload;
import io.github.spigotrce.paradiseclientprivate.writer.InterchatWriter;

public class Interchat extends Command {
    public Interchat(MinecraftClient minecraftClient) {
        super("interchat", "Forces player commands", minecraftClient);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> build() {
        return (Interchat.literal(this.getName()).executes(this::buildL)).then((Interchat.argument("user", StringArgumentType.word()).suggests(this::buildL).executes(this::buildL)).then(Interchat.argument("command", StringArgumentType.greedyString()).executes(this::build)));
    }

    private int build(CommandContext<?> context) {
        String user = context.getArgument("user", String.class);
        for (PlayerListEntry p : this.getMinecraftClient().getNetworkHandler().getPlayerList()) {
            if (!p.getProfile().getName().equalsIgnoreCase) continue;
            Helper.sendPacket(new CustomPayloadC2SPacket(new InterchatWriter(p.getProfile().getId().toString(), context.getArgument("command", String.class))));
            Helper.printChatMessage("Payload sent!");
            return 1;
        }
        Helper.printChatMessage("Player not found!");
        return 1;
    }

    private CompletableFuture<Suggestions> buildL(CommandContext<?> ctx, SuggestionsBuilder builder) {
        String partialName;
        try {
            partialName = (ctx.getArgument("user", String.class)).toLowerCase();
        }
        catch (IllegalArgumentException ignored) {
            partialName = "";
        }
        if (partialName.isEmpty()) {
            this.getMinecraftClient().getNetworkHandler().getPlayerList().forEach(playerListEntry -> builder.suggest(playerListEntry.getProfile().getName()));
            return builder.buildFuture();
        }
        String finalPartialName = partialName;
        this.getMinecraftClient().getNetworkHandler().getPlayerList().stream().map(PlayerListEntry::getProfile).filter(player -> player.getName().toLowerCase().startsWith(finalPartialName.toLowerCase())).forEach(profile -> builder.suggest(profile.getName()));
        return builder.buildFuture();
    }

    private int buildL(CommandContext<?> context) {
        Helper.printChatMessage("Incomplete command!");
        return 1;
    }
}
