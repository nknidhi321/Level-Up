// https://leetcode.com/problems/decode-ways/

// Recursion TLE

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

// Memoization

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

// Tabulation

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
                    if(twoLen <= 26) {
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

// Optimization

class Solution {
    
    public int numDecodings(String digits) {
        Integer[] dp = new Integer[digits.length() + 1];
        return numDecodings_opti(digits);
    }
    
    public static int numDecodings_opti(String s) {
        int a = 1, b = 0;  // Imagine when there only 1 digit, then a = 1 & b = 0
        for (int idx = s.length() - 1; idx >= 0; idx--) {
            char ch = s.charAt(idx);
            int sum = 0;
            if (ch != '0') {
                sum += a;

                if (idx < s.length() - 1) {
                    char ch1 = s.charAt(idx + 1);
                    int num = (ch - '0') * 10 + (ch1 - '0');
                    if (num <= 26)
                        sum += b;
                }
            }

            b = a;
            a = sum;
        }

        return a;  // Note : and will be in a, not b
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
