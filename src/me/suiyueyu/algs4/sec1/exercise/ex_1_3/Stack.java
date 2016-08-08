package me.suiyueyu.algs4.sec1.exercise.ex_1_3;

import java.util.Iterator;

/**
 * Created by yzcc on 2016/8/8.
 * 1.3.42 复制栈。编写一个新的构造函数，使用以下代码：
 * <pre>
 *     Stack<Item> t = new Stack<Item>(s)
 * </pre>
 * 得到的t向栈s的一个新的独立副本。
 */
public class Stack<Item> implements Iterable<Item> {
    private Node first;
    private int N;

    private class Node {
        Item item;
        Node next;
    }

    public Stack() {
        // 默认的，啥也不干
    }

    public Stack(Stack<Item> q) {
        Stack<Item> stack = new Stack<Item>();
        for (Item item : q) {
            stack.push(item);
        }
        for (Item item : stack) {
            this.push(item);
        }
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

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o == null) {
            return false;
        } else if (o.getClass() == this.getClass()) {
            Stack<Item> that = (Stack<Item>) o;
            if (isEmpty() && that.isEmpty()) {
                // 两个都是空的
                return true;
            } else if (this.size() == that.size()) {
                // 两个都不是空的，对比每个元素
                Node curr1 = this.first;
                Node curr2 = that.first;

                for (; curr1 != null && curr2 != null; curr1 = curr1.next,
                        curr2 = curr2.next) {
                    if (!curr1.item.equals(curr2.item)) {
                        return false;
                    }
                }
                return true;
            } else {
                // 大小不等
                return false;
            }
        } else {
            // 两个类型不同
            return false;
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

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // ex 1.3.7
    public Item peek() {
        if (isEmpty()) return null;
        return first.item;
    }

    public static void main(String[] args) {
        Stack<String> stack1 = new Stack<>();
        stack1.push("1");
        stack1.push("2");
        stack1.push("3");

        Stack<String> stack2 = new Stack<>(stack1);

        System.out.println(stack1.equals(stack2));

        stack2.push("4");

        System.out.println(stack1.equals(stack2));
    }
}
