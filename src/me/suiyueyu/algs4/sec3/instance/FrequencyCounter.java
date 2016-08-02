package me.suiyueyu.algs4.sec3.instance;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import me.suiyueyu.algs4.sec3.SequentialSearchST_algs_3_1;
import me.suiyueyu.algs4.sec3.exercise.ArrayST_3_1_2;
import me.suiyueyu.algs4.sec3.exercise.OrderedSequentialSearchST_3_1_3;


/**
 * Created by yzcc on 2016/3/8.
 */
public class FrequencyCounter {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("no argument");
            return;
        }
        int minlen = Integer.parseInt(args[0]);
        OrderedSequentialSearchST_3_1_3<String, Integer> st = new OrderedSequentialSearchST_3_1_3<String, Integer>();
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (word.length() < minlen) {
                continue;
            }
            if (!st.contains(word)) {
                st.put(word, 1);
            } else {
                st.put(word, st.get(word) + 1);
            }
        }

        // 找出出现概率最高的单词
        String max = " ";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max)) {
                max = word;
            }
        }
        StdOut.println(max + " " + st.get(max));

//        for (String word : st.keys()){
//            System.out.print(word + "  ");
//        }
//        System.out.println();
//        for (Integer word : st.vals()){
//            System.out.print(word + "  ");
//        }
//        System.out.println();
    }

}
