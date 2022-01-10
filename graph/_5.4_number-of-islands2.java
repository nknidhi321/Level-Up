//https://www.lintcode.com/problem/434/

public class Solution {
  
    public static int[] par;
    public static int[] size;

    public static int findPar(int v) {
        if(v == par[v]) return v;
        return par[v] = findPar(par[v]); 
    }

    public static void mergeOrUnionBySize(int gpu, int gpv) {
        if(size[gpu] > size[gpv]) {
            par[gpv] = gpu;
            size[gpu] += size[gpv];
        }
        else {
            par[gpu] = gpv;
            size[gpv] += size[gpu];
        }
    }

    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        List<Integer> list = new ArrayList<>();
        if(operators == null)
            return list;

        int[][] grid = new int[n][m];
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        par = new int[n * m];
        size = new int[n * m];
        
        for(int i = 0; i < par.length; i++) {
            par[i] = i;
        }

        int islandCount = 0;
        for(Point p : operators) {
            int x = p.x;
            int y = p.y;
            if(grid[x][y] == 0){
                grid[x][y] = 1;
                islandCount++;

                for(int d = 0; d < dir.length; d++) {
                    int r = x + dir[d][0];
                    int c = y + dir[d][1];

                    if(r >= 0 && r < n && c >= 0 && c < m && grid[r][c] == 1) {
                        int globalParentOfu = findPar(x * m + y);
                        int globalParentOfv = findPar(r * m + c);
                        if(globalParentOfu != globalParentOfv) {
                            mergeOrUnionBySize(globalParentOfu, globalParentOfv);    
                            islandCount--;            
                        }
                    }
                }
            }
            list.add(islandCount);
        }
        return list;       
    }
}
