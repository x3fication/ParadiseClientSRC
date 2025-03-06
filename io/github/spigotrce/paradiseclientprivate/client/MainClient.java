package io.github.spigotrce.paradiseclientprivate.client;

import io.github.spigotrce.paradiseclientprivate.packets.AtlasPayloadPacket;
import io.github.spigotrce.paradiseclientprivate.packets.CMDBRIPayloadPacket;
import io.github.spigotrce.paradiseclientprivate.packets.CloudSyncPayloadPacket;
import io.github.spigotrce.paradiseclientprivate.packets.DRSPayloadPacket;
import io.github.spigotrce.paradiseclientprivate.packets.InterChatPayloadPacket;
import io.github.spigotrce.paradiseclientprivate.packets.MultiChatPayloadPacket;
import io.github.spigotrce.paradiseclientprivate.packets.T2CPayloadPacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class MainClient implements ClientModInitializer {
   public void onInitializeClient() {
      PayloadTypeRegistry.playC2S().register(CloudSyncPayloadPacket.ID, CloudSyncPayloadPacket.CODEC);
      PayloadTypeRegistry.playC2S().register(DRSPayloadPacket.ID, DRSPayloadPacket.CODEC);
      PayloadTypeRegistry.playC2S().register(T2CPayloadPacket.ID, T2CPayloadPacket.CODEC);
      PayloadTypeRegistry.playC2S().register(AtlasPayloadPacket.ID, AtlasPayloadPacket.CODEC);
      PayloadTypeRegistry.playC2S().register(CMDBRIPayloadPacket.ID, CMDBRIPayloadPacket.CODEC);
      PayloadTypeRegistry.playC2S().register(MultiChatPayloadPacket.ID, MultiChatPayloadPacket.CODEC);
      PayloadTypeRegistry.playC2S().register(InterChatPayloadPacket.ID, InterChatPayloadPacket.CODEC);
   }
}
