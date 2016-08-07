package me.suiyueyu.algs4.sec1.exercise.ex_1_3;

/**
 * Created by yzcc on 2016/8/7.
 * <p>
 * 删除第k个元素。实现一个类并支持表1.3.12中的API
 * <p>
 * public class GeneralizedQueue<Item>
 * GeneralizedQueue() 创建一条空队列
 * boolean isEmpty() 队列是否为空
 * void insert(Item x) 添加一个元素
 * Item delete(int k) 删除并返回最早插入的第k个元素
 * <p>
 * 先用数组实现该数据类型，然后用链表实现该数据类型。注意：我们在第3章中
 * 介绍的算法和数据结构可以保证 insert() 和 delete()的实现所需的运行时间和队列中的元素数量成对数关系
 * 详见 3.5.27
 * <p>
 * 链表实现
 */
public class GeneralizedQueue<Item> {
    private class Node {
        Node next;
        Item item;
    }

    private Node last;
    private int N;

    public GeneralizedQueue() {

    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(Item item) {
        if (isEmpty()) {
            last = new Node();
            last.next = last;
            last.item = item;

        } else {
            Node first = last.next;
            last.next = new Node();
            last.next.item = item;
            last.next.next = first;
            last = last.next;
        }
        N++;
    }

    public Item delete(int k) {
        // 将链表变为3部分

        if (!isEmpty()) {
            Item item;
            if (N == 1) {
                item = last.item;
                last = null;
            } else {
                // 至少两个点
                // 要删的k点的父亲
                Node curr_parent = last.next;
                for (int i = 0; i < k - 2; i++) {
                    curr_parent = curr_parent.next;
                }
                item = curr_parent.next.item;
                // 排除掉下一个点
                curr_parent.next = curr_parent.next.next;
                // 这个点也是新的last
                last = curr_parent;
            }
            N--;
            return item;
        }
        throw new RuntimeException("Illegal parameter - delete() - class : GeneralizedQueue");
//        return null;
    }

    public static void main(String[] args) {
        int M = 7, N = 2;
        GeneralizedQueue<Integer> generalizedQueue = new GeneralizedQueue<>();
        for (int i = 0; i < M; i++) {
            generalizedQueue.insert(i);
        }
        while (!generalizedQueue.isEmpty()) {
            System.out.print(generalizedQueue.delete(N) + " ");
        }

        System.out.println();
    }
}
