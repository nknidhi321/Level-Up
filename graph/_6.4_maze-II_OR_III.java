// Check the follow up at below

// https://www.lintcode.com/problem/788/
// Minimum shortest steps

// Without dis[]

public class Solution {
   
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
        int n = maze.length, m = maze[0].length;
        int[][] dir = new int[][] { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
        boolean[][] vis = new boolean[n][m];

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        queue.add(new int[] {start[0] * m + start[1], 0});
        
        while(!queue.isEmpty()) {
            int[] node = queue.remove();
            int idx = node[0], steps = node[1];
            int r = idx / m;
            int c = idx % m;
            if(dest[0] == r && dest[1] == c) return steps; // Jab nikalta hai, tab ensured h ki minCost k saath he nikla hai // Ye shayad bina dis[] me he kaam krta hai 
            if(vis[r][c]) continue;

            vis[r][c] = true;
            for(int d = 0; d < dir.length; d++) {
                // Why assigning it to another variable ??
                // Kuki r && c ko intact rakhna parega dusre baaki direction k liye 
                int x = r;  
                int y = c;
                int currSteps = steps;
                while(x >= 0 && x < n && y >= 0 && y < m && maze[x][y] == 0) { // Why While ? Kuki ye jump wali loop hai
                    x += dir[d][0];
                    y += dir[d][1];
                    currSteps++;
                }

                // Agar out of boundary ya wall pe chale gaye, ya wall pe chale gaye
                // Then move back to where you were standing 1 step before
                x -= dir[d][0];
                y -= dir[d][1];
                currSteps--;
           
                queue.add(new int[] {x * m + y, currSteps});
            }
        }
        return -1;
    }
}

//---------------------------------------------------------------------------------------------------------------------------------------------------------------

// With dis[]
// Also using pair class

public class Solution {
    
    public static class pair implements Comparable<pair> {
        int r = 0, c = 0, steps = 0;

        pair(int r, int c, int steps) {
            this.r = r;
            this.c = c;
            this.steps = steps;
        }

        @Override
        public int compareTo(pair o) {
            return this.steps - o.steps;
        }
    }

     public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int n = maze.length, m = maze[0].length, sr = start[0], sc = start[1], er = destination[0], ec = destination[1];
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        int[][] dis = new int[n][m];
        for (int[] d : dis)
            Arrays.fill(d, (int) 1e8);

        PriorityQueue<pair> que = new PriorityQueue<>();
        que.add(new pair(sr, sc, 0));
        dis[sr][sc] = 0;

        while (que.size() != 0) {
            pair p = que.remove();
            if (p.r == er && p.c == ec) return p.steps;

            for (int[] d : dir) {
                int r = p.r, c = p.c, steps = p.steps;
                while (r >= 0 && c >= 0 && r < n && c < m && maze[r][c] == 0) {
                    r += d[0];
                    c += d[1];
                    steps++;
                }

                r -= d[0];
                c -= d[1];
                steps--;

                if (steps >= dis[r][c])
                    continue;

                que.add(new pair(r, c, steps));
                dis[r][c] = steps;
            }
        }
        return -1;
    }
    
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Follow up  // https://www.lintcode.com/problem/789/
// Minimum shortest steps + lex shortest path 


public class Solution {

    public static class pair implements Comparable<pair> {
        int r = 0, c = 0, steps = 0;
        String psf = "";

        pair(int r, int c, int steps, String psf) {
            this.r = r;
            this.c = c;
            this.steps = steps;
            this.psf = psf;
        }

        @Override
        public int compareTo(pair o) {
            if (this.steps != o.steps)
                return this.steps - o.steps;
            else
                return this.psf.compareTo(o.psf);
        }
    }

    public String findShortestWay(int[][] maze, int[] start, int[] destination) {
        int n = maze.length, m = maze[0].length, sr = start[0], sc = start[1], er = destination[0], ec = destination[1];
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        String[] dirS = { "d", "u", "r", "l" };
        pair[][] dis = new pair[n][m];
        for (int i = 0; i < n * m; i++)
            dis[i / m][i % m] = new pair(i / m, i % m, (int) 1e8, "");

        PriorityQueue<pair> que = new PriorityQueue<>();
        pair src = new pair(sr, sc, 0, "");

        que.add(src);
        dis[sr][sc] = src;

        while (que.size() != 0) {
            pair p = que.remove();
            for (int i = 0; i < 4; i++) {
                int[] d = dir[i];

                int r = p.r, c = p.c, steps = p.steps;
                while (r >= 0 && c >= 0 && r < n && c < m && maze[r][c] == 0 && !(r == er && c == ec)) { // hole pe v tum break kar jaaoge
                    r += d[0];
                    c += d[1];
                    steps++;
                }

                if (!(r == er && c == ec)) { // hole invalid cell nahi hai, islye hole pe -- nahi hoga
                    r -= d[0];
                    c -= d[1];
                    steps--;
                }

                pair np = new pair(r, c, steps, p.psf + dirS[i]);
                
                // In first check, koi zyada steps aari hai toh continue kar jaao
                // Equal steps pe second check lgega kuki lex choti path aa sakti hai 
                // In second check, dis[r][c] pe already lex choti path hai, toh q update karwau continue kar jaao 
                if (steps > dis[r][c].steps || dis[r][c].compareTo(np) <= 0) continue;

                que.add(np);
                dis[r][c] = np;
            }
        }

        pair ans = dis[er][ec];
        return ans.steps != (int) 1e8 ? ans.psf : "impossible";
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX