// https://practice.geeksforgeeks.org/problems/special-matrix4201/1#

// Memoization

class Solution{
    
   public int FindWays(int n, int m, int[][] blocked_cells){
	
	// Creating arr matrix to block the given blocked cells 
        int[][] arr = new int[n][m];
	   
	// Blocking the cells of the matrix
        for(int row = 0; row < blocked_cells.length; row++){
            int x = blocked_cells[row][0] - 1; //1 based index
            int y = blocked_cells[row][1] - 1; //1 based index
            arr[x][y] = 1;
        }
        
        // When starting box or ending box is blocked => No paths
        if(arr[0][0] == 1 || arr[n - 1][m - 1] == 1) return 0;
        
        Integer[][] dp = new Integer[n][m];
        return FindWays_Memo(0, 0, arr, dp); 
        
    }
    
    public static int M = 1000000007;
    public static int[][] dir = {{0, 1}, {1, 0}};
     
    public static int FindWays_Memo(int sr, int sc, int[][] arr, Integer[][] dp){
        if(sr == arr.length - 1 && sc == arr[0].length - 1) return dp[sr][sc] = 1;
        
        if(dp[sr][sc] != null) return dp[sr][sc];
        
        int count = 0;
        for(int d = 0; d < dir.length; d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            
            if(r <= arr.length - 1 && c <= arr[0].length - 1 && arr[r][c] == 0){
                 count = (count + (FindWays_Memo(r, c, arr, dp) % M)) % M;
            }
        }
        return dp[sr][sc] = count;
    }
    
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Tabulation

class Solution{
    
   public int FindWays(int n, int m, int[][] blocked_cells){
        int[][] arr = new int[n][m];
        for(int row = 0; row < blocked_cells.length; row++){
            int x = blocked_cells[row][0] - 1; //1 based index
            int y = blocked_cells[row][1] - 1; //1 based index
            arr[x][y] = 1;
        }
        
        if(arr[0][0] == 1 || arr[n - 1][m - 1] == 1) return 0;
        
        int[][] dp = new int[arr.length][arr[0].length];
        
        return FindWays_Tab(0, 0, arr, dp);
    }
    
    public static int M = 1000000007;
    public static int[][] dir = {{0, 1}, {1, 0}};
  
    public static int FindWays_Tab(int SR, int SC, int[][] arr, int[][] dp){
        
        for(int sr = arr.length - 1; sr >= 0; sr--){
            for(int sc = arr[0].length - 1; sc >= 0; sc--){
                if(sr == arr.length - 1 && sc == arr[0].length - 1) {
                    dp[sr][sc] = 1;
                    continue;
                }
                
                int count = 0;
                for(int d = 0; d < dir.length; d++){
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];
                    
                    if(r <= arr.length - 1 && c <= arr[0].length - 1 && arr[r][c] == 0){
                         count = (count + (dp[r][c] % M)) % M;
                    }
                }
                dp[sr][sc] = count;
            }
        }
        return dp[SR][SC];
    }
    
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
