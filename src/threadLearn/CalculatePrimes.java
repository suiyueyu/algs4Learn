package threadLearn;

/**
 * Created by yzcc on 2016/4/19.
 */
public class CalculatePrimes extends Thread {
    public static final int MAX_PRIMES = 1000;
    public static final int TEN_SECONDS = 1000;

    public volatile boolean finished = false;

    public void run() {
        int[] primes = new int[MAX_PRIMES];
        int count = 0;

        for (int i = 2; count < MAX_PRIMES; i++) {

            if (finished) {
                break;
            }

            boolean prime = true;
            for (int j = 0; j < count; j++) {
                if (i % primes[j] == 0) {
                    prime = false;
                    break;
                }
            }

            if (prime) {
                primes[count++] = i;
                System.out.println("Found prime : " + i);
            }
        }
    }

    public static void main(String[] args) {
        CalculatePrimes calculator = new CalculatePrimes();

        calculator.start();
        try {
            Thread.sleep(TEN_SECONDS);
        } catch (InterruptedException e) {

        }
        calculator.finished = true;
    }
}
