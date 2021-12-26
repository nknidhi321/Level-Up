// https://leetcode.com/problems/longest-palindromic-subsequence/
// Make sure to check the follow up at down, used backEngineering

/*
    Approach 1 :- [ Efficient ] 
    --------
    Keep a pointer at start and end of the given string and ask if :-
    1) Si == Sj, then what ?
    2) Si  != Sj, then what ?

    NOTE : We wil be using only top antiDiagonal half of the DP, also when i < j, when gap = -1, that diagonal elements will also be used
*/

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

---------------------------------------------------------------------------------------------------------------------------------------------------

// Tabulation 

/*
    NOTE : Don't use Integer[][] dp, because it is making call at left-down Diagonal (i + 1, j - 1), here i > j when gap = 1,
           which stores null, so that will lead to null pointer exception.
           when i > j, Memoization can handle this, but in Tabulation we are only using the anti Diagonal top values, so make sure to use int[][] dp
           If you use int[][] dp, by default it will have 0, so accessing that element is not an error and 0 + ans => ans         
*/

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
                if(i >= j) {                            // In Tabulation i will never be greater than j, since we are initializing i = 0 and j = gap, where gap >= 0
                    dp[i][j] = (i == j ? 1 : 0);        // That is why the lower half diagonal (i + 1, j - 1) will never be initialized 
                    continue;                           // and when you hit for gap = 1, when Si == Sj => Null Pointer Exception when Integer[][] dp
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

/*
    Approach 2 :-
    --------
    Longest Palindromic Sequence can be solved using Longest Common Subsequence,
    In LPS make the second string as the reverse of the 1st given string, rest all same as LCS.

    Longest common subsequence will be the longest palindromic subsequence.
*/

// Memoization

class Solution {
        
    public static int len1, len2;
    
    public int longestPalindromeSubseq(String s1) {
        
        StringBuilder sb = new StringBuilder(s1);
        String s2 = sb.reverse().toString();
        
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

----------------------------------------------------------------------------------------------------------

// Tabulation

class Solution {
        
    public static int len1, len2;
    
    public int longestPalindromeSubseq(String s1) {
        
        StringBuilder sb = new StringBuilder(s1);
        String s2 = sb.reverse().toString();
        
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

 Follow up : https://www.geeksforgeeks.org/print-longest-palindromic-subsequence/

// BackEngineering
// When longest Palindromic Subsequece String is asked use backEngineering

/*
    How to use backEngineering ?
        -> create maxLength Palindrome dp in one dfs,
        -> Now, make another dfs call same code with optimization, move only to those cells which is having max len (check from pdp and move)
        -> Keep forming ans in postArea
*/

    ..already created pdp
    
    // Here only forming longest Palindromic Subsequece String
    public static String lpss_backEng(String str, int si, int ei, int[][] pdp) {
        if (si >= ei) {
            return si == ei ? str.charAt(si) + "" : "";
        }

        if (str.charAt(si) == str.charAt(ei)) {
            return str.charAt(si) + lpss_backEng(str, si + 1, ei - 1, dp) + str.charAt(ei);
        } else if (pdp[si + 1][ei] > pdp[si][ei - 1]) {
            return lpss_backEng(str, si + 1, ei, dp);
        } else {
            return lpss_backEng(str, si, ei - 1, dp);
        }
    }

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Just an approach, but don't use

/*
     Using Dp of Pair class to store both max len and str of pali string, when maybe both is asked in question.
     If both is asked use backEngineering instead of the below faltu approach.
     NOTE : Space complexity is too poor, because you are storing string in dp of Pair class, and string might be toooo long.
*/

class Solution {

    public static class Pair {
        int len = 0;
        String str = "";
        
        public Pair(int len, String str) {
            this.len = len;
            this.str = str;
        }
    }
    
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        Pair[][] dp = new Pair[n][n];
        Pair ans = LPS_Memo(0, s, n - 1, dp);
        System.out.println(ans.str);
        return ans.len;
    }
    
    public static Pair LPS_Memo(int i, String s, int j, Pair[][] dp) {
        if(i >= j) {
            if(i == j) dp[i][j] = new Pair(1, s.charAt(i) + "");
            else dp[i][j] = new Pair(0, "");
            return dp[i][j];
        }
        
        if(dp[i][j] != null) return dp[i][j];
        
        if(s.charAt(i) == s.charAt(j)) { // When first and last character of string is equal
            Pair pair = LPS_Memo(i + 1, s, j - 1, dp);
            return dp[i][j] = new Pair(pair.len + 2, s.charAt(i) + pair.str + s.charAt(j));
        }
        else { // When last character of string is unequal
            Pair max1 = LPS_Memo(i + 1, s, j, dp);  // Ignoring 1st character from string
            Pair max2 = LPS_Memo(i, s, j - 1, dp);  // Ignoring last character from string
            if(max1.len > max2.len) return dp[i][j] = max1;
            else return dp[i][j] = max2;
        }
    }
}

----------------------------------------------------------------------------------------------

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
