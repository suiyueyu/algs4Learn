package test;

/**
 * Created by yzcc on 2015/5/23.
 */
public class Test {

    public static void main(String[] args){
        int N = 1027;
        while(N > 0){
            System.out.println(N + " : " + N%2);
            N/=2;
        }
    }
}
