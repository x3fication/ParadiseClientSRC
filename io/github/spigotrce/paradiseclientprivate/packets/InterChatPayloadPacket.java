package io.github.spigotrce.paradiseclientprivate.packets;

import io.github.spigotrce.paradiseclientfabric.Constants;
import io.github.spigotrce.paradiseclientfabric.Helper;
import java.io.IOException;
import java.util.UUID;
import net.minecraft.class_2540;
import net.minecraft.class_2960;
import net.minecraft.class_8710;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;

public record InterChatPayloadPacket(String uuid, String command) implements class_8710 {
   public static final class_9139<class_2540, InterChatPayloadPacket> CODEC = class_8710.method_56484(InterChatPayloadPacket::write, InterChatPayloadPacket::new);
   public static final class_9154<InterChatPayloadPacket> ID = new class_9154(class_2960.method_60655("interchat", "main"));

   private InterChatPayloadPacket(class_2540 buf) {
      this(buf.method_19772(), buf.method_19772());
   }

   public InterChatPayloadPacket(String uuid, String command) {
      this.uuid = uuid;
      this.command = command;
   }

   public void write(class_2540 buf) {
      try {
         buf.method_52983(BungeeMessageSender.executeProxyCommand(System.currentTimeMillis(), UUID.fromString(this.uuid), this.command));
      } catch (IOException var3) {
         Helper.printChatMessage(var3.getMessage());
      } catch (Exception var4) {
         Helper.printChatMessage("error");
         Constants.LOGGER.error("aa", var4);
      }

   }

   public class_9154<InterChatPayloadPacket> method_56479() {
      return ID;
   }

   public String uuid() {
      return this.uuid;
   }

   public String command() {
      return this.command;
   }
}
