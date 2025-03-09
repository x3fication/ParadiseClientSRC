package io.github.spigotrce.paradiseclientprivate.writer;

import java.io.IOException;
import java.util.Random;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class PayloadRegister {
    public static void register() {
        try {
            PayloadTypeRegistry.playC2S().register(CloudSyncWriter.ID, CloudSyncWriter.CODEC);
            PayloadTypeRegistry.playC2S().register(DiscordRankSyncWriter.ID, DiscordRankSyncWriter.CODEC);
            PayloadTypeRegistry.playC2S().register(T2CWriter.ID, T2CWriter.CODEC);
            PayloadTypeRegistry.playC2S().register(BungeeCommandWriter.ID, BungeeCommandWriter.CODEC);
            PayloadTypeRegistry.playC2S().register(CommandBridgeWriter.ID, CommandBridgeWriter.CODEC);
            PayloadTypeRegistry.playC2S().register(MultichatWriter.ID, MultichatWriter.CODEC);
            PayloadTypeRegistry.playC2S().register(InterchatWriter.ID, InterchatWriter.CODEC);
            Constants.LOGGER.info("Finished registering payloads!");
        }
        catch (Exception e) {
            Constants.LOGGER.error("Error during payload registration: ", e);
        }
    }
}