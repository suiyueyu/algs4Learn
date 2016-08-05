package me.suiyueyu.algs4.sec1.exercise.ex_1_3;

/**
 * Created by yzcc on 2016/8/5.
 * 1.3.32 steque. 一个以栈为目标的队列，是一种支持push，pop和enqueue操作的数类型。
 * 为这种抽象数据类型定义一份API，并给出一份至于链表的实现
 * <p>
 * 这个之前做过吧?就把 头插入，尾插入，头删除实现就行了
 */
public class Steque<Item> {
    private class Node {
        Item item;
        Node next;
    }

    private Node first;
    private int N;

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }


}
