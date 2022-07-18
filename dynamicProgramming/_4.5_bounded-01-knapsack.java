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


//---------------------------------------------------------------------------------------

// Wrong
// P&C, for loop
// Using Combination here, passing n - 1, each time for unique items
// Memo

class Solution { 

    static int knapSack(int w, int wt[], int val[], int n)  { 
        Integer[][] dp = new Integer[n + 1][w + 1];
        return knapSack_Memo(n, w, wt, val, dp);
    } 
    
    public static int knapSack_Memo(int n, int w, int[] wt, int[] val, Integer[][] dp) {
        if(n == 0 || w == 0) return dp[n][w] = 0;
        
        if(dp[n][w] != null) return dp[n][w];
        
        int max = 0;
        for(int i = n; i > 0; i--) {
            if(w - wt[i - 1] >= 0) {
                max = Math.max(max, knapSack_Memo(n - 1, w - wt[i - 1], wt, val, dp) + val[i - 1]);
            }
        }
        return dp[n][w] = max;
    }
    
}


//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
