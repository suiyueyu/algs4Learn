package me.suiyueyu.someOJ;

import edu.princeton.cs.algs4.BinaryOut;

import java.util.Arrays;


/**
 * Created by yzcc on 2016/9/6.
 * <p>
 * 题目描述
 * 对于一个矩阵，请设计一个算法，将元素按“之”字形打印。具体见样例。
 * 给定一个整数矩阵mat，以及他的维数nxm，请返回一个数组，其中元素依次为打印的数字。
 * 测试样例：
 * [[1,2,3],[4,5,6],[7,8,9],[10,11,12]],4,3
 * 返回：[1,2,3,6,5,4,7,8,9,12,11,10]
 */
public class Printer {

    public static int[] printMatrix(int[][] mat, int n, int m) {
        // write code here
        int[] result = new int[n * m];
        // rows
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i % 2 == 0) {
                    result[i * m + j] = mat[i][j];
                } else {
                    result[i * m + j] = mat[i][m - 1 - j];
                }
            }
        }
        return result;
    }

    public static void testCase1(String[] args) {
        //[[1,2,3],[4,5,6],[7,8,9],[10,11,12]],4,3
        int[][] a = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {10, 11, 12}
        };
        System.out.println(Arrays.toString(printMatrix(a, 4, 3)));
    }


    /**
     * 对于一个矩阵，请设计一个算法从左上角(mat[0][0])开始，顺时针打印矩阵元素。
     * 给定int矩阵mat,以及它的维数nxm，请返回一个数组，数组中的元素为矩阵元素的顺时针输出。
     * 测试样例：
     * [[1,2],[3,4]],2,2
     * 返回：[1,2,4,3]
     *
     * @param mat
     * @param n
     * @param m
     * @return
     */
    public static int[] clockwisePrint(int[][] mat, int n, int m) {
        // write code here
        int[] result = new int[n * m];
//        return clockwisePrint(mat, n, m, 0, result);
        int level = 0;
        int maxLevel = Math.min(n, m) / 2;

        int row, col;// 做下标
        for (int i = 0; i < n * m; ) {
            for (; level <= maxLevel; level++) {
                row = col = level;
                // 左到右
                for (; col < m - 2 * level - 1; ) {
                    result[i++] = mat[row][col++];
                }
                // 少复制一个，这里就正好是起点
                //上到下
                // 然而特殊情况只有一列的地方就都不复制了
                // @error !!! 这里有问题，不要执行
                for (; row < n - 2 * level - 1; ) {
                    result[i++] = mat[row++][col];
                }

                // 右到左 [ )
                for (; col > level; ) {
                    result[i++] = mat[row][col--];
                }
                // 下到上
                for (; row > level; ) {
                    result[i++] = mat[row--][col];
                }

            }
        }
        return result;
    }


    public static void main(String[] args) throws InterruptedException {
        int[][] a = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {10, 11, 12}
        };
        // 这个题目没做出来，不要执行
//        int[] result = clockwisePrint(a, 4, 3);
//        System.out.println(Arrays.toString(result));

//        Thread.sleep(5000);
//        System.out.println("nihao");
        System.out.println(Thread.currentThread().getName());


        BinaryOut
    }
}