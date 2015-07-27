package me.suiyueyu.algs4.sec1;

/**
 * Created by yzcc on 2015/5/22.
 */
public class FixedCapacityStack<Item> {
    private Item[] a;
    private int N;

    public FixedCapacityStack(int cap){
        a = (Item[])new Object[cap];
    }

    public boolean isEmpty(){
        return 0==N;
    }

    public int size(){
        return N;
    }

    public void push(Item item){
        if (isFull()){
            System.out.println("isFull, can't push");
        }else{
            a[N++] = item;
        }
    }

    public boolean isFull(){
        return (N-1)>=(a.length);
    }

    public Item pop(){
        Item item = a[--N];
        a[N] = null;
        return item;
    }

}
