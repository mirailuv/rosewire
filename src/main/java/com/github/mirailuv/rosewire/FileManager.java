package com.github.mirailuv.rosewire;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileManager {
    static BufferedReader r;
    static File f;

    public static boolean exists(String file) {
        f = new File("./config/rosewire/"+file);
        if (f.exists()) {
            return true;
        }
        return false;
    }

    public static String line1(String file) {
        try {
            r = new BufferedReader(new FileReader("./config/rosewire/"+file));
        } catch (FileNotFoundException e) {}
        String result = null;
        try {
            result = r.readLine();
        } catch (IOException e) {}
        try {
            r.close();
        } catch (IOException e) {}
        return result;
    }

    public static String line2(String file) {
        try {
            r = new BufferedReader(new FileReader("./config/rosewire/"+file));
        } catch (FileNotFoundException e) {}
        String result = null;
        try {
            result = r.readLine();
        } catch (IOException e) {}
        try {
            result = r.readLine();
        } catch (IOException e) {}
        try {
            r.close();
        } catch (IOException e) {}
        return result;
    }

    public static String line3(String file) {
        try {
            r = new BufferedReader(new FileReader("./config/rosewire/"+file));
        } catch (FileNotFoundException e) {}
        String result = null;
        try {
            result = r.readLine();
        } catch (IOException e) {}
        try {
            result = r.readLine();
        } catch (IOException e) {}
        try {
            result = r.readLine();
        } catch (IOException e) {}
        try {
            r.close();
        } catch (IOException e) {}
        return result;
    }

    public static String line4(String file) {
        try {
            r = new BufferedReader(new FileReader("./config/rosewire/"+file));
        } catch (FileNotFoundException e) {}
        String result = null;
        try {
            result = r.readLine();
        } catch (IOException e) {}
        try {
            result = r.readLine();
        } catch (IOException e) {}
        try {
            result = r.readLine();
        } catch (IOException e) {}
        try {
            result = r.readLine();
        } catch (IOException e) {}
        try {
            r.close();
        } catch (IOException e) {}
        return result;
    }

    public static void unzip(String file, String target) throws IOException {
        File t = new File(target);
        if(!t.exists()) t.mkdirs();
        FileInputStream f;
        byte[] buffer = new byte[1024];
        f = new FileInputStream(file);
        ZipInputStream z = new ZipInputStream(f);
        ZipEntry e = z.getNextEntry();
        while(e != null){
            String fileName = e.getName();
            File newFile = new File(target + File.separator + fileName);
            new File(newFile.getParent()).mkdirs();
            FileOutputStream s = new FileOutputStream(newFile);
            int len;
            while ((len = z.read(buffer)) > 0) {
            s.write(buffer, 0, len);
            }
            s.close();
            z.closeEntry();
            e = z.getNextEntry();
        }
        z.closeEntry();
        z.close();
        f.close();
    }
    
    public static void download(String file, String link) throws IOException {
            URL url = new URL(link);
            ReadableByteChannel c = Channels.newChannel(url.openStream());
            FileOutputStream s = new FileOutputStream(file);
            s.getChannel().transferFrom(c, 0, Long.MAX_VALUE);
            s.close();
            c.close();
    }
}
