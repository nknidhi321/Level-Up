// https://leetcode.com/problems/coloring-a-border/

/*
    Make a DFS call on all connected rcColor (given rowColColor), check all your nbrs, if "all of them" is rcColor then,
    you are not a boundary cell and you are not supposed to be colored with color, so keep a mark of that cell(say 1e9),
    and if "all of the nbrs is not" rcColor then you are a boundary Cell.

    Now, after you come out of DFS call, your boundary cells are all marked with -rcColor and withinBoundary cells are marked with 1e9,
    so change them accordingly.
*/

```
class Solution {
    
    public int n, m;
    
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        n = grid.length;
        m = grid[0].length;
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        int rcColor = grid[row][col];
        dfs(row, col, rcColor, grid, dir);
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == -rcColor) grid[i][j] = color; // Boundary cell, so mark with color
                else if(grid[i][j] == (int)1e9) grid[i][j] = rcColor; // Within boundary cell, so mark with rcColor(original)
            }
        }
        return grid;
    }
    
    public void dfs(int x, int y, int rcColor, int[][] grid, int[][] dir) {
        
        grid[x][y] = -rcColor; // visited
        int count = 0;
        for(int d = 0; d < dir.length; d++) {
            int r = x + dir[d][0];
            int c = y + dir[d][1];
            
            if(r >= 0 && r < n && c >= 0 && c < m) {
                if(Math.abs(grid[r][c]) == rcColor || grid[r][c] == (int)1e9) count++; // -rcColor, rcColor, 1e9 are same nbrs 
                if(grid[r][c] == rcColor) { // call on unvisited nbr which is of rcColor
                    dfs(r, c, rcColor, grid, dir);
                }
            }
        }
        
        // Acc. to question if all 4 direction is rcColor, then it is not a boundary cell
        // So, mark yourself as 1e9 because if you mark it with rcColor then,
        // when you return from this cell, then another cell can call on this cell, considering it unvisited,
        // since we are making calls on all connected rcColor cell
        if(count == 4) grid[x][y] = (int)1e9; // to keep it visited && a mark for within boundary cell
    }
    
}
```

-----------------------------------------------------------------------------------------------------------------------------------------------------
