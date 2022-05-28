// https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/dynamic-programming/catalan-number-official/ojquestion

import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println((int) findCatalan(n));
    }

    public static long findCatalan(int n) {

        long[] catlanDP = new long[n + 1];
        catlanDP[0] = catlanDP[1] = 1;

        for (int i = 2; i <= n; i++) {
            int catlan = 0;
            for (int j = 0; j < i; j++) {
                catlan += catlanDP[j] * catlanDP[i - 1 - j];
            }
            catlanDP[i] = catlan;
        }

        // Printing catalan DP
        // for(int i = 0; i <= n; i++) {
        //     System.out.println(catlanDP[i]);
        // }

        return catlanDP[n];
    }

}
