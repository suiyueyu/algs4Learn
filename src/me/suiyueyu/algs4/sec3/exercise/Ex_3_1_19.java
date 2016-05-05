package me.suiyueyu.algs4.sec3.exercise;

import me.suiyueyu.algs4.sec1.Queue_alg_1_3;
import stdlib.StdIn;

/**
 * Created by yzcc on 2016/5/2.
 * 修改 FrequencyCounter ，打印出频率最高的所有单词，而非其中之一。提示，使用Queue
 */
public class Ex_3_1_19 {
    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("no argument");
            return;
        }
        int minlen = Integer.parseInt(args[0]);
        ArrayST_3_1_2<String, Integer> st = new ArrayST_3_1_2<>();
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

        Queue_alg_1_3<String> maxQueue = new Queue_alg_1_3<String>();
        // 对于有序链表or有序数组，直接寻找max()，然后将生成的list输出就行

        // 对于无序链表or无序数组
        // 这里可以循环数组两次，这样复杂度是2*O(n)，先找一次max，然后循环，是max的
        // 就加入Queue。

        // 找出出现概率最高的单词，这种写法太怂了
//        String max = " ";
//        st.put(max, 0);
//        for (String word : st.keys()){
//            if (st.get(word) >= st.get(max)){
//                max = word;
//                maxQueue.enqueue(max);// 一旦发现更大的，就加入maxQueue
//                // 可是不是Stack，这样没有用
//            }
//        }
//
//        for (String word : st.keys()){
//            if (st.get(word).equals( st.get(max)) ){
//                maxQueue.enqueue(word);
//            }
//        }
//
//        for (String word : maxQueue){
//            System.out.print(word + " ");
//        }
//        System.out.println();


        // TODO:Better
        // 有没有能边寻找边放入queue的方法呢？现在这个解法并没有体现出使用queue的必要性
        // 找出出现概率最高的单词
        String max = " ";
        st.put(max, 0);
        for (String word : st.keys()) {
            int compareResult = st.get(word).compareTo(st.get(max));

            // 发现了更大的
            if (compareResult > 0) {
                max = word;
                // 当前的不是最大的

                // 这样会内存泄露吗？
                // maxQueue = null;

                //  清空queue,有效率更好地么？
                while (!maxQueue.isEmpty()) {
                    maxQueue.dequeue();
                }
                maxQueue.enqueue(max);

            } else if (compareResult == 0) { // 最大的
                maxQueue.enqueue(max);
            } else {
                // 比当前的要小
                // 什么都不做

            }
        }

        for (String word : maxQueue) {
            System.out.print(word + " ");
        }
        System.out.println();

    }
}
