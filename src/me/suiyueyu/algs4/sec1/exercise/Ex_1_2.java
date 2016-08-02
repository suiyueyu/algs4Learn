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
    public static void ex_1_2_16() {
//        Rational r1 = new Rational(1, 0);
        Rational r2 = new Rational(1, 2);
        Rational r3 = new Rational(1, 4);
        Rational r4 = new Rational(-1, 4);
        Rational r5 = new Rational(1, -4);

        System.out.println(r2);
        System.out.println(r2.plus(r3));
        System.out.println(r2.minus(r3));
        System.out.println(r3.plus(r4));// 0
        System.out.println(r3.plus(r5));// 0
        System.out.println(r3.times(r5));/// 1/16
        System.out.println(r3.divides(r5));


    }

    /**
     * 1.2.18 累加器的方差。以下代码为Accmulator类添加了var()和stddev()方法，
     * 他们计算了addDataValue()方法的参数的方差和标准差，验证这段代码。
     * 与直接对所有数据的平方求和的方法比较，这种实现可以更好地避免四舍五入产生的误差。
     */
    public static void ex_1_2_18() {

    }

    private class Accumulator {
        private double m;
        private double s;
        private int N;

        public void addDataValue(double x) {
            N++;
            s = s + 1.0 * (N - 1) / N * (x - m) * (x - m);
            m = m + (x - m) / N;
        }

        public double mean() {
            return m;
        }

        public double var() {
            return s / (N - 1);
        }

        public double stddev() {
            return Math.sqrt(this.var());
        }
    }

    /**
     * 1.2.19 为你在练习1.2.13中实现的Date和Transaction类型便携能够解析字符串数据的构造函数。
     * 它接受一个String参数指定的初始值，格式如表所示
     * |   类型          |   格式                          |   举例                  |
     * |   Date         |   由斜杠分割的整数                |  5/22/1939              |
     * |  Transaction   |   客户，日期和金额，由空白字符分割   |  Turing 5/22/1939 11.99 |
     * 见Transaction类
     */
    public static void ex_1_2_19() {
        System.out.println(new Transaction("Turing 5/22/1939 11.99"));
    }

    public static void main(String[] args) {
        ex_1_2_19();
    }
}

class Transaction implements Comparable<Transaction> {
    public String _who;
    public Date _when;
    public double amount;

    public Transaction(String who, Date when, double amount) {
        this._who = who;
        this._when = when;
        this.amount = amount;
    }

    /**
     * 1.2.13 字符串解析
     * 解析形如如下的字符串作为构造
     * Turing 5/22/1939 11.99
     *
     * @param transaction 解析的字符串
     */
    public Transaction(String transaction) {
        String[] fields = transaction.split("\\s+");
        this._who = fields[0];
        this._when = new Date(fields[1]);
        this.amount = Double.parseDouble(fields[2]);

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
        return who() + " " + when() + " / " + amount();
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
        // 这个地方的实现还没有说明
        return Double.compare(this.amount, o.amount);
    }
}


/**
 * Rational 有理数
 * 支持加减乘除操作
 * 在Rational的开发中使用断言来防止溢出。
 */

class Rational {
    private int numerator;
    private int denominator;

    public Rational(int numerator, int denominator) {
        // 这里输入就是int，所以先不考虑溢出
        this.numerator = numerator;
        this.denominator = denominator;
        assert denominator != 0 : "denominator cannot be 0";
        int gcd = gcd(numerator, denominator);
        this.numerator /= gcd;
        this.denominator /= gcd;
        // 保证分母不为负
        if (denominator < 0) {
            this.numerator *= -1;
            this.denominator *= -1;
        }
    }

