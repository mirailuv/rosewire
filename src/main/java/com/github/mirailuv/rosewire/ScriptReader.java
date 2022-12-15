package com.github.mirailuv.rosewire;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ScriptReader {

    public static void executeScript(String file) {
        String s = getType(file);
        System.out.println("Action: "+s);
        if (s == null) return;
        switch(s) {
            case "script":
                System.out.println("read script");
                readScript(file);
                return;

            case "download":
                System.out.println("downloading");
                FileManager.downloadScript(file);
                System.out.println("download complete");
                return;
                
            case "delete":
                System.out.println("deleting");
                FileManager.deleteScript(file);
                System.out.println("delete complete");
                return;
                
            case "unzip":
                System.out.println("unzipping");
                FileManager.unzipScript(file);
                System.out.println("unzip complete");
                return;

            default: 
                System.out.println("action could not be determined");
                return;
        }
    }

    public static String getType(String file) {
        File f = new File("./config/rosewire/"+file);
        if (f.exists()) {
            System.out.println("file exists");
            return line1(file);
        }
        else {
            System.out.println("file does not exist");
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

    public static void readScript(String file) {
        BufferedReader r = null;
        File f = new File("./config/rosewire/"+file);
        if (f.exists()) {
            try {
                r = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                return;
            }

            String line = null;

            // Read the first line (script type file)
            try {
                line = r.readLine();
            } catch (IOException e) {}

            // Execute scripts specified on every line
            while (true) {
                line = null;
                try {
                    line = r.readLine();
                } catch (IOException e) {}
                System.out.println("Read: "+line);
                if (line != null) {
                    executeScript(line);
                    continue;
                }
                else {
                    System.out.println("breaking");
                    break;
                }
            }

            try {
                r.close();
            } catch (IOException e) {}
        }
    }

    public static void preLaunchScript() {
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
                System.out.println("Reading: "+line);
                if (line != null) {
                    executeScript(line);
                    continue;
                }
                else break;
            }

            try {
                r.close();
            } catch (IOException e) {}
        }
    }
}
