// https://leetcode.com/problems/jump-game-ii/

```
class Solution {
    
    public int jump(int[] nums) {
        int n = nums.length;
        Integer[] dp = new Integer[n];
        return minJumps(0, n, dp, nums) - 1;
    }
    
    public int minJumps(int i, int n, Integer[] dp, int[] nums) {
        if(i == n - 1) return dp[i] = 1;
        
        if(dp[i] != null) return dp[i];
        
        int min = Integer.MAX_VALUE - 1; // -1 To prevent from overflow
        for(int j = i + 1; j <= i + nums[i] && j < n; j++) {
            min = Math.min(min, minJumps(j, n, dp, nums));
        }
        return dp[i] = min + 1;   
    }
    
}
```
