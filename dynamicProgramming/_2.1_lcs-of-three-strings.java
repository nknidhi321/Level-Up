// https://practice.geeksforgeeks.org/problems/lcs-of-three-strings0028/1/#

class Solution {
    
    public int LCSof3(String s1, String s2, String s3, int len1, int len2, int len3) { 
        Integer[][][] dp = new Integer[len1 + 1][len2 + 1][len3 + 1];
        return LCS_Memo(len1, len2, len3, s1, s2, s3, dp);
    }
    
    public static int LCS_Memo(int len1, int len2, int len3, String s1, String s2, String s3, Integer[][][] dp) {
        if(len1 == 0 || len2 == 0 || len3 == 0) return dp[len1][len2][len3] = 0;
        
        if(dp[len1][len2][len3] != null) return dp[len1][len2][len3];
        
        if(s1.charAt(len1 - 1) == s2.charAt(len2 - 1) && s1.charAt(len1 - 1) == s3.charAt(len3 - 1)) { // When last character of both string is equal
            return dp[len1][len2][len3] = LCS_Memo(len1 - 1, len2 - 1, len3 - 1, s1, s2, s3, dp) + 1;
        }
        else { // When last character of both string is unequal
            int max1 = LCS_Memo(len1 - 1, len2, len3, s1, s2, s3, dp);  // Ignoring last character from 1st string
            int max2 = LCS_Memo(len1, len2 - 1, len3, s1, s2, s3, dp);  // Ignoring last character from 2nd string
            int max3 = LCS_Memo(len1, len2, len3 - 1, s1, s2, s3, dp);
            return dp[len1][len2][len3] = Math.max(Math.max(max1, max2), max3);
        }
    }
        
} 
