package me.suiyueyu.algs4.sec2.exrecise.ex_2_2;

/**
 * Created by yzcc on 2016/8/17.
 * <p>
 * 2.2.10 快速归并。 实现一个merge() 方法，按照降序将a[] 的后半部分复制到 aux[]，然后将其归并回a[]中。
 * 这样就可以去掉内循环中检测某半边是否用尽的代码。
 * <p>
 * 注意：这样排序的后果是不稳定的(请见 2.5.1.8)
 */
public class FasterMerge {
    public static void sort(Comparable[] a) {
        int N = a.length;
        Comparable[] aux = new Comparable[N];

        for (int sz = 1; sz < N; sz = sz + sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                merge(a, lo, lo + sz - 1,
                        Math.min(lo + 2 * sz - 1, N - 1), aux);
            }
        }
    }

    /**
     * merge
     * 快速归并。 实现一个merge() 方法，按照降序将a[] 的后半部分复制到 aux[]，然后将其归并回a[]中。
     * 这样就可以去掉内循环中检测某半边是否用尽的代码。
     *
     * @param a
     * @param lo
     * @param mid
     * @param hi
     * @param aux 辅助数组
     */
    public static void merge(Comparable[] a, int lo, int mid, int hi, Comparable[] aux) {

        int i = lo, j = hi;

        // lo ~ mid
        for (int k = lo; k <= mid; k++) {
            aux[k] = a[k];
        }

        // 后半部分倒序放入 mid+1 ~ hi
        for (int k = hi; k > mid; k--) {
            aux[mid + 1 + hi - k] = a[k];
        }

        for (int k = lo; k <= hi; k++) {
            // 这里不用 i > mid 或者 j > hi 因为
            // 一旦i到头，i = mid+1，是倒序里面最大的，比较一定会 less
            // 或者j到头，j = mid 是正序里面最大的，比较一定false
            if (less(aux[i], aux[j])) a[k] = aux[i++];
            else a[k] = aux[j--];
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
}
