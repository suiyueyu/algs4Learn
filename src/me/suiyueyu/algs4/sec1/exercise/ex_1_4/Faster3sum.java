package me.suiyueyu.algs4.sec1.exercise.ex_1_4;

import java.util.Arrays;

/**
 * Created by yzcc on 2016/8/10.
 * 1.4.15 作为热身，使用一个线性级别的算法（而非基于二分查找的线性对数级别的算法）
 * 实现TwoSumFaster来计算已排序的数组中和为0的整数对的数量。用相同的思想为3-sum问题给出一个平方级别的算法
 * <p>
 * 这个也没想出来，是看了别人的实现
 *
 * @see <a href="http://www.cnblogs.com/Tinyshine/p/4781390.html">别人的实现</a>
 */
public class Faster3sum {

    /**
     * 考虑到数组a，从lo到hi之间，和为sum的对数
     *
     * @param sum
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    public static int twoSumCount(int sum, int[] a, int lo, int hi) {
        Arrays.sort(a);

        int count = 0;

        int hiNum;
        int loNum;

        while (lo < hi) {
            if (a[lo] + a[hi] < sum) {
                lo++;
            } else if (a[lo] + a[hi] > sum) {
                hi--;
            } else {
                // 考虑 -1, -1, -1, -1, 1, 1, 1
                hiNum = 1;// 等于a[hi]的元素数，例如上面 = 1的有3个
                hi--;

                while (hi > 0 && a[hi] == a[hi + 1]) {
                    hi--;
                    hiNum++;
                }

                loNum = 1;
                lo++;

                while (a.length > lo && a[lo - 1] == a[lo]) {
                    loNum++;
                    lo++;
                }
                // 接下来就是考虑
                // 0 0 0 0 0这种了
                if (a[lo - 1] == sum / 2) // a[lo-1] == 0
                    count += (loNum - 1) * hiNum / 2;
                else count += loNum * hiNum;
            } // end for a[lo] + a[hi] == 0

        }
        return count;
    }

    /**
     * 默认的计算的是a之间，和为0的对数
     *
     * @param a
     * @return
     */
    public static int twoSumCount(int[] a) {
        return twoSumCount(0, a, 0, a.length - 1);
    }

    /**
     * 计算数组中3个数为0的数目
     * 那个人的实现不行，它还是排除了重复元素
     * 而且数的会重复
     *
     * @param a
     * @return
     * @test 不想测试了，数的很麻烦
     */
    public static int threeSumCount(int[] a) {

        int count = 0;
        for (int i = 0; i < a.length; i++) {
            count += twoSumCount(-a[i], a, i + 1, a.length - 1);
        }
        return count;
    }

}
