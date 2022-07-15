// https://leetcode.com/problems/palindrome-partitioning-ii/

// Tabulation in pdp preparation + minimum cut based strategy for calulation of memo solution for all palindrome substring 
// TC : O(N^3)

```
class Solution {
    
    public int minCut(String s) {
        int n = s.length();
        Boolean[][] pdp = new Boolean[n][n];
        preparePalindromeDP(s, pdp);
        // for(int i = 0; i < pdp.length; i++) {
        //     for(int j = 0; j < pdp[0].length; j++) {
        //         System.out.print(pdp[i][j] + "\t"); 
        //     }
        //     System.out.println();
        // }
        
        Integer[][] dp = new Integer[n][n];
        return minCuts(0, s.length() - 1, s, pdp, dp);
    }
    
    public void preparePalindromeDP(String s, Boolean[][] pdp) {
        int n = s.length();
        for(int gap = 0; gap < n; gap++) {
            for(int i = 0, j = gap; j < n; i++, j++) {
                if(gap == 0) { 
                    pdp[i][j] = true;  
                }
                else if(gap == 1) {
                    if(s.charAt(i) == s.charAt(j)) pdp[i][j] = true;
                    else pdp[i][j] = false;
                }
                else {
                    if(s.charAt(i) == s.charAt(j) && pdp[i + 1][j - 1]) pdp[i][j] = true;
                    else pdp[i][j] = false;
                }
            }
        }
    }
    
	// Agar ye tab hota toh gap ka loop lgta, for(gap).. for(i,j).. for(cut) => O(N^3)
    public int minCuts(int si, int ei, String s, Boolean[][] pdp, Integer[][] dp) {
        if(pdp[si][ei]) return dp[si][ei] = 0;
        
        if(dp[si][ei] != null) return dp[si][ei];
        
        int min = (int)1e9;
        for(int cut = si; cut < ei; cut++) {
            int lc = minCuts(si, cut, s, pdp, dp);
            int rc = minCuts(cut + 1, ei, s, pdp, dp);
            
            int myAns = lc + 1 + rc;
            
            min = Math.min(min, myAns);
        }
        return dp[si][ei] = min;
    }
        
}
```
-------------------------------------------------------------------------------------------------------------------------------------------------------------

// Tabulation in pdp preparation + minimum cut calulation memo solution for all palindrome substring 
// Ditto as Palindrome Partitioning, here only difference is calculating minimum cut from all the recursive calls of cut
// Find prefix palindrome, and check for rest of the string (ros)
// So, here your left call is saved like we do in cut strategy, only right call goes, only si is changing, so can be managed in 1D DP, so O(N^2)
// tc :  O(N^2)

class Solution {
    
    public static int n;
    public static boolean[][] pdp;
    
    public int minCut(String s) {
        n = s.length();
        pdp = new boolean[n][n];
        preparePalindromeDP(s, pdp);
        
        Integer[] dp = new Integer[n + 1];
        return minCut_Memo(0, n - 1, s, dp);
    }
    
    // Tabulation palindrome dp of subtring 
    public static void preparePalindromeDP(String s, boolean[][] pdp) {
        for(int gap = 0; gap < n; gap++) {
            for(int i = 0, j = gap; j < n; i++, j++) {
                if(gap == 0) pdp[i][j] = true;
                else if(gap == 1 && s.charAt(i) == s.charAt(j)) pdp[i][j] = true;
                else pdp[i][j] = s.charAt(i) == s.charAt(j) && pdp[i + 1][j - 1];
            }
        }
    }
    
    public int minCut_Memo(int si, int ei, String s, Integer[] dp) {
        if(pdp[si][ei]) return dp[si] =  0;
        
        if(dp[si] != null) return dp[si];

        // [si,ei) tak sabse poocho are you palindrome ? 
        int minAns = (int) 1e8;
        for(int cut = si; cut < ei; cut++) {
            if(pdp[si][cut]) { // Checking in pdp if [si, cut] subtring is palindrome
                minAns = Math.min(minAns, minCut_Memo(cut + 1, ei, s, dp)); // If yes, pass ros in next call
            }
        }
        return dp[si] = minAns + 1; // Taking minimum from all the cuts and hamara ek cut so +1
    }
    
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
