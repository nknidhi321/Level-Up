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
        
        // Jis din given string s (here s1) target t (here s2) se choti ho jaaegi,
        // us din hm kvi target ko achieve he nahi kar paaenge from given s string
        if(len1 < len2) return dp[len1][len2] = 0; 
        
        if(dp[len1][len2] != null) return dp[len1][len2];
        
        int count = 0;
        if(s1.charAt(len1 - 1) == s2.charAt(len2 - 1)) { // Dono string ki last letter same hai
           count += numDistinctMemo(len1 - 1, len2 - 1, s1, s2, dp); // Including both the letters
        }
        
        // Agar dono string ki last letter same hai v, toh v agar given string s(here s1) se last letter to skip kare and 
        // pass len1 - 1, tvi possibility hai ki may be aur koi substring mil jaaye jo match ho rahi ho us len2 se.
        count += numDistinctMemo(len1 - 1, len2, s1, s2, dp); // Not include // s(s1) k nahi aane ki choice har baar hogi
        return dp[len1][len2] = count;
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
                
                // Jis din given string s (here s1) target t (here s2) se choti ho jaaegi,
                // us din hm kvi target ko achieve he nahi kar paaenge from given s string
                if(len1 < len2) {
                    dp[len1][len2] = 0;
                    continue;
                }

                int count = 0;
                if(s1.charAt(len1 - 1) == s2.charAt(len2 - 1)) { // Dono string ki last letter same hai
                   count += dp[len1 - 1][len2 - 1];  // numDistinctMemo(len1 - 1, len2 - 1, s1, s2, dp); // Including both the letters
                }

                // Agar dono string ki last letter same hai v, toh v agar given string s(here s1) se last letter to skip kare and 
                // pass len1 - 1, tvi possibility hai ki may be aur koi substring mil jaaye jo match ho rahi ho us len2 se.
                count += dp[len1 - 1][len2];  // numDistinctMemo(len1 - 1, len2, s1, s2, dp); // Not include // s(s1) k nahi aane ki choice har baar hogi
                dp[len1][len2] = count;
            }
        }
        return dp[LEN1][LEN2];
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
