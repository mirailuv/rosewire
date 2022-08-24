package com.github.mirailuv.rosewire;

import java.io.IOException;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class DownloadCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("download").requires(source -> source.hasPermissionLevel(0)).then(CommandManager.argument("source",StringArgumentType.string()).executes(context -> setTarget(StringArgumentType.getString(context, "source")))));
    }

    private static int setTarget(String source) {

        if (source != null) {
            if (FileManager.exists(source)) {
                String path = FileManager.line1(source);
                String link = FileManager.line2(source);
                if (path != null) {
                    if (link != null) {
                        try {
                            FileManager.download(path,link);
                        } catch (IOException e) {}
                        return 1;
                    }
                }
                Main.l.info("Failed to download");
                return 0;
            }
            Main.l.info("File doesn't exist");
            return 0;
        }

        Main.l.info("Source is null");
        return 0;
    }
}