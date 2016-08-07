package me.suiyueyu.algs4.sec1.exercise.ex_1_3;

import edu.princeton.cs.algs4.StdIn;
import me.suiyueyu.algs4.sec1.algs.Queue;

/**
 * Created by yzcc on 2016/8/7.
 * Josephus 问题。 在这个古老的问题中，N个身陷绝境的人一致同意通过以下方式减少生存人数。他们围坐在一圈
 * （位置记为0到N-1）并从第一个人开始报数，报道M的人会被杀死，直到最后一个人留下来。传说中Josephus找到了不会被杀死的位置。
 * 编写一个Queue的用例Josephus, 从命令行接受N和M并打印出人们被杀死的顺序（这也将显示Jostphus在圈中的位置）
 * java Josephus 7 2
 * 1 3 5 0 4 2 6
 */
public class JosephusProblem {
    public static void main(String[] args) {
        Queue<Integer> josephus = new Queue<>();

        System.out.println("input total people and lots ");
        int M = StdIn.readInt(); // 总人数
        int N = StdIn.readInt(); // 报数

        for (int i = 0; i < M; i++) {
            josephus.enqueue(i);
        }

        while (josephus.size() > 0) {
            for (int i = 0; i < N - 1; i++) {
                josephus.enqueue(josephus.dequeue());
            }
            System.out.print(josephus.dequeue() + " ");
        }

    }
}
