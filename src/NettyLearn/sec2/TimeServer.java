package NettyLearn.sec2;

import java.io.IOException;

/**
 * Created by yzcc on 2016/6/6.
 */
public class TimeServer {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {

            }
        }


    }
}
