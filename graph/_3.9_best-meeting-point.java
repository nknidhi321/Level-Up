// https://www.lintcode.com/problem/912/

public class Solution {

    public int minTotalDistance(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        if(n == 0 || m == 0) return 0;

        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; 
        int[][] distance = new int[n][m];

        // Jitne 1's hai utne time N*M ki bfs call lgegi
        // Worstcase me saare 1's ho sakte hai so (N*M) (N*M) TC hogi => TLE

        // Agar mai 1 hu toh mai apne se xth cell tak ka distance, distance[][] array in daal dungi
        // Saare 1's ek ek kar k same kaam karenge, same distance[][] array me jaake apne se lekar xth point,
        // tak k distance ko += distance ka k aa jaaenge distance[][] array me
        // Ab finally min of all from distance[][] array find kar lo that is your ans
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 1) {
                    bfs(i, j, dir, distance, grid);
                }
            }
        }

        // Finding min of distance[][] array
        int min = (int)1e8;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                min = Math.min(min, distance[i][j]);
            }
        }
        return min;
    }

    public static void bfs(int i, int j, int[][] dir, int[][] distance, int[][] grid){
        int n = grid.length;
        int m = grid[0].length;
        
        boolean[][] visited = new boolean[n][m];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i * m + j);
        visited[i][j] = true;

        int level = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size-- > 0){
                int idx = queue.poll();
                int r = idx / m;
                int c = idx % m;

                distance[r][c] += level;              // Make sure to do "+=" level => Saare 1's apna apna distance de rahe hai individually in each bfs call

                for(int d = 0; d < dir.length; d++){
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];

                    if(x >= 0 && x < n && y >= 0 && y < m && !visited[x][y]){
                        queue.add(x * m + y);
                        visited[x][y] = true;
                    }
                }
            }
            level++;
        }
    }

}
