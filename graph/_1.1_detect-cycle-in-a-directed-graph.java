// https://practice.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1

class Solution {

    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        // Mark all the vertices as not visited and
        // not part of recursion stack
        boolean[] visited = new boolean[V];
        boolean[] recStack = new boolean[V];
          
        // Call the recursive helper function to
        // detect cycle in different DFS trees
        for (int i = 0; i < V; i++) {
            if (isCyclicUtil(i, visited, recStack, adj)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isCyclicUtil(int i, boolean[] visited, boolean[] recStack, ArrayList<ArrayList<Integer>> adj) {
        // Mark the current node as visited and
        // part of recursion stack
        if (recStack[i]) return true;
        if (visited[i]) return false;
              
        visited[i] = true;
  
        recStack[i] = true;
        List<Integer> children = adj.get(i);
        for (Integer c: children) {
            if (isCyclicUtil(c, visited, recStack, adj)) {
                return true;
            }
        }                  
        
        recStack[i] = false; // backtrack
  
        return false;
    }
    
}
