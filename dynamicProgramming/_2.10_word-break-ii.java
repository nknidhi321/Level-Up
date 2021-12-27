// https://leetcode.com/problems/word-break-ii/

// Rajneesh used 1D Dp
// Not implemented here.

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Sumeet malik
// Simple Recursion and Backtracking
// No DP

class Solution {
    
    public static int n;
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        int maxLength = 0;
        Set<String> dict = new HashSet<>();
        for(String word : wordDict) {
            maxLength = Math.max(maxLength, word.length());
            dict.add(word);
        }
        n = s.length();
        List<String> ans = new ArrayList<>();
        wordBreak(0, n - 1, maxLength, s, "", dict, ans);
        return ans;
    }
    
    public static void wordBreak(int si, int ei, int maxLength, String s, String ssf, Set<String> dict, List<String> ans) {
        if(si == n) {
            ans.add(ssf.trim());
            return;
        }
        
        for(int brk = si; brk < si + maxLength && brk <= ei; brk++) {
            String substr = s.substring(si, brk + 1);
            if(dict.contains(substr)) { 
                // Make sure you don't "assign" those changes in ssf, pass your changes in parameter 
                wordBreak(brk + 1, ei, maxLength, s, ssf + substr + " ", dict, ans);
                // No need to backtrack since using string,
            }
        }
    }
    
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Mine 
// Tech Dose // 2D DP of word 
// backEngineering // Rajneesh

class Solution {
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        int maxLength = 0;
        Set<String> dict = new HashSet<>();
        for(String word : wordDict) {
            maxLength = Math.max(maxLength, word.length());
            dict.add(word);
        }
        int n = s.length();
        boolean[][] dp = new boolean[n + 1][n + 1];
        prepareWordDp_Tab(n, s, dict, dp);

        List<String> ans = new ArrayList<>();
        bacEngineering_Memo(0, n - 1, n, maxLength, s, "", dict, dp, ans);
        return ans;
    }
    
    
    public static void prepareWordDp_Tab(int N, String s, Set<String> dict, boolean[][] dp) {
        
        for(int n = 0; n <= N; n++) {
            for(int m = 0; m <= N; m++) {
                if(n > m) continue;
                if(n == 0 || m == 0) {
                    dp[n][m] = (n == 0 && m == 0 ? true : false);
                    continue;
                }
                
                String substr = s.substring(n - 1, m);
                if(dict.contains(substr)) { 
                    dp[n][m] = true;
                }
            }
        }
    }
    
    
    public static void bacEngineering_Memo(int si, int ei, int n, int maxLength, 
                                      String s, String ssf, Set<String> dict, boolean[][] dp, List<String> ans) {
        if(si == n) {
            ans.add(ssf.trim());
            return;
        }
        
        for(int brk = si; brk < si + maxLength && brk <= ei; brk++) {
            if(dp[si + 1][brk + 1]) { 
                // Make sure you don't "assign" those changes in ssf, pass your changes in parameter 
                String substr = s.substring(si, brk + 1);
                bacEngineering_Memo(brk + 1, ei, n, maxLength, s, ssf + substr + " ", dict, dp, ans);
                // No need to backtrack since using string,
            }
        }
    }
    
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