    /**
     * 加法运算
     * 现在问题是两数相乘如何判断溢出呢？
     * 加法的判断
     * version 1
     * a + b - a == a from 桶哥
     * version 2
     * flag1 = x<0&&y<0&&(x+y)>0;
     * flag2 = x>0&&y>0&&(x+y)<=0;
     * return !flag1&&(!flag2);
     * 简单看了下，最简单的方法，还是类型提升，保证中间结果和结果的一直
     * @see <a href = "http://www.cnblogs.com/yhlx/archive/2013/03/11/2953635.html">link 1</a>
     * 计算时，如果发现中间结果原有类型无法放下，就类型提升到long
     * 返回结果时，发现原有类型无法放下计算结果，就变窄转换，丢弃int无法存储的部分
     * 相当于 (a + b) % (2^32 - 1)
     * @see <a href = "http://coolshell.cn/articles/11466.html">link from coolshell</a>
     * c中间要特别关注unsigned( 比如size_t) 到0或者负数的情况
     * @see <a href = "http://fengzixu.net/2014/12/02/%E4%B9%98%E6%B3%95%E6%BA%A2%E5%87%BA%E5%92%8C%E5%8A%A0%E6%B3%95%E6%BA%A2%E5%87%BA%E7%9A%84%E5%88%A4%E6%96%AD">link 3</a>
     * @param b 被加数
     * @return
     */
    public Rational plus(Rational b) {

        int denominatorGcd = gcd(this.denominator, b.denominator);
        // 用int的话, 溢出warning，就算是约分gcd还是可能会溢出
        long newDenominator = this.denominator / denominatorGcd * b.denominator;
        assert newDenominator < Integer.MAX_VALUE : "Ex_1_2.java - Rational - plus(Ration b) plus overflow an int";
        assert newDenominator > (-1 * Integer.MAX_VALUE) : "Ex_1_2.java - Rational - plus(Ration b) plus overflow an int";

        long newANumerator = this.numerator * (newDenominator / this.denominator);
        long newBNumerator = b.numerator * (newDenominator / b.denominator);// 先是乘法，再是加法，都可能溢出

//        assert newANumerator < Integer.MAX_VALUE : "Ex_1_2.java - Rational - plus(Ration b) plus overflow an int";
//        assert newBNumerator < Integer.MAX_VALUE : "Ex_1_2.java - Rational - plus(Ration b) plus overflow an int";

        long newNumerator = newANumerator + newBNumerator;
        assert newNumerator < Integer.MAX_VALUE : "Ex_1_2.java - Rational - plus(Ration b) plus overflow an int";
        assert newNumerator > (-1 * Integer.MAX_VALUE) : "Ex_1_2.java - Rational - plus(Ration b) plus overflow an int";

        // 到这就安全了
        int gcd = gcd((int) newNumerator, (int) newDenominator);
        return new Rational((int) newNumerator / gcd, (int) newDenominator / gcd);
    }

    /**
     * 减法
     * 看了下algs4中关于溢出和断言的例子
     * 还是先提升到long，然后判断 result > Integer.MAX_VALUE;
     * @see <a href = "http://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/StdRandom.java">examples from algs4 StdRandom.java</a>
     * <pre>
     * long sum = 0;
     * for (int i = 0; i < frequencies.length; i++) {
     *     if (frequencies[i] < 0)
     *         throw new IllegalArgumentException("array entry " + i + " must be nonnegative: " + frequencies[i]);
     *     sum += frequencies[i];
     * }
     * if (sum == 0)
     *     throw new IllegalArgumentException("at least one array entry must be positive");
     * if (sum >= Integer.MAX_VALUE)
     *     throw new IllegalArgumentException("sum of frequencies overflows an int");
     * </pre>
     * @param b
     * @return minus result
     */
    public Rational minus(Rational b) {
        int denominatorGcd = gcd(this.denominator, b.denominator);
        // 用int的话, 溢出warning，就算是约分gcd还是可能会溢出
        long newDenominator = this.denominator / denominatorGcd * b.denominator;
        assert newDenominator < Integer.MAX_VALUE : "Ex_1_2.java - Rational - minus(Ration b) minus overflow an int";
        assert newDenominator > (-1 * Integer.MAX_VALUE) : "Ex_1_2.java - Rational - minus(Ration b) plus overflow an int";

        // 先是乘法，再是加法，都可能溢出
        long newANumerator = this.numerator * (newDenominator / this.denominator);
        long newBNumerator = b.numerator * (newDenominator / b.denominator);

//        assert newANumerator < Integer.MAX_VALUE : "Ex_1_2.java - Rational - plus(Ration b) plus overflow an int";
//        assert newBNumerator < Integer.MAX_VALUE : "Ex_1_2.java - Rational - plus(Ration b) plus overflow an int";

        long newNumerator = newANumerator - newBNumerator;
        assert newNumerator < Integer.MAX_VALUE : "Ex_1_2.java - Rational - minus(Ration b) plus overflow an int";
        assert newNumerator > (-1 * Integer.MAX_VALUE) : "Ex_1_2.java - Rational - minus(Ration b) plus overflow an int";
        // 这里的断言其实有点问题，比如 1/3 + 1/6 = 3/6 = 1/2
        // 其中3/6这部分的中间结果，可能分子分母有溢出，但是最后约分后的1/2是可能不越界的
        // 解决来说，全程long计算，最后再考虑越界

        // 到这就安全了
        int gcd = gcd((int) newNumerator, (int) newDenominator);
        return new Rational((int) newNumerator / gcd, (int) newDenominator / gcd);
    }

