// https://leetcode.com/problems/jump-game/

```
class Solution {
    
    public boolean canJump(int[] nums) {
        int n = nums.length;
        Integer[] dp = new Integer[n];
        minJumps(0, n, dp, nums);
        return dp[0] != null && dp[0] != Integer.MAX_VALUE ? true : false; 
        // Ex : [3,2,1,0,4] No path from idx 3, so making check of Integer.MAX_VALUE
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
