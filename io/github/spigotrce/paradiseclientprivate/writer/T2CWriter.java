package io.github.spigotrce.paradiseclientprivate.writer;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.minecraft.PacketByteBuf;
import net.minecraft.Identifier;
import net.minecraft.CustomPayload;
import net.minecraft.PacketCodec;

public record T2CWriter(String command) implements CustomPayload
{
    public static final PacketCodec<PacketByteBuf, T2CWriter> CODEC = CustomPayload.codecOf(T2CWriter::write, T2CWriter::new);
    public static final CustomPayload.Id<T2CWriter> ID = new CustomPayload.Id(Identifier.of("t2c", "bcmd"));

    public T2CWriter(PacketByteBuf buf) {
        this(buf.readString());
    }

    public void write(PacketByteBuf buf) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("T2Code-Console");
        out.writeUTF(this.command);
        buf.writeBytes(out.toByteArray());
    }

    public CustomPayload.Id<T2CWriter> getId() {
        return ID;
    }
}
