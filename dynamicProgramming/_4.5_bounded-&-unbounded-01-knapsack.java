// https://practice.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1
// Bounded Knapsack [Can take an item only once]

// Memoization

class Solution { 
  
    static int knapSack(int W, int wt[], int val[], int n) { 
       int[][] dp = new int[n + 1][W  + 1];
       for(int[] arr : dp) Arrays.fill(arr, -1);
       return Util(W, wt, val, n, dp);
      } 
    
    public static int Util(int W, int[] wt, int[] val, int n, int[][] dp){
        if(W == 0 || n == 0) return dp[n][W] = 0;
        
        if(dp[n][W] != -1) return dp[n][W];
        
        int max1 = 0;
        int max2 = 0;
        if(W - wt[n - 1] >= 0){
            max1 = Util(W - wt[n - 1], wt, val, n - 1, dp) + val[n - 1];   // Same item cannot be used again, so pass n - 1 
        }
        max2 = Util(W, wt, val, n - 1, dp);
        return dp[n][W] = Math.max(max1, max2);
    }
}

//----------------------------------------------------------------------------------------------

// Tabulation

class Solution { 
   static int knapSack(int W, int wt[], int val[], int n) { 
        int[][] dp = new int[n + 1][W  + 1];
        
        for(n = 0; n < dp.length; n++){ 
            for(W = 0; W < dp[0].length; W++){
                if(W == 0 || n == 0){
                    dp[n][W] = 0;
                    continue;
                }
                
                int max1 = 0;
                int max2 = 0;
                if(W - wt[n - 1] >= 0){
                    max1 = dp[n - 1][W - wt[n - 1]] + val[n - 1];
                }
                max2 = dp[n - 1][W];
                dp[n][W] = Math.max(max1, max2);
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// https://practice.geeksforgeeks.org/problems/knapsack-with-duplicate-items4201/1
// Unbounded Knapsack [Same item can be used again, infine supply of item]

// Memoization

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
