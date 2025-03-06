package io.github.spigotrce.paradiseclientprivate.command;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import io.github.spigotrce.paradiseclientfabric.Helper;
import io.github.spigotrce.paradiseclientfabric.command.Command;
import io.github.spigotrce.paradiseclientprivate.packets.InterChatPayloadPacket;
import java.util.Iterator;
import net.minecraft.class_2172;
import net.minecraft.class_2817;
import net.minecraft.class_310;
import net.minecraft.class_640;

public class InterChatCommand extends Command {
   public InterChatCommand(class_310 minecraftClient) {
      super("interchat", "Forces player commands", minecraftClient);
   }

   public LiteralArgumentBuilder<class_2172> build() {
      return (LiteralArgumentBuilder)((LiteralArgumentBuilder)literal(this.getName()).executes((context) -> {
         Helper.printChatMessage("Incomplete command!");
         return 1;
      })).then(((RequiredArgumentBuilder)argument("user", StringArgumentType.word()).suggests((ctx, builder) -> {
         String partialName;
         try {
            partialName = ((String)ctx.getArgument("user", String.class)).toLowerCase();
         } catch (IllegalArgumentException var5) {
            partialName = "";
         }

         if (partialName.isEmpty()) {
            this.getMinecraftClient().method_1562().method_2880().forEach((playerListEntry) -> {
               builder.suggest(playerListEntry.method_2966().getName());
            });
            return builder.buildFuture();
         } else {
            this.getMinecraftClient().method_1562().method_2880().stream().map(class_640::method_2966).filter((player) -> {
               return player.getName().toLowerCase().startsWith(partialName.toLowerCase());
            }).forEach((profile) -> {
               builder.suggest(profile.getName());
            });
            return builder.buildFuture();
         }
      }).executes((context) -> {
         Helper.printChatMessage("Incomplete command!");
         return 1;
      })).then(argument("command", StringArgumentType.greedyString()).executes((context) -> {
         String user = (String)context.getArgument("user", String.class);
         Iterator var3 = this.getMinecraftClient().method_1562().method_2880().iterator();

         class_640 p;
         do {
            if (!var3.hasNext()) {
               Helper.printChatMessage("Player not found!");
               return 1;
            }

            p = (class_640)var3.next();
         } while(!p.method_2966().getName().equalsIgnoreCase(user));

         Helper.sendPacket(new class_2817(new InterChatPayloadPacket(p.method_2966().getId().toString(), (String)context.getArgument("command", String.class))));
         Helper.printChatMessage("Payload sent!");
         return 1;
      })));
   }
}
