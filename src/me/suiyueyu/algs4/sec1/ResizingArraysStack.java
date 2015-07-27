package me.suiyueyu.algs4.sec1;

import java.util.Iterator;

/**
 * Created by yzcc on 2015/5/21.
 */
public class ResizingArraysStack<Item> implements Iterable<Item> {
    private Item[] a = (Item[]) new Object[1];
    private int N = 0;

    public boolean isEmpty() {
        return 0 == N;
    }

    public int size()
    {
        return N;
    }

    public void resize(int max){
        Item[] temp = (Item[])new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = null;
        a = temp;
    }

    public void push(Item item){
        if (N == a.length){
            resize(2*a.length);
        }
        a[N++] = item;
    }

    public Item pop(){

        Item item = a[--N];
        a[N] = null;
        if ((N>0) && (N == a.length / 4)){
            resize(a.length/2);
        }
        return item;
    }

    public String toString(){
        String str = "";
        for (int i = 0; i < a.length; i++){
            str += (a[i] + "   ");
        }
        return str;
    }

    /**
     * Returns an iterator over a set of elements of type T.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Item> iterator() {
        return null;
    }

    private class ReverseArrayIterator implements Iterator<Item>{
        private int i = N;

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return i>0;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public Item next() {
            return a[--i];
        }

        /**
         * Removes from the underlying collection the last element returned
         * by this iterator (optional operation).  This method can be called
         * only once per call to {@link #next}.  The behavior of an iterator
         * is unspecified if the underlying collection is modified while the
         * iteration is in progress in any way other than by calling this
         * method.
         *
         * @throws UnsupportedOperationException if the {@code remove}
         *                                       operation is not supported by this iterator
         * @throws IllegalStateException         if the {@code next} method has not
         *                                       yet been called, or the {@code remove} method has already
         *                                       been called after the last call to the {@code next}
         *                                       method
         */
        @Override
        public void remove() {
            ;
        }

    }

    public static void main(String[] args){

    }
}
