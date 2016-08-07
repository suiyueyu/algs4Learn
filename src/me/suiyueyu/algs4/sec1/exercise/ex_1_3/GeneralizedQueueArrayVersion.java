package me.suiyueyu.algs4.sec1.exercise.ex_1_3;

/**
 * Created by yzcc on 2016/8/7.
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
 */
public class GeneralizedQueueArrayVersion<Item> {
    private Item[] a;
    private int N;

    public GeneralizedQueueArrayVersion(int capcity) {
        a = (Item[]) new Object[capcity];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(Item item) {
        if (N == a.length) {
            Item[] temp = (Item[]) new Object[2 * a.length];
            for (int i = 0; i < N; i++) {
                temp[i] = a[i];
            }
            a = temp;
        }
        a[N++] = item; // 要删东西，动态调整数组大小就没啥用了吧
    }

    public Item delete(int k) {
        if (N == 1) {
            Item item = a[0];
//            a = null;
            N--;
            return item;
        }
        Item[] tmp = (Item[]) new Object[N - 1];
        int i;
        Item item = a[k - 1];
        for (i = k; i < N; i++) {
            tmp[i - k] = a[i];
        }
        for (int j = 0; j < k - 1; j++) {
            tmp[N - k + j] = a[j];
        }

        a = tmp;
        N--;
        return item;
    }

    public static void main(String[] args) {
        int M = 7, N = 2;
        GeneralizedQueueArrayVersion<Integer> generalizedQueue = new GeneralizedQueueArrayVersion<>(4);
        for (int i = 0; i < M; i++) {
            generalizedQueue.insert(i);
        }
        while (!generalizedQueue.isEmpty()) {
            System.out.print(generalizedQueue.delete(N) + " ");
        }
    }
}
