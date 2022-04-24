// https://codingcompetitions.withgoogle.com/kickstart/round/00000000008caa74/0000000000acf318
// Find any hamiltonianCycle path from (0,0) , where you can only walk on * and # is blocker

import java.util.Scanner;

public class Solution {

    public static int n, m;
    public static int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
  	public static String[] path = { "S", "E", "N", "W" };

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        int casee = 1;
        while (t-- > 0) {
            int row = sc.nextInt();
            int col = sc.nextInt();

            char[][] grid = new char[2 * row][2 * col];

            n = grid.length;
            m = grid[0].length;

            int tot = n * m;
            for (int i = 0; i < 2 * row; i += 2) {
                int idx = 0;
                String s = sc.next();
                for (int j = 0; j < 2 * col; j += 2) {
                    char c = s.charAt(idx++);
                    if (c == '#')
                        tot -= 4;
                    grid[i + 1][j + 1] = grid[i][j + 1] = grid[i + 1][j] = grid[i][j] = c;
                }
            }

            String res = hamiltonainCycle(grid, 0, 0, 0, "", tot);
            System.out.println("Case #" + casee++ + ": " + (!res.equals("") ? res : "IMPOSSIBLE"));
        }

    }

    // E = v - 1
    public static String hamiltonainCycle(char[][] graph, int i, int j, int edgeCount, String psf, int tot) {
        if (edgeCount == tot - 1) {
            String npsf = findEdge(graph, i, j, psf);
            if (!npsf.equals("")) { // Found a direct edge
                return npsf;
            }
            return "";
        }

        char org = graph[i][j];
        graph[i][j] = '#';
        for (int d = 0; d < dir.length; d++) {
            int x = i + dir[d][0];
            int y = j + dir[d][1];
            if (x >= 0 && x < n && y >= 0 && y < m && graph[x][y] == '*') {
                String rec = hamiltonainCycle(graph, x, y, edgeCount + 1, psf + path[d], tot);
                if (!rec.equals("")) {
                    return rec;
                }
            }
        }
        graph[i][j] = org;
        return "";
    }

    public static String findEdge(char[][] graph, int r, int c, String psf) {
        for (int d = 0; d < dir.length; d++) { // Finding all nbr's
            int x = r + dir[d][0];
            int y = c + dir[d][1];
            if (x >= 0 && x < n && y >= 0 && y < m) {
                if (x == 0 && y == 0) { // Found direct edge with original source (0,0)
                    return psf + path[d];
                }
            }
        }
        return "";
    }

}
