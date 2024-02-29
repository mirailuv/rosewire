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

    // return line 2 of file
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

    // return line 3 of file
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

    // unzipping thing that i copied from the internet
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

    // make config directory if it doesn't exist, also download example files when creating directory
    public static void mkConfigDir() {
        File c = new File("./config/rosewire/");
        if (!c.exists()) {
            c.mkdirs();
            try {
                download("./config/rosewire/scriptexample","https://github.com/mirailuv/rosewire/raw/master/scriptexample");
            } catch (IOException e) {}
            try {
                download("./config/rosewire/downloadexample","https://github.com/mirailuv/rosewire/raw/master/downloadexample");
            } catch (IOException e) {}
            try {
                download("./config/rosewire/unzipexample","https://github.com/mirailuv/rosewire/raw/master/unzipexample");
            } catch (IOException e) {}
            try {
                download("./config/rosewire/deleteexample","https://github.com/mirailuv/rosewire/raw/master/deleteexample");
            } catch (IOException e) {}

        }
    }

    public static void downloadScript(String file) {
        String path = line2(file);
        String link = line3(file);
        if (link != null) {
            try {
                download(path,link);
            } catch (IOException e) {}
        }
    }

    public static void unzipScript(String file) {
        String path = line2(file);
        String target = line3(file);
        try {
            unzip(path,target);
        } catch (IOException e) {}
    }

    public static void deleteScript(String file) {
        try {
            r = new BufferedReader(new FileReader("./config/rosewire/"+file));
        } catch (FileNotFoundException e) {}
        String result = null;
        // read first line
        try {
            result = r.readLine();
        } catch (IOException e) {}

        // read rest of the lines and delete everything
        while(true) {
            try {
                result = r.readLine();
            } catch (IOException e) {}
            if (result != null) {
                delete(result);
            } else break;
        }
    }
    
    public static void download(String file, String link) throws IOException {
            // will change this to something that isn't deprecated eventually
            URL url = new URL(link);
            ReadableByteChannel c = Channels.newChannel(url.openStream());
            FileOutputStream s = new FileOutputStream(file);
            s.getChannel().transferFrom(c, 0, Long.MAX_VALUE);
            s.close();
            c.close();
    }

    public static void delete(String file) {
        File f = new File(file);
        if(!f.exists()) return;
        if(f.isDirectory()) deleteDir(f);
        else f.delete();
    }

    public static void deleteDir(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                deleteDir(f);
            }
        }
        file.delete();
    }
}
