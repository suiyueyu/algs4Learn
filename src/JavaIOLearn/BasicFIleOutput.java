package JavaIOLearn;

import java.io.*;

/**
 * Created by yzcc on 2016/8/26.
 */
public class BasicFIleOutput {
    static String file = "BasicFileOutput.out";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(
                new StringReader(
                        BufferedInputFile.read("BasicFileOutput.java")
                )
        );
        PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter(file)));
        int lineCount = 1;
        String s;
        while ((s = in.readLine()) != null)
            out.println(lineCount++ + ": " + s);
        out.close();

        System.out.println(BufferedInputFile.read(file));
    }
}
