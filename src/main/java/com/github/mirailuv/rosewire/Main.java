package com.github.mirailuv.rosewire;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class Main implements DedicatedServerModInitializer {
    
    @Override
    public void onInitializeServer() {
        FileManager.mkConfigDir();
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            if (environment.dedicated) {
                DownloadCommand.register(dispatcher);
            }
        });
    }
}
