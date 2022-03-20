// https://www.pepcoding.com/resources/online-java-foundation/dynamic-programming-and-greedy/friends-pairing-official/ojquestion
// Levels pe har ek frnd hoga ek baar, pehle 0, 1, 2, so on...

// Recursion
// Sumeet Malik
// Note recursion logic is different from memo, tab, opti

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean[] visited = new boolean[n];
        System.out.println(pairing(0, n, visited));
    }
    
    // Levels pe har ek frnd hoga ek baar, pehle 0, 1, 2, so on...
    public static int pairing(int idx, int n, boolean[] visited) {
        if(idx == n) return 1; // Sbko process kar liya => Ek tareeka mil gaya 
        
        int count = 0;
        
        // Your fate is already decided by someone, aage badh jaao, someone already made pair with you from prev calls
        if(visited[idx]) count += pairing(idx + 1, n, visited);
        
        // Tumhe kvi kisi ne nahi choona => Ab tumhare pass 2 choice hai 1)akele jaao 2) Kisi k saath jaao
        else {
            visited[idx] = true; // Mark yourself as visited
            
            // Akele jaane ki choice
            count += pairing(idx + 1, n, visited);
            
            // Kisi k saath jaane ki choice
            for(int i = idx + 1; i < n; i++) {
                if(!visited[i]) { // Jiske saath jaana chahte ho wo visited nahi h
                    visited[i] = true; // Toh tum uske saath chale jaao
                    count += pairing(idx + 1, n, visited); // Here idx + 1 he pass hoga kuki har ek ka apna level hoga 0, 1, 2, so on..
                    visited[i] = false; // Backtrack the frnd, jiske saath gaye the
                }
            }
            visited[idx] = false; // Backtrack, unmark yourself
        }
        return count; // Jitne v tareeke aaye uska count 
    } 

}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// https://practice.geeksforgeeks.org/problems/friends-pairing-problem5425/1#
// Dp will store n log kitne tareeko se jaa saktey ho
// Rajneesh

// Memoization

class Solution {
    
    public static int mod = (int)(1e9 + 7);
    
    public long countFriendsPairings(int n) {
       Long[] dp = new Long[n + 1];
       return countFriendsPairings_Memo(n, dp);
    }
    
    public static long countFriendsPairings_Memo(int n, Long[] dp) {
        if(n == 0) return dp[n] = (long)1; // 1 tareeka hai, kuki sab log process ho paaye
        
        if(dp[n] != null) return dp[n];
        
        long single = 0, pairUp = 0;
        single = countFriendsPairings_Memo(n - 1, dp);
        
        if(n - 2 >= 0) {
            // Why * (n - 1) ? Kuki n k option h ki wo n - 1 logo k saath pair up ho jaaye
            pairUp = countFriendsPairings_Memo(n - 2, dp) * (n - 1); 
        }
        
        return dp[n] = (single + (pairUp % mod)) % mod;
    }
    
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Tabulation

// Make sure to make dp of primitive type and not of class type, because class type stores null by default where as primite type stores 0
// And memo makes "only necessary calls", whereas tab makes "all" the looped calls, so it will throw null ptr exception when tried to be retrived

class Solution {
    
    public static int mod = (int)(1e9 + 7);
    
    public long countFriendsPairings(int n) {
       long[] dp = new long[n + 1];                          // PRIMITIVE TYPE
       return countFriendsPairings_Tab(n, dp);
    }
    
    public static long countFriendsPairings_Tab(int N, long[] dp) {
        for(int n = 0; n <= N; n++) {
            if(n == 0) {
                dp[n] = (long)1; // 1 tareeka hai, kuki sab log process ho paaye
                continue;
            }
            
            long single = 0, pairUp = 0;
            single = dp[n - 1]; //countFriendsPairings_Memo(n - 1, dp);
            
            if(n - 2 >= 0) {
                // Why * (n - 1) ? Kuki n k option h ki wo n - 1 logo k saath pair up ho jaaye
                pairUp = dp[n - 2] * (n - 1); //countFriendsPairings_Memo(n - 2, dp) * (n - 1); 
            }
            dp[n] = (single + (pairUp % mod)) % mod;
        }
        return dp[N];
    }
    
}    

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Two pointer, fibonacci Type

class Solution {
    
    public static int mod = (int)(1e9 + 7);
    
    public long countFriendsPairings(int n) {
       return countFriendsPairings_Opti(n);
    }
    
    public static long countFriendsPairings_Opti(int N) {
        long a = 1, b = 1;
        for(int n = 2; n <= N; n++) {
            long sum = (((a * (n - 1)) % mod) + b) % mod;
            
            a = b;
            b = sum;
        }
        return b;
    }
    
}    

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
