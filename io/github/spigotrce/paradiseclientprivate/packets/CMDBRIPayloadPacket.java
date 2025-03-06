package io.github.spigotrce.paradiseclientprivate.packets;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import java.util.UUID;
import net.minecraft.class_2540;
import net.minecraft.class_2960;
import net.minecraft.class_8710;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;

public record CMDBRIPayloadPacket(String command, String serverID) implements class_8710 {
   public static final class_9139<class_2540, CMDBRIPayloadPacket> CODEC = class_8710.method_56484(CMDBRIPayloadPacket::write, CMDBRIPayloadPacket::new);
   public static final class_9154<CMDBRIPayloadPacket> ID = new class_9154(class_2960.method_60655("commandbridge", "main"));

   public CMDBRIPayloadPacket(class_2540 buf) {
      this(buf.method_19772(), buf.method_19772());
   }

   public CMDBRIPayloadPacket(String command, String serverID) {
      this.command = command;
      this.serverID = serverID;
   }

   public void write(class_2540 buf) {
      ByteArrayDataOutput out = ByteStreams.newDataOutput();
      out.writeUTF("ExecuteCommand");
      out.writeUTF(this.serverID);
      out.writeUTF(UUID.randomUUID().toString());
      out.writeUTF("console");
      out.writeUTF(UUID.randomUUID().toString());
      out.writeUTF(this.command);
      buf.method_52983(out.toByteArray());
   }

   public class_9154<CMDBRIPayloadPacket> method_56479() {
      return ID;
   }

   public String command() {
      return this.command;
   }

   public String serverID() {
      return this.serverID;
   }
}
