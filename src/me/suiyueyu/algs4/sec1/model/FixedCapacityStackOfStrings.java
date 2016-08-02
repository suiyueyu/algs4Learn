package me.suiyueyu.algs4.sec1.model;

/**
 * Created by yzcc on 2016/8/2.
 */
public class FixedCapacityStackOfStrings {
    private String[] a; // stack entries
    private int N;// size

    public FixedCapacityStackOfStrings(int cap) {
        a = new String[cap];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(String item) {
        if (!isFull()) {
            a[N++] = item;
            return;
        }
        System.out.println("the stack is full");

    }

    public String pop() {
        return a[--N];
    }

    // ex_1_3_1
    public boolean isFull() {
        return N == a.length;
    }
}
