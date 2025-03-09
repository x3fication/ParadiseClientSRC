package io.github.spigotrce.paradiseclientprivate.writer;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.minecraft.PacketByteBuf;
import net.minecraft.Identifier;
import net.minecraft.CustomPayload;
import net.minecraft.PacketCodec;

public record CloudSyncWriter(String playerName, String command) implements CustomPayload {
    public static final PacketCodec<PacketByteBuf, CloudSyncWriter> CODEC = CustomPayload.codecOf(CloudSyncWriter::write, CloudSyncWriter::new);
    public static final CustomPayload.Id<CloudSyncWriter> ID = new CustomPayload.Id(Identifier.of("plugin", "cloudsync"));

    public CloudSyncWriter(PacketByteBuf buf) {
        this(buf.readString(), buf.readString());
    }

    public void write(PacketByteBuf buf) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(this.playerName);
        out.writeUTF(this.command);
        buf.writeBytes(out.toByteArray());
    }

    public CustomPayload.Id<CloudSyncWriter> getId() {
        return ID;
    }
}