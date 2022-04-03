// https://practice.geeksforgeeks.org/problems/matrix-chain-multiplication0303/1 

class Solution{
    
    static int matrixMultiplication(int n, int arr[]) {
        Integer[][] dp = new Integer[n][n];
        return MM_Memo(0, n - 1, arr, dp);
    }
    
    public static int MM_Memo(int si, int ei, int[] arr, Integer[][] dp) {
        if(ei - si == 1) return 0;
        
        if(dp[si][ei] != null) return dp[si][ei];
        
        int minCost = (int)1e9;
        for(int cut = si + 1; cut < ei; cut++) {
            int leftCost = MM_Memo(si, cut, arr, dp);
            int rightCost = MM_Memo(cut, ei, arr, dp);
            
            int currCutCost = leftCost + rightCost + (arr[si] * arr[cut] * arr[ei]);
            
            minCost = Math.min(minCost, currCutCost);
        }
        return dp[si][ei] = minCost;
    }
    
}

