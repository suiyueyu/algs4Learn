package me.suiyueyu.algs4.sec1.exercise.ex_1_4;

import java.util.Arrays;

/**
 * Created by yzcc on 2016/8/10.
 * 1.4.12 编写一个程序，打印给定的两个有序数组(含有N个int元素)中的所有公共元素，程序在最坏情况下
 * 所需的运行时间应该和logN成正比
 * <p>
 * 遍历是比较直接的方法，时间应该是较小数组的长度O(n)
 * <p>
 * 搜了下还有一种是利用hash方法去做，然后遍历hash，如果这个key的val=2，那么就是两个集合的交集
 * 这种方法依赖hash的insert，然后是一遍遍历
 * 拉链法+链表的话，是O(n)的插入
 */
public class CommonArrayElements {
    /**
     * 这道题假定了输入的数组已经有序
     * 且长度相等
     *
     * @param a
     * @param b
     */
    public static void printCommonElements(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);

        int i = 0,
                j = 0;
        if (a.length > 0) {
            int lastCommonElements = a[0] - 1;// 反正找一个不存在的元素
            for (; i < a.length && j < b.length; ) {
                if (a[i] == b[j]) {
                    if (a[i] != lastCommonElements) {
                        System.out.print(a[i] + " ");
                        lastCommonElements = a[i];
                    }
                    i++;
                    j++;
                } else if (a[i] < b[j]) {
                    i++;
                } else {
                    j++;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 4, 5, 5, 5, 7, 8};
        int[] b = {2, 5, 6, 8, 2, 5};

        CommonArrayElements.printCommonElements(a, b);
    }
}
