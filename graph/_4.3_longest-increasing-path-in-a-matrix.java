// https://leetcode.com/problems/longest-increasing-path-in-a-matrix/

// Using DFS and DP

/*
    Approach : 
    --------
    The idea is to make a dfs call on each cell, and in that dfs call move until you find a cell a that is greater than the current cell.
    Now, form your answer by taking maximum of all valid nbr cells + 1 for your current cell.

    NOTE : DP will store max length chain that can be formed for the curr cell
*/

```
class Solution {
    
    public int longestIncreasingPath(int[][] matrix) {
        
        int max = 1;
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int[][] dp = new int[matrix.length][matrix[0].length];
        
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                max = Math.max(max, dfs(matrix, i, j, dp, dir));
            }
        }       
        return max;
    }
    
    public static int dfs(int[][] matrix, int i, int j, int[][] dp, int[][] dir){
        
        if(dp[i][j] != 0) 
            return dp[i][j];
        
        int max = 0;
        for(int d = 0;  d < dir.length; d++) {    
            int x = i + dir[d][0];
            int y = j + dir[d][1];
            
            if(x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length && matrix[x][y] > matrix[i][j]){
                max = Math.max(max, dfs(matrix, x, y, dp, dir));
            }
        }
        return dp[i][j] = max + 1;
    }
}
```

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Rajneesh // Union Find
// creating indegree + adding in queue together // Apna indegree badhao

```
class Solution {
    
    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] indegree = new int[n][m];
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        // Creating indegree + adding in queue together
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                for(int d = 0; d < dir.length; d++) { // Check all your neighbours
                     int x = i + dir[d][0];
                     int y = j + dir[d][1]; 
                
                     if(x >= 0 && y >= 0 && x < n && y < m && matrix[i][j] > matrix[x][y]) {
                        indegree[i][j]++; // If you are greater than your nbr, increase your indegree
                     }
                }
                if(indegree[i][j] == 0) queue.add(i * m + j);  // Adding all 0 indegee in queue 
            }
        }
              
        // Kahn's Algo
        int level = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                int idx = queue.poll();
                int r = idx / m;
                int c = idx % m;
                
                for(int d = 0; d < dir.length; d++) {
                     int x = r + dir[d][0];
                     int y = c + dir[d][1]; 
                
                     if(x >= 0 && y >= 0 && x < n && y < m && matrix[x][y] > matrix[r][c]) {
                        indegree[x][y]--;
                        if(indegree[x][y] == 0) {
                            queue.add(x * m + y);
                        }
                    }
                }
            }
            level++;
        }
       return level; // Last level is your answer
    }
}
```

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Mine // Using Khan's Algo // BFS // DAG
// creating indegree & adding in queue separately // Nbr ka indegree badhao

```
class Solution {
    
    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] indegree = new int[n][m];
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        // Creating indegree // Neighbour ka indegree badhao
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                for(int d = 0; d < dir.length; d++) {
                     int x = i + dir[d][0];
                     int y = j + dir[d][1]; 
                
                     if(x >= 0 && y >= 0 && x < n && y < m && matrix[x][y] > matrix[i][j]) {
                        indegree[x][y]++;
                     }
                }
            }
        }
        
        // Adding all 0 indegee in queue 
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(indegree[i][j] == 0) {
                    queue.add(i * m + j);
                }
            }
        }
        
        // Kahn's Algo
        int level = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                int idx = queue.poll();
                int r = idx / m;
                int c = idx % m;
                
                for(int d = 0; d < dir.length; d++) {
                     int x = r + dir[d][0];
                     int y = c + dir[d][1]; 
                
                     if(x >= 0 && y >= 0 && x < n && y < m && matrix[x][y] > matrix[r][c]) {
                        indegree[x][y]--;
                        if(indegree[x][y] == 0) {
                            queue.add(x * m + y);
                        }
                    }
                }
            }
            level++;
        }
        
       return level; // Last level is your answer  // Dry run on small TC of size 2
    }
}
```
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
