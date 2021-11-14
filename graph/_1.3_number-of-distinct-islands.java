//https://www.lintcode.com/problem/860/

//Rajneesh

public class Solution {

    public int numberofDistinctIslands(int[][] grid) {
        char[] dirPath  = {'U', 'R', 'D', 'L'};
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        HashSet<String> set = new HashSet<>();

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    StringBuilder sbsf = new StringBuilder();
                    dfs(i, j, sbsf, dir, dirPath, grid);  
                    set.add(sbsf.toString());
                }
            }
        }
        return set.size();
    }

    public void dfs(int i, int j, StringBuilder sbsf, int[][] dir, char[] dirPath, int[][] grid) {
        grid[i][j] = 0;

        for(int d = 0; d < dir.length; d++){
            int x = i + dir[d][0];
            int y = j + dir[d][1];

            if(x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) {   
                sbsf.append(dirPath[d]);
                dfs(x, y, sbsf, dir, dirPath, grid);
                sbsf.append('B');
            }
        }
    }
}


//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

//Mine

public class Solution {

  public int numberofDistinctIslands(int[][] grid) {
        HashSet<String> set = new HashSet<>();
  
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    StringBuilder sbsf = new StringBuilder();
                    dfs(i, j, sbsf, grid);  
                    set.add(sbsf.toString());
                }
            }
        }
        return set.size();
    }

    public void dfs(int i, int j, StringBuilder sbsf, int[][] grid){
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) return;
        
        grid[i][j] = 0;     //Mark visited
 
        sbsf.append("U");
        dfs(i - 1, j, sbsf, grid);   //To mark up
        sbsf.append("B");            //Add Backtrack Dir after each call
        
        sbsf.append("R");
        dfs(i, j + 1, sbsf, grid);  //To mark right
        sbsf.append("B");           //Add Backtrack Dir after each call
        
        sbsf.append("D");
        dfs(i + 1, j, sbsf, grid);  //To mark down
        sbsf.append("B");           //Add Backtrack Dir after each call
        
        sbsf.append("L");
        dfs(i, j - 1, sbsf, grid);  //To mark left
        sbsf.append("B");           //Add Backtrack Dir after each call
    }
}
