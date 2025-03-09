package io.github.spigotrce.paradiseclientprivate.auth;

import java.net.HttpURLConnection;
import java.net.URL;
import net.minecraft.MinecraftClient;

public class Authentication {
    public static String hwid = HWID.getHWID();
    public static String username = "";
    public static String token = "";
    public static LoginResult status = LoginResult.NONE;
    public static long lastKeepAlive = 0L;
    public static int recursionTime = 0;

    public static boolean isLoggedIn() {
        return true; // pro crack :sob:
    }

    public static void login() {
        status = LoginResult.LOGIN_SUCCESS;
        Authentication.closeMinecraftHead();
    }

    public static void sendKeepAlive() {
        lastKeepAlive = System.currentTimeMillis();
        try {
            HttpURLConnection connection = (HttpURLConnection)new URL("https://auth.paradise-client.xyz/keep-alive?hwid=" + hwid).openConnection();
            connection.setRequestProperty("Accept", "application/vnd.github.v3+json");
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == 404) {
                status = LoginResult.EXPIRED;
            }
        }
        catch (Exception e) {
            status = LoginResult.EXPIRED;
        }
        if (status != LoginResult.LOGIN_SUCCESS) {
            Authentication.closeMinecraftHead();
        }
    }

    public static void closeMinecraftHead() {
        if (recursionTime > 5) {
            return;
        }
        if (MinecraftClient.getInstance().isRunning()) {
            Authentication.closeMinecraft();
        }
    }

    public static void closeMinecraft() {

    }

    public static String[] decodeAndExtract(String input) {
        if (input.length() <= 1152) {
            throw new IllegalArgumentException("Invalid input length.");
        }
        String usefulPart = input.substring(1024).trim();
        String base64Encoded = usefulPart.substring(0, usefulPart.length() - 128).trim();
        String sha512Hash = usefulPart.substring(usefulPart.length() - 128).trim();
        return new String[]{base64Encoded, sha512Hash};
    }

    public static enum LoginResult {
        NONE,
        LOGIN_FAILED,
        LOGIN_SUCCESS,
        EXPIRED;
    }
}
