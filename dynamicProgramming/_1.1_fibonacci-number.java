// https://leetcode.com/problems/fibonacci-number/

// Memoization
class Solution {
    public int fib(int n) {
        Integer[] dp = new Integer[n + 1];
        return fibo(n, dp);
    }
    public int fibo(int n, Integer[] dp) {
        if(n == 0) return 0;
        if(n == 1) return 1;
        
        if(dp[n] != null) return dp[n];
        
        int sum = 0;
        if(n - 1 >= 0) sum += fibo(n - 1, dp);   
        if(n - 2 >= 0) sum += fibo(n - 2, dp);
        return dp[n] = sum;
    }
}

-------------------------------------------------
// Tabulation 
class Solution {
    public int fib(int n) {
        Integer[] dp = new Integer[n + 1];
        return fibo(n, dp);
    }
    public int fibo(int N, Integer[] dp) {
        for(int n = 0; n <= N; n++) {
            if(n == 0 || n == 1) {
                dp[n] = n;
                continue;
            }

            int sum = 0;
            if(n - 1 >= 0) sum += dp[n - 1]; // fibo(n - 1, dp);   
            if(n - 2 >= 0) sum += dp[n - 2]; // fibo(n - 2, dp);
            dp[n] = sum;
        }
        return dp[N];
    }
}
