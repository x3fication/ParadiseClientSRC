package io.github.spigotrce.paradiseclientprivate.command;

import io.github.spigotrce.paradiseclientfabric.Constants;
import io.github.spigotrce.paradiseclientfabric.ParadiseClient_Fabric;
import io.github.spigotrce.paradiseclientfabric.command.Command;
import net.minecraft.MinecraftClient;

public class CommandRegister {
    public static void register() {
        Constants.LOGGER.info("Attempting to register all commands!");
        try {
            ParadiseClient_Fabric.COMMAND_MANAGER.register(new CloudSync(MinecraftClient.getInstance()));
            ParadiseClient_Fabric.COMMAND_MANAGER.register(new DiscordRankSyncExploit(MinecraftClient.getInstance()));
            ParadiseClient_Fabric.COMMAND_MANAGER.register(new T2CConsoleCommandExploit(MinecraftClient.getInstance()));
            ParadiseClient_Fabric.COMMAND_MANAGER.register(new BungeeConsoleExploit(MinecraftClient.getInstance()));
            ParadiseClient_Fabric.COMMAND_MANAGER.register(new CommandBridgeExploit(MinecraftClient.getInstance()));
            ParadiseClient_Fabric.COMMAND_MANAGER.register(new MultiChatExploit(MinecraftClient.getInstance()));
            ParadiseClient_Fabric.COMMAND_MANAGER.register(new Interchat(MinecraftClient.getInstance()));
            Constants.LOGGER.info("Finished registering commands!");
        }
        catch (Exception e) {
            Constants.LOGGER.error("Error during command registration: ", e);
        }
    }
}
