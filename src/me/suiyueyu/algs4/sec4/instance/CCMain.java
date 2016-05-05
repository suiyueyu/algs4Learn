package me.suiyueyu.algs4.sec4.instance;

import me.suiyueyu.algs4.sec1.Bag_alg_1_4;
import me.suiyueyu.algs4.sec4.CC;
import me.suiyueyu.algs4.sec4.Graph_model;
import stdlib.In;
import stdlib.StdOut;

/**
 * Created by yzcc on 2016/4/28.
 */
public class CCMain {
    public static void main(String[] args) {
        Graph_model G = new Graph_model(new In(args[0]));

        CC cc = new CC(G);

        int M = cc.count();
        StdOut.println(M + " components");

        Bag_alg_1_4<Integer>[] components;
        components = (Bag_alg_1_4<Integer>[]) new Bag_alg_1_4[M];

        for (int i = 0; i < M; i++) {
            components[i] = new Bag_alg_1_4<Integer>();
        }

        for (int v = 0; v < G.V(); v++) {
            components[cc.id(v)].add(v);
        }
        for (int i = 0; i < M; i++) {
            for (int v : components[i]) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }


    }
}
