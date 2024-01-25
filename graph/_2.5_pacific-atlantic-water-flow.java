// https://leetcode.com/problems/pacific-atlantic-water-flow/

// DFS
// Making DFS calls only from the boundary cells
// Take 2 separate matrix one for atlantic and one for pacific
// Now take out the commons 

class Solution {
    int row;
    int col;
    int dir[][];
    
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        row = heights.length; // row
        col = heights[0].length; // col
        List<List<Integer>> ans = new ArrayList<>();
        dir = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int[][] visAtlantic = new int[row][col];
        int[][] visPacific = new int[row][col];
        
        // row and col is different so making 2 loops separately
        // left and right boundary
        for(int i = 0; i < row; i++) {
            DFS(i, 0, visPacific, heights); // from left(pacific) make DFS
            DFS(i, col - 1, visAtlantic, heights); // from right(atlantic) make DFS
        }
        // top and bottom boundary
        for(int i = 0; i < col; i++) {
            DFS(0, i, visPacific, heights); // from top(pacific) make DFS
            DFS(row - 1, i, visAtlantic, heights); // from bottom(atlantic) make DFS
        }
        
        // now check common cell marked in both visAtlantic && visPacific
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                List<Integer> cell = new ArrayList<>();
                if(visAtlantic[i][j] == 1 && visPacific[i][j] == 1) {
                    cell.add(i);
                    cell.add(j);
                    ans.add(cell);
                }
            }
        }
        return ans;
    }
    
    // simple DFS
    public void DFS(int i, int j, int[][] visited, int[][] heights) {
        visited[i][j] = 1;
        for(int d = 0; d < dir.length; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];
            if((r >= 0 && c >= 0 && r < row && c < col && visited[r][c] != 1 && heights[r][c] >= heights[i][j])) {
                DFS(r, c, visited, heights);
            }
        }
    }
    
}

---------

// O(n * m) ^ 2
// From every cell make DFS call and keep track if are going out of boundary
// from (i < 0 || j < 0) it's pacific ocean and if (i > row || j > col) then it's pacific ocean
// Just store that you found atlantic or pacific
// You need visited because you are going in all 4 direction
// try finding 2 possible paths from a cell which can lead you to atlantic or pacific ocean respectively

class Solution {
    int row;
    int col;
    int dir[][];
    
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        row = heights.length; // row
        col = heights[0].length; // col
        List<List<Integer>> ans = new ArrayList<>();
        dir = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                List<Integer> cell = new ArrayList<>();
                Boolean[] atlanticPacific = new Boolean[2]; // {atlantic, pacific}
                int[][] visited = new int[row][col];
                DFS(i, j, atlanticPacific, visited, heights);
                if(atlanticPacific[0] != null && atlanticPacific[0] && atlanticPacific[1] != null && atlanticPacific[1]) {
                    cell.add(i);
                    cell.add(j);
                    ans.add(cell);
                }
            }
        }
        return ans;
    }
    
    public void DFS(int i, int j, Boolean[] atlanticPacific, int[][] visited, int[][] heights) {
        if(i < 0 || j < 0) { // Found Pacific
            atlanticPacific[0] = true;
            return;
        } 
        if(i >= row || j >= col) { // Found Atlantic
            atlanticPacific[1] = true; 
            return;
        }
        
        visited[i][j] = -1; // mark visited
        
        for(int d = 0; d < dir.length; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];
            if((r < 0 || c < 0 || r >= row || c >= col) || (visited[r][c] != -1 && heights[r][c] <= heights[i][j])) {
                DFS(r, c, atlanticPacific, visited, heights);
                if(atlanticPacific[0] != null && atlanticPacific[0] && atlanticPacific[1] != null && atlanticPacific[1]) {
                    break;
                }
            }
        }
    }
    
}
