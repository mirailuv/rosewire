package com.github.mirailuv.rosewire;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class Server implements DedicatedServerModInitializer {
    
    @Override
    public void onInitializeServer() {
        FileManager.mkConfigDir();
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            DownloadCommand.register(dispatcher);
        });
    }
}
