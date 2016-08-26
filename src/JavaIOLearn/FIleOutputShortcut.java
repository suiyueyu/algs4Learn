package JavaIOLearn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

/**
 * Created by yzcc on 2016/8/26.
 */
public class FIleOutputShortcut {
    static String file = "FileOutputShortcut.out";

    public static void main(String[] args) throws IOException {


        BufferedReader in = new BufferedReader(
                new StringReader(
                        BufferedInputFile.read("FIleOutputShortcut.java")
                )
        );

        PrintWriter out = new PrintWriter(file);
        int lineCount = 1;
        String s;
        while ((s = in.readLine()) != null) {
            out.println(lineCount++ + ": " + s);
        }
        out.close();
        System.out.println(BufferedInputFile.read(file));

    }
}
