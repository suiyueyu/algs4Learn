package JavaIOLearn;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by yzcc on 2016/8/26.
 */
public class MemoryInput {
    public static void main(String[] args) throws IOException {
        StringReader in = new StringReader(BufferedInputFile.read("MemoryInput.java"));
        int c;
        while ((c = in.read()) != -1) {
            System.out.print((char) c);
        }
    }
}
