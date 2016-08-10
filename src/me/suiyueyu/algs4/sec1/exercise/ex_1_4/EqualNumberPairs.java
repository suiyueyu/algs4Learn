package me.suiyueyu.algs4.sec1.exercise.ex_1_4;

import me.suiyueyu.algs4.sec1.BinarySearch;

import java.util.Arrays;

/**
 * Created by yzcc on 2016/8/10.
 * 1.4.8 编写一个程序，计算输入文件中相等的整数对的数量。
 * 如果你的第一个程序是平方级别的，请继续思考并用Array.sort() 给出一个线性对数级别的回答
 *
 * @see <a href = "http://www.cnblogs.com/Tinyshine/p/4781390.html">看别人的时候发现写错了</a>
 */
public class EqualNumberPairs {
    public static int numbers(int[] a) {
        Arrays.sort(a);
        int number = 0;
        int j;
        for (int i = 0; i < a.length; i++) {
            j = i + 1;
            while (j < a.length && a[j] == a[i]) {
                number++;
                j++;
                // 这样的话 1 2 2 2
                // 就有3对<2, 2>了
            }

        }
        return number;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {1, 2, 2, 2, 4, 5};
        System.out.println(EqualNumberPairs.numbers(a));
        System.out.println(EqualNumberPairs.numbers(b));
    }
}
