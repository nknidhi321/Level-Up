//https://leetcode.com/problems/decode-ways/

//Recursion, TLE

class Solution {
    public int numDecodings(String digits) {
        return numDecodings_Util(0, digits);
    }
    
    public static int numDecodings_Util(int idx, String digits) {
        if(idx == digits.length()) return 1;
        
        int count = 0;
        int oneLen = digits.charAt(idx) - '0';
        if(oneLen > 0) {
            count += numDecodings_Util(idx + 1, digits);
            if(idx + 2 <= digits.length()) {
                int twoLen = (digits.charAt(idx) - '0') * 10 + (digits.charAt(idx + 1) - '0');
                if(twoLen <= 26){
                    count += numDecodings_Util(idx + 2, digits);
                }
            }
        }
        return count;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

//Memoization

class Solution {
    public int numDecodings(String digits) {
        Integer[] dp = new Integer[digits.length() + 1];
        return numDecodings_Util(0, digits, dp);
    }
    
    public static int numDecodings_Util(int idx, String digits, Integer[] dp) {
        if(idx == digits.length()) return dp[idx] = 1;
        
        if(dp[idx] != null) return dp[idx];
        
        int count = 0;
        int oneLen = digits.charAt(idx) - '0';
        if(oneLen > 0) {
            count += numDecodings_Util(idx + 1, digits, dp);
            if(idx + 2 <= digits.length()) {
                int twoLen = (digits.charAt(idx) - '0') * 10 + (digits.charAt(idx + 1) - '0');
                if(twoLen <= 26){
                    count += numDecodings_Util(idx + 2, digits, dp);
                }
            }
        }
        return dp[idx] = count;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

//Tabulation

class Solution {
    public int numDecodings(String digits) {
        Integer[] dp = new Integer[digits.length() + 1];
        return numDecodings_Util(0, digits, dp);
    }
    
    public static int numDecodings_Util(int IDX, String digits, Integer[] dp) {
        for(int idx = digits.length(); idx >= 0; idx--) {
            if(idx == digits.length()) {
                dp[idx] = 1;
                continue;
            }
            
            int count = 0;
            int oneLen = digits.charAt(idx) - '0';
            if(oneLen > 0) {
                count += dp[idx + 1]; //numDecodings_Util(idx + 1, digits, dp);
                if(idx + 2 <= digits.length()) {
                    int twoLen = (digits.charAt(idx) - '0') * 10 + (digits.charAt(idx + 1) - '0');
                    if(twoLen <= 26){
                        count += dp[idx + 2]; //numDecodings_Util(idx + 2, digits, dp);
                    }
                }
            }
            dp[idx] = count;
        }
        return dp[IDX];
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
