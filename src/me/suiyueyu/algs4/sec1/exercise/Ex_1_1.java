package me.suiyueyu.algs4.sec1.exercise;

import stdlib.In;
import stdlib.StdIn;
import stdlib.StdOut;

import java.util.Arrays;

/**
 * Created by yzcc on 2016/7/31.
 */
public class Ex_1_1 {
    /**
     * 输出如下数组的输出
     * 0 1 2 3 4 4 3 2 1 0
     */
    public static void ex1_1_12() {

        int[] a = new int[10];
        for (int i = 0; i < 10; i++) {
            a[i] = 9 - i;
        }
        for (int i = 0; i < 10; i++) {
            a[i] = a[a[i]];
        }
        for (int i : a) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    /**
     * 编写一段代码，打印出一个M行N列的二维数组的转置（交换行和列）
     */
    public static void ex_1_1_14(int[][] arr, int M, int N) {
        // 不考虑异常输入
        arr = arr.clone();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(arr[j][i] + " ");
            }
            System.out.println();
        }

    }

    /**
     * 接受一个整数N，返回不大于log_2 N的最大整数。不要使用Math库
     *
     * @param N 输入的正整数
     * @return 不大于log_2 N的最大整数
     */
    public static int ex_1_1_14(int N) {
        // 异常处理略

//        int power = Math.pow(2, nth);
//        if(Math.pow(2, nth) > N){
//            return result - 1;
//        }
        int nth = 0;
        int power = 1;
        while ((power < (Integer.MAX_VALUE / 2)) && (power < N)) {
            nth += 1;
            power *= 2;
        }
        return nth - 1;
    }

    /**
     * 编写一个静态方法histogram(), 接受一个整型数组a[]和一个整数M为参数并返回一个大小为M的数组，
     * 其中第i个元素的值为整数i在参数数组中出现的次数。如果a[]中的值均在0到M-1之间，返回数组中所有
     * 元素之和应该和a.length相等。
     *
     * @param a 传入的数组
     * @param M 返回大小为M的数组，统计0~M-1之间的数在数组中出现的次数
     * @return 大小为M的数组
     */
    public static int[] histogram_Ex_1_1_15(int[] a, int M) {
        if (a.length == 0) {
            return null;
        }
        // stackoverflow 不管
        int[] result = new int[M];
        for (int res : result) {
            res = 0;
        }
        for (int i : a) {
            if (i >= 0 && i < M) {
                result[i]++;
            }
        }
        return result;
    }

    /**
     * 输出exR1(6)的返回值
     */
    public static void ex_1_1_16() {
        // exR1(1) = 11
        // exR1(2) = 22
        // exR1(3) = 3113
        // exR1(4) = 114224
        // exR1(6) = 3113 6 114224 6
        System.out.println(exR1(6));
    }

    private static String exR1(int n) {
        if (n <= 0) return "";
        return exR1(n - 3) + n + exR1(n - 2) + n;
    }

    /**
     * 请看以下递归函数
     * mystery(2, 25) 和
     * mystery(3, 11)的返回值是多少？
     * 给定正整数a和b，mystery(a, b)计算的结果是什么？
     * 将代码中的+替换为*，并将return 0改为return 1， 然后回答相同的问题
     */
    public static void ex_1_1_18() {
        System.out.println(mystery(2, 25));// 50
        System.out.println(mystery(3, 11));// 33
        // 计算的是a*b,
        // 把b展开成二进制 b = b1 * 2^k + b2 * 2^(k-1) + ...
        // b_x \in {0, 1}
        // 例如 11 = 1011
        // mystery(3, 11) = 3 * (2^3 + 2^1 + 2^0)
        // 结合律
        // mystery(a, b)
        // a * 2^k * b_k + a* 2^k-1 * b_k-1 +...

        // 如果改成*，以及return 1
        // a^b
        System.out.println(mystery2(2, 25));// 33554432
        System.out.println(mystery2(3, 11));// 177147
    }

    private static int mystery(int a, int b) {
        if (b == 0) return 0;
        if (b % 2 == 0) return mystery(a + a, b / 2);
        return mystery(a + a, b / 2) + a;
    }

    private static int mystery2(int a, int b) {
        if (b == 0) return 1;
        if (b % 2 == 0) return mystery2(a * a, b / 2);
        return mystery2(a * a, b / 2) * a;
    }

    // 在计算机上运行以下程序, 见辅助类
    // 计算机用这段程序在1个小时之内能够得到F(N)结果的最大N值是多少？
    // 开发F(N)的一个更好地实现，用数组保存已经计算过的值。
    public static void ex_1_1_19() {
        Fibonacci.solve(20);
    }

    /**
     * 编写一个递归的静态方法计算ln(N!)的值
     */
    public static void ex_1_1_20() {
        double[] resultCache = new double[100];
//        for (double result : resultCache){
//            result = -1.0;
//        }
        for (int i = 0; i < 100; i++) {
            resultCache[i] = -1.0;
        }
        // 这个不用math库怎么做?逐步逼近?
        for (int i = 1; i < 20; i++) {
            System.out.println(i + " : " + cal_ex_1_1_20(i, resultCache));
        }
    }

    /**
     * 递归计算ln(N!)的值
     *
     * @param N
     * @param resultCache 计算过的值
     * @return 返回ln(N!)的值
     */
    private static double cal_ex_1_1_20(int N, double[] resultCache) {
        if (Double.compare(resultCache[N], -1.0) == 0) {
            // the result is unKnown
            if (N == 1) {
                resultCache[N] = 0;
            } else {
                // ln(N!) = ln((N-1)! ) + ln(N)
                resultCache[N] = resultCache[N - 1] + Math.log(N);
            }
        } else {
            // already known
        }
        return resultCache[N];
    }

    /**
     * 编写一段程序，从标准输入按照行读取数据。其中每一行包含一个名字和两个整数。然后用printf打印一个表格，
     * 每行的若干列数据包含名字，两个整数和第一个整数除以第二个整数的结果，精确到小数点后三位。
     * 输入格式：
     * Name Interger1 Integer2
     * 输出格式
     * | ------| ---------| ------   | ---------------   |
     * | Name  | Integer1 | Integer2 | Integer1/Integer2 |
     * | ----- | ---------| ------   | ------------------|
     * 边栏还是先不写了...
     */
    public static void ex_1_1_21() {
        String name;
        int i1, i2;
        double result;
        System.out.println("input like ");
        System.out.println("Name Interger1 Integer2");
        while (!StdIn.isEmpty()) {
            name = StdIn.readString();
            i1 = StdIn.readInt();
            i2 = StdIn.readInt();
            StdOut.printf("| %s | %d | %d | %.2f | \n ", name, i1, i2, (double) i1 / i2);
        }
    }

    /**
     * 使用1.1.6.4节中的rank()递归方法重新实现BinarySearch并追踪该方法的调用。
     * 每当该方法被调用时，打印出他的参数lo和hi并按照递归的深度缩进。提示：
     * 为递归方法添加一个参数来保存递归的深度。
     */
    public static void ex_1_1_22() {
        BinarySearch.solve();
    }


    public static void main(String[] args) {
        ex_1_1_22();

    }

    public static void test(int[] a) {

    }

}

