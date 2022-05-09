// https://practice.geeksforgeeks.org/problems/painting-the-fence3727/1/#
// Reference : https://www.youtube.com/watch?v=ju8vrEAsa3Q&t=864s

class Solution {
    
    public long countWays(int n, int k) {
        if(n == 1) return k;
        
        long mod = (long)1e9 + 7;

        long[] dp0 = new long[n + 1]; // Last 2 colors Ending at ii(same color)
        long[] dp1 = new long[n + 1]; // Last 2 colors Ending at ij(diff color)
        
        // NOTE : dp[0] is waste
        // dp0[i] will store number of ways, i fences which can be painted by ii
        // dp1[i] will store number of ways, i fences which can be painted by ij		
        // ans of n will be calculated by dp0[i] + dp1[i]  
		
	    dp0[2] = k;
	    dp1[2] = k * (k - 1);  
		
        for(int i = 3; i <= n; i++) {
            dp0[i] = dp1[i - 1];
            dp1[i] = (((dp0[i - 1] + dp1[i - 1]) % mod) * (k - 1)) % mod;
        }
        return (dp0[n] + dp1[n]) % mod;
    }

}
