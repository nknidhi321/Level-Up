// https://leetcode.com/problems/word-break-ii/

// Rajneesh used 1D Dp
// Not implemented here.

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Sumeet malik
// Simple Recursion and Backtracking
// No DP

```
class Solution {
    
    int n;
    
    public List<String> wordBreak(String s, List<String> arr) {
        n = s.length();
        int maxWordLen = 0;
        List<String> ans = new ArrayList<>();
        Set<String> set = new HashSet<>();
        
        for(int i = 0; i < arr.size(); i++) {
            set.add(arr.get(i));
            maxWordLen = Math.max(maxWordLen, arr.get(i).length());
        }
        
        wordBreak(0, n - 1, maxWordLen, s, set, "", ans);
        return ans;
    }
        
    public void wordBreak(int si, int ei, int maxWordLen, String s, Set<String> set, String ssf, List<String> ans) {
        if(si == n) {
            ans.add(ssf.trim());
            return;
        }
        
        for(int cut = si; cut <= Math.min(ei, si + maxWordLen - 1); cut++) {
            String sub = s.substring(si, cut + 1);
            if(set.contains(sub)) {
                wordBreak(cut + 1, ei, maxWordLen, s, set, ssf + sub + " ", ans);
            }
        }
    }
    
}
```

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
