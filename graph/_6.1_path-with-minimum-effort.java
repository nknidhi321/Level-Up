// https://leetcode.com/problems/path-with-minimum-effort/

// Modified Dijkstra, just like swim in rising water

class Solution {
    
    public int minimumEffortPath(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        if(n == 1 && m == 1) return 0; // Edge case
        
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        boolean[][] vis = new boolean[n][m];
        
        PriorityQueue<int[]> pqueue = new PriorityQueue<>((a, b) -> a[1] - b[1]); 
		pqueue.add(new int[] {0 * m + 0, Integer.MIN_VALUE}); // {idx, currPathMaxDiff}
        
        while(!pqueue.isEmpty()) {
            int[] node = pqueue.remove();
            int currIdx = node[0];
            int x = currIdx / m;
            int y = currIdx % m;
            int currPathMaxDiff = node[1]; // Current path ka maximum difference between 2 nbrs is currPathMaxDiff
            if(x == n - 1 && y == m - 1) return currPathMaxDiff; // Jo sbse pehle pahuchega wahi shortest hoga
            if(vis[x][y]) continue;
            
            vis[x][y] = true;
            for(int d = 0; d < dir.length; d++) {
                int r = x + dir[d][0];
                int c = y + dir[d][1];
                
                if(r >= 0 && c >= 0 && r < n && c < m) {
                    if(!vis[r][c]) {
                        int nbrWeight = grid[r][c];
                        int diff = Math.abs(grid[x][y] - grid[r][c]);
                        pqueue.add(new int[] { r * m + c, Math.max(currPathMaxDiff, diff) });
                    }
                }
            }
        }
        return -1;
    }
    
}
