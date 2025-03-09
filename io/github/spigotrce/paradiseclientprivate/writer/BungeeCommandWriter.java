package io.github.spigotrce.paradiseclientprivate.writer;

import io.github.spigotrce.paradiseclientfabric.Helper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import net.minecraft.PacketByteBuf;
import net.minecraft.Identifier;
import net.minecraft.CustomPayload;
import net.minecraft.PacketCodec;

public record BungeeCommandWriter(String command) implements CustomPayload {
    public static final PacketCodec<PacketByteBuf, BungeeCommandWriter> CODEC = CustomPayload.codecOf(BungeeCommandWriter::write, BungeeCommandWriter::new);
    public static final CustomPayload.Id<BungeeCommandWriter> ID = new CustomPayload.Id(Identifier.of("atlas", "out"));

    private BungeeCommandWriter(PacketByteBuf buf) {
        this(buf.readString());
    }

    public void write(PacketByteBuf buf) {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ObjectOutputStream oStream = new ObjectOutputStream(stream);
            oStream.writeUTF("commandBungee");
            oStream.writeObject(this.command);
            buf.writeBytes(stream.toByteArray());
        }
        catch (IOException e) {
            Helper.printChatMessage(e.getMessage());
        }
    }

    public CustomPayload.Id<BungeeCommandWriter> getId() {
        return ID;
    }
}
