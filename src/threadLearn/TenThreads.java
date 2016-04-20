package threadLearn;

/**
 * Created by yzcc on 2016/4/19.
 */
public class TenThreads {

    private static class WorkerThread extends Thread {
        int max = Integer.MIN_VALUE;
        int[] ourArray;

        public WorkerThread(int[] ourArray) {
            this.ourArray = ourArray;
        }

        public void run() {
            for (int i = 0; i < ourArray.length; i++) {
                max = Math.max(max, ourArray[i]);
            }
        }

        public int getMax() {
            return max;
        }
    }

    public static void main(String[] args) {
        WorkerThread[] threads = new WorkerThread[10];
//        int[][] bigMatrix = getBigHairyMatrix();

        int max = Integer.MAX_VALUE;

        for (int i = 0; i < 10; i++) {
//            threads[i] = new WorkerThread();
        }
    }
}
