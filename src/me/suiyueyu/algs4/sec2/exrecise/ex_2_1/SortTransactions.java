package me.suiyueyu.algs4.sec2.exrecise.ex_2_1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import me.suiyueyu.algs4.sec1.algs.Queue;
import me.suiyueyu.algs4.sec2.algs.Shell;

/**
 * Created by yzcc on 2016/8/16.
 */
public class SortTransactions {
    public static Transaction[] readTransactions() {

        Queue<Transaction> queue = new Queue<>();
        String input;
        while (!StdIn.isEmpty()) {
            input = StdIn.readLine();
            Transaction transaction = new Transaction(input);

            queue.enqueue(transaction);
        }
        int size = queue.size();
        Transaction[] transactions = new Transaction[size];
        for (int i = 0; i < size; i++) {
            transactions[i] = queue.dequeue();
        }
        return transactions;
    }

    public static void main(String[] args) {
        System.out.println("input like Turing 5/22/1939 11.99:");
        Transaction[] transactions = readTransactions();
        Shell.sort(transactions);
        for (Transaction t : transactions) {
            StdOut.println(t);
        }
    }
}
