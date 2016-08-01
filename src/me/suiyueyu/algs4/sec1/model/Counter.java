package me.suiyueyu.algs4.sec1.model;

/**
 * Created by yzcc on 2016/8/1.
 */
public class Counter {
    private String name;
    public int count;

    public Counter(String id) {
        name = id;

    }

    public void increment() {
        count++;
    }

    /**
     * tally
     * count's getter
     *
     * @return count's value
     */
    public int tally() {
        return count;
    }

    public String toString() {
        return count + " " + name;
    }

    public void clear() {
        count = 0;
    }
}
