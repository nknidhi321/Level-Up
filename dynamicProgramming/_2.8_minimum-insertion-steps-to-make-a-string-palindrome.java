// https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/

class Solution {
    
    public int minInsertions(String s1) {
        int n = s1.length();
        
        StringBuilder sb = new StringBuilder(s1);
        String s2 = reverseString(sb);
        int m = s2.length();
        
        Integer[][] dp = new Integer[n + 1][m + 1];
        return n - LCS(n, s1, m, s2, dp);
    }

    public int LCS(int n, String s1, int m, String s2, Integer[][] dp)  {
        if(n == 0 || m == 0) return dp[n][m] = 0;
        
        if(dp[n][m] != null) return dp[n][m];
        
        int max = -(int)1e9;
        if(s1.charAt(n - 1) == s2.charAt(m - 1)) {
            max = Math.max(max, LCS(n - 1, s1, m - 1, s2, dp) + 1);
        }
        else {
            max = Math.max(max, LCS(n - 1, s1, m, s2, dp));
            max = Math.max(max, LCS(n, s1, m - 1, s2, dp));
        }
        return dp[n][m] = max;
    }
    
    public String reverseString(StringBuilder sb) {
        int n = sb.length();
        for(int i = 0; i < n/2; i++) {
            char temp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(n -  1 - i));
            sb.setCharAt(n - 1 - i, temp);
        }
        return sb.toString();
    }
        
}
