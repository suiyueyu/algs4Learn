package me.suiyueyu.algs4.sec4.instance;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import me.suiyueyu.algs4.sec4.Graph_model;
import me.suiyueyu.algs4.sec4.SymbolGraph;

/**
 * Created by yzcc on 2016/5/5.
 */
public class SymbolGraphMain {
    public static void main(String[] args) {
        String filename = args[0];
        String delim = args[1];
        SymbolGraph sg = new SymbolGraph(filename, delim);

        Graph_model G = sg.G();

        while (StdIn.hasNextLine()) {
            String source = StdIn.readLine();
            for (int w : G.adj(sg.index(source))) {
                StdOut.println("  " + sg.name(w));
            }
        }

    }
}
