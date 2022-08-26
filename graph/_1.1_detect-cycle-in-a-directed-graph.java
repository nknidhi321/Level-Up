// https://practice.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1

// GFG
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

//================================================================================================================================================

// Shamii
class Solution {

    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] begin = new boolean[V];
        boolean[] end = new boolean[V];
        for(int i = 0; i < V; i++) {
            if(isCyclicUtil(i, begin, end, adj)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isCyclicUtil(int i, boolean[] begin, boolean[] end, ArrayList<ArrayList<Integer>> adj) {
        if(begin[i] && end[i]) return false; 
        if(begin[i] && !end[i]) return true;    
              
        begin[i] = true;
        List<Integer> children = adj.get(i);
        for(Integer c: children) {
            if(isCyclicUtil(c, begin, end, adj)) {
                return true;
            }
        }                  
        end[i] = true;

        return false;
    }
    
}
