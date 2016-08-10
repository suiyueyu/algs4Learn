package me.suiyueyu.algs4.sec1.exercise.ex_1_4;

import java.util.Arrays;

/**
 * Created by yzcc on 2016/8/10.
 * 1.4.14 4-sum 为4-sum 设计一个算法
 */
public class FourSum {

    public static int count(int[] a) {
        // 排序先
        Arrays.sort(a);

        int cnt = 0;
        int pos = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                for (int k = j + 1; k < a.length; k++) {
                    // 对比了下人家做的，我这个版本肯定是错了，重新改下
//                    if (ExBinarySearch.rank( -a[i]-a[j]-a[k], a) > 0){
//                        cnt++;
//                    }
                    // 主要的问题是适应于如下的例子
                    // 索引  0  1  2  3 4 5 6 7
                    // 数组 -1 -1 -1 -1 3 3 3 3
                    // 这里查找(a[2] = -1, a[3] = -1, a[4] = 3)的时候，会
                    // 回头找到a[0] = -1，但是不应该回头找
                    // 为了这个，我把rank改成(int key, int[] a, int lo, int hi)
                    pos = ExBinarySearch.rankLower(-a[i] - a[j] - a[k], a, k + 1, a.length - 1);
                    if (pos > k) {
                        // 首先这样不会回头找了
                        // >0也是考虑起码也是3了
                        // 应对 -1 -1 -1 -1 3 3 3 3
                        cnt++;
                        pos++;
                        while (pos < a.length && a[pos] == a[pos - 1]) {
                            cnt++;
                            pos++;
                        }
                    }

                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] a = {-1, -1, -1, -1, 3, 3, 3, 3}; // 16 = c_4^3 * count(3)
        System.out.println(FourSum.count(a));
    }
}
