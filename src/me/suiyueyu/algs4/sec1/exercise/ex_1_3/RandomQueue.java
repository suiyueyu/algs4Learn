package me.suiyueyu.algs4.sec1.exercise.ex_1_3;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/**
 * Created by yzcc on 2016/8/7.
 * 1.3.35 随机队列。 随机队列能够存储一组数据并支持如下API
 * public class RandomQueue &lt; Item &gt;
 * RandomQueue() 创建一条空的随机队列
 * boolean isEmpty() 队列是否为空
 * void enqueue(Item item ) 添加一个元素
 * Item dequeue() 删除并随机返回一个元素(取样且不放回)
 * Item sample() 随机返回一个元素但不删除它(取样且放回)
 * <p>
 * 编写一个RandomQueue类来实现这份API，提示：使用(能够动态调整大小的)数组表示数据。
 * 删除一个元素时，随机交换某个元素(索引在0和N-1之间)和末位元素(索引为N-1)的位置，然后像
 * ResizingArrayStack 一样删除并返回末位元素。
 * <p>
 * 编写一个用例，使用RandomQueue &lt;Card &gt;在桥牌中发牌(每人13张)
 * <p>
 * 1.3.36 随机迭代器。为上一题中的 RandomQueue &lt;Item&gt; 编写一个迭代器
 * 随机返回队列中的所有元素
 * <p>
 * 这个转义好尴尬...
 */
public class RandomQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int N;

    public RandomQueue(int cap) {

        a = (Item[]) new Object[cap];
        N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void enqueue(Item item) {
        a[N++] = item;

        if (N == a.length) {
            resize(a.length * 2);
        }
    }

    /**
     * Item dequeue() 删除并随机返回一个元素(取样且不放回)
     * 删除一个元素时，随机交换某个元素(索引在0和N-1之间)和末位元素(索引为N-1)的位置，然后像
     * ResizingArrayStack 一样删除并返回末位元素。
     *
     * @return
     */
    public Item dequeue() {
        if (isEmpty()) {
            return null;
        } else {
            int randi = StdRandom.uniform(N); // 0 - N

            Item item = a[randi];
            a[randi] = a[N - 1];// exchange randi last
            a[N - 1] = null;// 防止对象游离

            N--;
            if (N == a.length / 4) {
                resize(a.length / 2);
            }
            return item;
        }
    }

    /**
     * Item sample() 随机返回一个元素但不删除它(取样且放回)
     *
     * @return
     */
    public Item sample() {
        int randi = StdRandom.uniform(N);
        return a[randi];
    }

    /**
     * 调整数组大小
     *
     * @param max
     */
    private void resize(int max) {
        Item[] tmp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            tmp[i] = a[i];
        }

        a = tmp;

    }

    public int size() {
        return N;
    }

    private void shuffle() {
        Item temp;
        int randi;

        for (int i = 0; i < N; i++) {
            randi = StdRandom.uniform(N);

            temp = a[i];
            a[i] = a[randi];
            a[randi] = temp;
        }
    }

    @Override
    public Iterator<Item> iterator() {
        shuffle();
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        private int index;

        public boolean hasNext() {
            return index < N;
        }

        public Item next() {
            return a[index++];
        }
    }

    public static void main(String[] args) {
        // 不懂桥牌规则怎么办...
        String[] suits = {"黑桃♠", "红心♥", "梅花♣", "方块♦"};
        String[] point = {"A", "2", "3", "4",
                "5", "6", "7", "8",
                "9", "10", "J", "Q", "K"};
        RandomQueue<Card> cards = new RandomQueue<>(64);
        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < point.length; j++) {
                cards.enqueue(new Card(suits[i], point[j]));
            }
        }

        int cardNumForOneMan = 13;
        Card[] cardsForFirstMan = new Card[13];
        for (int i = 0; i < cardNumForOneMan; i++) {
            cardsForFirstMan[i] = cards.dequeue();
        }

        for (Card card : cardsForFirstMan) {
            System.out.print(card + " ");
        }
        System.out.println();
        System.out.println();

        // for RandomIterator

        System.out.println("there are " + cards.size() + " cards");
        for (Card card : cards) {
            System.out.print(card + " ");
        }
    }


}

class Card {
    public String suit;
    public String point;

    public Card(String suit, String point) {
        this.suit = suit;
        this.point = point;
    }

    public String toString() {
        return suit + " " + point;
    }
}