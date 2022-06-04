// https://leetcode.com/problems/minimum-score-triangulation-of-polygon/

/*
    si && ei ko base mante huye, har ek remaining points pe cut lgao, that means,
    1) Connect xth point with si
    2) Connect xth  point with ei
    3) si and ei is already connected
    So you formed a triagle, now call recursively for left and right cost.

    And get the min of all these cuts.
    Observe tree diagram to find out what will be the base of traigle for the recursive calls.
*/

```
class Solution {

    public int minScoreTriangulation(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        return maxCoins(nums, 0, n - 1, dp);
    }
    
    public int maxCoins(int[] nums, int si, int ei, int[][] dp) {
        if(dp[si][ei] != 0) return dp[si][ei];

        int minScore = Integer.MAX_VALUE;
        for(int cut = si + 1; cut < ei; cut++) {
            int lcost = cut == si + 1 ? 0 : maxCoins(nums, si, cut, dp); // Agar si + 1 pe cut lgaogi toh leftCost 0 aaega
            int rcost = cut == ei - 1 ? 0 : maxCoins(nums, cut, ei, dp); // Agar ei - 1 pe cut lgaogi toh rightCost 0 aaega
            
            int currCost =  lcost + (nums[si] * nums[cut] * nums[ei]) + rcost; // lcost + Tmhare traigulation ka cost + rcost 

            minScore = Math.min(minScore, currCost); // Overall min of all 
        }
        return dp[si][ei] = minScore;
    }
    
}
```
---------------------------------------------------------------------------------------------------------------------------------
