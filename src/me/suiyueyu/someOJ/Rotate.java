package me.suiyueyu.someOJ;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by yzcc on 2016/9/6.
 * 有一个NxN整数矩阵，请编写一个算法，将矩阵顺时针旋转90度。
 * 给定一个NxN的矩阵，和矩阵的阶数N,请返回旋转后的NxN矩阵,保证N小于等于300。
 * 测试样例：
 * [[1,2,3],[4,5,6],[7,8,9]],3
 * 返回：[[7,4,1],[8,5,2],[9,6,3]]
 */
public class Rotate {
    public static int[][] rotateMatrix(int[][] mat, int n) {
        // write code here
        reverseAdj(mat, n);
        System.out.println(Arrays.deepToString(mat));
        for (int i = 0; i < n; i++) {
            reverseRow(mat[i], 0, n - 1);
        }
        return mat;
    }

    /**
     * 数组交换
     *
     * @param a
     * @param lo
     * @param hi
     */
    private static void reverseRow(int[] a, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;
        int tmp;
        for (int i = lo; i <= mid; i++) {
            tmp = a[i];
            a[i] = a[hi - (i - lo)];
            a[hi - (i - lo)] = tmp;
        }
    }

    /**
     * 按照中轴线交换
     *
     * @param a
     * @param n
     */
    private static void reverseAdj(int[][] a, int n) {
        int tmp;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                tmp = a[i][j];
                a[i][j] = a[j][i];
                a[j][i] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] mat = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16},
        };
        rotateMatrix(mat, 4);

        System.out.println(Arrays.deepToString(mat));
    }
}