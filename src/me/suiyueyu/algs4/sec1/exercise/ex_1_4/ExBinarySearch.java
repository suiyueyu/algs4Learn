
package me.suiyueyu.algs4.sec1.exercise.ex_1_4;

import java.util.Arrays;


/**
 * Created by yzcc on 2016/8/10.
 * 1.4.10 修改二分查找算法，使之总是返回和被查找元素的键匹配的索引最小的元素
 * (且仍然能够保证对数级别的运行时间)
 */
public class ExBinarySearch {
    public static int rank(int key, int[] a) {
        return rank(key, a, 0, a.length - 1);
    }

    public static int rank(int key, int[] a, int lo, int hi) {

        while (lo <= hi) {
//            int mid = (hi+lo) / 2;
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) {
                hi = mid - 1;
            } else if (key > a[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static int rankLower(int key, int[] a) {

        return rankLower(key, a, 0, a.length - 1);
    }

    public static int rankLower(int key, int[] a, int lo, int hi) {

        Arrays.sort(a);


        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] == key) {
                hi = mid;
                if (lo == hi) {
                    return lo;
                }
            } else if (a[mid] > key) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        // 见测试

    }
}

