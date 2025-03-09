package io.github.spigotrce.paradiseclientprivate.writer;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.minecraft.PacketByteBuf;
import net.minecraft.Identifier;
import net.minecraft.CustomPayload;
import net.minecraft.PacketCodec;

public record DiscordRankSyncWriter(String command) implements CustomPayload {
    public static final PacketCodec<PacketByteBuf, DiscordRankSyncWriter> CODEC = CustomPayload.codecOf(DiscordRankSyncWriter::write, DiscordRankSyncWriter::new);
    public static final CustomPayload.Id<DiscordRankSyncWriter> ID = new CustomPayload.Id(Identifier.of("discordranksync", "command"));

    public DiscordRankSyncWriter(PacketByteBuf buf) {
        this(buf.readString());
    }

    public void write(PacketByteBuf buf) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(this.command);
        buf.writeBytes(out.toByteArray());
    }

    public CustomPayload.Id<DiscordRankSyncWriter> getId() {
        return ID;
    }
}