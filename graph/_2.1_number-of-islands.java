/*
 https://leetcode.com/problems/number-of-islands/
 get connected component Application
*/

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

//DFS Approach

class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
              
                //Trying to make DFS calls through all nodes/cells having 1's or land which are not visited
                if(grid[i][j] == '1'){
                    //Making dfs call which will make all 4 directional 1's nbr to 0
                    count += dfs(i, j, dir, grid);
                }
            }
        }
        return count;
    }
    
    public static int dfs(int i, int j, int[][] dir, char[][] grid){
        grid[i][j] = '0'; //Marking visited
        for(int d = 0; d < dir.length; d++){
            //Finding all nbr's
            int x = i + dir[d][0];
            int y = j + dir[d][1];
            
            //If Within boundary and cell not visited then make dfs call on nbr
            if(x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == '1'){
                dfs(x, y, dir, grid);
            }
        }
        return 1;
    }
}


//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

//Union-Find 
//With Union by size 

class Solution {
    public int[] par;
    public int[] size;
    
    public int findPar(int v) {
        if(par[v] == v) return v;
        return par[v] = findPar(par[v]);
    }
    
    public void mergeOrUnionBySize(int gpu, int gpv) {
        if(size[gpu] > size[gpv]) {
            par[gpv] = gpu;
            size[gpu] += size[gpv];
        }
        else {
            par[gpu] = gpv;
            size[gpv] += size[gpu];
        }
    }
    
    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dir = {{0, 1}, {1, 0}};
        
        par = new int[n * m];
        size = new int[n * m];
        
        int count = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                int idx = i * m + j;
                if(grid[i][j] == '1') {
                    par[idx] = idx;
                    size[idx] = 1;
                    count++;
                }
            }
        }
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == '1') {
                    int idx = i * m + j;
                    
                    for(int d = 0; d < dir.length; d++){
                        int x = i + dir[d][0];
                        int y = j + dir[d][1];

                        if(x >= 0 && x < n && y >= 0 && y < m && grid[x][y] == '1') {
                            int globalParentOfu = findPar(idx); 
                            int globalParentOfv = findPar(x * m + y);
                            
                            if(globalParentOfu != globalParentOfv) {
                                mergeOrUnionBySize(globalParentOfu, globalParentOfv);
                                count--;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }
}


//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

//Union-Find 
//Without Union by Size

class Solution {
    public int[] par;
    
    public int findPar(int v) {
        if(par[v] == v)
            return v;
        return par[v] = findPar(par[v]);
    }
    
    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        
        par = new int[n * m];
        int[][] dir = {{0, 1}, {1, 0}};
        int count = 0;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                int idx = i * m + j;
                if(grid[i][j] == '1') {
                    par[idx] = idx;
                    count++;
                }
            }
        }
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == '1') {
                    int idx = i * m + j;
                    int globalParentOfu = findPar(idx);
                    
                    for(int d = 0; d < dir.length; d++){
                        int x = i + dir[d][0];
                        int y = j + dir[d][1];

                        if(x >= 0 && x < n && y >= 0 && y < m && grid[x][y] == '1') {
                            int globalParentOfv = findPar(x * m + y);
                            
                            if(globalParentOfu != globalParentOfv) {
                                par[globalParentOfv] = globalParentOfu;
                                count--;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }
}


//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

//BFS 
//Using 2D as 1D

class Solution {
    public int numIslands(char[][] grid) {
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int count = 0;
        
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    count += bfs(i, j, grid, dir);
                }
            }
        }
        return count;
    }
    
    public int bfs(int i, int j, char[][] grid, int[][] dir) {
        int n = grid.length;
        int m = grid[0].length;
       
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i * m + j);
        grid[i][j] = '0';
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                int idx = queue.poll();
                int r = idx / m;
                int c = idx % m;
                
                for(int d = 0; d < dir.length; d++){
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];
                    
                    if(x >= 0 && x < n && y >= 0 && y < m && grid[x][y] == '1'){
                        queue.offer(x * m + y);
                        grid[x][y] = '0';
                    }
                }
            }
        }
        return 1;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

//BFS 
//Using Pair Class

class Solution {
    class Pair {
        int x;
        int y;
        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    public int numIslands(char[][] grid) {     
        int count = 0;
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1'){
                    count += bfs(i, j, grid, dir);
                }
            }
        }
        return count;
    }
    
    public int bfs(int i, int j, char[][] grid, int[][] dir) {
        Queue<Pair> queue = new LinkedList<Pair>();
        queue.offer(new Pair(i, j));
        grid[i][j] = '0';
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                Pair currentPair = queue.poll();
                
                for(int d = 0; d < dir.length; d++){
                    int x = currentPair.x + dir[d][0];
                    int y = currentPair.y + dir[d][1];
                    
                    if(x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == '1'){
                        queue.offer(new Pair(x, y));
                        grid[x][y] = '0';
                    }
                }
            }
        }
        return 1;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

//Kevin DFS

class Solution {   
    public int numIslands(char[][] grid) {
        int islandCount = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == '1') {
                    islandCount += dfs(grid, i, j);
                }
            }
        }
        return islandCount;
    }
    
    public static int dfs(char[][] grid, int i, int j){
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == '0')
            return 0;
        grid[i][j] = '0';
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
        return 1;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

/*
  Follow up questions:
  1. Number of island2  => https://www.lintcode.com/problem/434/
  2. Distinct island by Shape => https://www.lintcode.com/problem/number-of-distinct-islands/description
     Number of distinct Island 694 Leetcode (Lintcode 860) // Distinct in respect to shape
*/
