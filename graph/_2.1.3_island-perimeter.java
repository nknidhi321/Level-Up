//https://leetcode.com/problems/island-perimeter/
Calculate number of leaf nodes in binary tree

/*

  # Square Calculation Approach

  Approach Intuition Wise:-
  -------------------------
  Say there are 2 squares, A and B, kept separetely, then total perimeter is 4 + 4 => 8
  Now, If we merge these 2 squares, side by side, then the common overlapping edge will have 2 edges, one for A and the other for B.

  So, say there are N squares, so total perimeter is N * 4.
  So, if we remove all the common edges [2 => A + B] we have our answer. 


  Approach Code Wise:-
  --------------------
  The idea is count all the ones, and multiply by 4, so we get total perimeter including all the boxes separatly.

  For the given example,
  We have 7 ones, so 7 * 4 = 28, but here we have to neglect all the connected sides (2 edges, birectional connection) of the square with another square. 
  So, if for (0, 1) cell we find all the neighbours (which is 1), so here we find the cell (1, 1) is the only neighbour of cell (0, 1).
  So if we subtract 1 from the total Perimeter we have successfully removed a connected edge from 0 -> 1 row for 1st column, now 1 -> 0 for 1st column connection is still left to break (inorder to neglect the common edge).
  So, when we count neighbour of (1, 1) we find 4 neighbours (since 4 ones are connected to that (1,1) cell, so when we solve for (1,1) another edge from 1-> 0 row of 1st column will  be removed. Also, at cell (1,1) 4 edges will be removed for all its direction, because there are 4 neighbours of (1,1) cell.
  Similarly, for the rest.

  So, 28(total permeter) - 12(total neighbours of each 1's cell) = 16 Ans

*/

class Solution {
    
    public int islandPerimeter(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int onesCount_Or_squareCount = 0;
        int neighCount_Or_CommonEdgeCount = 0;
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == 1) { //If there is a square *
                    onesCount_Or_squareCount++;
                    
                    //* Count all it's neighbour sqaures 4 directionally
                    for(int d = 0; d < dir.length; d++) {
                        int x = i + dir[d][0];
                        int y = j + dir[d][1];
                        if(x >= 0 && x < row && y >= 0 && y < col && grid[x][y] == 1) {
                            neighCount_Or_CommonEdgeCount++;
                        }
                    }
                }
            }
        }
        //Total Perimeter = (4 * Number Of Squares) - Total Common Edge
        //where 1 common edge will be counted twice [One for myself and the other for my neighbour] 
        return 4 * onesCount_Or_squareCount - neighCount_Or_CommonEdgeCount;
    }
    
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

/*

  # DFS Approach

  Same as **Calculate number of leaf node in binary tree**
  For leaf nodes null is the mark similarly water(0) is the mark for boundary 
  
  Jitni Galat Calls (Out ofBoundary + water(0)) maroge uska sum is your answer.
  Matlab jitni baar call mar k water pe pahuchoge return 1
  Agar visited pe pahuche toh return 0
  
*/

//All galat calls i.e with base case calls Approach:-
//DFS Approach 2 With Direction matrix:-

class Solution {
    
    public int islandPerimeter(int[][] grid) {    
        int row = grid.length;
        int col = grid[0].length;
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == 1) {
                    return dfs(grid, i, j, dir);
                }
            }
        }
        return 0;
    }
    
    public static int dfs(int[][] grid, int i, int j, int[][] dir) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) return 1;
        if(grid[i][j] == -1) return 0;

        int perimeter = 0;
        grid[i][j] = -1;
        for(int d = 0; d < dir.length; d++) {
            int x = i + dir[d][0];
            int y = j + dir[d][1];
            perimeter += dfs(grid, x, y, dir);
        }
        return perimeter;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

//No galat calls i.e No base case calls Approach:-
//DFS Approach 1 With Direction matrix:-

class Solution {
    public int islandPerimeter(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == 1) {
                    // There is exactly 1 island so only 1 DFS call
                    return dfs(grid, i, j, dir);
                }
            }
        }
        return 0;
    }
    
    public static int dfs(int[][] grid, int i, int j, int[][] dir) {
        grid[i][j] = -1; // Visited
        int perimeter = 0;
        
        for(int d = 0; d < dir.length; d++) {    
            int x = i + dir[d][0];
            int y = j + dir[d][1];
            if(x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) { // Agar within boundary hai
                if(grid[x][y] == 1) perimeter += dfs(grid, x, y, dir); // Toh ya toh island milega => DFS call
                else if(grid[x][y] == 0) perimeter++;  // Ya fir water => +1
            }
            else perimeter++;  //OutOfBoundary => water => +1
        }
        return perimeter;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

//DFS
//Without direction matrix

class Solution {
    public int islandPerimeter(int[][] grid) {
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j] == 1){
                    return dfs(grid, i, j);
                }
            }
        }
        return 0;
    }
    
    public static int dfs(int[][] grid, int i, int j){
        if(i<0 || i>= grid.length || j<0 || j>= grid[0].length || grid[i][j] == 0) return 1;
        if(grid[i][j] == -1) return 0;

        grid[i][j] = -1;
        int result = 0;
        result += dfs(grid, i-1, j);
        result += dfs(grid, i, j+1);
        result += dfs(grid, i+1, j);
        result += dfs(grid, i, j-1);
        return result;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
