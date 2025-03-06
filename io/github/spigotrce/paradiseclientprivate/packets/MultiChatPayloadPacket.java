package io.github.spigotrce.paradiseclientprivate.packets;

import io.github.spigotrce.paradiseclientfabric.Helper;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.class_2540;
import net.minecraft.class_2960;
import net.minecraft.class_8710;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;

public record MultiChatPayloadPacket(String command) implements class_8710 {
   public static final class_9139<class_2540, MultiChatPayloadPacket> CODEC = class_8710.method_56484(MultiChatPayloadPacket::write, MultiChatPayloadPacket::new);
   public static final class_9154<MultiChatPayloadPacket> ID = new class_9154(class_2960.method_60655("multichat", "act"));

   private MultiChatPayloadPacket(class_2540 buf) {
      this(buf.method_19772());
   }

   public MultiChatPayloadPacket(String command) {
      this.command = command;
   }

   public void write(class_2540 buf) {
      try {
         ByteArrayOutputStream stream = new ByteArrayOutputStream();
         DataOutputStream out = new DataOutputStream(stream);
         out.writeUTF(this.command);
         buf.method_52983(stream.toByteArray());
      } catch (IOException var4) {
         Helper.printChatMessage(var4.getMessage());
      }

   }

   public class_9154<MultiChatPayloadPacket> method_56479() {
      return ID;
   }

   public String command() {
      return this.command;
   }
}
