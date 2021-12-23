// https://leetcode.com/problems/longest-palindromic-substring/

/*
    NOTE :
    ----
    For substring question is it always advisable to go for tabulation directly
    When you have such a problm where you are iterating from start and end in string,
    Your lower part of dp is never used of filled, so make sure to fill gap 0 and gap 1 accordingly in base case. 
    
    Make sure to checkout the follow up, go down !!
*/

// Tabulation

class Solution {
    
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        return longestPalindrome_Tab(n, s, dp);
    }
    
    public static String longestPalindrome_Tab(int n, String s, boolean[][] dp) {
        int si = 0, maxLen = 0, count = 0;
        
        for(int gap = 0; gap < n; gap++) {
            for(int i = 0, j = gap; i < n && j < n; i++, j++) {
                
                // String of length 1, gap = 0
                // String of length 1 is always palindrome
                if(gap == 0) dp[i][j] = true; 
                    
                // It is important to make gap 1 as base case and fill dp manually because, 
                // it makes call at (i+1, j-1) and your lower part of dp is not being used, garbage value
                
                // String of length 2, gap = 1
                // 2 length string can only be palindrome when both characters are equal
                else if(gap == 1 && s.charAt(i) == s.charAt(j)) dp[i][j] = true;
                
                // If rec(i+1, j-1) is equal && charAt i && j is equal 
                // I can be the largest palindrome so far
                else dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                    
                
                if(dp[i][j]) { // If from [i, j] index String is palindrome
                    count++; // When total number of palindromes is asked
                    if(j - i + 1 > maxLen) { // Updating maxLen of palindrome
                        si = i; // Keeping starting index of largest palindrome
                        maxLen = j - i + 1; // So that from si + length of max Palindrome, I can get my final answer 
                    }
                }
            }
        }
        
        String maxLenPaliString = s.substring(si, si + maxLen);
        return maxLenPaliString;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Follow up : https://leetcode.com/problems/palindromic-substrings/

/*
    Ditto same except here you have to return count, and in above question you have to return maxLenPaliString
    Same code just returning count
*/

// Tabulation

class Solution {
    
    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        return countSubstrings_Tab(n, s, dp);
    }
    
    public static int countSubstrings_Tab(int n, String s, boolean[][] dp) {
        int si = 0, maxLen = 0, count = 0;
        
        for(int gap = 0; gap < n; gap++) {
            for(int i = 0, j = gap; i < n && j < n; i++, j++) {
                
                // String of length 1, gap = 0
                // String of length 1 is always palindrome
                if(gap == 0) dp[i][j] = true; 
                    
                // It is important to make gap 1 as base case and fill dp manually because, 
                // it makes call at (i+1, j-1) and your lower part of dp is not being used, garbage value
                
                // String of length 2, gap = 1
                // 2 length string can only be palindrome when both characters are equal
                else if(gap == 1 && s.charAt(i) == s.charAt(j)) dp[i][j] = true;
                
                // If rec(i+1, j-1) is equal && charAt i && j is equal 
                // I can be the largest palindrome so far
                else dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                    
                
                if(dp[i][j]) { // If from [i, j] index String is palindrome
                    count++; // When total number of palindromes is asked
                    if(j - i + 1 > maxLen) { // Updating maxLen of palindrome
                        si = i; // Keeping starting index of largest palindrome
                        maxLen = j - i + 1; // So that from si + length of max Palindrome, I can get my final answer 
                    }
                }
            }
        }
        
        String maxLenPaliString = s.substring(si, si + maxLen);
        return count;
    }
}
XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
