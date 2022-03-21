// https://leetcode.com/problems/knight-probability-in-chessboard/ 

// Using HashMap, Code Bix 

Probability will be same for a dp cell "only" when I reach to a cell with "same x th move" + same row col obviously.
So just keep track of row, col and move of that cell, so just keep String for that 
Ex :     i + "n" + j + "s" + move
where i & j is row & col respectively and
"n" & "s" is char just to segreagate, can use any char or sequence of chars
This idea will make the string unique.

```
class Solution {
    
    public double knightProbability(int n, int move, int row, int col) {
        
        int[][] dir = { {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1} };
        Map<String, Double> map = new HashMap<>();
        return knightMoves(row, col, move, n, dir, map);   
    }
    
    public static double knightMoves(int i, int j, int move, int n, int[][] dir, Map<String, Double> map) {
        if(move == 0) return 1;
        
        if(map.containsKey(i + "n" + j + "s" + move)) return map.get(i + "n" + j + "s" + move);
        
        double probability = 0.0;
        for(int d = 0; d < dir.length; d++) {
            int x = i + dir[d][0];
            int y = j + dir[d][1];
            
            if(x >= 0 && x < n && y >= 0 && y < n) {
                probability += knightMoves(x, y, move - 1, n, dir, map) / 8.0;
            }
        }
        map.put(i + "n" + j + "s" + move, probability);
        return probability;
    } 
}
```

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Memoization Using 3D DP
// Discuss Section
// More efficient

Why 3D DP ??
See there are 3 changing variables, and you will get your answer when move will become 0 from k
So,   [0, k]  =>  (k - 0 + 1)   => k + 1 
where k == move
  
```
class Solution {
    
    public double knightProbability(int n, int move, int row, int col) {
        
        int[][] dir = { {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1} };
        double[][][] dp = new double[n][n][move + 1];
        
        return knightMoves(row, col, move, n, dir, dp);   
    }
    
    public static double knightMoves(int i, int j, int move, int n, int[][] dir, double[][][] dp) {
        if(move == 0) return dp[i][j][move] = 1;
        
        if(dp[i][j][move] != 0.0) return dp[i][j][move];
            
        double probability = 0.0;
        for(int d = 0; d < dir.length; d++) {
            int x = i + dir[d][0];
            int y = j + dir[d][1];
            
            if(x >= 0 && x < n && y >= 0 && y < n) {
                probability += knightMoves(x, y, move - 1, n, dir, dp) / 8.0;
            }
        }
        return dp[i][j][move] = probability;
    } 
}
```

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
