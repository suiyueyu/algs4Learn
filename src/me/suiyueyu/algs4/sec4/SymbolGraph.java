package me.suiyueyu.algs4.sec4;

import edu.princeton.cs.algs4.In;
import me.suiyueyu.algs4.sec3.SequentialSearchST_algs_3_1;


/**
 * Created by yzcc on 2016/5/5.
 */
public class SymbolGraph {

    private SequentialSearchST_algs_3_1<String, Integer> st;
    private String[] keys;
    private Graph_model G;

    public SymbolGraph(String stream, String sp) {
        st = new SequentialSearchST_algs_3_1<>();
        In in = new In(stream);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(sp);

            for (int i = 0; i < a.length; i++) {
                if (!st.contains(a[i])) {
                    st.put(a[i], st.size());
                }
            }
        }
        keys = new String[st.size()];

        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        Graph_model G = new Graph_model(st.size());
        in = new In(stream);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(sp);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                G.addEdge(v, st.get(a[i]));
            }
        }
    }

    public boolean contains(String s) {
        return st.contains(s);
    }

    public int index(String s) {
        return st.get(s);
    }

    public String name(int v) {
        return keys[v];
    }

    public Graph_model G() {
        return G;
    }
}
