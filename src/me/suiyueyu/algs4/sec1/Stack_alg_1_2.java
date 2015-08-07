package me.suiyueyu.algs4.sec1;

import java.util.Iterator;

/**
 * Created by boge on 2015/8/2.
 */
public class Stack_alg_1_2<Item> implements Iterable<Item> {
    private Node first;
    private int N;

    private class Node{
        Item item;
        Node next;
    }
    public boolean isEmpty(){
        return first == null;
    }
    public int size (){
        return N;
    }

    public void push(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    public Item pop(){
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    private class ListIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext(){
            return current!=null;
        }

        public void remove(){
            ;
        }
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

    }

    public Iterator<Item> iterator(){
        return new ListIterator();
    }

}
