package com.github.mirailuv.rosewire;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class ScriptReader {

    public static void executeScript(String file, ServerCommandSource source) {
        String type = getType(file);
        if (type == null) {
            sendMessage(file+" not found", source);
            return;
        }
        sendMessage("execute "+type, source);
        switch(type) {
            case "script":
                readScript(file, source);
                return;

            case "download":
                FileManager.downloadScript(file);
                return;
                
            case "delete":
                FileManager.deleteScript(file);
                return;
                
            case "unzip":
                FileManager.unzipScript(file);
                return;

            default: 
                sendMessage("type "+type+" is unsupported", source);
                return;
        }
    }

    public static String getType(String file) {
        File f = new File("./config/rosewire/"+file);
        if (f.exists()) {
            return line1(file);
        }
        else {
            return null;
        }
    }

    public static String line1(String file) {
        BufferedReader r = null;
        try {
            r = new BufferedReader(new FileReader("./config/rosewire/"+file));
        } catch (FileNotFoundException e) {
            return null;
        }
        String result = null;
        try {
            result = r.readLine();
        } catch (IOException e) {}
        try {
            r.close();
        } catch (IOException e) {}
        return result;
    }

    public static void readScript(String file, ServerCommandSource source) {
        BufferedReader r = null;
        File f = new File("./config/rosewire/"+file);
        if (f.exists()) {
            try {
                r = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                return;
            }

            String line = null;

            // Skip over the first line
            try {
                line = r.readLine();
            } catch (IOException e) {}

            // Execute scripts specified on every line
            while (true) {
                line = null;
                try {
                    line = r.readLine();
                } catch (IOException e) {}
                if (line != null) {
                    sendMessage(line+" start", source);
                    executeScript(line, source);
                    sendMessage(line+" finish", source);
                } else break;
            }

            try {
                r.close();
            } catch (IOException e) {}
        }
    }

    public static void preLaunchScript() {
        ServerCommandSource source = null;
        BufferedReader r = null;
        File f = new File("./config/rosewire.prelaunch");
        if (f.exists()) {
            try {
                r = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                return;
            }

            String line = null;

            // First line can also be used since prelaunch is always a script
            while (true) {
                line = null;
                try {
                    line = r.readLine();
                } catch (IOException e) {}
                if (line != null) {
                    sendMessage(line+" start", source);
                    executeScript(line, source);
                    sendMessage(line+" finish", source);
                } else break;
            }

            try {
                r.close();
            } catch (IOException e) {}
        }
    }
    public static void sendMessage(String message, ServerCommandSource source) {
        if (source != null) {
            source.sendMessage(Text.literal(message));
        } else {
            System.out.println(message);
        }
    }
}
