//https://www.lintcode.com/problem/walls-and-gates/description

/*
  Similar question 01 Matrix
  
  Like 01 matrix, we need extra visited matrix, because we need to prepare our answer in the input matrix.
  
  Why we chose to start from 0 because we have to assign our answer at INF cell, 
  So if we make INF as source vtx then we will move radially from INF cell and move to find 0's
  But we need to prepare our answer at INF cell, so why to move away from the answer.
  Even this can be achieved but we have to make individual BFS calls from every INF cell which will increase complexity.
  
  So, we chose to insert 0 as the src vtx, and now we will search for INF cell, and the moment we get INF cell assign the level or distance.
  
*/

//Rajneesh

public class Solution {

    public void wallsAndGates(int[][] rooms) {
        int n = rooms.length;
        int m = rooms[0].length;

        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        boolean[][] visited = new boolean[n][m];
        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(rooms[i][j] == 0) {
                    queue.offer(i * m + j);
                    visited[i][j] = true;
                }
            }
        }

        int level = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                int idx = queue.poll();
                int x = idx / m;
                int y = idx % m;

                for(int d = 0; d < dir.length; d++){
                    int r = x + dir[d][0];
                    int c = y + dir[d][1];

                    if(r >= 0 && r < n && c >= 0 && c < m && rooms[r][c] == 2147483647 && !visited[r][c]) {
                        rooms[r][c] = level + 1;
                        queue.offer(r * m + c);
                        visited[r][c] = true;
                    }
                } 
            }
            level++;
        }
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Using Pair Class

public class Solution {

  public class Pair{
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public void wallsAndGates(int[][] rooms) {
        int row = rooms.length;
        int col = rooms[0].length;

        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        boolean[][] visited = new boolean[row][col];
        Queue<Pair> queue = new LinkedList<Pair>();

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(rooms[i][j] == 0) {
                    queue.offer(new Pair(i, j));
                    visited[i][j] = true;
                }
            }
        }

        int level = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                Pair currPair = queue.poll();
                for(int d = 0; d < dir.length; d++){
                    int r = currPair.x + dir[d][0];
                    int c = currPair.y + dir[d][1];

                    if(r >= 0 && r < row && c >= 0 && c < col && rooms[r][c] == 2147483647){
                        if(!visited[r][c]) {
                            rooms[r][c] = level + 1;
                            queue.offer(new Pair(r, c));
                            visited[r][c] = true;
                        }
                    }
                } 
            }
            level++;
        }
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
