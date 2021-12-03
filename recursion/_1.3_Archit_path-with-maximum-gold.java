//https://leetcode.com/problems/path-with-maximum-gold/

class Solution {
    
    public static int[][] dir = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    
    public int getMaximumGold(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int max = Integer.MIN_VALUE;
        
        //Making dfs call from every cell to find max
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
               max = Math.max(max, getMaximumGold_Util(i, j, n, m, arr)); 
            }
        }   
        return max;
    }
    
    public static int getMaximumGold_Util(int sr, int sc, int n, int m, int[][] arr) {
        int orignalCost = arr[sr][sc];
        arr[sr][sc] = 0; //Marking visited
        
        //Assign 0 as max for sure and not -INF, because we have to get max of all other + us and
        //if all the cells around is visited then no calls would take place from for loop
        //and we will return our originalCost as the first base Cost, so we need to add in 0 and not in -INF
        int max = 0; 
        
        for(int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            
            if(r >= 0 && r < n && c >= 0 && c < m && arr[r][c] != 0) {
                max = Math.max(max, getMaximumGold_Util(r, c, n, m, arr));
            }
        }
        arr[sr][sc] = orignalCost; //Backtrack, mark unvisited
        return max + arr[sr][sc];
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
