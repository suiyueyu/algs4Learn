package me.suiyueyu.algs4.sec2.exrecise.ex_2_1;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by yzcc on 2016/8/16.
 * 2.1.11 将希尔排序中实时计算递增序列改为预先计算并存储在一个数组中
 */
public class ShellSort {

    public static void sortWithoutSequence(Comparable[] a) {
        int N = a.length;
        int h = 1;

        while (h < N / 3) h = h * 3 + 1;

        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h)
                    exch(a, j, j - h);
            }
        }
    }

    private static int[] generateShellSequence(int N, canGeneratShellSequence c) {

        int sequenceLen = 0;

        int curr = c.next();
        while (curr < N) {
            sequenceLen++;
            curr = c.next();
        }
//        System.out.println("seqlen :" + sequenceLen);

        c.clear();

        int[] sequence = new int[sequenceLen];
        sequence[0] = c.next();
        for (int i = 1; i < sequenceLen; i++) {
            sequence[i] = c.next();
        }
//
//        for(int i : sequence){
//            System.out.print(i + " ");
//        }
//        System.out.println();

        return sequence;
    }

    public static void sort(Comparable[] a) {
        final int[] shellSequence = generateShellSequence(a.length, new DifficultGenerateShellSequence());

        for (int k = shellSequence.length - 1; k >= 0; k--) {
            int h = shellSequence[k];

            for (int i = h; i < a.length; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
        }

    }

    public static boolean less(Comparable a, Comparable b) {
        if (a.compareTo(b) < 0) {
            return true;
        }
        return false;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable tmp;
        tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
    }
}

interface canGeneratShellSequence {
    int next();

    void clear();
}

class SingleGenerateShellSequence implements canGeneratShellSequence {
    private int h = 0;

    public int next() {
        h = h * 3 + 1;
        return h;
    }

    @Override
    public void clear() {
        h = 0;
    }
}

class DifficultGenerateShellSequence implements canGeneratShellSequence {
    private int i = 0;
    private int j = 2;
    private int curr;

    @Override
    public int next() {

        double a1 = 9 * Math.pow(4, i) - 9 * Math.pow(2, i) + 1;
        double a2 = Math.pow(4, j) - 3 * Math.pow(2, j) + 1;

        assert a1 < Integer.MAX_VALUE : "out of bound";
        assert a2 < Integer.MAX_VALUE : "out of bound";

//        System.out.println("a1 : "+ a1 + " a2: " + a2 );
        curr = Integer.min((int) a1, (int) a2);
        if (a1 < a2) {
            i++;
        } else {
            j++;
        }
        return curr;
    }

    @Override
    public void clear() {
        i = 0;
        j = 2;
    }
}