package me.suiyueyu.algs4.sec2.exrecise.ex_2_1;

/**
 * Created by yzcc on 2016/8/16.
 * 2.1.24 插入排序的哨兵  在插入排序的实现中先找出最小的元素并将其置于数组的最左边，
 * 这样就能去掉内循环的判断条件 j>0。使用SortCompare来评估这种做法的效果。
 * 注意：这是一种常见的规避边界测试的方法，能够省略判断条件的元素通常被称为哨兵。
 * <p>
 * 2.1.25 不需要交换的插入排序。 在插入排序的视线中使较大元素右移一位只需要访问一次数组(而不是用exch())
 * 使用SortCompare来评估这种做法的效果
 */
public class InsertionSort {
    public static void sort(Comparable[] a) {
        int N = a.length;

        for (int i = a.length - 1; i > 0; i--) {
            if (less(a[i], a[i - 1])) {
                exch(a, i, i - 1);
            }
        }

//        if (a.length > 0){
//
//            int min = 0;
//            for (int i = 1; i < a.length; i++){
//                if (less(a[i], a[min])){
//                    min = i;
//                }
//            }
//            exch(a, 0, min);
//        }

        for (int i = 1; i < N; i++) {

            Comparable tmp = a[i];
            int j = i;

            // 在a[1], a[0]的地方，由于a[0] 最小，直接卡出去循环
            for (; less(tmp, a[j - 1]); j--) {
                a[j] = a[j - 1];
            }
            a[j] = tmp;
        }
    }

    public static void sort(int[] a) {
        int N = a.length;

        for (int i = N - 1; i > 0; i--) {
            if (a[i] < a[i - 1]) {
                exch(a, i, i - 1);
            }
        }

        for (int i = 1; i < N; i++) {
            int tmp = a[i];
            int j = i;
            for (; tmp < a[j - 1]; j--) {
                a[j] = a[j - 1];
            }
            a[j] = tmp;
        }
    }

    private static void exch(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private static boolean less(Comparable a, Comparable b) {

        if (a.compareTo(b) < 0) {
            return true;
        } else {
            return false;
        }
    }

    // TODO: 2016/8/16 后面的题目主要是比较算法的时间了，以后再写
}
