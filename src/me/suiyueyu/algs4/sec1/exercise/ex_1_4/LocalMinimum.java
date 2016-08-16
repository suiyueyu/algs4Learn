package me.suiyueyu.algs4.sec1.exercise.ex_1_4;

/**
 * Created by yzcc on 2016/8/11.
 * <p>
 * 1.4.18 数组的局部最小元素。 编写一个程序，给定一个含有N个 <b>不同</b> 整数的数组，
 * 找到一个局部最小元素：满足 a[i] < a[i - 1]，且 a[i] < a[i+1]的索引i。
 * 程序在最坏情况下所需的比较次数为 ~ 2lgN。
 * <p>
 * 检查数组的中间值a[N/2] 以及和它相邻的元素 a[N/2 - 1]和 a[N/2 + 1]。如果a[N/2] 是一个局部最小值则算法终止；
 * 否则则在较小的邻居元素的半边中继续寻找。
 * <p>
 * 1.4.19 矩阵的局部最小元素。 给定一个含有N^2 个不同整数的 N*N数组 a[]。
 * 设计一个运行时间和N成正比的算法来找出一个局部最小元素：满足a[i][j] < a[i+1][j], a[i][j] < a[i][j+1],
 * a[i][j] < a[i-1][j], 以及a[i][j] < a[i][j-1] 的索引i和j。程序的运行时间在最坏情况下应该和N成正比
 */
public class LocalMinimum {
    public static int findArrayValley(int[] a, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;

        if (hi == lo) {
            // 数组只有一个元素,这就是本地最优
            return lo;
        } else if (hi - lo == 1) {
            // 有两个元素
            return a[lo] < a[hi] ? lo : hi;
        } else if (a[mid] < a[mid + 1] && a[mid] < a[mid - 1]) {
            return mid;// 找到了
        } else if (a[mid - 1] < a[mid + 1]) {
            // 等号无所谓，放到另一边就行了
            return findArrayValley(a, lo, mid - 1);
        } else {
            return findArrayValley(a, mid + 1, hi);
        }
    }

    /**
     * @param a
     * @return
     * @see <a href = "http://stackoverflow.com/questions/12238241/find-local-minima-in-an-array">参考链接</a>
     * <p>
     * If the array elements are not guaranteed to be distinct,
     * then it's not possible to do this in O(log n) time.
     * 如果我们允许重复元素，那么可以举出一个数组
     * 3, 2, 2, 2, 2, 2, 2
     * 这里不存在元素比它的邻居都小，
     * 为了判断有多少个重复元素，我们需要遍历数组，这个操作起码是O(n)的，
     * 这样, 在这个反例里面，O(log n)时间内是无法完成的
     * <p>
     * we'll use the fact that a local minimum will exist in this half of the array
     * as a justification for throwing away one half of the array.
     * <p>
     * 1. If there is just one array element, it's a local minimum.
     * 2. If there are two array elements, check each. One must be a local minimum.
     * 3. Otherwise, look at the middle element of the array. If it's a local minimum,
     * return it. Otherwise, at least one adjacent value must be smaller than this one.
     * Recurse in the half of the array containing that smaller element (but not the middle).
     * <p>
     * T(1) ≤ 1
     * T(2) ≤ 1
     * T(n) ≤ T(n / 2) + 1
     * 这原理我解释不清楚，不过可以画一个sin()函数看下效果，局部最小，的确在某个区域的中心
     * 要不就枚举，一阶导数+二阶导数 ，递增递减一共四种，讨论下也行
     * <p>
     * 而算法的复杂度，由主定理有，是O(log n)
     */
    public static int findArrayValley(int[] a) {
        return findArrayValley(a, 0, a.length - 1);
    }

    /**
     * @param a
     * @return int[2] x, y
     * @see <a href="http://stackoverflow.com/questions/18525179/find-local-minimum-in-n-x-n-matrix-in-on-time">参考链接</a>
     * 解法不是这个，不过解释很清楚
     * Turn the problem into 3-D one to see why the algorithm works.
     * Put the matrix on a table. Pretend there are pillars extending out of each cell
     * and that the height of the pillar is directly proportional to its value.
     * Put a ball on any pillar. Have the ball always fall onto the adjacent pillar that
     * is the lowest altitude until it is at a local minimum.
     * <p>
     * 简单来说，把矩阵的值投射到z轴，从某个最高点(a[i][j]最大的点)放置一个球
     * 这个球会沿着邻居中最低(坡度最大)的那个点的方向滚去，直到局部最优
     * @see <a href="http://www.filipekberg.se/2014/02/10/understanding-peak-finding/">某个人的解释</a>
     * @see <a href="http://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-006-introduction-to-algorithms-fall-2011/lecture-videos/MIT6_006F11_lec01.pdf">mit 算法导论的课件</a>
     * 这个问题也可以叫peek finding
     */
    public static int[] findValley2D(int[][] a) {
        int N = a.length - 1;

        Point p = new Point(1, 1);
        return new int[]{p.X, p.Y};
    }

