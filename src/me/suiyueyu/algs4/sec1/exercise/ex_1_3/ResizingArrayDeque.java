package me.suiyueyu.algs4.sec1.exercise.ex_1_3;

import java.util.Iterator;

/**
 * Created by yzcc on 2016/8/6.
 * <p>
 * 1.3.33 一个双向队列(或者成为deque)和栈或队列类似，但它同时支持在两端添加或删除元素。
 * Deque能够存储一组元素并支持表1.3.9中的API
 * <p>
 * public class Deque<Item> implements Iterable(Item)
 * Deque() 创建空双向队列
 * boolean isEmpty() 双向队列是否为空
 * int size() 双向队列中的元素数量
 * void pushleft(Item item) 向左端添加一个新元素
 * void pushRight(Item item) 向右段添加一个新元素
 * Item popLeft() 从左端删除一个元素
 * Item popRight() 从右端删除一个元素
 * <p>
 * 编写一个使用双向链表实现这份API的Deque类，
 * 以及一个使用动态数组调整实现这份API的 ResizingArrayDeque 类
 */
public class ResizingArrayDeque<Item> implements Iterable<Item> {

    private Item[] a;
    private int start;
    private int N;

    public ResizingArrayDeque(int cap) {
        a = (Item[]) new Object[cap];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void pushRight(Item item) {
        N++;
        a[N] = item;
        if (N >= a.length) {
            resize(N * 2);
        }
    }

    public void pushLeft(Item item) {
        start = (start - 1 + a.length) % a.length;
        N++;
        a[start] = item;

        if (N == a.length) {
            resize(a.length * 2);
        }
    }

    public Item popLeft() {
        if (isEmpty()) {
            return null;
        } else {
            Item item = a[start];
            start = (start + 1) % a.length;
            N--;
            if (N == a.length / 4) {
                resize(a.length / 2);
            }
            return item;
        }
    }

    public Item popRight() {
        if (isEmpty()) {
            return null;
        } else {
            Item item = a[(start + N - 1) % a.length];
            N = (N - 1 + a.length) % a.length;

            if (N == a.length / 4) {
                resize(a.length / 2);
            }
            return item;
        }
    }

    public void resize(int max) {
        Item[] newArray = (Item[]) new Object[max];

        for (int i = 0; i < N; i++) {
            newArray[i] = a[(start + i) % a.length];
        }
        start = 0;
        a = newArray;
    }
//    * void pushleft(Item item) 向左端添加一个新元素
//    * void pushRight(Item item) 向右段添加一个新元素
//    * Item popLeft() 从左端删除一个元素
//    * Item popRight() 从右端删除一个元素


    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < N;
        }

        @Override
        public Item next() {
            Item item = a[(start + i) % a.length];
            i++;
            return item;
        }
    }

    public static void main(String[] args) {
        ResizingArrayDeque<String> arrayDeque = new ResizingArrayDeque<>(4);
        arrayDeque.pushLeft("a");
        arrayDeque.pushLeft("b");
        arrayDeque.pushLeft("c");
        arrayDeque.pushLeft("d");
        arrayDeque.pushLeft("e");
        arrayDeque.popRight();
        arrayDeque.popRight();
        arrayDeque.popRight();
        arrayDeque.pushLeft("f");
        arrayDeque.pushLeft("g");
        arrayDeque.pushLeft("h");
        for (String str : arrayDeque) {
            System.out.print(str + " ");
        }
        System.out.println();

    }
}
