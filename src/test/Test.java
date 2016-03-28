package test;

import java.util.Scanner;

/**
 * Created by yzcc on 2015/5/23.
 */
public class Test {

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int num;

        for (int i = 0; i >= 0; i++) {
            System.out.println("input some num");

            num = input.nextInt();
            if (num == 0) {
                System.out.println("最大值最小值" + max + " " + min);
                return;
            }
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }


        }
        System.out.println("max : " + max + " ; min : " + min);
    }
}
