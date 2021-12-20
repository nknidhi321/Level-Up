// https://leetcode.com/problems/longest-palindromic-subsequence/

// Memoization

class Solution {
    
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        Integer[][] dp = new Integer[n][n];
        return LPS_Memo(0, s, n - 1, dp);   
    }
    
    public static int LPS_Memo(int i, String s, int j, Integer[][] dp) {
        if(i >= j) return dp[i][j] = (i == j ? 1 : 0);
        
        if(dp[i][j] != null) return dp[i][j];
        
        if(s.charAt(i) == s.charAt(j)) { // When first and last character of string is equal
            return dp[i][j] = LPS_Memo(i + 1, s, j - 1, dp) + 2;
        }
        else { // When last character of string is unequal
            int max1 = LPS_Memo(i + 1, s, j, dp);  // Ignoring 1st character from string
            int max2 = LPS_Memo(i, s, j - 1, dp);  // Ignoring last character from string
            return dp[i][j] = Math.max(max1, max2);
        }
    }
}

-------------------------------------------------------------------------------------------------------------------------

// Tabulation 

NOTE : Don't use Integer[][] dp, because it is making call at anti Diagonal (i + 1, j - 1) when gap = 1,
       which stores null, so that will lead to null pointer exception.
       But if you use int[][] dp, by default it will have 0, so accessing that element is not an error and 0 + ans => ans  


class Solution {
        
    public static int n;
    
    public int longestPalindromeSubseq(String s) {
        n = s.length();
        int[][] dp = new int[n][n];
        return LPS_Tab(0, s, n - 1, dp);
    }
    
    
    public static int LPS_Tab(int I, String s, int J, int[][] dp) {
       
        for(int gap = 0; gap < n; gap++) {
            for(int i = 0, j = gap; j < n; i++, j++) {
                if(i >= j) {
                    dp[i][j] = (i == j ? 1 : 0);
                    continue;
                }

                if(s.charAt(i) == s.charAt(j)) { // When first and last character of string is equal
                    dp[i][j] = dp[i + 1][j - 1] + 2;     // LPS_Memo(i + 1, s, j - 1, dp) + 2;                // CALL AT ANTI DIAGONAL
                }
                else { // When last character of string is unequal

                    // Ignoring 1st character from string
                    int max1 = dp[i + 1][j];    // LPS_Memo(i + 1, s, j, dp);  

                    // Ignoring last character from string
                    int max2 = dp[i][j - 1];    // LPS_Memo(i, s, j - 1, dp); 

                    dp[i][j] = Math.max(max1, max2);
                }
            }
        }
        return dp[I][J];
    }
    
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Using dp of String:-
// Bad Approach since Using dp of string so lot  of complexity and storage issue
// String dp[][] = new String[][] // Stores null so initalize before use

// Memoization

class Solution {
    public int longestPalindromeSubseq(String s) {
        String[][] dp = new String[s.length()][s.length()];
        
        for(String[] arr : dp){
            Arrays.fill(arr, "-1");
        }    
        return Util(0, s.length() - 1, s, dp).length();
    }
    
    public static String Util(int i, int j, String s, String[][] dp) {
        if(i == j) return dp[i][j] = s.charAt(i) + "";
        
        if(i > j) return dp[i][j] = "";
        
        if(dp[i][j] != "-1") return dp[i][j];
        
        if(s.charAt(i) == s.charAt(j)) {
            return dp[i][j] = s.charAt(i) + Util(i + 1, j - 1, s, dp) + s.charAt(j);
        }
        else {
            String max1 = Util(i + 1, j, s, dp);
            String max2 = Util(i, j - 1, s, dp);
            if(max1.length() > max2.length())
            	return dp[i][j] = max1;
            else
            	return dp[i][j] = max2;
        }
    }
}

---------------------------------------------------------------------------------

// Tabulation

class Solution {
    public int longestPalindromeSubseq(String s) {
        String[][] dp = new String[s.length()][s.length()];
        for(String[] st : dp){
            Arrays.fill(st, "");
        }
        
        for(int gap = 0; gap < s.length(); gap++){
            for(int i = 0, j = gap; j < s.length(); i++, j++){
                if(i == j) {
                    dp[i][j] = s.substring(i, j + 1);
                    continue;
                }

                if(s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = s.charAt(i) + dp[i + 1][j - 1] + s.charAt(j);
                }
                else {
                    int max1 = dp[i][j - 1].length();
                    int max2 = dp[i + 1][j].length();  
                    if(max1 >= max2){
                        dp[i][j] = dp[i][j - 1];
                    }
                    else{
                        dp[i][j] = dp[i + 1][j];
                    }
                }
            }
        }
        return dp[0][dp[0].length - 1].length();
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
