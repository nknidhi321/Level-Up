// https://practice.geeksforgeeks.org/problems/knapsack-with-duplicate-items4201/1
// Unbounded Knapsack [Same item can be used again, infine supply of item]

// Memoization
// Subsequence // 2D DP

class Solution{
 
  static int knapSack(int N, int W, int val[], int wt[]){
        int[][] dp = new int[N + 1][W  + 1];      
        for(int[] arr : dp) Arrays.fill(arr, -1);
        return Util(W, wt, val, N, dp);
   } 
  
	public static int Util(int W, int[] wt, int[] val, int n, int[][] dp) {
        if(W == 0 || n == 0) return dp[n][W] = 0;
        
        if(dp[n][W] != -1) return dp[n][W];
        
        int max1 = 0;
        int max2 = 0;
        if(W - wt[n - 1] >= 0){
            max1 = Util(W - wt[n - 1], wt, val, n, dp) + val[n - 1];  // Same item can be used again, so pass n 
        }
        max2 = Util(W, wt, val, n - 1, dp);
        return dp[n][W] = Math.max(max1, max2);
    }

}

// ------------------------------------------------------------------------------------------------------------

// Tabulation
// Subsequence // 2D DP

class Solution{
  
    static int knapSack(int N, int W, int val[], int wt[]) {
        int[][] dp = new int[N + 1][W  + 1];
        
        for(int n = 0; n < dp.length; n++) {
            for(W = 0; W < dp[0].length; W++) {    
                if(W == 0 || n == 0){
                    dp[n][W] = 0;
                    continue;
                }
                
                int max1 = 0;
                int max2 = 0;
                if(W - wt[n - 1] >= 0) {
                    max1 = dp[n][W - wt[n - 1]] + val[n - 1];
                }
                max2 = dp[n - 1][W];
                dp[n][W] = Math.max(max1, max2);
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }

}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// When it's unbounded/infinite you can use P&C wala tareeka // Kuki 1D Dp me baat bn jaaegi
// P&C works here, becaause max le rahe ho, and saare permutation/combination nikal rahe ho, but it doesn't matter kuki aapko count chahiye
// 2 aage joro piche joro kisi k, sum me dono jagah same he contribute karega. 

// P&C, permutation for loop
// Memoization

class Solution {
    
    static int knapSack(int N, int w, int val[], int wt[]) {
        Integer[] dp = new Integer[w + 1];
        return knapSack_Memo(w, wt, val, dp);
    } 
    
    public static int knapSack_Memo(int w, int[] wt, int[] val, Integer[] dp) {
        if(w == 0) return dp[w] = 0;
        
        if(dp[w] != null) return dp[w];
        
        int max = 0;
        for(int i = 0; i < wt.length; i++) {
            if(w - wt[i] >= 0) {
                max = Math.max(max, knapSack_Memo(w - wt[i], wt, val, dp) + val[i]);
            }
        }
        return dp[w] = max;
    }
}

//-------------------------------------------------------------------------------------------------------------

// P&C, permutation for loop
// Tabulation

class Solution {
    
    static int knapSack(int N, int w, int val[], int wt[]) {
        Integer[] dp = new Integer[w + 1];
        return knapSack_Tab(w, wt, val, dp);
    } 
    
    public static int knapSack_Tab(int W, int[] wt, int[] val, Integer[] dp) {
        for(int w = 0; w <= W; w++) {
            if(w == 0) {
                dp[w] = 0;
                continue;
            }
            
            int max = 0;
            for(int i = 0; i < wt.length; i++) {
                if(w - wt[i] >= 0) {
                    max = Math.max(max, dp[w - wt[i]] + val[i]); //knapSack_Memo(w - wt[i], wt, val, dp) + val[i]);
                }
            }
            dp[w] = max;
        }
        return dp[W];
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// P&C, combination for loop
// Tabulation

class Solution {
    
    public static int knapSack(int N, int w, int val[], int wt[]) {
        int[] dp = new int[w + 1];
        return knapSack_Tab(w, wt, val, dp);
    } 
    
    public static int knapSack_Tab(int TAR, int[] coins, int[] val, int[] dp) {
        dp[0] = 0;
        for(int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            int value = val[i];
            for(int tar = 1; tar <= TAR; tar++) {
                if(tar - coin >= 0) {
                    dp[tar] = Math.max(dp[tar], dp[tar - coin] + value);   
                }
            }   
        }
        return dp[TAR];
    }
    
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
