package me.suiyueyu.algs4.sec1.exercise.ex_1_3;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by yzcc on 2016/8/7.
 * 1.3.34 随机背包。随机背包能够存储一组元素并支持表1.3.10中的API
 * public class RandomBag()<Item> implements <Item>
 * RandomBag()  创建一个空随机背包
 * boolean isEmpty() 背包是否为空
 * int size() 背包中的元素数量
 * void add(Item item) 添加一个元素
 * 编写一个RandomBag来实现这份API。请注意除了形容词随机以外，这份API和Bag的API是相同的
 * 这意味着迭代应该随机访问背包中的所有元素(对于每次迭代，所有的N!种排列出现的可能性均相同)
 * 提示：用数组保存所有元素并在迭代器的构造函数中随机打乱他们的顺序
 */
public class RandomBag<Item> implements Iterable<Item> {
    private Item[] a;
    private int N;

    /**
     * 构造
     * RandomBag()  创建一个空随机背包
     *
     * @param cap 容量
     */
    public RandomBag(int cap) {
        a = (Item[]) new Object[cap];

    }

//    * boolean isEmpty()

    /**
     * boolean isEmpty() 背包是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * int size() 背包中的元素数量
     *
     * @return
     */
    public int size() {
        return N;
    }
//    * void add(Item item)

    /**
     * void add(Item item) 添加一个元素
     *
     * @param item
     */
    public void add(Item item) {
        if (!(N == a.length)) {
            a[N++] = item;
        }
    }

    private void shuffle() {
        Random random = new Random();
        Item temp;
        int randi;
        for (int i = 0; i < N; i++) {
            randi = random.nextInt(N);

            temp = a[i];
            a[i] = a[randi];
            a[randi] = temp;
        }
    }

    @Override
    public Iterator<Item> iterator() {
        shuffle();
        return new RandomBagIterator();
    }

    private class RandomBagIterator implements Iterator<Item> {
        private int index;

        public boolean hasNext() {
            return index < N;
        }

        @Override
        public Item next() {
            Item item = (Item) a[index++];

            return item;
        }

    }

    public static void main(String[] args) {
        RandomBag<String> randomBag = new RandomBag<>(20);
        randomBag.add("a");
        randomBag.add("b");
        randomBag.add("c");
        randomBag.add("d");
        randomBag.add("e");
        for (String str : randomBag) {
            System.out.print(str + " ");
        }
        System.out.println();
    }

}
