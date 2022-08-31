// https://www.lintcode.com/problem/515/

// Tabulation
public class Solution {
 
    public int minCost(int[][] cost) {
        if(cost.length == 0) return 0;

        int n = cost.length;
        for(int i = 1; i < n; i++) {
            cost[i][0] += Math.min(cost[i - 1][1], cost[i - 1][2]);
            cost[i][1] += Math.min(cost[i - 1][0], cost[i - 1][2]);
            cost[i][2] += Math.min(cost[i - 1][0], cost[i - 1][1]);
        }
        return Math.min(cost[n - 1][0], Math.min(cost[n - 1][1], cost[n - 1][2]));
    }
}

//---------------------------------------------------------------------------------------

// Recursively
// TLE
public class Solution {
    
    class Triplet {
        int A;
        int B;
        int C;
        Triplet(int A, int B, int C) {
            this.A = A;
            this.B = B;
            this.C = C;
        }
    }

    public int minCost(int[][] costs) {
        int n = costs.length;
        if(costs == null || n == 0) return 0;

        Triplet tri = minCost(n, costs);
        return Math.min(tri.C, Math.min(tri.A, tri.B));
    }

    public Triplet minCost(int n, int[][] costs) {
        if(n == 1) return new Triplet(costs[0][0], costs[0][1], costs[0][2]);
        
        Triplet tri = minCost(n - 1, costs);
        int A = Math.min(tri.B, tri.C) + costs[n - 1][0];
        int B = Math.min(tri.A, tri.C) + costs[n - 1][1];
        int C = Math.min(tri.A, tri.B) + costs[n - 1][2];
        System.out.println(n);
        return new Triplet(A, B, C);
    }

}
