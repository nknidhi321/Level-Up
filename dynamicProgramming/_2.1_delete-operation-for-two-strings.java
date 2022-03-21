// https://leetcode.com/problems/delete-operation-for-two-strings/

/*
    Take out longest common subsequence(LCSS) of both the string
    Now, Add both the strings, observe that you have the LCSS string twice in that added final string
    So just, get count of final added string and 2 * LCSS k count ko subtract kr do
    Why into 2 ? Because common toh dono s1 and s2 me milegi na 
    So jo remaining string bachegi, utne number of steps hame lenge honge to make s1 == s2
    
    Ex: word1 = "leetcode", word2 = "etco"
    
    LCSS = etco
    word1 + word2 = leetcodeetco   <= etco is twice here
    So just subtract 2 * LCSS count from word1 + word2 count
    
*/

// Memoization

class Solution {
    
    public static int len1, len2;
    
    public int minDistance(String s1, String s2) {
        len1 = s1.length();
        len2 = s2.length();
        int totalLength = len1 + len2;
        Integer[][] dp = new Integer[len1 + 1][len2 + 1];
        return totalLength - (2 * (LCS_Memo(len1, len2, s1, s2, dp)));                      // The only extra line from LCSS
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

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX// Tabulation

// Tabulation

class Solution {
    
    public static int len1, len2;
    
    public int minDistance(String s1, String s2) {
        len1 = s1.length();
        len2 = s2.length();
        int totalLength = len1 + len2;
        Integer[][] dp = new Integer[len1 + 1][len2 + 1];
        return totalLength - (2 * (LCS_Tab(len1, len2, s1, s2, dp)));                     // The only extra line from LCSS
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
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
