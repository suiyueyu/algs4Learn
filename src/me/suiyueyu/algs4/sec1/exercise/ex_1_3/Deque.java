package me.suiyueyu.algs4.sec1.exercise.ex_1_3;

import java.util.Iterator;

/**
 * Created by yzcc on 2016/8/6.
 * 1.3.33 一个双向队列(或者成为deque)和栈或队列类似，但它同时支持在两端添加或删除元素。
 * Deque能够存储一组元素并支持表1.3.9中的API
 * <p>
 * public class Deque<Item> implements Iterable(Item)
 * <p>
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
 * <p>
 * 我突然在想，是不是应该内部直接放一个DoubleNodeList的对象
 */
public class Deque<Item> implements Iterable<Item> {

    private class DoubleNode {
        Item item;
        DoubleNode prev;
        DoubleNode next;
    }

    public DoubleNode first;
    public int N;

    /**
     * 构造函数
     * 没想到什么可以做的...
     */
//    public Deque(){
//
//    }

    /**
     * 双向队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * int size() 双向队列中的元素数量
     *
     * @return
     */
    public int size() {
        return N;
    }

    /**
     * 向左端添加一个新元素
     *
     * @param item
     */
    public void pushLeft(Item item) {
        if (isEmpty()) {
            first = new DoubleNode();
            first.item = item;
        } else {
            DoubleNode oldFirst = first;
            first = new DoubleNode();
            first.next = oldFirst;
            first.item = item;
            oldFirst.prev = first;
        }
        N++;
    }

    /**
     * void pushRight(Item item) 向右段添加一个新元素
     *
     * @param item
     */
    public void pushRight(Item item) {
        if (isEmpty()) {
            first = new DoubleNode();
            first.item = item;
        } else {
            DoubleNode curr = first;
            for (int i = 0; i < N - 1; i++) {
                curr = curr.next;
            }
            // 最后一个元素
            curr.next = new DoubleNode();
            curr.next.item = item;
            curr.next.prev = curr;
        }
        N++;
    }

    /**
     * Item popLeft() 从左端删除一个元素
     *
     * @return
     */
    public Item popLeft() {
        if (isEmpty()) {
            return null;
        } else {
            DoubleNode oldFirst = first;
            first = first.next;

            // single node
            if (size() == 1) {

            } else {
                first.prev = null;
            }
            N--;
            return oldFirst.item;
        }
    }

    /**
     * Item popRight() 从右端删除一个元素
     *
     * @return
     */
    public Item popRight() {
        if (isEmpty()) {
            // do nothing
            return null;
        } else {
            if (size() == 1) {
                // single node
                first = null;
                N--;
                return first.item;
            } else {
                DoubleNode last_parent = first;
                for (int i = 0; i < N - 2; i++) {
                    last_parent = last_parent.next;
                }
                DoubleNode oldLast = last_parent.next;
                last_parent.next = null;

                N--;
                return oldLast.item;
            }
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        DoubleNode curr = first;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public Item next() {
            Item item = curr.item;
            curr = curr.next;
            return item;
        }
    }

}
