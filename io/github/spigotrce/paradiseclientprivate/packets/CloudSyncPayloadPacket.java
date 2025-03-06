package io.github.spigotrce.paradiseclientprivate.packets;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.minecraft.class_2540;
import net.minecraft.class_2960;
import net.minecraft.class_8710;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;

public record CloudSyncPayloadPacket(String playerName, String command) implements class_8710 {
   public static final class_9139<class_2540, CloudSyncPayloadPacket> CODEC = class_8710.method_56484(CloudSyncPayloadPacket::write, CloudSyncPayloadPacket::new);
   public static final class_9154<CloudSyncPayloadPacket> ID = new class_9154(class_2960.method_60655("plugin", "cloudsync"));

   public CloudSyncPayloadPacket(class_2540 buf) {
      this(buf.method_19772(), buf.method_19772());
   }

   public CloudSyncPayloadPacket(String playerName, String command) {
      this.playerName = playerName;
      this.command = command;
   }

   public void write(class_2540 buf) {
      ByteArrayDataOutput out = ByteStreams.newDataOutput();
      out.writeUTF(this.playerName);
      out.writeUTF(this.command);
      buf.method_52983(out.toByteArray());
   }

   public class_9154<CloudSyncPayloadPacket> method_56479() {
      return ID;
   }

   public String playerName() {
      return this.playerName;
   }

   public String command() {
      return this.command;
   }
}
