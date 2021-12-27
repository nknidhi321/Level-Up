// https://leetcode.com/problems/longest-common-subsequence/

// Dry run from start of the string and end code from end of the string.
// So that base case can be formed at (0, 0)


Approach1 :- Iterating from end 

// Memoization

class Solution {
    
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
        
        if(s1.charAt(len1 - 1) == s2.charAt(len2 - 1)) { // When last character of both string is equal
            return dp[len1][len2] = LCS_Memo(len1 - 1, len2 - 1, s1, s2, dp) + 1;
        }
        else { // When last character of both string is unequal
            int max1 = LCS_Memo(len1 - 1, len2, s1, s2, dp);  // Ignoring last character from 1st string
            int max2 = LCS_Memo(len1, len2 - 1, s1, s2, dp);  // Ignoring last character from 2nd string
            return dp[len1][len2] = Math.max(max1, max2);
        }
    }
    
}

-------------------------------------------------------------------------------------------------------------

// Tabulation

class Solution {
    
    public static int len1, len2;
    
    public int longestCommonSubsequence(String s1, String s2) {
        len1 = s1.length();
        len2 = s2.length();
        Integer[][] dp = new Integer[len1 + 1][len2 + 1];
        return LCS_Tab(len1, len2, s1, s2, dp);
    }
    
    public static int LCS_Tab(int LEN1, int LEN2, String s1, String s2, Integer[][] dp) {
        for(int len1 = 0; len1 <= LEN1; len1++) {
            for(int len2 = 0; len2 <= LEN2; len2++) {
                if(len1 == 0 || len2 == 0) {
                    dp[len1][len2] = 0;
                    continue;
                }

                // When last character of both string is equal
                if(s1.charAt(len1 - 1) == s2.charAt(len2 - 1)) {  
                    dp[len1][len2] = dp[len1 - 1][len2 - 1] + 1;    //LCS_Memo(len1 - 1, len2 - 1, s1, s2, dp) + 1;
                }
                else { // When last character of both string is unequal
                   
                    // Ignoring last character from 1st string
                    int max1 = dp[len1 - 1][len2];   // LCS_Memo(len1 - 1, len2, s1, s2, dp);
                    
                    // Ignoring last character from 2nd string
                    int max2 = dp[len1][len2 - 1];   // LCS_Memo(len1, len2 - 1, s1, s2, dp);  
                    
                    dp[len1][len2] = Math.max(max1, max2);
                }
            }
        }
        return dp[LEN1][LEN2];
    }
    
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

Approach2 :- Itearting from start of the string

// Memoization

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        
        for(int i = 0; i < dp.length; i++)
            Arrays.fill(dp[i], -1);
        
        return Util(text1, text2, 0, 0, dp);
    }
    public static int Util(String text1, String text2, int i, int j, int[][] dp){
        if(i == text1.length() || j == text2.length())
            return dp[i][j] = 0;
        
        if(dp[i][j] != -1)
            return dp[i][j];
        
        if(text1.charAt(i) == text2.charAt(j)){
            return dp[i][j] = Util(text1, text2, i + 1, j + 1, dp) + 1;
        }
        else{
            dp[i + 1][j] = Util(text1, text2, i + 1, j, dp);
            dp[i][j + 1] = Util(text1, text2, i, j + 1, dp);
            return dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
        }
    }
}

-----------------------------------------------------------------------------------------

// Tabulation

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        
        for(int i = dp.length - 1; i >= 0; i--) {
            for(int j = dp[0].length - 1; j >= 0; j--) {
                if(i == dp.length - 1 || j == dp[0].length - 1)
                    dp[i][j] = 0;
                else if(text1.charAt(i) == text2.charAt(j))
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                else
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
            }
        }
        return dp[0][0];
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Follow up : // https://leetcode.com/problems/delete-operation-for-two-strings/
