package me.suiyueyu.algs4.sec1.exercise.ex_1_3;

import java.util.Iterator;

/**
 * Created by yzcc on 2016/8/9.
 * 1.3.48 用一个双向队列实现两个栈，保证每个栈操作只需要常数次的双向队列操作
 * 请见练习 1.3.33
 * <p>
 * 1.3.48 Two stacks with a deque. Implement two stacks with a single deque so that each
 * operation takes a constant number of deque operations (see Exercise 1.3.33).
 * <p>
 * 这是我看错了，弄成Steque了
 */
public class TwoStacksWithaDeque<Item> {
    private Deque<Item> deque = new Deque<Item>();
    private int leftNum;
    private int rightNum;


    public boolean isLeftStackEmpty() {
        return leftNum == 0;
    }

    public boolean isRightStackEmpty() {
        return rightNum == 0;
    }

    public int leftStackSize() {
        return leftNum;
    }

    public int rightStackSize() {
        return rightNum;
    }

    public void pushLeftStack(Item item) {
        deque.pushLeft(item);
        leftNum++;
    }

    public Item popLeftStack() {
        Item item = null;
        if (leftNum > 0) {
            item = deque.popLeft();
            leftNum--;
        }
        return item;

    }

    public void pushRightStack(Item item) {
        deque.pushRight(item);
        rightNum++;
    }

    public Item popRightStack() {
        Item item = null;
        if (rightNum > 0) {
            item = deque.popRight();
            rightNum--;
        }
        return item;
    }

    // todo 如何为左右两个stack都实现一个iterator?
//    public Iterator<Item> leftStackIterator(){
//        return new
//    }
}
