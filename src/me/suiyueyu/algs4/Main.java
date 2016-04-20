package me.suiyueyu.algs4;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        int f=0;
//        int g = 1;
//        for (int i = 0; i <= 15; i++) {
//            System.out.println("f " + f + " g: " + g);
//            f = f+g;
//            g = f-g;
//        }
//    System.out.println((char)('a'+4));

//        Scanner stdin = new Scanner(System.in);
//
//        while (stdin.hasNext()) {
//            String s = stdin.next();
//            char[] chars = s.toCharArray();
//            System.out.println(s);
//            for (char c : chars) {
////                if(!Character.isLetter(c)) {
////
////                }
//                System.out.println((int)c);
//            }
//        }

//        String cmd = "ping www.baidu.com";
        String cmd = "cmd.exe /c start C:\\Users\\yzcc\\Desktop\\中亚国家.xlsx";

        Runtime run = Runtime.getRuntime();
        try {
            Process p = run.exec(cmd);
            BufferedInputStream in = new BufferedInputStream(p.getInputStream());
            BufferedReader inBr = new BufferedReader(new InputStreamReader(in));

            String lineStr;

            while ((lineStr = inBr.readLine()) != null) {
                System.out.println(lineStr);

                if (p.waitFor() != 0) {
                    if (p.exitValue() == 1) {
                        System.out.println("命令运行失败");
                    }
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}

