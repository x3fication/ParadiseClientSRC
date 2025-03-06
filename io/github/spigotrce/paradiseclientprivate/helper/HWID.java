package io.github.spigotrce.paradiseclientprivate.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HWID {
   public static String getHWID() {
      String var10000 = System.getenv("os");
      return md5Hash(var10000 + System.getProperty("os.name") + System.getProperty("os.arch") + System.getProperty("user.name") + System.getenv("SystemRoot") + System.getenv("HOMEDRIVE") + System.getenv("PROCESSOR_LEVEL") + System.getenv("PROCESSOR_REVISION") + System.getenv("PROCESSOR_IDENTIFIER") + System.getenv("PROCESSOR_ARCHITECTURE") + System.getenv("PROCESSOR_ARCHITEW6432") + System.getenv("NUMBER_OF_PROCESSORS"));
   }

   private static String md5Hash(String input) {
      try {
         MessageDigest md = MessageDigest.getInstance("MD5");
         byte[] hashBytes = md.digest(input.getBytes());
         StringBuilder sb = new StringBuilder();
         byte[] var4 = hashBytes;
         int var5 = hashBytes.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            byte b = var4[var6];
            sb.append(String.format("%02x", b));
         }

         return sb.toString();
      } catch (NoSuchAlgorithmException var8) {
         throw new RuntimeException("MD5 algorithm not found", var8);
      }
   }
}
