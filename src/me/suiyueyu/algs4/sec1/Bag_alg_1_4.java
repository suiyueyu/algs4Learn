package me.suiyueyu.algs4.sec1;

import java.util.Iterator;

/**
 * Created by boge on 2015/8/4.
 */
public class Bag_alg_1_4<Item> implements Iterable<Item>{

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

    private class Node{
        Item item;
        Node next;
    }
    private Node first;
    public void add(Item item){
        Node  oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }


}
