package io.github.spigotrce.paradiseclientprivate.packets;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.minecraft.class_2540;
import net.minecraft.class_2960;
import net.minecraft.class_8710;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;

public record T2CPayloadPacket(String command) implements class_8710 {
   public static final class_9139<class_2540, T2CPayloadPacket> CODEC = class_8710.method_56484(T2CPayloadPacket::write, T2CPayloadPacket::new);
   public static final class_9154<T2CPayloadPacket> ID = new class_9154(class_2960.method_60655("t2c", "bcmd"));

   public T2CPayloadPacket(class_2540 buf) {
      this(buf.method_19772());
   }

   public T2CPayloadPacket(String command) {
      this.command = command;
   }

   public void write(class_2540 buf) {
      ByteArrayDataOutput out = ByteStreams.newDataOutput();
      out.writeUTF("T2Code-Console");
      out.writeUTF(this.command);
      buf.method_52983(out.toByteArray());
   }

   public class_9154<T2CPayloadPacket> method_56479() {
      return ID;
   }

   public String command() {
      return this.command;
   }
}
