// https://leetcode.com/problems/number-of-paths-with-max-score/

class Solution {
    
    class Pair {
        int numWays;
        int pathSum;
        public Pair(int numWays, int pathSum) {
            this.pathSum = pathSum;
            this.numWays = numWays;
        }
    }
    
    int n, m;
    int mod = (int)(1e9 + 7);
    
    public int[] pathsWithMaxScore(List<String> board) {
        n = board.size();
        m = board.get(0).length();
        Pair[][] dp = new Pair[n][m];
        Pair ans = calculateAns(board, n - 1, m - 1, dp);
        if(ans.numWays == -1) return new int[] {0, 0};
        return new int[] {ans.pathSum, ans.numWays};
    }
   
    private Pair calculateAns(List<String> board, int i, int j, Pair [][] dp) { 
        if(i < 0 || j < 0) return new Pair(-1, -(int)1e9); // reached out of boundary
   
        char ch = board.get(i).charAt(j);
        if(ch == 'X') return new Pair(-1, -(int)1e9); // blocked cell
        if(ch == 'E') return new Pair(1, 0); // reached dest
        
        if(dp[i][j] != null) return dp[i][j];

        int ways = 0, maxSum; // ways is mandatory to initialize, since you will add in it
        Pair call1 = calculateAns(board, i, j - 1, dp); // left
        Pair call2 = calculateAns(board, i - 1, j - 1, dp); // top dia
        Pair call3 = calculateAns(board, i - 1, j, dp); // up
        
        maxSum = Math.max(call3.pathSum, Math.max(call1.pathSum, call2.pathSum));
        
        if(maxSum == -(int)1e9) {
            ways = -1;
        }
        // Suppose agar tino cell se maxSum say 10 hai, then kitne raste honge ?
        // Tino jitna v rasta leke aa rahe hai, sbka sum
        else {
            if(maxSum == call1.pathSum) ways = (ways + call1.numWays) % mod;
            if(maxSum == call2.pathSum) ways = (ways + call2.numWays) % mod;
            if(maxSum == call3.pathSum) ways = (ways + call3.numWays) % mod;
        }
        
        if(ch != 'S') maxSum = (maxSum + (ch - '0')) % mod; // Starting cell pe koi value nai h
        return dp[i][j] = new Pair(ways, maxSum);
    }
    
}
