package me.suiyueyu.algs4.sec6;

/**
 * Created by yzcc on 2016/9/15.
 */
public class BTreeSET<Key extends Comparable<Key>> {
    private Page root;// = new Page(true);

    public BTreeSET(Key sentinel) {
        add(sentinel);
    }

    public boolean contains(Key key) {
        return contains(root, key);
    }

    private boolean contains(Page h, Key key) {
        if (h.isExternal()) return h.contains(key);
        return contains(h.next(key), key);
    }

    public void add(Key key) {
        add(root, key);
        if (root.isFull()) {
            Page lefthalf = root;
            Page righthalf = root;
            // TODO: 2016/9/17
//            root = new Page(false);
//            root.add(lefthalf);
//            root.add(righthalf);
        }
    }

    public void add(Page h, Key key) {
        if (h.isExternal()) {
            h.add(key);
            return;
        }

        Page next = h.next(key);
        add(next, key);
        if (next.isFull()) {
            h.add(next.split());
        }

        next.close();
    }
}
