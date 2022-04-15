// https://practice.geeksforgeeks.org/problems/minimum-cost-path3833/1#
// NOTE : vis[] is required if you are not using dis[] array
// If you are using dis[] then vis[] array is not required

// Dijkstra with dis[] 

class Solution {
    
    public int minimumCostPath(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

        boolean[][] vis = new boolean[n][m];
        
        int[][] dis = new int[n][m];
        for(int[] arr : dis) Arrays.fill(arr, (int)1e8);
        
        PriorityQueue<int[]> pqueue = new PriorityQueue<>((a, b) -> a[1] - b[1]); // sorting on wsf
		    pqueue.add(new int[] {0 * m + 0, grid[0][0]}); // src node is the starting point so wsf for starting node will be grid[0][0]
        
        while(!pqueue.isEmpty()) {
            int[] node = pqueue.remove();
            int currIdx = node[0];
            int x = currIdx / m;
            int y = currIdx % m;
            int currWsf = node[1];
            
            if(vis[x][y]) continue;
            
            vis[x][y] = true;
            for(int d = 0; d < dir.length; d++) {
                int r = x + dir[d][0];
                int c = y + dir[d][1];
                
                if(r >= 0 && c >= 0 && r < n && c < m) {
                    int nbrWeight = grid[r][c];
                    if(currWsf + nbrWeight < dis[r][c]) {
                        dis[r][c] = currWsf + nbrWeight;
                        pqueue.add(new int[] { r * m + c, currWsf + nbrWeight });
                    }
                }
            }
        }
        
        return dis[n - 1][m - 1];
    }
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------------
// Without dis[] 

class Solution {
    
    public int minimumCostPath(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

        boolean[][] vis = new boolean[n][m];
        
        PriorityQueue<int[]> pqueue = new PriorityQueue<>((a, b) -> a[1] - b[1]); // sorting on wsf
		pqueue.add(new int[] {0 * m + 0, grid[0][0]}); // src node is the starting point so wsf for starting node will be grid[0][0]
        
        while(!pqueue.isEmpty()) {
            int[] node = pqueue.remove();
            int currIdx = node[0];
            int x = currIdx / m;
            int y = currIdx % m;
            int currWsf = node[1];
            
            if(x == n - 1 && y == m - 1) return currWsf;
            
            if(vis[x][y]) continue;
            
            vis[x][y] = true;
            for(int d = 0; d < dir.length; d++) {
                int r = x + dir[d][0];
                int c = y + dir[d][1];
                
                if(r >= 0 && c >= 0 && r < n && c < m) {
                    int nbrWeight = grid[r][c];
                    pqueue.add(new int[] { r * m + c, currWsf + nbrWeight });
                }
            }
        }
        return -1;
    }
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------------
