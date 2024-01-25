// https://leetcode.com/problems/pacific-atlantic-water-flow/
// DFS
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
