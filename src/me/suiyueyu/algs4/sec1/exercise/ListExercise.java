package me.suiyueyu.algs4.sec1.exercise;

import java.util.Iterator;

/**
 * Created by boge on 2015/8/2.
 */
public class ListExercise<Item>  {

    private class Node{
        Item item;
        Node next;
    }

    private Node first;
    private boolean isEmpty(){
        return (first == null);
    }

}
