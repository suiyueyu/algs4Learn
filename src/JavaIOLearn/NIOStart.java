package JavaIOLearn;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by yzcc on 2016/9/9.
 */
public class NIOStart {
    public static void main(String[] args) {
        try {
            FileInputStream fin = new FileInputStream("readandshow.txt");
            FileChannel fc = fin.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            fc.read(buffer);

            FileOutputStream fout = new FileOutputStream("writesomebytes.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
