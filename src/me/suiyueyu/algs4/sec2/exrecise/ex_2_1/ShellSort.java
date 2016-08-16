package me.suiyueyu.algs4.sec2.exrecise.ex_2_1;

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

    public static void sort(Comparable[] a, canGeneratShellSequence c) {
        int N = a.length;
        int sequenceLen = 0;
        while (c.hasNext(N)) {
            sequenceLen++;
        }

        c.clear();

        int[] sequence = new int[sequenceLen];
        int i = 0;
        while (c.hasNext(N)) {
            sequence[i++] = c.next();
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
        SingleGenerateShellSequence s = new SingleGenerateShellSequence();
        while (s.hasNext(1000)) {
            // TODO: 2016/8/16 生成的不对
            System.out.println(s.next());
        }
    }
}

interface canGeneratShellSequence {
    int next();

    boolean hasNext(int upperBound);

    void clear();
}

class SingleGenerateShellSequence implements canGeneratShellSequence {
    private int h = 0;
    private int last = 1;

    public int next() {
        h = h * 3 + 1;
        last = h;
        return h;
    }

    public boolean hasNext(int upperBound) {
        return last < upperBound;
    }

    @Override
    public void clear() {
        h = 0;
    }
}

class DifficultGenerateShellSequence implements canGeneratShellSequence {
    private int i = 1;
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
    public boolean hasNext(int upperBound) {
        return curr < upperBound;
    }

    @Override
    public void clear() {
        i = 1;
        j = 2;
    }
}