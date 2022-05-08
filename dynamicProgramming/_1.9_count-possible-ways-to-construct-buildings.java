// https://practice.geeksforgeeks.org/problems/count-possible-ways-to-construct-buildings5007/1#

class Solution {
    
    public int TotalWays(int n) {
        long mod = (long)1e9 + 7;

        long[] dp0 = new long[n + 1]; // Ending at space
        long[] dp1 = new long[n + 1]; // Ending at building
        
        // NOTE : dp[0] is waste
        // dp0[i] will store number of ways, string ending with space of length i
        // dp1[i] will store number of ways, string ending with building of length i		
        // ans of n will be calculated by dp0[i] + dp1[i]  
		
	    dp0[1] = dp1[1] = 1;  
		
        for(int i = 2; i <= n; i++) {
            dp0[i] = (dp0[i - 1] + dp1[i - 1]) % mod;
            dp1[i] = dp0[i - 1];
        }
        
        long ans = (dp0[n] + dp1[n]) % mod;
        
        // Jitne tareeke se ek taraf arrange kar saktey hai
        // Utne he tareeke se dusre taraf v arrange kar saktey hai
        // So, total number of ways will be ans * ans
        return (int)((ans * ans) % mod);    
    }
    
}

