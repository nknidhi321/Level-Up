// https://leetcode.com/problems/swim-in-rising-water/
// Modified Dijkstra
// wsf k jagah mwsf(maximumWeightSoFar) pe Dijkstra lga do kuki minimum time find karna hai aapko kisi v path ka

class Solution {
    
    public static int n, m;
    public static int[][] dir = {{1, 0}, {1, -1}, {1, 1}};
   
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

        boolean[][] vis = new boolean[n][m];
        
        PriorityQueue<int[]> pqueue = new PriorityQueue<>((a, b) -> a[1] - b[1]); // sorting on mwsf(maximumWeightSoFar)
		    pqueue.add(new int[] {0 * m + 0, grid[0][0]}); // src node is the starting point so Mwsf for starting node will be grid[0][0]
        
        while(!pqueue.isEmpty()) {
            int[] node = pqueue.remove();
            int currIdx = node[0];
            int x = currIdx / m;
            int y = currIdx % m;
            int currMwsf = node[1];
            if(x == n - 1 && y == m - 1) return currMwsf;
            if(vis[x][y]) continue;
            
            vis[x][y] = true;
            for(int d = 0; d < dir.length; d++) {
                int r = x + dir[d][0];
                int c = y + dir[d][1];
                
                if(r >= 0 && c >= 0 && r < n && c < m) {
                    if(!vis[r][c]) {
                        int nbrWeight = grid[r][c];
                        pqueue.add(new int[] { r * m + c, Math.max(currMwsf, nbrWeight)});
                    }
                }
            }
        }
        return -1;
    }
    
}

