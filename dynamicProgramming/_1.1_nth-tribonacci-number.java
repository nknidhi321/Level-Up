// https://leetcode.com/problems/n-th-tribonacci-number/

// Memo
```
class Solution {
    
    public int tribonacci(int n) {
        Integer[] dp = new Integer[n + 1];
        return tribonacci_Memo(n, dp);    
    }
    
    public int tribonacci_Memo(int n, Integer[] dp) {
        if(n == 0 || n == 1 || n == 2) return dp[n] = (n == 0 ? 0 : 1);
        
        if(dp[n] != null) return dp[n];
        
        int sum  = 0;
        if(n - 3 >= 0) sum += tribonacci_Memo(n - 3, dp);
        if(n - 2 >= 0) sum += tribonacci_Memo(n - 2, dp);
        if(n - 1 >= 0) sum += tribonacci_Memo(n - 1, dp);
        return dp[n] = sum;
    }
    
}
```
-------------------------------------------------------------
// Tab
```
class Solution {
    
    public int tribonacci(int n) {
        Integer[] dp = new Integer[n + 1];
        return tribonacci_Tab(n, dp);    
    }
    
    public int tribonacci_Tab(int N, Integer[] dp) {
        for(int n = 0; n <= N; n++) {
            if(n == 0 || n == 1 || n == 2) {
                dp[n] = (n == 0 ? 0 : 1);
                continue;
            }
            
            int sum  = 0;
            if(n - 3 >= 0) sum += dp[n - 3];    // tribonacci_Memo(n - 3, dp);
            if(n - 2 >= 0) sum += dp[n - 2];    // tribonacci_Tab(n - 2, dp);
            if(n - 1 >= 0) sum += dp[n - 1];    // tribonacci_Tab(n - 1, dp);
            dp[n] = sum;
        }
        return dp[N];
    }
    
}
```
-------------------------------------------------------------
// Opti
```
class Solution {
    
    public int tribonacci(int n) {
        if(n == 0) return 0;
        else if(n == 1 || n == 2) return 1;
        return tribonacci_Opti(n);    
    }
    
    public int tribonacci_Opti(int N) {
        int a = 0, b = 1, c = 1, sum = 0;
        for(int n = 3; n <= N; n++) {
            sum = a + b + c;
            a = b;
            b = c;
            c = sum;
        }
        return c;
    }
    
}
```
-------------------------------------------------------------
