package me.suiyueyu.algs4.sec1.algs;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by yzcc on 2016/8/2.
 */
public class FixedCapacityStack<Item> implements Iterable<Item> {
    private Item[] a;
    private int N;// size

    public FixedCapacityStack(int cap) {
        a = (Item[]) new Object[cap];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        a[N++] = item;
    }

    public Item pop() {
        // 从栈顶删除元素
        Item item = a[--N];
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            if (i == 0) {
                throw new NoSuchElementException("i cannot be 0");
            }
            return a[--i];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove is not support");
        }
    }
}
