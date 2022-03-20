// https://practice.geeksforgeeks.org/problems/friends-pairing-problem5425/1#

// Memo

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

// Tab
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
