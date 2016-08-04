package me.suiyueyu.algs4.sec1.exercise.ex_1_3;

/**
 * Created by yzcc on 2016/8/4.
 * 1.3.29
 * 用环形链表实现Queue。环形链表也是一条链表，只是没有任何节点的链接为空，
 * 且只要链表非空则last.next的值为first。只能使用一个Node类型的实例变量(last)
 * isEmpty() : boolean
 * size() : int
 * enqueue(Item item) : void
 * dequeue() : Item
 */

public class QueueCycleList<Item> {
    private class Node {
        Item item;
        Node next;
    }

    private Node last;

    public boolean isEmpty() {
        return last == null;
    }

    public int size() {
        if (isEmpty())
            return 0;
        else {
            int size = 1;
            Node curr = last.next;
            for (; curr != last; curr = curr.next) {
                size++;
            }
            return size;
        }
    }

    public void enqueue(Item item) {
        if (isEmpty()) {
            last = new Node();
            last.item = item;
            last.next = last;
        } else {
            // todo 插错了，插到头了
            // 要插到尾巴
            Node curr = last.next;
            for (; curr.next != last; curr = curr.next) {

            }
            curr.next = new Node();
            curr.next.item = item;
            curr.next.next = last;

        }
    }

    public Item dequeue() {
        if (isEmpty()) {
            // empty list
            return null;
        } else if (last.next == last) {
            // size() == 1
            // one node
            Item item = last.item;
            last = null;
            return item;
        } else {
            // more than 1 node
            // first 出列
            Node first = last.next;
            last.next = first.next;
            return first.item;
        }

    }

    public void print() {
        if (isEmpty()) {
            System.out.println("[ Empty ]");
        } else {
            // last 放到最后输出
            // 当然如果用toString，那么是last的时候，用 str = last + str就行
            if (last.next == last) {
                System.out.println(" [ " + last.item + " ] ");
            } else {
                Node curr = last.next;
                for (; curr != last; curr = curr.next) {
                    System.out.print(" [ " + curr.item + " ] ");
                }
                // for last node
                System.out.print(" [ " + last.item + " ] ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        QueueCycleList<String> queue = new QueueCycleList<>();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.print();
    }
}
