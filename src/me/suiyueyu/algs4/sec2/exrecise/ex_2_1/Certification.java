package me.suiyueyu.algs4.sec2.exrecise.ex_2_1;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by yzcc on 2016/8/16.
 * 2.1.16 验证。编写一个check() 方法，调用sort()对任意数组排序。
 * 如果排序成功而且数组中的所有对象均没有被修改则返回true，否则返回false
 * 不要假设sort() 只能通过exch() 来移动数据，可以信任并使用 Arrays.sort()
 */
public class Certification {

    /**
     * 这个sort我就随便的调一下 Insertion好了
     * <p>
     * 如果排序成功而且数组中的所有对象均没有被修改则返回true，否则返回false
     */
    public boolean check(SortAlgs sortalgs, int[] a) {
        int[] b = a.clone();
        Arrays.sort(b);

        sortalgs.sort(a);

        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] a = {3, 2, 1, 5, 4};
        Certification c = new Certification();
        boolean result = c.check(new Insertion(), a);
        System.out.println(result);

    }
}

final class Insertion implements SortAlgs {

    @Override
    public void sort(int[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    @Override
    public void exch(int[] a, int i, int j) {
        int tmp;
        tmp = a[j];
        a[j] = a[i];
        a[i] = tmp;
    }

    private boolean less(int v, int w) {
        return v < w;
    }
}