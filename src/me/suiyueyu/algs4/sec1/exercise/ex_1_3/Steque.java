package me.suiyueyu.algs4.sec1.exercise.ex_1_3;

import java.util.Iterator;

/**
 * Created by yzcc on 2016/8/5.
 * 1.3.32 steque. 一个以栈为目标的队列，是一种支持push，pop和enqueue操作的数类型。
 * 为这种抽象数据类型定义一份API，并给出一份至于链表的实现
 * <p>
 * 这个之前做过吧?就把 头插入，尾插入，头删除实现就行了
 *
 * 1.3.47 可连接的队列，栈或steque。 为队列，栈或者steque(请见练习1.3.22 )添加一个能够(破坏性的)
 * 连接两个同类对象的额外操作 catenation
 *
 */
public class Steque<Item> implements Iterable<Item> {


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

    /**
     * 头插入
     *
     * @param item
     */
    public void push(Item item) {
        if (isEmpty()) {
            first = new Node();
            first.item = item;
        } else {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
        }
        N++;
    }

    public Item pop() {
        if (isEmpty()) {
            return null;
        } else {
            Node oldFirst = first;
            first = first.next;
            N--;
            return oldFirst.item;
        }
    }

    public void enqueue(Item item) {
        if (isEmpty()) {
            // 空链表
            // curr = first = null
            first = new Node();
            first.item = item;

        } else {
            Node curr = first;
            for (int i = 0; i < N - 1; i++) {
                curr = curr.next;
            }

            // 最后一个节点
            curr.next = new Node();
            curr.next.item = item;
        }
        N++;
    }


    /**
     * 1.3.47 可连接的队列，栈或steque。 为队列，栈或者steque(请见练习1.3.22 )添加一个能够(破坏性的)\
     * 连接两个同类对象的额外操作 catenation
     *
     * @param s
     */
    public void catenation(Steque<Item> s) {
        for (Item item : s) {
            this.enqueue(item);
        }
    }


    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        Node curr = first;

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


    public static void main(String[] args) {
        Steque<String> s = new Steque<>();

        s.enqueue("a");
        s.enqueue("b");
        s.enqueue("c");
        s.enqueue("d");
        s.push("e");
        s.pop();
        s.pop();

        Steque<String> s2 = new Steque<>();
        s2.enqueue("a1");
        s2.enqueue("b2");
        s2.enqueue("c3");


        for (String str : s) {
            System.out.print(str + " ");
        }
        System.out.println();

        s.catenation(s2);

        for (String str : s) {
            System.out.print(str + " ");
        }
        System.out.println();



    }
}
