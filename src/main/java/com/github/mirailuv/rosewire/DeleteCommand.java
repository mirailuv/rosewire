package com.github.mirailuv.rosewire;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class DeleteCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("delete").requires(source -> source.hasPermissionLevel(2)).then(CommandManager.argument("source",StringArgumentType.string()).executes(context -> setTarget(context.getSource(),StringArgumentType.getString(context, "source")))));
    }

    private static int setTarget(ServerCommandSource source,String file) {

        if (file != null) {
            if (FileManager.exists(file)) {
                String path = FileManager.line1(file);
                FileManager.delete(path);
                source.sendMessage(Text.literal("File was deleted (if exists)"));
                return 1;
            }
            source.sendMessage(Text.literal("No input file found"));
            return 0;
        }
        source.sendMessage(Text.literal("Source is null"));
        return 0;
    }
}