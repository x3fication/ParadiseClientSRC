package io.github.spigotrce.paradiseclientprivate.writer;

import io.github.spigotrce.paradiseclientfabric.Helper;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.PacketByteBuf;
import net.minecraft.Identifier;
import net.minecraft.CustomPayload;
import net.minecraft.PacketCodec;

public record MultichatWriter(String command) implements CustomPayload {
    public static final PacketCodec<PacketByteBuf, MultichatWriter> CODEC = CustomPayload.codecOf(MultichatWriter::write, MultichatWriter::new);
    public static final CustomPayload.Id<MultichatWriter> ID = new CustomPayload.Id(Identifier.of("multichat", "act"));

    private MultichatWriter(PacketByteBuf buf) {
        this(buf.readString());
    }

    public void write(PacketByteBuf buf) {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(stream);
            out.writeUTF(this.command);
            buf.writeBytes(stream.toByteArray());
        }
        catch (IOException e) {
            Helper.printChatMessage(e.getMessage());
        }
    }

    public CustomPayload.Id<MultichatWriter> getId() {
        return ID;
    }
}
