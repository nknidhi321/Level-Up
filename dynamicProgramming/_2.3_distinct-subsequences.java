// https://leetcode.com/problems/distinct-subsequences/

// Memoization

class Solution {
    public int numDistinct(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        Integer[][] dp = new Integer[len1 + 1][len2 + 1];
        
        return numDistinctMemo(len1, len2, s1, s2, dp);
    }
    
    public static int numDistinctMemo(int len1, int len2, String s1, String s2, Integer[][] dp) {
        if(len2 == 0) return dp[len1][len2] = 1; // Jis din target string s2 exhaust ho gayi, hame ek tareeka mil gaya
        if(len1 < len2) return dp[len1][len2] = 0; // When target/pattern string becomes smaller than s1
        
        if(dp[len1][len2] != null) return dp[len1][len2];
            
        if(s1.charAt(len1 - 1) == s2.charAt(len2 - 1)) { // Dono string ki last letter same hai
            int count = 0;
            
            // Including both the letters
            count += numDistinctMemo(len1 - 1, len2 - 1, s1, s2, dp);
            
            // Agar dono string ki last letter same hai v, toh v agar given string s(here s1) se last letter to skip kare and 
            // pass len1 - 1, tvi possibility hai ki may be aur koi substring mil jaaye jo match ho rahi ho us len2 se
            count += numDistinctMemo(len1 - 1, len2, s1, s2, dp);
            
            return dp[len1][len2] = count;
        }
        else { // Both chars are different // Usi char ko aage dhoondho
            return dp[len1][len2] = numDistinctMemo(len1 - 1, len2, s1, s2, dp); 
        }
    }
    
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Tabulation

class Solution {
    public int numDistinct(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        Integer[][] dp = new Integer[len1 + 1][len2 + 1];
        
        return numDistinctTab(len1, len2, s1, s2, dp);
    }
    
    public static int numDistinctTab(int LEN1, int LEN2, String s1, String s2, Integer[][] dp) {
        
        for(int len1 = 0; len1 <= LEN1; len1++) {
            for(int len2 = 0; len2 <= LEN2; len2++) {
                if(len2 == 0) {
                    dp[len1][len2] = 1; // Jis din target string s2 exhaust ho gayi, hame ek tareeka mil gaya
                    continue;
                }
                
                // When target/pattern string becomes smaller than s1
                if(len1 < len2) {
                    dp[len1][len2] = 0;
                    continue;
                }

                if(s1.charAt(len1 - 1) == s2.charAt(len2 - 1)) { // Dono string ki last letter same hai
                    int count = 0;

                    // Including both the letters
                    count += dp[len1 - 1][len2 - 1]; // numDistinctMemo(len1 - 1, len2 - 1, s1, s2, dp);

                    // Agar dono string ki last letter same hai v, toh v agar given string s(here s1) se last letter to skip kare and 
                    // pass len1 - 1, tvi possibility hai ki may be aur koi substring mil jaaye jo match ho rahi ho us len2 se
                    count += dp[len1 - 1][len2]; // numDistinctMemo(len1 - 1, len2, s1, s2, dp);

                    dp[len1][len2] = count;
                }
                else { // Both chars are different // Usi char ko aage dhoondho
                    dp[len1][len2] =  dp[len1 - 1][len2]; // numDistinctMemo(len1 - 1, len2, s1, s2, dp); 
                }
            }
        }
        
        return dp[LEN1][LEN2];
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
