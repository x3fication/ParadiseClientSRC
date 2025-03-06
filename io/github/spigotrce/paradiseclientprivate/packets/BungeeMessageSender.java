package io.github.spigotrce.paradiseclientprivate.packets;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.regex.Pattern;

public class BungeeMessageSender {
   public static final Pattern VALID_CUSTOM_CHANNEL = Pattern.compile("[a-z]+:[a-z0-9_]+");
   private static final Random random = new Random();
   private static final ConcurrentSkipListMap<Long, Set<String>> sent = new ConcurrentSkipListMap();
   protected static short itemStackScheme = 0;
   protected static short inventoryScheme = 0;

   public static byte[] forwardData(long time, int packetId, byte[] data) throws Exception {
      try {
         byte[][] dataArray = divideArray(data, 32700);
         int i = 0;
         if (i < dataArray.length) {
            byte[] chunk = dataArray[i];
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeInt(random.nextInt());
            out.writeInt(i);
            out.writeInt(dataArray.length);
            out.writeShort(packetId);
            out.write(chunk);
            return out.toByteArray();
         }
      } catch (Exception var8) {
         var8.printStackTrace();
      }

      return new byte[0];
   }

   public static byte[] executeProxyCommand(long time, UUID player, String command) throws Exception {
      ByteArrayDataOutput out = ByteStreams.newDataOutput();
      writeUUID(out, player);
      writeString(out, command, StandardCharsets.UTF_8);
      return forwardData(time, 21, out.toByteArray());
   }

   public static byte[][] divideArray(byte[] source, int chunkSize) {
      int length = (int)Math.ceil((double)source.length / (double)chunkSize);
      if (length <= 1) {
         return new byte[][]{source};
      } else {
         byte[][] ret = new byte[length][];
         int start = 0;

         for(int i = 0; i < ret.length; ++i) {
            int end = start + chunkSize;
            ret[i] = Arrays.copyOfRange(source, start, Math.min(end, source.length));
            start += chunkSize;
         }

         return ret;
      }
   }

   public static void writeUUID(ByteArrayDataOutput out, UUID uuid) throws IOException {
      out.writeLong(uuid.getMostSignificantBits());
      out.writeLong(uuid.getLeastSignificantBits());
   }

   public static void writeString(ByteArrayDataOutput out, String string, Charset charset) throws IOException {
      byte[] bytes = string.getBytes(charset);
      out.writeInt(bytes.length);
      out.write(bytes);
   }
}
