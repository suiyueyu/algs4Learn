package me.suiyueyu.algs4.sec1.exercise.ex_1_3;

/**
 * Created by yzcc on 2016/8/7.
 * 1.3.39 环形缓冲区。又称环形队列，是一种定长为为N的先进先出的数据结构。他在进程间的异步数据传输
 * 或记录日志文件时十分有用。当缓冲区为空时，消费者会在数据存入缓冲区前等待；
 * 当缓冲区满时，生产者会等待将数据存入缓冲区。为RingBuffer设计一份API并用（回环）数组将其实现。
 */
public class RingBuffer<Item> {
    private Item[] a;
    private int N;
    private int first;

    public RingBuffer(int cap) {
        a = (Item[]) new Object[cap];
        first = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public boolean isFull() {
        return N == a.length;
    }

    public boolean enqueue(Item item) {
        if (!isFull()) {
            a[(first + N) % a.length] = item;
            N++;
            return true;
        }
        return false;
    }

    public Item dequeue() {
        if (!isEmpty()) {

            Item item = a[first];
            a[first] = null;

            first = (first + 1) % a.length;
            N--;

            return item;
        }
        return null;
    }

}
