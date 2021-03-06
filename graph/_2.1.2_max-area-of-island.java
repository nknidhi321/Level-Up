//https://leetcode.com/problems/max-area-of-island/
NOTE : Same as size of Generic Tree

//Rajneesh //DFS

class Solution {
 
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, dfs(i, j, grid, dir));
                }
            }
        }
        return maxArea;
    }
    
    public int dfs(int i, int j, int[][] grid, int[][] dir) {
        
        grid[i][j] = 0;
        int count = 0;
        for(int d = 0; d < dir.length; d++) {
            int x = i + dir[d][0];
            int y = j + dir[d][1];

            if(x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) {
                count += dfs(x, y, grid, dir); 
            }
        }
        return count + 1;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Union Find

class Solution {
    
    public int[] par;
    public int[] size;
    
    public int findPar(int v) {
        if(par[v] == v) return v;
        return par[v] = findPar(par[v]);
    }
    
    public int mergeOrUnionBySize(int gpu, int gpv) {
        if(size[gpu] > size[gpv]) {
            par[gpv] = gpu;
            size[gpu] += size[gpv];
            return size[gpu];
        }
        else {
            par[gpu] = gpv;
            size[gpv] += size[gpu];
            return size[gpv];
        }
    }
    
    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dir = {{0, 1}, {1, 0}};
        
        par = new int[n * m];
        size = new int[n * m];
        
        int maxArea = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                int idx = i * m + j;
                if(grid[i][j] == 1) {
                    par[idx] = idx;
                    size[idx] = 1;
                    maxArea = 1;
                }
            }
        }
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 1) {
                    int idx = i * m + j;
                    
                    for(int d = 0; d < dir.length; d++){
                        int x = i + dir[d][0];
                        int y = j + dir[d][1];

                        if(x >= 0 && x < n && y >= 0 && y < m && grid[x][y] == 1) {
                            int globalParentOfu = findPar(idx); 
                            int globalParentOfv = findPar(x * m + y);
                            
                            if(globalParentOfu != globalParentOfv) {
                                maxArea = Math.max(maxArea, mergeOrUnionBySize(globalParentOfu, globalParentOfv));
                            }
                        }
                    }
                }
            }
        }
        return maxArea;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

//Mine //BFS

class Solution {
    
    class Pair {
        int x;
        int y;
        
        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, bfs(i, j, grid, dir));
                }
            }
        }
        return maxArea;
    }
    
    public int bfs(int i, int j, int[][] grid, int[][] dir) {
        
        int count = 0;
        Queue<Pair> queue = new LinkedList<Pair>();
        queue.offer(new Pair(i, j));
        grid[i][j] = 0;
        count++;
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                Pair currentPair = queue.poll();
                
                for(int d = 0; d < dir.length; d++){
                    int x = currentPair.x + dir[d][0];
                    int y = currentPair.y + dir[d][1];
                    
                    if(x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1){
                        queue.offer(new Pair(x, y));
                        grid[x][y] = 0;
                        count++;
                    }
                }
            }
        }
        return count;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

//Kevin

class Solution {
    
    public int maxAreaOfIsland(int[][] grid) {
    
        int maxIsland = 0;
        
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    maxIsland = Math.max(maxIsland, dfs(grid, i, j));
                }
            }
        }
        return maxIsland;
    }
    
    public static int dfs(int[][] grid, int i, int j) {
    
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0)
            return 0;
        
        grid[i][j] = 0;
        int result = 0;
        result += dfs(grid, i-1, j);
        result += dfs(grid, i+1, j);
        result += dfs(grid, i, j+1);
        result += dfs(grid, i, j-1);
        return result + 1;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
