package me.suiyueyu.algs4.sec2.exrecise.ex_2_2;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by yzcc on 2016/8/18.
 * <p>
 * 2.2.12 次线性的额外空间。
 * 用大小M将数组分为 N/M 块(简单起见，设M是N的约数)。
 * 实现一个归并方法，使之所需的额外空间减少到 max(M, N/M) :
 * 1. 可以先将一个块看做一个元素，将块的第一个元素作为块的主键，用选择排序将快排序；
 * 2. 遍历数组，将第一块和第二块归并，完成后将第二块和第三块归并，等等
 * <p>
 * 这里提升，a[mid] < a[mid+1] 这个应该还是可以用的
 */
public class SublinearExtraSpaceMergeSort {

    private SublinearExtraSpaceMergeSort() {
    }

    /**
     * 这个M没法直接算吧?
     * 先偷懒直接传入了
     *
     * @param a
     * @param M 用大小M将数组分为 N/M 块
     *          实现一个归并方法，使之所需的额外空间减少到 max(M, N/M) :
     *          1. 可以先将一个块看做一个元素，将块的第一个元素作为块的主键，用选择排序将 block 排序；
     *          2. 遍历数组，将第一块和第二块归并，完成后将第二块和第三块归并，等等
     */
    public static void sort(Comparable[] a, int M) {
        int N = a.length;

        int extraSpaceLen = Math.max(M, N / M);

        Comparable[] aux = new Comparable[extraSpaceLen];

        sort(a, aux);
    }

    private static void sort(Comparable[] a, Comparable[] aux) {
        // 1. 可以先将一个块看做一个元素，将块的第一个元素作为块的主键，用选择排序将 block 排序；
        BlockSelection.sort(a, aux);

        int N = a.length;
        int sz = aux.length;

        // 2. 遍历数组，将第一块和第二块归并，完成后将第二块和第三块归并，等等
        for (int i = 0; i < N - sz; i += sz) {
            // 现在问题是，这两块里面都不是有序的啊, 怎么归并?
            merge(a, i, i + sz - 1, Math.min((i + 2 * sz - 1), N - 1), aux);
            show(a);
        }
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi, Comparable[] aux) {
//        注意i是0,  不是lo
        int i = 0, j = mid + 1;

//        for (int k = 0; k < aux.length; k++){
//            aux[k] = a[lo + k];
//        }
        // 这里没有这个点，相当于 sz=2的时候，逐步归并到一起，sz是不增长的
//        if (less(a[mid], a[mid+1]))
        System.arraycopy(a, lo, aux, 0, aux.length);

        for (int k = lo; k <= hi; k++) {
            if (i >= aux.length) a[k] = a[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[i], a[j])) a[k] = aux[i++];
            else a[k] = a[j++];
        }

    }


    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        Integer[] i = new Integer[]{7, 9, 6, 1, 8, 2, 4, 5, 3};

        sort(i, 3);
        show(i);
    }
}

/**
 * 为了上面的修改的，按块大小 (aux.length) 排序的选择排序
 */
class BlockSelection {
    public static void sort(Comparable[] a, Comparable[] aux) {
        int N = a.length;
        int sz = aux.length;

        for (int i = 0; i < N; i += sz) {
            // 注意都是block
            int min = i;
            for (int j = i + sz; j < N; j += sz) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, min, i, aux);
        }
    }

    public static void exch(Comparable[] a, int i, int j, Comparable[] aux) {
//        Comparable tmp = a[i];
//        a[i] = a[j];
//        a[j] = tmp;
//        for (int k = 0; k < aux.length; k++){
//            aux[k] = a[i + k];
//            a[i+k] = a[j+k];
//            a[j+k] = aux[k];
//        }
        // 试试看，玄学加速
        System.arraycopy(a, i, aux, 0, aux.length);
        System.arraycopy(a, j, a, i, aux.length);
        System.arraycopy(aux, 0, a, j, aux.length);

    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    private static void test() {

        Integer[] i = new Integer[]{1, 2, 3, 7, 8, 9, 4, 5, 6};
        Integer[] aux = new Integer[3];

        BlockSelection.sort(i, aux);
        show(i);

    }
}