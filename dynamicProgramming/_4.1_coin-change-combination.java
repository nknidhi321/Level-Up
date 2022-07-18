// https://www.pepcoding.com/resources/online-java-foundation/dynamic-programming-and-greedy/coin-change-combination-official/ojquestion
// Infinite Coin change combination

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] coins = new int[n];
        for(int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }
        int tar = sc.nextInt();
        
        System.out.println(totalCombinations(tar, coins));
    }
    
    public static int totalCombinations(int TAR, int[] coins) {
        int[] dp = new int[TAR + 1];
        return totalCombinations(TAR, coins, dp);
    }
    
  
    //======================================================================================
    public static int totalCombinations(int TAR, int[] coins, int[] dp) {
        dp[0] = 1;
        for(int coin : coins) {
            for(int tar = 1; tar <= TAR; tar++) {
                if(tar - coin >= 0) {
                    dp[tar] += dp[tar - coin];   
                }
            }   
        }
        return dp[TAR];
    }    
    //======================================================================================
    
}
