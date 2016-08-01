package me.suiyueyu.algs4.sec1.exercise;

import edu.princeton.cs.algs4.In;
import me.suiyueyu.algs4.sec1.model.Date;
import me.suiyueyu.algs4.sec1.model.Counter;

/**
 * Created by yzcc on 2016/8/1.
 */
public class Ex_1_2 {

    // 1.2.4
    public static void ex_1_2_4() {

        String string1 = "hello";
        String string2 = string1;
        string1 = "world";
        System.out.println(string1);
        System.out.println(string2);
    }

    // 1.2.5
    public static void ex_1_2_5() {
        String s = "Hello World";
        s.toUpperCase();
        s.substring(6, 11);
        System.out.println(s);
        StringBuilder s1 = new StringBuilder("Hello World");
//        s1.toUpperCase();'
    }

    /**
     * 如果字符串s中的字符循环移动任意位置之后能够得到另一个字符串t，那么s就被称为t的回环变位(circular rotation)。
     * 例如，ACTGACG就是TGACGAC的一个回环变位,反之亦然。判定这个条件在基因组的序列的研究中是很重要的。
     * 便携一个程序检查两个给定的字符串s和t是否互为回环变位。
     * hint : 答案只需要一行用到indexOf(), length(), 字符串连接的代码
     *
     * @param s 字符串1
     * @param t 字符串2
     * @return 是否为回环变位
     */
    public static boolean ex_1_2_6(String s, String t) {
        return s.length() == t.length() && (s.concat(s).indexOf(t) > 0);
    }

    /**
     * 以下递归函数的返回值是什么？
     * 倒转 reverse
     */
    public static void ex_1_2_7() {
        String test1 = "abcde";
        String test2 = "abcdef";
        System.out.println(mystery(test1));
        System.out.println(mystery(test2));

    }

    private static String mystery(String s) {
        int N = s.length();
        if (N <= 1) return s;
        String a = s.substring(0, N / 2);
        String b = s.substring(N / 2, N);
        return mystery(b) + mystery(a);
    }

    /**
     * 修改BinarySearch(详见1.1.10.1中的二分查找代码)，使用Counter统计在有查找中被检查的键的总数
     * 并在查找全部结束后打印该值。提示：在main()中创建一个Counter对象并将它作为参数传给rank()
     */
    public static void ex_1_2_9() {
        int[] test1 = {1, 2, 3, 4, 5};
        int[] test2 = {1, 2, 3, 4, 5, 6};
        Counter counter = new Counter("key accessed number");
        System.out.println(binarySearch_ex_1_2_9(2, test1, counter));
        counter.clear();
        System.out.println(binarySearch_ex_1_2_9(2, test2, counter));
        counter.clear();
        System.out.println(binarySearch_ex_1_2_9(1, test2, counter));
        counter.clear();
        System.out.println(binarySearch_ex_1_2_9(6, test2, counter));
        counter.clear();
        System.out.println(binarySearch_ex_1_2_9(0, test2, counter));
        counter.clear();
    }

    private static int binarySearch_ex_1_2_9(int key, int[] a, Counter count) {
        return binarySearch_ex_1_2_9(key, a, 0, a.length - 1, count);
    }

    private static int binarySearch_ex_1_2_9(int key, int[] a, int lo, int hi, Counter count) {
        if (hi < lo) return -1;
        int mid = (hi - lo) / 2 + lo;

        // 检查的key++
        count.increment();

        if (a[mid] == key) {
            // 命中
            System.out.println(count);
            return mid;
        } else if (key < a[mid]) {
            return binarySearch_ex_1_2_9(key, a, lo, mid - 1, count);
        } else {
            return binarySearch_ex_1_2_9(key, a, mid + 1, hi, count);
        }
    }

    // TODO: 2016/8/1 1.2.10~1.2.12有点不想做了
    public static void ex_1_2_11() {
    }

    /**
     * 1.2.13 用我们对Date的实现(见表1.2.12)为模版实现Transaction的类型
     * 1.2.14 用我们对Date中的equals()方法的实现（请见1.2.5.8节中的Date类代码框）作为模板，
     * 实现Transaction中的equals方法。
     */
    public static void ex_1_2_12_and_13() {

    }

    public class Transaction implements Comparable<Transaction> {
        public String _who;
        public Date _when;
        public double amount;

        public Transaction(String who, Date when, double amount) {
            this._who = who;
            this._when = when;
            this.amount = amount;
        }

        public Transaction(String transaction) {

        }

        public String who() {
            return _who;
        }

        public Date when() {
            return _when;
        }

        public double amount() {
            return amount;
        }

        public String toString() {
            return who() + "/" + when() + " / " + amount();
        }

        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }
            if (o == this) {
                return true;
            }
            if (o.getClass() == this.getClass()) {
                Transaction that = (Transaction) o;
                if (this._when == that._when
                        && this._who == that._who
                        && Double.compare(this.amount, that.amount) == 0) {
                    return true;
                }
            }
            return false;
        }

//        public int hashCode(){
//
//        }

        @Override
        public int compareTo(Transaction o) {
            return 0;
        }
    }

    /**
     * 基于string的split()方法实现In中的静态方法readInts()
     */
    public static void ex_1_2_15() {

    }

    private static int[] readInts(String name) {
        In in = new In(name);
        String input = in.readAll();
        String[] words = input.split("\\s+");
        int[] ints = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            ints[i] = Integer.parseInt(words[i]);
        }
        return ints;
    }

    // 1.2.16 有理数 ： 为有理数实现一个不可变数据类型Rational。支持加减乘除操作
    // 1.2.17 有理数实现的健壮性: 在Rational的开发中使用断言来防止溢出。
    public static void ex_1_2_16

    {

    }

    /**
     * Rational 有理数
     *
     * @param numerator   分子
     * @param denominator 分母
     *                    public Rational plus(Rational b); 加
     *                    public Rational minus(Rational b); 减
     *                    public Rational times(Rational b); 乘
     *                    public Rational divides(Rational b); 除
     *                    todo 201608012307 没写完
     */

    private class Rational {
        private int numerator;
        private int denominator;

        public Rational(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
        }

        public Rational plus(Rational b);

        public Rational minus(Rational b);

        public Rational times(Rational b);

        public Rational divides(Rational b);

        public boolean equals() {

        }

        public String toString() {
            System.out.println(numerator + "/" + denominator);
        }
    }


    public static void main(String[] args) {
        ex_1_2_9();
    }
}
