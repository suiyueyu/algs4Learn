package me.suiyueyu.algs4.model;

import java.util.Date;

/**
 * Created by yzcc on 2015/9/17.
 */
public class Transaction {
    public String who;
    public Date when;
    public double amount;

    public Transaction(String who,Date when, double amount)
    {
        this.who = who;
        this.when = when;
        this.amount = amount;
    }
//    public
// TODO: 没写完
}
