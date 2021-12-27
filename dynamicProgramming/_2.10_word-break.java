// https://leetcode.com/problems/word-break/

// Rajneesh, 
// Efficient // 1D DP
// He solved Direct tabulation
// Not making calls from false idx

class Solution {
    
    public static int n;
    
    public boolean wordBreak(String s, List<String> wordDict) {
        int maxLength = 0;
        Set<String> dict = new HashSet<>();
        for(String word : wordDict) {
            maxLength = Math.max(maxLength, word.length());
            dict.add(word);
        }
        n = s.length();
        boolean[] dp = new boolean[n + 1];
        return wordBreak_Tab(maxLength, s, dict, dp);
    }
    
    public static boolean wordBreak_Tab(int maxLength, String s, Set<String> dict, boolean[] dp) {
        dp[0] = true;
        
        for(int si = 0; si <= n; si++) {
            if(!dp[si]) continue; // Agar waha false that means uske next char se start hone wala koi word nai h
            
            for(int l = 1; l <= maxLength && si + l <= n ; l++) {
                if(dict.contains(s.substring(si, si + l))) {
                    dp[si + l] = true; // Jis letter pe word khatam hota h waha true mark karo
                }
            }
        }
        return dp[n]; // Last char pe true marked
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Sumeet malik
// Recursion and Backtracking // TLE
// Not efficient // Not using DP here

/*
    Intuition :-
    ---------
    If you exist in dict, cut yourself and pass ros, 
    make sure to check every possibility by using for loop
*/

class Solution {
    
    public static int n;
    
    public boolean wordBreak(String s, List<String> wordDict) {
        int maxLength = 0;
        Set<String> dict = new HashSet<>();
        for(String word : wordDict) {
            maxLength = Math.max(maxLength, word.length());
            dict.add(word);
        }
        n = s.length();
        return wordBreak(0, n - 1, maxLength, s, dict);
    }
    
    public static boolean wordBreak(int si, int ei, int maxLength, String s, Set<String> dict) {
        if(si == n) return true;
        
        for(int brk = si; brk < si + maxLength && brk <= ei; brk++) {
            if(dict.contains(s.substring(si, brk + 1))) {
                if(wordBreak(brk + 1, ei, maxLength, s, dict)) {
                    return true;
                }
            }
        }
        return false;
    }
    
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Mine // 1D DP
// Same above Sumeet Malik code + using dp
// Memoization

// Here, making calls from faltu false indexes also, since we are passing brk + 1, that means prev rec call didn't make all it's for loop calls,
// and moved to curr call(like normal recursion) if you wanna stop making calls from false idx then make sure you have alredy processed the entire for loop of the prev call
// until maxLength, and this can be achieved only if you do it iteratively(Tabulation), we have this implemetaion in Rajneesh code above.
// Making True at start idx, Rajneesh code is making true at end idx

class Solution {
    
    public static int n;
    
    public boolean wordBreak(String s, List<String> wordDict) {
        int maxLength = 0;
        Set<String> dict = new HashSet<>();
        for(String word : wordDict) {
            maxLength = Math.max(maxLength, word.length());
            dict.add(word);
        }
        n = s.length();
        Integer[] dp = new Integer[n + 1];
        return wordBreak(0, n - 1, maxLength, s, dict, dp) == 1 ? true: false;
    }
    
    public static int wordBreak(int si, int ei, int maxLength, String s, Set<String> dict, Integer[] dp) {
        if(si == n) {
            return dp[si] = 1; // True
        }
        
        if(dp[si] != null) return dp[si];
        
        for(int brk = si; brk < si + maxLength && brk <= ei; brk++) {
            if(dict.contains(s.substring(si, brk + 1))) {
                if(wordBreak(brk + 1, ei, maxLength, s, dict, dp) == 1){
                    return dp[si] = 1; // True
                }
            }
        }
        return dp[si] = 0; // False
    }
    
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
