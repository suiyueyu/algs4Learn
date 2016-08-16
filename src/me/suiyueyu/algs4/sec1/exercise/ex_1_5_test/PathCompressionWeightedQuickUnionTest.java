package me.suiyueyu.algs4.sec1.exercise.ex_1_5_test;

import me.suiyueyu.algs4.sec1.exercise.ex_1_5.PathCompressionWeightedQuickUnion;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yzcc on 2016/8/15.
 */
public class PathCompressionWeightedQuickUnionTest {

    private PathCompressionWeightedQuickUnion pCWQU;

    /**
     * 生成UF
     *
     * @throws Exception
     */
    @Before
    public void genereteUF() throws Exception {
        pCWQU = new PathCompressionWeightedQuickUnion(10);

    }

    @Test
    public void find() throws Exception {

        pCWQU.union(4, 3);
        pCWQU.union(3, 8);
        pCWQU.union(6, 5);
        pCWQU.union(9, 4);
        pCWQU.union(2, 1);
        pCWQU.union(8, 9);
        pCWQU.union(5, 0);
        pCWQU.union(7, 2);
        pCWQU.union(6, 1);
        pCWQU.union(1, 0);
        pCWQU.union(6, 7);

        assertEquals(4, pCWQU.find(3));
        assertEquals(6, pCWQU.find(1));
        assertEquals(6, pCWQU.find(2));
    }

    @Test
    public void union() throws Exception {

    }


    /**
     * clearUF 清理UF
     *
     * @throws Exception
     */
    @After
    public void clearUF() throws Exception {
        pCWQU = null;
    }

}