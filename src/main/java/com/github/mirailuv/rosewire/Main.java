package com.github.mirailuv.rosewire;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class Main implements DedicatedServerModInitializer {

    public static final Logger l = LoggerFactory.getLogger("rosewire");

    @Override
    public void onInitializeServer() {

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            if (environment.dedicated) {
                DownloadCommand.register(dispatcher);
            }
        });

    }
}