// Ex_1_1_19
class Fibonacci {
    public static long F(int N) {
        hitTimes[N]++;
        if (N == 0) return 0;
        if (N == 1) return 1;
        return F(N - 1) + F(N - 2);
    }

    // 我的答案
    public static long[] result = new long[150];
    public static int[] hitTimes = new int[150];

    public static void solve(int N) {
        if (N > 150) {
            return;
        }
        // 未计算的用-1标记
        for (int i = 0; i < 150; i++) {
            result[i] = -1;
            hitTimes[i] = 0;
        }
        for (int i = 0; i < 20; i++) {
            System.out.println(i + " " + F(i, result));
//            System.out.println(i + " " + F(i));
        }
        for (int i = 0; i < 20; i++) {
            System.out.println(i + " " + hitTimes[i]);
        }
    }

    public static void main(String[] args) {
        solve(20);
    }

    /**
     * 斐波那契，递归 已经求结的放入缓存
     * 算到F(93)好像就爆Long上界了...
     *
     * @param N
     * @param result
     * @return
     */
    public static long F(int N, long[] result) {
        // 还不知道的解
        if (result[N] == -1) {
            if (N == 0) {
                result[N] = 0;
            } else if (N == 1) {
                result[N] = 1;
            } else {
                result[N] = F(N - 1, result) + F(N - 2, result);
            }
        } else {
            // already known

        }
        hitTimes[N]++;
        return result[N];
    }
}

