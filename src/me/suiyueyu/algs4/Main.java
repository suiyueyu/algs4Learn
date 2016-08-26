package me.suiyueyu.algs4;

import edu.princeton.cs.algs4.StdOut;
import sun.nio.cs.Surrogate;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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


        try {
            try {
                throw new Sneeze();

            } catch (Annoyance a) {
                System.out.println("Caught Annoyance");
                throw a;
            }
        } catch (Sneeze s) {
            System.out.println("Caught Sneeze");
            return;
        } finally {
            System.out.println("Hello World!");
        }

    }

}

class Annoyance extends Exception {
}

class Sneeze extends Annoyance {
}
