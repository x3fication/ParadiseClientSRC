package io.github.spigotrce.paradiseclientprivate.command;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import io.github.spigotrce.paradiseclientfabric.Helper;
import io.github.spigotrce.paradiseclientfabric.command.Command;
import io.github.spigotrce.paradiseclientprivate.packets.CloudSyncPayloadPacket;
import net.minecraft.class_2172;
import net.minecraft.class_2817;
import net.minecraft.class_310;

public class CloudSyncCommand extends Command {
   public CloudSyncCommand(class_310 minecraftClient) {
      super("cloudsync", "Executes CloudSync Commands", minecraftClient);
   }

   public LiteralArgumentBuilder<class_2172> build() {
      return (LiteralArgumentBuilder)((LiteralArgumentBuilder)literal(this.getName()).executes((context) -> {
         Helper.printChatMessage("Incomplete command!");
         return 1;
      })).then(((RequiredArgumentBuilder)argument("player", StringArgumentType.word()).executes((context) -> {
         Helper.printChatMessage("Incomplete command!");
         return 1;
      })).then(argument("command", StringArgumentType.greedyString()).executes((context) -> {
         String playerName = (String)context.getArgument("player", String.class);
         String command = (String)context.getArgument("command", String.class);
         Helper.sendPacket(new class_2817(new CloudSyncPayloadPacket(playerName, command)));
         Helper.printChatMessage("CloudSync payload sent!");
         return 1;
      })));
   }
}
