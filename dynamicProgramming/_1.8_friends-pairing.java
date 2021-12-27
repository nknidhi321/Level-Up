// https://practice.geeksforgeeks.org/problems/friends-pairing-problem5425/1#

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
