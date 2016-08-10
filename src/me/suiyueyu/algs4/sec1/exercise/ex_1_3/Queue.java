package me.suiyueyu.algs4.sec1.exercise.ex_1_3;

import java.util.Iterator;

/**
 * Created by yzcc on 2016/8/2.
 * 1.3.41 复制队列。编写一个新的构造函数，使用以下代码
 * <pre>
 *     Queue<Item> r = new Queue<Item>(q);
 * </pre>
 * 得到的r指向队列q的一个新的独立的副本。可以对q或r进行任意入列或出列操作但他们不会互相影响
 * 提示：从q中去除所有的元素再将他们插入q和r
 *
 * 1.3.47 可连接的队列，栈或steque。 为队列，栈或者steque(请见练习1.3.22 )添加一个能够(破坏性的)
 * 连接两个同类对象的额外操作 catenation
 */

public class Queue<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int N;

    private class Node {
        Item item;
        Node next;
    }

    public Queue() {

    }

    public boolean equals(Object o) {
        if (o == null) return false;
        else if (this == o) return true;
        else if (!(this.getClass() == o.getClass())) return false;
        else {
            Queue<Item> that = (Queue<Item>) o;
            if (this.isEmpty() && that.isEmpty()) return true;
            else if (this.size() == that.size()) {
                // 比较全部元素
                Node curr1 = this.first,
                        curr2 = that.first;

                for (; curr1 != null && curr2 != null; curr1 = curr1.next,
                        curr2 = curr2.next) {
                    if (!curr1.item.equals(curr2.item)) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        }
    }

    public Queue(Queue<Item> q) {
        for (Item item : q) {
            this.enqueue(item);
        }
    }

    public boolean isEmpty() {
        return first == null;
        // 或者 N == 0;
    }

    public int size() {
        return N;
    }

    public void enqueue(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }
        N++;
    }

    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        N--;
        return item;
    }

    /**
     * 1.3.47 可连接的队列，栈或steque。 为队列，栈或者steque(请见练习1.3.22 )添加一个能够(破坏性的)
     * 连接两个同类对象的额外操作 catenation
     * 题目提到破坏性的，那么应该是破坏了this吧
     * 如果是static的方法，接受两个参数，那么就返回了一个新的，没有破坏的意思吧?
     * <p>
     * 成链（catenation）是指同一种化学元素的原子经由连续的共价键互相连接形成长链状的分子。
     * 这单词...
     *
     * @param q
     */
    public void catenation(Queue<Item> q) {
        for (Item item : q) {
            this.enqueue(item);
        }
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Queue<String> queue1 = new Queue<>();
        queue1.enqueue("a");
        queue1.enqueue("b");
        queue1.enqueue("c");

        System.out.println(queue1.equals(queue1));

        Queue<Integer> queue3 = new Queue<>();
        queue3.enqueue(1);
        queue3.enqueue(2);
        queue3.enqueue(3);
        System.out.println(queue1.equals(queue3));

        Queue<String> queue2 = new Queue<>(queue1);
        System.out.println(queue1.equals(queue2));

        queue2.enqueue("d");
        System.out.println(queue1.equals(queue2));
    }
}
