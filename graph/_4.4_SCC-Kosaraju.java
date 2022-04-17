// https://practice.geeksforgeeks.org/problems/strongly-connected-components-kosarajus-algo/1/#

class Solution {

    public int kosaraju(int V, ArrayList<ArrayList<Integer>> adj) {

        boolean[] vis = new boolean[V];
                
        // Step 1 : DFS (Random)
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < V; i++) {
            if(!vis[i]) {
                DFS_1(i, vis, adj, stack);
            }
        }
        
        // Step 2 : Reverse Graph 
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < V; i++) graph.add(new ArrayList<>());
        
        for(int i = 0; i < V; i++) {
            ArrayList<Integer> nbrs = adj.get(i);
            for(int nbr : nbrs) {
                graph.get(nbr).add(i);
            }
        }        
        
        // Step 3 : DFS on stack from top
        int SCC = 0;
        vis = new boolean[V]; 
        while(!stack.isEmpty()) {
            int i = stack.pop();
            if(!vis[i]) {
                DFS_2(i, vis, graph);
                SCC++;
            }
        }
        return SCC;
    }
    
    public static void DFS_1(int src, boolean[] vis, ArrayList<ArrayList<Integer>> adj, Stack<Integer> stack) {
        vis[src] = true;
        
        ArrayList<Integer> nbrs = adj.get(src);
        for(int nbr : nbrs) {
            if(!vis[nbr]) {
                DFS_1(nbr, vis, adj, stack);
            }
        }
        stack.push(src);
    }
    
    public static void DFS_2(int src, boolean[] vis, ArrayList<ArrayList<Integer>> graph) {
        vis[src] = true;

        ArrayList<Integer> nbrs = graph.get(src);
        for(int nbr : nbrs) {
            if(!vis[nbr]) {
                DFS_2(nbr, vis, graph);
            }
        }
    }
    
}
