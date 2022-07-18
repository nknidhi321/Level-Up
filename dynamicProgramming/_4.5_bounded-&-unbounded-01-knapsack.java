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

// https://practice.geeksforgeeks.org/problems/knapsack-with-duplicate-items4201/1
// Unbounded Knapsack [Same item can be used again, infine supply of item]

// Memoization
// Subsequence

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
// Subsequence

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

//---------------------------------------------------------------------------------------------------------------------------

// P&C works here, becaause max le rahe ho, and saare permutation nikal rahe ho, that is a total waste.
// Use Subsequence approach for this question.

// P&C, for loop

/*	
	Here question is unbounded knapsack, so you can use permutation, so 1D DP is sufficient here
	But if you would have gone for combination then:-
	 1) You would also need to pass idx apart from target/w , so in that case you will need 2D DP
	 2) Here its "unbounded", so your combination should pass n to the next iteration and not n - 1, to use same item again
*/

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

// P&C, for loop
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
