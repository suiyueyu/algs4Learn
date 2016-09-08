package me.suiyueyu.algs4.sec2.algs;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import me.suiyueyu.algs4.sec1.algs.Stack;
import me.suiyueyu.algs4.sec2.exrecise.ex_2_1.Transaction;

/**
 * Created by yzcc on 2016/9/2.
 */
public class TopM {
    public static void main(String[] args) {
        int M = Integer.parseInt(args[0]);
        MinPQ<Transaction> pq = new MinPQ<>(M + 1);
        while (StdIn.hasNextLine()) {
            pq.insert(new Transaction(StdIn.readLine()));
            if (pq.size() > M) {
                pq.delMin();
            }
        }

        Stack<Transaction> stack = new Stack<Transaction>();
        while (!pq.isEmpty()) stack.push(pq.delMin());
        for (Transaction t : stack) StdOut.println();

    }
}
