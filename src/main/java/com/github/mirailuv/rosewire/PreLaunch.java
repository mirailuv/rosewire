package com.github.mirailuv.rosewire;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class PreLaunch implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        ScriptReader.preLaunchScript();
    }
}
