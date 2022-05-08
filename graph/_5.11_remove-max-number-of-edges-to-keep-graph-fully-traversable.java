// https://leetcode.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/
// Kruskal's

```
class Solution {
    
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        
        // Agar type 3 pe sort karenge toh dono Alice Bob us edge ko use kar paaenge
        // And aapko max. number of edges remove krne hai, so sort on type 3
        Arrays.sort(edges, (a, b) -> b[0] - a[0]); 
        
        // Why n+1? 1 based indexing
        int[] alicePar = new int[n + 1];
        int[] aliceRank = new int[n + 1];
        
        int[] bobPar = new int[n + 1];
        int[] bobRank = new int[n + 1];
        
        // Par && Rank initialization
        for(int i = 0; i <= n; i++) { // 0th idx is useless
            alicePar[i] = bobPar[i] = i;
            aliceRank[i] = bobRank[i] = 1;
        }
        
        int removeEdge = 0; // Jab jab cycle bnega 
        int mergeAlice = 0; // Jab Alice me merging hogi
        int mergeBob = 0; // Jab Bob me merging hogi
        
        for(int[] edge : edges) {
            
            int type = edge[0]; int u = edge[1]; int v = edge[2];
        
            if(type == 3) { // type == 3 => Both Alice and Bob
                boolean isCycleInAlice = union(u, v, alicePar, aliceRank);
                boolean isCycleInBob = union(u, v, bobPar, bobRank);
                if(!isCycleInAlice) mergeAlice++;
                if(!isCycleInBob) mergeBob++;

                // Na Alice me merging huyi na Bob me, mtlb dono me cycle bnri thi
                if(isCycleInAlice && isCycleInBob) removeEdge++;
                
                // NOTE : Agar kisi ek me v merging huyi,
                // then aap us edge ko nahi hata saktey
            }
            else if(type == 2) { // type == 2 => Bob
                boolean isCycleInBob = union(u, v, bobPar, bobRank);
                if(!isCycleInBob) mergeBob++;
                
                // No merging in Bob
                if(isCycleInBob) removeEdge++;
            }
            else { // type == 1 => Alice
                boolean isCycleInAlice = union(u, v, alicePar, aliceRank);
                if(!isCycleInAlice) mergeAlice++;

                // No merging in Alice
                if(isCycleInAlice) removeEdge++;
            }
        }
        
        // Agar Alice && Bob dono apna apna MST bna paa raha hai then you can travel on entire graph
        // Why ? Because Alice && Bob dono unhi n nodes ko use kar k apna MST bnaenge
        if(mergeAlice == n - 1 && mergeBob == n - 1) return removeEdge;
        else return -1;
    }
    
    public int find(int x, int[] par) {
        if(par[x] == x) return x;
        return par[x] = find(par[x], par);
    }
    
    public boolean union(int x, int y, int[] par, int[] height) {
        int lx = find(x, par);
        int ly = find(y, par);
        if(lx != ly) { // dono nbr hai and different leader aaya merge them
            if(height[lx] > height[ly]) {
                par[ly] = lx;
            }
            else if(height[ly] > height[lx]) {
                par[lx] = ly;
            }
            else {
                par[lx] = ly;
                height[ly]++;
            }
            return false;
        }
        else { // If the leader is same, they will form cycle => extra edge, so remove
            return true;
        }
    }
    
}
```
-----------------------------------------------------------------------------------------
