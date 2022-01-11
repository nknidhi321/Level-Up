// https://www.lintcode.com/problem/434/

/*
    If you solve using DFS complexity increases a lot. 
    Why ?
    Because Each time you pick an island from operators, make a DFS on entire n*n matrix, and simply count all the components, that is you ansSoFar...
    TC : Suppose you have k operators then complexity would be k * (n*n)

    A better approach would be DSU. Why ? TC : k + O(n*n)
    
    Assume you are surrounded by 4 islands all around you, and you are the middle island who has just arrived from operator, then :-
    
    Step1 ) You do a +1 in island count for including yourself.
    Step2 ) Check all your nbr's in 4 directions, each time you find 1/island in your nbr merge yourself with your neighbouring island and reduce the island count by 1,
            that becomes your ansSoFar.
    
    Observation:
    ------------
    For the same above scenario :- 
    You have already been merged with the left neighbour, then when you go to merge with your top neighbour, you should/must find youself already merged in that group 
    becuase of that left merge that happend just before, and this can be only ensured by DSU, like to know you are already a part of that group or not.
*/

// Union Find

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
