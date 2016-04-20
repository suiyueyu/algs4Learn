package me.suiyueyu.algs4.sec3.exercise;

import me.suiyueyu.algs4.sec3.SequentialSearchST_algs_3_1;

/**
 * Created by yzcc on 2016/3/29.
 * 3.1.5 实现SequentialSearchST中的size(), delete(), keys()方法
 */
public class Ex_3_1_5 {
    // 处于保护private权限的问题，first不访问的情况下实在没法写
    // 所以都写在SequentialSearchST_3_1里面了
    // 测试的main写在这里了

    public static void main(String[] args) {
        SequentialSearchST_algs_3_1<String, Integer> st = new SequentialSearchST_algs_3_1<String, Integer>();
        st.put("a", 1);
        st.put("b", 2);
        st.put("d", 4);
        st.put("c", 3);
        st.put("c", 5);

        System.out.println(st);

        System.out.println("size is " + st.size());

//        System.out.println("delete e ends " + st.delete("e"));
        System.out.println("del d ends " + st.delete("d"));

        System.out.println(st);
        System.out.println("size is " + st.size());

    }
}
