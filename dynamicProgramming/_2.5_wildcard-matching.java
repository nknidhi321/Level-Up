// https://leetcode.com/problems/wildcard-matching/

/*
	Don't make boolean array because both T and F is part of our answer.
	so, we need to store both of them + mark of unvisited dp[len1][len2]
	Since, boolean can only store only T | F and we also need some mark for unvisited dp cells,
	So, go for Integer[][] dp,  where    1 -> T,    0 -> F,    null -> unvisited dp cell
*/

// Memoization

class Solution {
    
    public boolean isMatch(String s, String p) {
        
        // Replace 1 or more * with *
        // because jo 1 or more * kar sakta h wo sirf akela 1 * v kar sakta hai
        // Doing this just to save the faltu calls
        // If you don't do this, make sure to alter the 2nd base case accordingly (All remainining p characters should be *)
        p = p.replaceAll("\\*+", "*"); 
        
        int len1 = s.length();
        int len2 = p.length();
        
        Integer[][] dp = new Integer[len1 + 1][len2 + 1];
        
        return isMatch_Memo(len1, len2, s, p, dp) == 1 ? true : false;
    }
    
    
    public static int isMatch_Memo(int len1, int len2, String s, String p, Integer[][] dp) {
        
        if(len1 == 0 || len2 == 0) {
            
            // Dono string khatam ho gayi, pattern matched
            if(len1 == 0 && len2 == 0) return dp[len1][len2] = 1; // True 
            
            // Only len1 is exhausted, len1 = 0 and len2 = 1 and len2's only character is *
            // Then also there's possibilty to get the pattern matched, when * chooses to come 0 number of times
            else if(len1 == 0 && (len2 == 1 && p.charAt(len2 - 1) == '*')) return dp[len1][len2] = 1; // True; 
            
            // len1 > 0 and len2 = 0, len2 is exhausted, Pattern he kahatam ho gayi, pattern cannot be matched
            else return dp[len1][len2] = 0; // False
        }
        
        
        if(dp[len1][len2] != null) return dp[len1][len2];
        
        
        char ch1 = s.charAt(len1 - 1);
        char ch2 = p.charAt(len2 - 1);
        
        if(ch1 == ch2 || ch2 == '?') {
            return dp[len1][len2] = isMatch_Memo(len1 - 1, len2 - 1, s, p, dp);
        }
        else if(ch2 == '*') {
            
            boolean res = false;
            
            // sequence of characters // * says Aaenge more than 1 times 
            // single character ko match krwa diya 
            // And same * sequence of characters ko v match krwa sakta hai islye same len2 pass hoga
            res = res || isMatch_Memo(len1 - 1, len2, s, p, dp) == 1;
            
            // empty character // * says Aaenge 0 times
            // wo * character match nahi karwa chahta, toh aage badh jaao, len2 - 1
            // Aur kisi character ko match nahi krwaya islye same len1 pass ho jaaega
            res = res || isMatch_Memo(len1, len2 - 1, s, p, dp) == 1;
            
            return dp[len1][len2] = (res == true ? 1 : 0);
        }
        else { // ch1 != ch2   // character mismatched
            return dp[len1][len2] = 0; // Pattern can never be matched, return 0
        }
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Tabulation


class Solution {
    
    public boolean isMatch(String s, String p) {
        
        // Replace 1 or more * with *
        // because jo 1 or more * kar sakta h wo sirf akela 1 * v kar sakta hai
        // Doing this just to save the faltu calls
        // If you don't do this, make sure to alter the 2nd base case accordingly (All remainining p characters should be *)
        p = p.replaceAll("\\*+", "*"); 
        
        int len1 = s.length();
        int len2 = p.length();
        
        Integer[][] dp = new Integer[len1 + 1][len2 + 1];
        
        return isMatch_Tab(len1, len2, s, p, dp) == 1 ? true : false;
    }
    
    
    public static int isMatch_Tab(int LEN1, int LEN2, String s, String p, Integer[][] dp) {
        
        for(int len1 = 0; len1 <= LEN1; len1++) {
            for(int len2 = 0; len2 <= LEN2; len2++) {
                if(len1 == 0 || len2 == 0) {

                    // Dono string khatam ho gayi, pattern matched
                    if(len1 == 0 && len2 == 0) dp[len1][len2] = 1; // True 

                    // Only len1 is exhausted, len1 = 0 and len2 = 1 and len2's only character is *
                    // Then also there's possibilty to get the pattern matched, when * chooses to come 0 number of times
                    else if(len1 == 0 && (len2 == 1 && p.charAt(len2 - 1) == '*')) dp[len1][len2] = 1; // True; 

                    // len1 > 0 and len2 = 0, len2 is exhausted, Pattern he kahatam ho gayi, pattern cannot be matched
                    else dp[len1][len2] = 0; // False
                    
                    continue;
                }


                char ch1 = s.charAt(len1 - 1);
                char ch2 = p.charAt(len2 - 1);

                if(ch1 == ch2 || ch2 == '?') {
                    dp[len1][len2] = dp[len1 - 1][len2 - 1];    // isMatch_Memo(len1 - 1, len2 - 1, s, p, dp);
                }
                else if(ch2 == '*') {

                    boolean res = false;

                    // sequence of characters // * says Aaenge more than 1 times 
                    // single character ko match krwa diya 
                    // And same * sequence of characters ko v match krwa sakta hai islye same len2 pass hoga
                    res = res || dp[len1 - 1][len2] == 1;    // isMatch_Memo(len1 - 1, len2, s, p, dp);

                    // empty character // * says Aaenge 0 times
                    // wo * character match nahi karwa chahta, toh aage badh jaao, len2 - 1
                    // Aur kisi character ko match nahi krwaya islye same len1 pass ho jaaega
                    res = res || dp[len1][len2 - 1] == 1;    // isMatch_Memo(len1, len2 - 1, s, p, dp);

                    dp[len1][len2] = (res == true ? 1 : 0);
                }
                else { // ch1 != ch2   // character mismatched
                    dp[len1][len2] = 0; // Pattern can never be matched, return 0
                }
            }
        }
        return dp[LEN1][LEN2];
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
