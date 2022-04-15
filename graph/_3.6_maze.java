// https://www.lintcode.com/problem/787/description

public class Solution {

    public boolean hasPath(int[][] maze, int[] start, int[] dest) {
        int n = maze.length, m = maze[0].length;
        int[][] dir = new int[][] { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
        boolean[][] vis = new boolean[n][m];

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(start[0] * m + start[1]);
        vis[start[0]][start[1]] = true;
        
        while(!queue.isEmpty()) {
            int idx = queue.removeFirst();
            int r = idx / m;
            int c = idx % m;

            for(int d = 0; d < dir.length; d++) {
                // Why assigning it to another variable ??
                // Kuki r && c ko intact rakhna parega dusre baaki direction k liye 
                int x = r;  
                int y = c;
                while(x >= 0 && x < n && y >= 0 && y < m && maze[x][y] == 0) { // Why While ? Kuki ye jump wali loop hai
                    x += dir[d][0];
                    y += dir[d][1];
                }

                // Agar out of boundary ya wall pe chale gaye,
                // Then move back to where you were standing 1 step before
                x -= dir[d][0];
                y -= dir[d][1];

                // Here this check is important kuki
                // Jo tum queue se nikale ho, chances ye v hai ki tum same cell v daalne aa sakte ho
                // Why ? Because Note while k andar hum increment kar rahe hai
                // And suppose mere charo taraf sab wall hai, toh while nai chalega
                // and tum same cell ko firse queue me daalne aa jaaoge => INFINITE loop
                if(vis[x][y]) continue;    
            
                queue.add(x * m + y);
                vis[x][y] = true;
                if(dest[0] == x  && dest[1] == y) return true;
            }
        }
        return false;
    }
}
