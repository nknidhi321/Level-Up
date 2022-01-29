// https://leetcode.com/problems/count-sub-islands/

/*
    Without extra space

    Intuition :-
    ------
    Grid2 pe "poori" DFS call maro [ jitne connected 1's hai sab pe] and last me grid1[i][j] check karna.

    2 cases me subIsland nahi bnega mtlb DFS ka answer false ho sakta hai :-
        1)  Agar DFS ka recursive answer (recAns) false ho ya
        2)  Grid1 pe wo corresponding cell 0 ho toh answer false hoga
*/


class Solution {
    
    public static int n, m;
    public static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        n = grid2.length;
        m = grid2[0].length;
        
        int count = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid2[i][j] == 1 && dfs(i, j, grid1, grid2)) {
                    count++;
                }
            }
        }
        return count;
    }
    
    public static boolean dfs(int i, int j, int[][] grid1, int[][] grid2) {
        grid2[i][j] = 0;
        boolean ans = true;
        
        for(int d = 0; d < dir.length; d++) {
            int x = i + dir[d][0];
            int y = j + dir[d][1];
            
            // "Poori" dfs call maaro recAns false aaye tab v, 
            // otherwise kuch cells 0's mark ho jaaenge visited k wazah se and agar false milte he return kr jaatey ho then 
            // jab uske nbr pe nayi speparate dfs call lgegi toh wo ek individual subIsland treat hone lgega joki galat hoga
            if(x >= 0 && x < n && y >= 0 && y < m && grid2[x][y] == 1) {
                if(!dfs(x, y, grid1, grid2)) {
                    ans = false;
                }
            }
        }
        return ans && (grid1[i][j] == 1);
    }
}

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

/*
    With extra space of List

    Intuition : 
    ------
    Make dfs call on each subIsland of grid2 and keep on adding the idx of that dfs call in List.
    Now, when you come out of that dfs call, now check if all the idx of that list in grid1 consists 1, if all the cells of list of that grid is 1 then increase the count. 
*/

class Solution {
    
    public static int n, m;
    public static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        n = grid2.length;
        m = grid2[0].length;
        
        int count = 0;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid2[i][j] == 1) {
                    
                    List<Integer> listIdx = new ArrayList<>();
                    dfs(i, j, grid1, grid2, listIdx);
                    
                    boolean isSubIsland = true;
                    for(int idx : listIdx) {
                        int x = idx / m;
                        int y = idx % m;
                        if(grid1[x][y] != 1) {
                            isSubIsland = false;
                            break;
                        }
                    }
                    if(isSubIsland) count++;
                }
            }
        }
        return count;
    }
    
    public static void dfs(int i, int j , int[][] grid1, int[][] grid2, List<Integer> listIdx) {
        listIdx.add(i * m + j);
        grid2[i][j] = 0;
        
        for(int d = 0; d < dir.length; d++) {
            int x = i + dir[d][0];
            int y = j + dir[d][1];
            
            if(x >= 0 && x < n && y >= 0 && y < m && grid2[x][y] == 1) {
                dfs(x, y, grid1, grid2, listIdx);
            }
        }
    }
}

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
