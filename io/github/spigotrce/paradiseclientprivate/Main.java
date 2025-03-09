package io.github.spigotrce.paradiseclientprivate;

import io.github.spigotrce.paradiseclientfabric.Constants;
import io.github.spigotrce.paradiseclientprivate.command.CommandRegister;
import io.github.spigotrce.paradiseclientprivate.writer.PayloadRegister;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.MinecraftClient;

public class Main implements ClientModInitializer, ModInitializer {
    public void onInitialize() {
        MinecraftClient.getInstance().execute(() -> {
            Constants.EDITION = "PRIVATE";
            Constants.reloadTitle();
            this.registerCommands();
        });
    }

    public void onInitializeClient() {
        // Authentication.login();
        PayloadRegister.register();
    }

    private void registerCommands() {
        CommandRegister.register();
    }
}
