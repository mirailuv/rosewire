package com.github.mirailuv.rosewire;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class ScriptCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("rosewire").requires(source -> source.hasPermissionLevel(2)).then(CommandManager.argument("file",StringArgumentType.string()).executes(context -> runScript(context.getSource(),StringArgumentType.getString(context, "file")))));
    }

    private static int runScript(ServerCommandSource source,String file) {
        source.sendMessage(Text.literal(file+" start"));
        ScriptReader.executeScript(file, source);
        source.sendMessage(Text.literal(file+" finish"));
        return 1;
    }
}