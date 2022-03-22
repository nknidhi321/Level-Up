// https://www.pepcoding.com/resources/online-java-foundation/dynamic-programming-and-greedy/coin-change-permutations-official/ojquestion

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n  = sc.nextInt();
        int[] coins = new int[n];
        for(int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }
        int tar = sc.nextInt();
        System.out.println(change(tar, coins));
    }
    
    public static int change(int tar, int[] coins) {
        int[] dp = new int[tar + 1]; 
        return change_Memo(tar, coins, dp);
    }
    
    public static int change_Memo(int tar, int[] coins, int[] dp) {
        if(tar == 0) return dp[tar] = 1; 
        
        if(dp[tar] != 0) return dp[tar];
        
        int count = 0;
        for(int ele : coins) {
            if(tar - ele >= 0) {
                count += change_Memo(tar - ele, coins, dp);
            } 
        }
        return dp[tar] = count;
    }
}
