//https://leetcode.com/problems/shortest-path-in-binary-matrix/
(0, 0) se (n-1, m-1) tak ka shortest path ?

class Solution {
    
	public int shortestPathBinaryMatrix(int[][] grid) {       
        int n = grid.length;
        int m = grid[0].length;
        
        if(grid[0][0] == 1 || grid[n - 1][m - 1] == 1) return -1; //Either source or dest point is blocked
        
        // when grid will be like [[0]] 
        // you are already at your dest
        if(n == 1 && m == 1 && grid[0][0] == 0) return 1;
        
        int[][] dir = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
        
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(0 * m + 0);
        grid[0][0] = 1;
        
        int level = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                int idx = queue.poll();
                int x = idx / m;
                int y = idx % m;
                
                for(int d = 0; d < dir.length; d++) {
                    int r = x + dir[d][0];
                    int c = y + dir[d][1];
                    
                    if(r >= 0 && r < n && c >= 0 && c < m && grid[r][c] == 0) {
                        queue.add(r * m + c);
                        grid[r][c] = 1;
                        if(r == n - 1 && c == m - 1) return level + 1;
                    }
                }
            }
            level++;
        }
        return -1;
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
