package me.suiyueyu.someOJ;

import java.util.Arrays;

/**
 * Created by yzcc on 2016/9/6.
 * 题目描述
 * <p>
 * 对于一个字符串，和字符串中的某一位置，请设计一个算法，将包括i位置在内的左侧部分移动到右边，将右侧部分移动到左边。
 * 给定字符串A和它的长度n以及特定位置p，请返回旋转后的结果。
 * 测试样例：
 * "ABCDEFGH",8,4
 * 返回："FGHABCDE"
 */
public class StringRotation {
    public String rotateString(String A, int n, int p) {
        // write code here
        char[] arr = A.toCharArray();

        reverse(arr, 0, n - 1);

        reverse(arr, 0, n - 2 - p);

        reverse(arr, n - 1 - p, n - 1);

        return new String(arr);
    }

    public static void reverse(char[] a, int lo, int hi) {
        if (a == null) return;
        else {
            char tmp;
            int mid = lo + (hi - lo) / 2;
            for (int i = lo; i <= mid; i++) {
                tmp = a[i];
                a[i] = a[hi - (i - lo)];
                a[hi - (i - lo)] = tmp;
            }
        }

    }

}