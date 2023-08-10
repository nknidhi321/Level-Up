// https://leetcode.com/problems/word-break/

// Latest
class Solution {
  
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        for(int i = 0; i < wordDict.size(); i++) {
            set.add(wordDict.get(i));
        }

        int n = s.length();
        int[] dp = new int[n];
        int si = 0, ei = 0;
        while(ei < n) {
            si = 0;
            while(si <= ei) {
                if(set.contains(s.substring(si, ei + 1))) {
                    dp[ei] += (si - 1 >= 0 ? dp[si - 1] : 1);    
                }
                else dp[ei] += 0;
                si++;
            }
            ei++;
        }
        return dp[n - 1] > 0 ? true : false;
    }
    
}

----------------------------------XXX---------------------------------

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

```
class Solution {
    
    int n;
    
    public boolean wordBreak(String s, List<String> arr) {
        n = s.length();
        int maxWordLen = 0;
        Set<String> set = new HashSet<>();
        for(int i = 0; i < arr.size(); i++) {
            set.add(arr.get(i));
            maxWordLen = Math.max(maxWordLen, arr.get(i).length());
        }
        return wordBreak(0, n - 1, maxWordLen, s, set);
    }
        
    public boolean wordBreak(int si, int ei, int maxWordLen, String s, Set<String> set) {
        if(si == n) return true;

        boolean flag = false;
        for(int cut = si; cut <= Math.min(ei, si + maxWordLen - 1); cut++) {
            String sub = s.substring(si, cut + 1);
            if(set.contains(sub)) {
                if(wordBreak(cut + 1, ei, maxWordLen, s, set)) {
                    return true;
                }
            }
        }
        return false;
    }
    
}
```

---------------------------------------------------------------------------------------------------

// Mine Memo // 1D DP

```
class Solution {
    
    int n;
    
    public boolean wordBreak(String s, List<String> arr) {
        n = s.length();
        int maxWordLen = 0;
        Set<String> set = new HashSet<>();
        for(int i = 0; i < arr.size(); i++) {
            set.add(arr.get(i));
            maxWordLen = Math.max(maxWordLen, arr.get(i).length());
        }
        Boolean[] dp = new Boolean[n + 1];
        return wordBreak(0, n - 1, maxWordLen, s, set, dp);
    }
        
    public boolean wordBreak(int si, int ei, int maxWordLen, String s, Set<String> set, Boolean[] dp) {
        if(si == n) return dp[n] = true;
        
        if(dp[si] != null) return dp[si];
        
        for(int cut = si; cut <= Math.min(ei, si + maxWordLen - 1); cut++) {
            String sub = s.substring(si, cut + 1);
            if(set.contains(sub)) {
                if(wordBreak(cut + 1, ei, maxWordLen, s, set, dp)) {
                    return dp[si] = true;
                }
            }
        }
        return dp[si] = false;
    }
    
}
```

----------------------------------------------------------------------------------------------------------------

// Mine Tab // 1D DP

```
class Solution {
    
    int n;
    
    public boolean wordBreak(String s, List<String> arr) {
        n = s.length();
        int maxWordLen = 0;
        Set<String> set = new HashSet<>();
        for(int i = 0; i < arr.size(); i++) {
            set.add(arr.get(i));
            maxWordLen = Math.max(maxWordLen, arr.get(i).length());
        }
        Boolean[] dp = new Boolean[n + 1];
        return wordBreak(0, n - 1, maxWordLen, s, set, dp);
    }
        
    public boolean wordBreak(int SI, int ei, int maxWordLen, String s, Set<String> set, Boolean[] dp) {
        for(int si = n; si >= 0; si--) {
            if(si == n) {
                dp[n] = true;
                continue;
            }

            boolean flag = false;
            for(int cut = si; cut <= Math.min(ei, si + maxWordLen - 1); cut++) {
                String sub = s.substring(si, cut + 1);
                if(set.contains(sub)) {
                    if(dp[cut + 1]) { // if(wordBreak(cut + 1, ei, maxWordLen, s, set, dp)) {
                        flag = true;
                        break;
                    }
                }
            }
            dp[si] = flag == true;
        }
        return dp[SI];
    }
    
}
```

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Mine // 1D DP // Old
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
