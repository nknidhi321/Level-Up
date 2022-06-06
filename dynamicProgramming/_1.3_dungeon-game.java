// https://leetcode.com/problems/dungeon-game/

// NOTE : Max 1 unit of energy honi he chahiye, to play the game 
// So, take max(1, required energy)

class Solution {
    
    public int n, m;
    
    public int calculateMinimumHP(int[][] dungeon) {
        n = dungeon.length;
        m = dungeon[0].length;
        Integer[][] dp = new Integer[n][m];
        return calculateMinimumHP_Memo(0, 0, dp, dungeon);
    }
    
    public int calculateMinimumHP_Memo(int sr, int sc, Integer[][] dp, int[][] dungeon) {
        if(sr == n - 1 && sc == m - 1) return dp[sr][sc] = Math.max(1, (0 - dungeon[sr][sc]) + 1);
        
        if(dp[sr][sc] != null) return dp[sr][sc];
        
        int hori = Integer.MAX_VALUE, vert = Integer.MAX_VALUE;
        if(sr + 1 < n) hori = calculateMinimumHP_Memo(sr + 1, sc, dp, dungeon);
        if(sc + 1 < m) vert = calculateMinimumHP_Memo(sr, sc + 1, dp, dungeon);
        return dp[sr][sc] = Math.max(1, Math.min(hori, vert) - dungeon[sr][sc]);
    }
    
}