    /**
     * 乘法，全程升格long运算
     *
     * @param b
     * @return
     */
    public Rational times(Rational b) {
//        int denominatorGcd = gcd(this.denominator, b.denominator);
        long newDenominator = this.denominator * b.denominator;
        long newNumerator = this.numerator * b.numerator;

        long gcd = gcd(newNumerator, newDenominator);
        newDenominator /= gcd;
        newNumerator /= gcd;

        assert newDenominator < Integer.MAX_VALUE : "Ex_1_2.java - Rational - times(Ration b) Denominator overflow an int";
//        assert newDenominator > (-1 * Integer.MAX_VALUE) : "Ex_1_2.java - Rational - times(Ration b) Denominator overflow an int";
        assert newNumerator < Integer.MAX_VALUE : "Ex_1_2.java - Rational - times(Ration b) Numerator overflow an int";
        assert newNumerator > (-1 * Integer.MAX_VALUE) : "Ex_1_2.java - Rational - times(Ration b) Numerator overflow an int";
//        assert newDenominator < Integer.MAX_VALUE : "Ex_1_2.java - Rational - minus(Ration b) plus overflow an int";

        return new Rational((int) newNumerator, (int) newDenominator);

    }

    /**
     * 除法是乘法的倒数
     * 除数不能为0
     *
     * @param b
     * @return
     */
    public Rational divides(Rational b) {
        assert b.numerator != 0 : "the b.Numerator cannot be 0 in divides operations";

        return times(new Rational(b.denominator, b.numerator));
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (o.getClass() == this.getClass()) {
            Rational that = (Rational) o;
            if (this.numerator == that.numerator
                    && this.denominator == that.denominator) {
                return true;
            }
        }
        return false;
    }

    // inner class cannot have static declarations
    // @see <a href=http://www.jianshu.com/p/452c1a5acd31>
    //    C/Java 的处理方式
    //    大多数语言的处理方式都与 C/Java 一致，采用了 (整数截断)truncate 除法。所以在 C/Java 语言中:
    //            -17 % 10 的计算结果如下：r = (-17) - (-17 / 10) x 10 = (-17) - (-1 x 10) = -7
    //            17 % -10 的计算结果如下：r = 17 - (17 / -10) x (-10) = (17) - (-1 x -10) = 7
    //            -17 % -10 的计算结果如下：r = (-17) - (-17 / -10) x (-10) = (-17) - (1 x -10) = -7
    //    文／丰俊文（简书作者）
    //    原文链接：http://www.jianshu.com/p/452c1a5acd31
    //    著作权归作者所有，转载请联系作者获得授权，并标注“简书作者”。
    public static int gcd(int p, int q) {
        if (q == 0) {
            return p;
        }
        int r = p % q;
        return gcd(q, r);
    }

    public static long gcd(long p, long q) {
        if (q == 0) {
            return p;
        }
        long r = p % q;
        return gcd(q, r);
    }

    public String toString() {
        return numerator + "/" + denominator;
    }

}


