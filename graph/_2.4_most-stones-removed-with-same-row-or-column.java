// Memory Limit exceeded
// But correct approach
// Simple DFS, just make sure you don't call on your parent since it's 4 dir
// Keep track of the last encountered row and col where there was a stone
```
class Solution {
    int n;
    int m;
    public int removeStones(int[][] stones) {
        
        // To create matrix
        int maxOnXAxis = Integer.MIN_VALUE;
        int maxOnYAxis = Integer.MIN_VALUE;
        
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0},{0, -1}};
        
        for(int[] stone : stones) {
            maxOnXAxis = Math.max(maxOnXAxis, stone[0]);
            maxOnYAxis = Math.max(maxOnYAxis, stone[1]);
        }
        n = maxOnXAxis + 1;
        m = maxOnYAxis + 1;
        
        long [][] arr = new long[n][m];
        for(int[] stone : stones) {
            arr[stone[0]][stone[1]] = 1;
        }
        
        int ans = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(arr[i][j] == 1) {
                    ans += (removeStones(i, j, i, j, dir, arr, -1, -1) - 1);
                }
            }
        }
        return ans;
    }
    
    public int removeStones(int i, int j, int prevStoneRow, int prevStoneCol, int[][] dir, long[][] arr, int pi, int pj) {
        long originalValue = arr[i][j];
        if(originalValue == 1) { // If you are stone, you become the last stone
            prevStoneRow = i;
            prevStoneCol = j;
        }
        if(originalValue == 1) // visited
            arr[i][j] = -1;
        
        int size = 0;
        for(int d = 0; d < dir.length; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];
            if(r >= 0 && r < n && c >= 0 && c < m && arr[r][c] != -1 && !(r == pi && c == pj) && (prevStoneRow == r || prevStoneCol == c)) {
                size += removeStones(r, c, prevStoneRow, prevStoneCol, dir, arr, i, j);
            }
        }
        return originalValue == 1 ? size + 1 : size;
    }
}
```
