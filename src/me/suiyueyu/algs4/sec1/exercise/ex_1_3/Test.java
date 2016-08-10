package me.suiyueyu.algs4.sec1.exercise.ex_1_3;

/**
 * Created by yzcc on 2016/8/9.
 */
abstract public class Test<Item> {
    final public int label = 0;

    public Test push(Item item) {
        Test2<Item> head = new Test2<Item>();
        head.item = item;
        head.test = this;
        return head;
    }
}

final class Nnull<Item> extends Test<Item> {
    final public int label = 1;

}

class Test2<Item> extends Test<Item> {
    public Item item;
    final public int label = 2;
    Test<Item> test;

    public static void main(String[] args) {
        Test2<String> test = new Test2<>();
        test.item = "a";
        Test2<String> testt = new Test2<>();
        testt.item = "12";
        testt.test = test;
        test.test = new Nnull<String>();
    }
}

