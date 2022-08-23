// https://leetcode.com/problems/count-sorted-vowel-strings/

class Solution {
    
    public int countVowelStrings(int n) {
        int[] dp = new int[5]; // a, e, i, o, u
        Arrays.fill(dp, 1); // For length 1, ans will be suffixSum at dp[0]
        
        // 0  1  2  3  4
        // a, e, i, o, u
        for(int i = 1; i <= n; i++) {
            // System.out.print(dp[4] + " ");
            for(int j = 3; j >= 0; j--) { // Calculating suffixSum from end
                dp[j] += dp[j + 1];
                // System.out.print(dp[j] + " ");
            }
            // System.out.println();
        }
        return dp[0];
    }
    
}
