package JavaIOLearn;

import java.io.*;

/**
 * Created by yzcc on 2016/8/26.
 */
public class TestEOF {
    public static void main(String[] args) throws IOException {
        DataInputStream in = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream("TestEOF.java")
                )
        );
        while (in.available() != 0) {
            System.out.print((char) in.readByte());
        }


    }
}
