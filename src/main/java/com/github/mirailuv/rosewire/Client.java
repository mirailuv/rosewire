package com.github.mirailuv.rosewire;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class Client implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        FileManager.mkConfigDir();
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            DeleteCommand.register(dispatcher);
            DownloadCommand.register(dispatcher);
        });
    }
}
