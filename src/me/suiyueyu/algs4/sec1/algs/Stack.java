package me.suiyueyu.algs4.sec1.algs;

import java.util.Iterator;

/**
 * Created by yzcc on 2016/8/2.
 * alg 1.2 下压堆栈(链表实现)
 */
public class Stack<Item> implements Iterable<Item> {
    private Node first;
    private int N;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        // 从栈顶添加元素
        Node oldItem = first;
        first = new Node();
        first.item = item;
        first.next = oldItem;
        N++;
    }

    public Item pop() {
        // 从栈顶删除元素
        Item item = first.item;
        first = first.next;
        N--;
        return item;
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

    // ex 1.3.7
    public Item peek() {
        return first.item;
    }
}
