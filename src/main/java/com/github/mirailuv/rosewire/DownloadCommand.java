package com.github.mirailuv.rosewire;

import java.io.IOException;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class DownloadCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("download").requires(source -> source.hasPermissionLevel(4)).then(CommandManager.argument("source",StringArgumentType.string()).executes(context -> setTarget(context.getSource(),StringArgumentType.getString(context, "source")))));
    }

    private static int setTarget(ServerCommandSource source,String file) {

        if (file != null) {
            if (FileManager.exists(file)) {
                String path = FileManager.line1(file);
                String link = FileManager.line2(file);
                String unzip = FileManager.line3(file);
                if (path != null) {
                    if (link != null) {
                        try {
                            FileManager.download(path,link);
                            source.sendMessage(Text.literal("Download successful"));
                        } catch (IOException e) {}
                        if (unzip != null) {
                            source.sendMessage(Text.literal("Attempting unzip"));
                            String target = FileManager.line4(file);
                            try {
                                FileManager.unzip(path,target);
                                source.sendMessage(Text.literal("Unzip successful"));
                                return 1;
                            } catch (IOException e) {}
                            source.sendMessage(Text.literal("Failed to unzip"));
                            return 0;
                        }   else {
                            return 1;
                        }
                    }
                }
                source.sendMessage(Text.literal("Download failed"));
                return 0;
            }
            source.sendMessage(Text.literal("No input file found"));
            return 0;
        }
        source.sendMessage(Text.literal("Source is null"));
        return 0;
    }
}