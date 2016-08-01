package me.suiyueyu.algs4.sec1.model;

/**
 * Created by yzcc on 2016/8/1.
 */
public class Date {
    private final int year;
    private final int month;
    private final int day;

    public Date(int m, int d, int y) {
        this.year = y;
        this.month = m;
        this.day = d;
    }

    public int month() {
        return month;
    }

    public int day() {
        return day;
    }

    public int year() {
        return year;
    }

    public String toString() {
        return month() + "/" + day() + "/" + year();
    }

    public boolean equals(Object x) {
        if (this == x) {
            return true;
        }
        if (x == null) {
            return false;
        }
        if (this.getClass() != x.getClass()) {
            return false;
        }
        Date that = (Date) x;
        if (this.day != that.day) {
            return false;
        }
        if (this.month != that.month) {
            return false;
        }
        if (this.year != that.year) {
            return false;
        }
        return true;
    }
}
