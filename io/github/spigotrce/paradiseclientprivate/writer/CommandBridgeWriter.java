package io.github.spigotrce.paradiseclientprivate.writer;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import java.util.UUID;
import net.minecraft.PacketByteBuf;
import net.minecraft.Identifier;
import net.minecraft.CustomPayload;
import net.minecraft.PacketCodec;

public record CommandBridgeWriter(String command, String serverID) implements CustomPayload {
    public static final PacketCodec<PacketByteBuf, CommandBridgeWriter> CODEC = CustomPayload.codecOf(CommandBridgeWriter::write, CommandBridgeWriter::new);
    public static final CustomPayload.Id<CommandBridgeWriter> ID = new CustomPayload.Id(Identifier.of("commandbridge", "main"));

    public CommandBridgeWriter(PacketByteBuf buf) {
        this(buf.readString(), buf.readString());
    }

    public void write(PacketByteBuf buf) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("ExecuteCommand");
        out.writeUTF(this.serverID);
        out.writeUTF(UUID.randomUUID().toString());
        out.writeUTF("console");
        out.writeUTF(UUID.randomUUID().toString());
        out.writeUTF(this.command);
        buf.writeBytes(out.toByteArray());
    }

    public CustomPayload.Id<CommandBridgeWriter> getId() {
        return ID;
    }
}