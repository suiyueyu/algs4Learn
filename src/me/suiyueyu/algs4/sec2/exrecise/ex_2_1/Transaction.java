package me.suiyueyu.algs4.sec2.exrecise.ex_2_1;

import me.suiyueyu.algs4.sec1.model.Date;

/**
 * Created by yzcc on 2016/8/16.
 */
public class Transaction implements Comparable<Transaction> {
    public String _who;
    public Date _when;
    public double amount;

    public Transaction(String who, Date when, double amount) {
        this._who = who;
        this._when = when;
        this.amount = amount;
    }

    /**
     * 1.2.13 字符串解析
     * 解析形如如下的字符串作为构造
     * Turing 5/22/1939 11.99
     *
     * @param transaction 解析的字符串
     */
    public Transaction(String transaction) {
        String[] fields = transaction.split("\\s+");
        this._who = fields[0];
        this._when = new Date(fields[1]);
        this.amount = Double.parseDouble(fields[2]);

    }

    public String who() {
        return _who;
    }

    public Date when() {
        return _when;
    }

    public double amount() {
        return amount;
    }

    public String toString() {
        return who() + " " + when() + " / " + amount();
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o.getClass() == this.getClass()) {
            Transaction that = (Transaction) o;
            if (this._when == that._when
                    && this._who == that._who
                    && Double.compare(this.amount, that.amount) == 0) {
                return true;
            }
        }
        return false;
    }

//        public int hashCode(){
//
//        }

    @Override
    public int compareTo(Transaction o) {
        // 这里书上给的实现就是
        // a < b return -1
        // b < a return 1
        // else return 0;
        // 看了下Double 里面的实现，还考虑了下NaN的情况
        return Double.compare(this.amount, o.amount);
    }
}
