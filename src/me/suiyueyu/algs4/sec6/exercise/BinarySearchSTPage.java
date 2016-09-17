package me.suiyueyu.algs4.sec6.exercise;

import me.suiyueyu.algs4.sec3.algs.BinarySearchST;
import me.suiyueyu.algs4.sec6.Page;

/**
 * Created by yzcc on 2016/9/15.
 * 6.15 开发一个Page的实现，将B- 树的节点表示为一个BinarySearchST对象。
 * todo
 */
public class BinarySearchSTPage<Key extends Comparable<Key>> extends Page<Key> {
    private BinarySearchST<Key, Integer> bst = new BinarySearchST<>(16);

    /**
     * 关闭页
     * <p>
     * 不知道有啥用...
     */
    @Override
    public void close() {

    }

    @Override
    public void add(Key key) {

    }

    @Override
    public boolean isExternal() {
        return false;
    }

    @Override
    public boolean contains(Key key) {
        return false;
    }

    @Override
    public Page next(Key key) {
        return null;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public Page split() {
        return null;
    }

    @Override
    public Iterable<Key> keys() {
        return null;
    }

}