    /**
     * 递归函数，在由left, top, ... bottom 围成的象限中
     * 先在边框(4条)和两条中轴上寻找最小值(会组成一个window形状)
     * 找到以后
     * if 它是valley点
     * return it
     * else
     * 找一个更小的邻居点
     * 这个邻居点不能在window上
     * 递归在邻居所在的象限上寻找，包含边框(2条边框的一半，以及2条中轴的一半)
     *
     * @param a
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @return
     */
    public static Point findValley2D(int[][] a, int left, int top, int right, int bottom) {
        if (a.length == 0 || a[0].length == 0)
            return new Point(-1, -1);// 空的输入，或者是空矩阵，返回没有
        else if (isSmallMatrix(left, top, right, bottom)) {
            // 小于3*3的矩阵，不存在valley 点
            return new Point(-1, -1);
        } else {
            Point p = findMinimumPointInTheWindow(a, left, top, right, bottom);
            int midRow = top + (bottom - top) / 2;
            int midColumn = left + (right - left) / 2;
            // if 它是valley点
            //    return it
            if (isValleyPoint(p, a)) {
                return p;
            } else {
                /*
                 * else
                 *    找一个更小的邻居点
                 *    这个邻居点不能在window上
                 *    递归在邻居所在的象限上寻找，包含边框(2条边框的一半，以及2条中轴的一半)
                 */
                if (p.X == left) {
                    // 那肯定是右边那个点
                    if (p.Y < midRow) {
                        return findValley2D(a, left, top, midColumn, midRow);
                    }
                    // TODO: 2016/8/14 这个题先放在这里了，浪费太多时间了，讨论分治的切分太浪费时间了，赶紧往后看
                    // TODO: 2016/8/14 题目也是先卡到1.4.19
                }
            }
        }

        return new Point(-1, -1);

    }

    /**
     * 判断是不是小于2*2的矩阵，
     * 这种矩阵的中轴线和边框重合,我们在window上找到的最小点的邻居，都在window上
     * 这样就不再切分矩阵了
     * <p>
     * 而且因为山谷节点的判断，要求4个方向都有
     * 那么起码是3*3的矩阵才有valley点
     *
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @return
     */
    private static boolean isSmallMatrix(int left, int top, int right, int bottom) {
        // 因为矩阵是对称的N * N, 所以其实right-left = bottom - top
        if (right - left < 2 && bottom - top < 2) {
            return true;
        }
        return false;
    }

    /**
     * 判断点(x, y)是不是在window上
     * 即4条边框 +　2条中轴
     *
     * @param p
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @return
     */
    private static boolean isInTheWindow(Point p, int left, int top, int right, int bottom) {
        int midColumn = left + (right - left) / 2;
        int midRow = top + (bottom - top) / 2;

        if (p.X == left || p.X == right || p.Y == top || p.Y == bottom) {
            return true;
        } else if (p.X == midColumn || p.Y == midRow) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 寻找窗口上的最小点
     *
     * @param a
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @return
     */
    private static Point findMinimumPointInTheWindow(int[][] a, int left, int top, int right, int bottom) {
        Point p = new Point(0, 0);
        int min = a[0][0];

        int midRow = top + (bottom - top) / 2;
        int midColumn = left + (right - left) / 2;

        for (int x = left; x <= right; x++) {
            // top boundary
            if (a[top][x] < min) {
                p.X = x;
                p.Y = top;
                min = a[top][x];
            }

            // bottom boundary
            if (a[bottom][x] < min) {
                p.X = x;
                p.Y = bottom;
                min = a[bottom][x];
            }

            // midRow
            if (a[midRow][x] < min) {
                p.X = x;
                p.Y = midRow;
                min = a[midRow][x];
            }
        }

        for (int y = top; y <= bottom; y++) {
            // left boundary
            if (a[y][left] < min) {
                p.X = left;
                p.Y = y;
                min = a[y][left];
            }
            // right
            if (a[y][right] < min) {
                p.X = right;
                p.Y = y;
                min = a[y][right];
            }
            // midColumn
            if (a[y][midColumn] < min) {
                p.X = midColumn;
                p.Y = y;
                min = a[y][midColumn];
            }
        }
        return p;
    }

    /**
     * 判断是不是valley点, 即
     * 满足a[i][j] < a[i+1][j], a[i][j] < a[i][j+1],
     * a[i][j] < a[i-1][j], 以及a[i][j] < a[i][j-1] 的索引i和j
     * <p>
     * 这个地方逻辑有问题，这对于windows上外边框的点根本没有用
     *
     * @param a
     * @param p
     * @return
     */
    private static boolean isValleyPoint(Point p, int[][] a) {
        if ((p.X > 0) && (a[p.X][p.Y] < a[p.X - 1][p.Y])
                && ((p.X < a.length - 1) && (a[p.X][p.Y] < a[p.X + 1][p.Y]))
                && ((p.Y > 0) && (a[p.X][p.Y] < a[p.X - 1][p.Y - 1]))
                && ((p.Y) < a.length - 1 && (a[p.X][p.Y] < a[p.X][p.Y + 1]))
                ) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] problem = new int[][]{
                new int[]{0, 0, 9, 0, 0, 0, 0},
                new int[]{0, 0, 0, 0, 0, 0, 0},
                new int[]{0, 1, -1, 0, 0, 0, 0},
                new int[]{0, 2, 0, 0, 0, 0, 0},
                new int[]{0, 3, 0, 0, 0, 0, 0},
                new int[]{0, 5, 0, 0, 0, 0, 0},
                new int[]{0, 4, 7, 0, 0, 0, 0},
        };
        System.out.println(problem[1][2]);

    }

    public static class Point {
        int X;
        int Y;

        public Point(int X, int Y) {
            this.X = X;
            this.Y = Y;
        }

        public boolean equals(Object o) {
            if (o == this) return true;
            else if (o == null) return false;
            else if (o.getClass() == this.getClass()) {
                Point that = (Point) o;
                if (this.X == that.X && this.Y == that.Y) {
                    return true;
                }
                return false;
            } else {
                return false;
            }
        }

        public String toString() {
            return "( " + this.X + ", " + this.Y + " )";
        }
    }
}

