package com.github.mirailuv.rosewire;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class FileManager {

    static BufferedReader r;
    static File f;
    /*try {
			r = new BufferedReader(new FileReader(
					"/Users/pankaj/Downloads/myfile.txt"));
			String line = r.readLine();
			while (line != null) {
				System.out.println(line);
				// read next line
				line = r.readLine();
			}
			r.close();
		} catch (IOException e) {
			e.printStackTrace();
		}*/

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
        } catch (IOException e1) {}
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
        } catch (IOException e1) {}
        try {
            result = r.readLine();
        } catch (IOException e1) {}
        try {
            r.close();
        } catch (IOException e) {}
        return result;
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
