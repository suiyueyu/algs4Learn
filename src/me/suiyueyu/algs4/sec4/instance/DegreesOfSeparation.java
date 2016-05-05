package me.suiyueyu.algs4.sec4.instance;

import me.suiyueyu.algs4.sec4.BreadthFristPaths;
import me.suiyueyu.algs4.sec4.Graph_model;
import me.suiyueyu.algs4.sec4.SymbolGraph;
import stdlib.StdIn;
import stdlib.StdOut;

/**
 * Created by yzcc on 2016/5/5.
 */
public class DegreesOfSeparation {
    public static void main(String[] args) {
        SymbolGraph sg = new SymbolGraph(args[0], args[1]);

        Graph_model G = sg.G();

        String source = args[2];
        if (!sg.contains(source)) {
            StdOut.println(source + "not in database. ");
            return;
        }
        int s = sg.index(source);
        BreadthFristPaths bfs = new BreadthFristPaths(G, s);

        while (!StdIn.isEmpty()) {
            String sink = StdIn.readLine();
            if (sg.contains(sink)) {
                int t = sg.index(sink);
                if (bfs.hasPathTo(t)) {
                    for (int v : bfs.pathTo(t)) {
                        StdOut.println("  " + sg.name(v));
                    }
                } else {
                    StdOut.println("Not connected");
                }
            } else {
                StdOut.println("Not in database");
            }
        }
    }
}
