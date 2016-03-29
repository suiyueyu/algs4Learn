package me.suiyueyu.algs4.sec3;

import me.suiyueyu.algs4.sec1.Queue_alg_1_3;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by yzcc on 2015/10/31.
 */
public class SequentialSearchST_algs_3_1<Key extends Comparable<Key>, Value> {

    private Node first;

    private class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public SequentialSearchST_algs_3_1() {
        super();
    }

    public void put(Key key, Value val) {
        // 不是很懂这一段，示例代码是没有的，但是讲解的部分是有的
        // 但是直接这么写不就递归调用死循环了么...
        // 除非delete里面不用put(key, null)这种延时形实现

        // 2016年3月29日15:48:43 把delete改掉了
        if (val == null) {
            delete(key);
            return;
        }
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return; // 命中，更新
            }
        }
        first = new Node(key, val, first); // 未命中，新节点放在链表头
    }

    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.val; //  命中
            }
        }
        return null; //未命中
    }

    public boolean delete(Key key) {
        // put(key, null);
        // 即时性的删除，即不是上述的延时性的删除

        // 链表为空，删除失败
        if (isEmpty()) {
            return false;
        } else {
            // 删除的是头结点
            if (first.key.equals(key)) {
                Node temp = first;
                first = first.next;
                temp = null;
                return true;
            } else {
                // 要删除的节点不是头结点
                Node parentNode = first;
                Node x = parentNode.next;
                for (; x != null; x = x.next, parentNode = parentNode.next) {
                    if (x.key.equals(key)) {
                        parentNode.next = x.next;
                        // x.next = null;
                        x = null;
                        return true;
                    }
                }
                // 遍历完仍没有找到节点
                return false;
            }
        }
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return null == first;
    }

    public int size() {
        int length = 0;
        for (Node x = first; x != null; x = x.next) {
            ++length;
        }
        return length;
    }

    // 这是无序链表，底下这些和排序有关的都无法实现
//    public Key min(){
//        if (isEmpty()){
//            return null;
//        }else{
//            Key minKey = first.key;
//
//            for (Node x = first.next; x != null; x = x.next){
//                int compare = x.key.compareTo(minKey);
//                if (compare < 0){
//                    minKey = x.key;
//                }
//            }
//            return minKey;
//        }
//    }

    //    /**
//     * max 找到最大的key
//     * @return maxKey
//     */
//    Key max(){
//        if (isEmpty()){
//            return null;
//        }else{
//            Key maxKey = first.key;
//
//            for (Node x = first.next; x != null; x = x.next){
//                int compare = x.key.compareTo(maxKey);
//                if (compare > 0){
//                    maxKey = x.key;
//                }
//            }
//            return maxKey;
//        }
//    }
//
//    Key floor(Key key);
//    Key ceiling(Key key);
//    int rank(Key key);
//    Key select(int k);
//    void deleteMin(){
//        BinarySearchST_algs_3_2(min());
//    }
//    void deleteMax(){
//        BinarySearchST_algs_3_2(max());
//    }
//    int size(Key lo, Key hi){
//        if (hi.compareTo(lo) < 0){
//            return 0;
//        }else if(contains(hi)){
//            return rank(hi) - rank(lo) + 1;
//        }else{
//            return rank(hi) - rank(lo);
//        }
//
//    }
//    Iterable<Key> keys(Key lo, Key hi);
    public Iterable<Key> keys() {
//        return keys(min(), max());
        Queue_alg_1_3<Key> q = new Queue_alg_1_3<Key>();

        for (Node x = first; x != null; x = x.next) {
            q.enqueue(x.key);
        }

        return q;
    }

    public Iterable<Value> vals() {
        Queue_alg_1_3<Value> q = new Queue_alg_1_3<Value>();

        for (Node x = first; x != null; x = x.next) {
            q.enqueue(x.val);
        }
        return q;
    }

    public String toString() {

        String s = "";
        for (Key key : keys()) {
            s += (key.toString() + "  ");
        }
        s += '\n';
        for (Value val : vals()) {
            s += (val.toString() + "  ");
        }
        s += '\n';

        return s;
    }

}
