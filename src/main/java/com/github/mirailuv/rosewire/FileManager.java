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

    public static void mkConfigDir() {
        File c = new File("./config/rosewire/");
        if (!c.exists()) {
            c.mkdirs();
            try {
                download("./config/rosewire/example.txt","https://github.com/mirailuv/rosewire/raw/master/example.txt");
            } catch (IOException e) {}
            try {
                download("./config/rosewire/examplezip.txt","https://github.com/mirailuv/rosewire/raw/master/examplezip.txt");
            } catch (IOException e) {}
        }
    }

    public static void preLaunch() {
        File x = new File("./config/rosewire.prelaunch");
        if (x.exists()) {
            try {
                r = new BufferedReader(new FileReader(x));
            } catch (FileNotFoundException e) {}
            String line = null;
            boolean b = true;
            while (b) {
                line = null;
                try {
                    line = r.readLine();
                } catch (IOException e) {}
                if (line == null) b=false; else preDl(line);
            }
            try {
                r.close();
            } catch (IOException e) {}
        } else {
            File configFolder = new File("./config/");
            configFolder.mkdirs();
            try {
                x.createNewFile();
            } catch (IOException e) {}
        }
    }

    public static void preDl(String file) {
        if (exists(file)) {
            String path = line1(file);
            String link = line2(file);
            String action = line3(file);
            if (link != null) {
                try {
                    download(path,link);
                } catch (IOException e) {}
                if (action != null) {
                    if (action.equals("unzip")) {
                        String target = line4(file);
                        try {
                            unzip(path,target);
                        } catch (IOException e) {}
                    }                    
                }
            }
        }
    }
    
    public static void download(String file, String link) throws IOException {
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
