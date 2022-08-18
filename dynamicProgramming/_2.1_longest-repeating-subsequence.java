// https://practice.geeksforgeeks.org/problems/longest-repeating-subsequence2004/1

class Solution {
    
    public int LongestRepeatingSubsequence(String str) {
        return longestCommonSubsequence(str, str);
    }
    
    public static int len1, len2;
    
    public int longestCommonSubsequence(String s1, String s2) {
        len1 = s1.length();
        len2 = s2.length();
        Integer[][] dp = new Integer[len1 + 1][len2 + 1];
        return LCS_Memo(len1, len2, s1, s2, dp);
    }
    
    public static int LCS_Memo(int len1, int len2, String s1, String s2, Integer[][] dp) {
        if(len1 == 0 || len2 == 0) return dp[len1][len2] = 0;
        
        if(dp[len1][len2] != null) return dp[len1][len2];
        // namaste 🙏  java script
        if(s1.charAt(len1 - 1) == s2.charAt(len2 - 1) && (len1)  != (len2)) { // When last character of both string is equal
            return dp[len1][len2] = LCS_Memo(len1 - 1, len2 - 1, s1, s2, dp) + 1;
        }
        else { // When last character of both string is unequal
            int max1 = LCS_Memo(len1 - 1, len2, s1, s2, dp);  // Ignoring last character from 1st string
            int max2 = LCS_Memo(len1, len2 - 1, s1, s2, dp);  // Ignoring last character from 2nd string
            return dp[len1][len2] = Math.max(max1, max2);
        }
    }
    
}
