package me.suiyueyu.algs4.sec6;

/**
 * Created by yzcc on 2016/9/15.
 */
public abstract class Page<Key> {
    //    public Page(boolean bottom);
    public abstract void close();

    public abstract void add(Key key);

    public abstract boolean isExternal();

    public abstract boolean contains(Key key);

    public abstract Page next(Key key);

    public abstract boolean isFull();

    public abstract Page split();

    public abstract Iterable<Key> keys();
}
