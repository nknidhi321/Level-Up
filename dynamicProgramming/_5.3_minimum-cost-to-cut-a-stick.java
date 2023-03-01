// https://leetcode.com/problems/minimum-cost-to-cut-a-stick/

class Solution {
    
    // Note : n is rod length
    
    public int minCost(int n, int[] cuts) {
        // Sorting taaki cuts wale array ko he as a rod use kr paaye to cal. length
        Arrays.sort(cuts); 
        return getMinCost(n, 0, cuts.length - 1, cuts, new Integer[cuts.length][cuts.length]);
    }
    
    public int getMinCost(int n, int si, int ei, int[] cuts, Integer[][] dp) {
        if(dp[si][ei] != null) return dp[si][ei];
        
        // Jitne subarray or rod pe cuts lagane aaye hai, how to cal. utne rod ka length
        int leftEnd = si == 0 ? 0 : cuts[si - 1]; // To cal. rod's leftEnd just see cuts[si - 1]
        int rightEnd = ei == cuts.length - 1 ? n : cuts[ei + 1]; // To cal. rod's rightEnd just see cuts[ei + 1]
        
        int min = Integer.MAX_VALUE;
        for(int cut = si; cut <= ei; cut++) { // for loop k kaaran cuts k saare permutations ka minCost aa raha
            int leftCost = cut == si ? 0 : getMinCost(n, si, cut - 1, cuts, dp);
            int rightCost = cut == ei ? 0 : getMinCost(n, cut + 1, ei, cuts, dp);
            int myCost = leftCost + rightCost + rightEnd - leftEnd;
            min = Math.min(min, myCost);
        }
        return dp[si][ei] = min;
    }
    
}
