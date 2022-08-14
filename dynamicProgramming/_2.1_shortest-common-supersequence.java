// https://leetcode.com/problems/shortest-common-supersequence/

class Solution {
    
    public String shortestCommonSupersequence(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int dp[][] = LCS(str1, str2 , n, m);
        
        // Agar length maanga hota toh (n + m - LCS.length) ans hota
        // Common subsequence ko ek he baar lena hai, 
        // So simply subtract LCS length ones from n + m would give the answer  
        // Par string maanga hai, so string prepare karo LCS dp se
        return backEngineering(dp, n, m, str1, str2); 
    }
    
    // Preparing LCS dp
    public int[][] LCS(String str1 , String str2 , int n , int m) {     
        int dp[][] = new int[n + 1][m + 1];
        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= m ; j++) {
                if(str1.charAt(i-1) == str2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp;
    }
 
    public String backEngineering(int[][]dp, int n, int m, String s1, String s2) {
        StringBuilder ans = new StringBuilder();
        int i = n, j = m;
        while(i > 0 && j > 0) {
           if(s1.charAt(i - 1)  == s2.charAt(j - 1)) { // equal char => LCS char => ek he baar lena hai is char ko 
                ans.append(s1.charAt(i-1)); // So, us char ko lelo and upper left dia pe move kr jaao 
                i--; j--;
            }
            else if(dp[i - 1][j] > dp[i][j - 1]) { // Jo v bada hoga uski taraf move kar jaao kuki saare chars ko lena hai
                ans.append(s1.charAt(i - 1)); // Uski taraf move krne se pehle khud ko ans me inc kar lo
                i--;
            }
            else { // Jo v bada hoga uski taraf move kar jaao kuki saare chars ko lena hai
                ans.append(s2.charAt(j - 1)); // Uski taraf move krne se pehle khud ko ans me inc kar lo
                j--;   
            }
        }
        
        // Dono me se koi ek string pakke se bacha reh jaaega
        
        while(i != 0) {  // Jo v bacha hai usko apne ans me append kar lo
            ans.append(s1.charAt(i - 1));
            i--;
        }
        while(j != 0) { // Jo v bacha hai usko apne ans me append kar lo
            ans.append(s2.charAt(j - 1));  
            j--;
        }
        
        return ans.reverse().toString(); // NOTE : Ans ulte direction me bnaye the LCS dp se, so reverse it
    }
    
}
