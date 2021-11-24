//https://leetcode.com/problems/rotting-oranges/
//MultiSource BFS

/*
    Creating a Pair class to keep track of the x and y coordinate,
    x and y coordinate would be there to keep track of which grid is being processed.

    The idea is to put all the grid with value = 2 in queue by iterating the whole grid and also count one, 
    one needs to be counted because there can be some grid with value = 1, which would be surrounded by all 0's 
    therefore that grid with value = 1 cannot be rotten ever.

    Now, While we are processing queue (simple bfs) we reach to each of the neighbours of that polled currentPair, 
    if that i, j is within the grid and grid value of that i and j is 1, 
    then we keep adding the neighbours of 2 which is 1 in the queue 
    and also we keep on subtracting freshOranges(ones) each time and also to keep track of visited grids we mark it as 2.
    Now, observe that if all ones would have been processed then countOnes will eventually become 0, 
    if not then returning -1, simply because freshOrange(ones)s is not 0, 
    that means there was some grid which could not be reached from any 2's of the grid.
*/

class Solution {
    
    static class Pair {
        int x;
        int y;
        
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        
        Queue<Pair> queue = new LinkedList<>();
        
        int freshOranges = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 2) queue.add(new Pair(i, j));
                else if(grid[i][j] == 1)  freshOranges++;
            }
        }
        
        if(freshOranges == 0) return 0;
        
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        int time = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                Pair currentPair = queue.poll();

                for(int d = 0; d < dir.length; d++) {
                    int r = currentPair.x + dir[d][0];
                    int c = currentPair.y + dir[d][1];
                    if(r >= 0 && r < n && c >= 0 && c < m && grid[r][c] == 1) {
                        queue.add(new Pair(r, c));
                        grid[r][c] = 2;
                        freshOranges--;
                        if(freshOranges == 0) return time + 1;
                    }
                }
            }
            time++;
        }
        return -1;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

/*
    In case you did not understand the level concept then here's another way out.
    Keep an additional time field in the Pair class to keep track to the dist or time from the parent 2 to 1.
    Also, x and y coordinate would be there to keep track of which grid is being processed.
    Rest would work the same as the above explantion.
*/

class Solution {
    
    static class Pair {
        int x;
        int y;
        int time;
        
        public Pair(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    
    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        
        Queue<Pair> queue = new LinkedList<>();
        
        int freshOranges = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 2) queue.add(new Pair(i, j, 0));
                else if(grid[i][j] == 1)  freshOranges++;
            }
        }
        
        if(freshOranges == 0) return 0;
        
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                Pair currentPair = queue.poll();
                int time = currentPair.time;
                
                for(int d = 0; d < dir.length; d++) {
                    int r = currentPair.x + dir[d][0];
                    int c = currentPair.y + dir[d][1];
                    if(r >= 0 && r < n && c >= 0 && c < m && grid[r][c] == 1) {
                        queue.add(new Pair(r, c, time + 1));
                        grid[r][c] = 2;
                        freshOranges--;
                        if(freshOranges == 0) return time + 1;
                    }
                }
            }
        }
        return -1;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

/*
    2D into 1D
    Without Pair class
*/

class Solution {
    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        
        Queue<Integer> queue = new LinkedList<>();
        
        int freshOranges = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 2) queue.add(i * m + j);
                else if(grid[i][j] == 1)  freshOranges++;
            }
        }
        
        if(freshOranges == 0) return 0;
        
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        int time = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                int idx = queue.poll();
                int x = idx / m;
                int y = idx % m;
                
                for(int d = 0; d < dir.length; d++) {
                    int r = x + dir[d][0];
                    int c = y + dir[d][1];
                    
                    if(r >= 0 && r < n && c >= 0 && c < m && grid[r][c] == 1) {
                        queue.add(r * m + c);
                        grid[r][c] = 2;
                        freshOranges--;
                        if(freshOranges == 0) return time + 1;
                    }
                }
            }
            time++;
        }
        return -1;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
