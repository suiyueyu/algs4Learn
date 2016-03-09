package me.suiyueyu.algs4.sec3.instance;

import me.suiyueyu.algs4.sec3.SequentialSearchST_algs_3_1;
import stdlib.StdIn;
import stdlib.StdOut;

/**
 * Created by yzcc on 2016/3/8.
 */
public class FrequencyCounter {
    public static void main(String[] args) {
        int minlen = Integer.parseInt(args[0]);
        SequentialSearchST_algs_3_1<String, Integer> st = new SequentialSearchST_algs_3_1<String, Integer>();
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
    }

}
