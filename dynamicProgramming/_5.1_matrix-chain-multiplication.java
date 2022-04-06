// https://practice.geeksforgeeks.org/problems/matrix-chain-multiplication0303/1 
// Do checkout the follow up

// si is increasing and ei is decreasing so obviously apply gap strategy

class Solution{
    
    static int matrixMultiplication(int n, int arr[]) {
        Integer[][] dp = new Integer[n][n];
        return MM_Memo(0, n - 1, arr, dp);
    }
    
    public static int MM_Memo(int si, int ei, int[] arr, Integer[][] dp) {
        if(ei - si == 1) return dp[si][ei] = 0;
        
        if(dp[si][ei] != null) return dp[si][ei];
        
        int minCost = (int)1e9;
        for(int cut = si + 1; cut < ei; cut++) {
            int leftCost = MM_Memo(si, cut, arr, dp); // An index is 2 different matrix's part
            int rightCost = MM_Memo(cut, ei, arr, dp); // So here cut will only be passed
            
            int currCutCost = leftCost + rightCost + (arr[si] * arr[cut] * arr[ei]);
            
            minCost = Math.min(minCost, currCutCost);
        }
        return dp[si][ei] = minCost;
    }
    
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Tabulation

class Solution{
    
    public static int matrixMultiplication(int n, int arr[]) {
        int[][] dp = new int[n][n];
        return MM_Tab(0, n - 1, arr, dp);
    }
    
    public static int MM_Tab(int SI, int EI, int[] arr, int[][] dp) {
        int n = arr.length;
        
        // Starting with gap == 0, would also work
        // But logically a matrix has two index's as given in MCM array
        // So, At (0,0) cell no matrix exists, which starts and ends at 0
        for(int gap = 1; gap < n; gap++) { 
            for(int si = 0, ei = gap; ei < n; si++, ei++) {
                if(ei - si == 1) {
                    dp[si][ei] = 0;
                    continue;
                }
                
                int minCost = (int)1e9;
                for(int cut = si + 1; cut < ei; cut++) {
                    int leftCost = dp[si][cut]; // MM_Tab(si, cut, arr, dp);
                    int rightCost = dp[cut][ei]; //MM_Tab(cut, ei, arr, dp);
                    
                    int currCutCost = leftCost + rightCost + (arr[si] * arr[cut] * arr[ei]);
                    
                    minCost = Math.min(minCost, currCutCost);
                }
                dp[si][ei] = minCost;
            }
        }
        return dp[SI][EI];
    }
    
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Follow up :- 
// Print the minimum cost matrix example like ((A(BC))D)

    public static int mcm_Dp(int[] arr, int SI, int EI, int[][] dp) {
        int n = arr.length;
        String[][] sdp = new String[n][n];

        for (int gap = 1; gap < n; gap++) {
            for (int si = 0, ei = gap; ei < n; si++, ei++) {
                if (ei - si == 1) {
                    dp[si][ei] = 0;
                    sdp[si][ei] = (char) (si + 'A') + "";
                    continue;
                }

                int minRes = (int) 1e9;
                String minStr = "";
                for (int cut = si + 1; cut < ei; cut++) {
                    int leftRes = dp[si][cut];// mcm_memo(arr, si, cut, dp);
                    int rightRes = dp[cut][ei];// mcm_memo(arr, cut, ei, dp);

                    if (leftRes + arr[si] * arr[cut] * arr[ei] + rightRes < minRes) {
                        minRes = leftRes + arr[si] * arr[cut] * arr[ei] + rightRes;
                        minStr = "(" + sdp[si][cut] + sdp[cut][ei] + ")";
                    }
                }

                dp[si][ei] = minRes;
                sdp[si][ei] = minStr;
            }

        }
        System.out.println(sdp[SI][EI]);
        return dp[SI][EI];
    }

    public static void mcm() {
        int[] arr = { 10, 30, 5, 60 };
        int n = arr.length;
        int[][] dp = new int[n][n];

        System.out.println(mcm_Dp(arr, 0, n - 1, dp));

    }


//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
