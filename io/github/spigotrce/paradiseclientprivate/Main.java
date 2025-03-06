package io.github.spigotrce.paradiseclientprivate;

import io.github.spigotrce.paradiseclientfabric.Constants;
import io.github.spigotrce.paradiseclientfabric.ParadiseClient_Fabric;
import io.github.spigotrce.paradiseclientprivate.command.AtlasCommand;
import io.github.spigotrce.paradiseclientprivate.command.CMDBRICommand;
import io.github.spigotrce.paradiseclientprivate.command.CloudSyncCommand;
import io.github.spigotrce.paradiseclientprivate.command.DRSCommand;
import io.github.spigotrce.paradiseclientprivate.command.InterChatCommand;
import io.github.spigotrce.paradiseclientprivate.command.MultiChatCommand;
import io.github.spigotrce.paradiseclientprivate.command.T2CCommand;
import io.github.spigotrce.paradiseclientprivate.helper.HWID;
import java.util.ArrayList;
import net.fabricmc.api.ModInitializer;
import net.minecraft.class_310;

public class Main implements ModInitializer {
   public static final ArrayList<String> hwids = new ArrayList();

   public void onInitialize() {
      class_310.method_1551().execute(() -> {
         hwids.add("77d027a02f99f19cc6f72b78b19f4924");
         if (!hwids.contains(HWID.getHWID())) {
            Constants.LOGGER.info(HWID.getHWID());
         }

         Constants.EDITION = "PRIVATE";
         Constants.WINDOW_TITLE = "ParadiseClient-Fabric [" + Constants.EDITION + "] 1.21.4-3-12 " + (ParadiseClient_Fabric.miscMod.isClientOutdated ? "Outdated" : "");
         this.registerCommands();
         Constants.LOGGER.error("All commands and exploits have been registered!");
      });
   }

   private void registerCommands() {
      Constants.LOGGER.info("Attempting to register all commands!");

      try {
         ParadiseClient_Fabric.commandManager.register(new CloudSyncCommand(class_310.method_1551()));
         ParadiseClient_Fabric.commandManager.register(new DRSCommand(class_310.method_1551()));
         ParadiseClient_Fabric.commandManager.register(new T2CCommand(class_310.method_1551()));
         ParadiseClient_Fabric.commandManager.register(new AtlasCommand(class_310.method_1551()));
         ParadiseClient_Fabric.commandManager.register(new CMDBRICommand(class_310.method_1551()));
         ParadiseClient_Fabric.commandManager.register(new MultiChatCommand(class_310.method_1551()));
         ParadiseClient_Fabric.commandManager.register(new InterChatCommand(class_310.method_1551()));
      } catch (Exception var2) {
         Constants.LOGGER.error("Error during command registration: ", var2);
      }

      Constants.LOGGER.info("Finished registering commands!");
   }
}
