package me.suiyueyu.algs4.sec3.algs;

import java.util.HashMap;

/**
 * Created by yzcc on 2016/8/29.
 */
public class LinearProbingHashST<Key, Value> {
    private int N;// 符号表中键值对的总数
    private int M = 16;// 线性探测表的大小
    private Key[] keys;// 键
    private Value[] vals;// 值

    public LinearProbingHashST() {
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
//        this(16);
    }

    private LinearProbingHashST(int M) {
        this.M = M;
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    private int hash(Key key) {
        // 这个0x7fffffff 是Integer.MAX_VALUE
        // 即 0111 1111 ... 1111
        // 和它做 & 操作能去掉符号位
        return ((key.hashCode()) & 0x7fffffff) % M;
    }


    public void put(Key key, Value val) {
        // 不允许使用率到达1(即数组被占用满) ,
        // 因为这样的寻找会导致无限循环
        if (N >= M / 2) resize(2 * M);

        int i;

        // 找到下一个空位
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            // 如果已经有了，就更新
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }

        }

        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key))
                return vals[i];
        }
        // 碰到了下一个不是自己的
        // 但是这样如果开头整个ST都为空怎么办?
        return null;
    }

    /**
     * 判断下存不存在keys
     * 先找到hash位置的数
     * while 这个位置有人占了，但不是自己的, 别人挤过来的
     * 找自己的位置
     * 循环退出是因为 keys[i] = null 没找到 return false
     * 循环退出因为 keys[i] = key 找到了 return true
     *
     * @param key
     * @return
     */
    private boolean contains(Key key) {
        int i = hash(key);
        while (keys[i] != null && !key.equals(keys[i])) {
            i = (i + 1) % M;
        }
        if (keys[i] == null) {
            return false;
        }
        return true;
    }

    public void delete(Key key) {
        if (!contains(key)) return;

        // 找到自己
        // 自己的槽被人占了(碰撞了)
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % M;
        }
        // 删除
        keys[i] = null;
        vals[i] = null;

        i = (i + 1) % M;
        while (keys[i] != null) {
            // 清掉了key，但是要考虑到剩下的这些节点可能都是被挤到下个位置的
            Key keyToRedo = keys[i];
            Value valToRedo = vals[i];
            // 让出位置
            keys[i] = null;
            vals[i] = null;
            N--;
            // 重新放入
            put(keyToRedo, valToRedo);
            i = (i + 1) % M;
        }
        // 为什么这个N-- 不紧接着放在while的前面?
        // 也没有马上resize
        N--;

        if (N > 0 && N == N / 8) resize(M / 2);
    }

    private void resize(int cap) {
        LinearProbingHashST<Key, Value> t;
        t = new LinearProbingHashST<Key, Value>(cap);

        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                t.put(keys[i], vals[i]);
            }
        }
        keys = t.keys;
        vals = t.vals;
        M = t.M;

    }
}