// Ex_1_1_22
class BinarySearch {
    public static int rank(int key, int[] a) {
//        return rank(key, a, 0, a.length, 0);
        return rank(key, a, 0, a.length, 0);
    }

    public static int rank(int key, int[] a, int lo, int hi) {
        if (lo > hi) {
            return -1;
        } else {
            int mid = lo + (hi - lo) / 2;
            if (key == a[mid]) return mid;
            else if (key > a[mid]) return rank(key, a, mid + 1, hi);
            else return rank(key, a, lo, mid - 1);
        }
    }

    // 我的答案
    public static final int blankWidth = 4;

    public static int rank(int key, int[] a, int lo, int hi, int recursiveDepth) {

        if (lo > hi) {
            return -1;
        } else {
// 大约输出就是这样
//            >| lo : 0                                         hi : 10 |
//            ->                    | lo : 6                 hi : 10 |
//                    -->                    | lo : 6     hi : 7 |
//                    --->                        | lo : 7 hi : 7 |
            for (int i = 0; i < recursiveDepth; i++) {
                System.out.print("-");// 4个空格
            }
            System.out.print(">");
            double fraction;
            int intervalLength;
            // 0 - lo 区间
            fraction = (double) (lo) / a.length;
            intervalLength = (int) (fraction * a.length);
            for (int i = 0; i < intervalLength - 1; i++) {
                System.out.print("    ");// 4个空格
            }
            // lo - hi 区间
            System.out.print("| lo : " + lo);
            fraction = (double) (hi - lo + 1) / a.length;
            intervalLength = (int) (fraction * a.length);
            for (int i = 0; i < intervalLength - 1; i++) {
                System.out.print("    ");// 4个空格
            }
            System.out.println(" hi : " + hi + " |");

            int mid = lo + (hi - lo) / 2;
            if (key == a[mid]) return mid;
            else if (key > a[mid]) return rank(key, a, mid + 1, hi, recursiveDepth + 1);
            else return rank(key, a, lo, mid - 1, recursiveDepth + 1);
        }
    }

    public static void solve() {
        int[] a = {1, 2, 3,
                4, 5, 6,
                7, 8, 9, 10};
        System.out.println(rank(8, a));
    }

    public static void main(String[] args) {
        int[] whiteList = In.readInts(args[0]);
        boolean isPrintInWhiteList = true;
        if (args.length > 1 && args[1] != null) {
            String c = args[1];
            // 参数后带+ 打印不在白名单上的值
            if (c.equals("+")) {
                isPrintInWhiteList = false;
            }
            // - 打印在白名单上的值
            else {
                isPrintInWhiteList = true;
            }
        }
        Arrays.sort(whiteList);
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            // 不在白名单上
            if (rank(key, whiteList) < 0) {
                // 打印不在的
                if (!isPrintInWhiteList) {
                    System.out.print(key);
                }
            } else {
                // 输入的在白名单上
                if (isPrintInWhiteList) {
                    System.out.print(key);
                }
            }
        }
    }
}