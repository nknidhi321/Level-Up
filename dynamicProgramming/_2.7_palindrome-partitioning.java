// https://leetcode.com/problems/palindrome-partitioning/
// Tabulation in pdp preparation + normal backtracking recursive solution for all palindrome substring 

class Solution {
    
    public static int n;
    public static boolean[][] pdp;
    
    public List<List<String>> partition(String s) {
        n = s.length();
        pdp = new boolean[n][n];
        preparePalindromeDP(s, pdp);
        
        List<List<String>> ans = new ArrayList<>();
        partition_Rec(0, n - 1, s, new ArrayList<>(), ans);
        return ans;
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
    
    public static void partition_Rec(int si, int ei, String s, List<String> asf, List<List<String>> ans) {
        if(si == n) {
            ans.add(new ArrayList<>(asf));
            return;
        }
        
        // [si,ei] tak sabse poocho are you palindrome ? 
        // If yes, add that palindrome substring your ans and make call for rest of the string
        // Recursion ka path(option) is added in your answer
        for(int cut = si; cut <= ei; cut++) {
            if(pdp[si][cut]) { // Check in pdp if [si, cut] subtring is palindrome
                asf.add(s.substring(si, cut + 1));  // Add in list
                partition_Rec(cut + 1, ei, s, asf, ans);  // Making call to ros
                asf.remove(asf.size() - 1);  // Backtrack
            }
        }
    }
        
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
