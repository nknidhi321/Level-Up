// https://practice.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1

// Using Kahn's Algo
// Agar saare nodes queue se bhr aa gaye mtlb sbki indegree resolve ho gayi thi => No cycle 

class Solution {

    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        int n = adj.size();
        int[] indegree = new int[V];
        for(int i = 0; i < n; i++) {
            for(int nbr : adj.get(i)) {
                indegree[nbr]++;
            }
        }
        
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0) {
                queue.add(i);
            }
        }
        
        //In topBFS we add parents first and then child
        //And parent node should be as last as possible in the array as asked in question
        //So start filling ans from back so that parents can be as last as possible
        
        int count = 0;
        int[] ans = new int[V];
        while(!queue.isEmpty()) {
            int vtx = queue.poll();
            count++;
            
            for(int v : adj.get(vtx)) {
                if(--indegree[v] == 0) {
                    queue.add(v);
                }
            }
        }
        return count == V ? false : true;
    }
    
}

//==============================================================================================================================================

// GFG 
// Using DFS, keeping track of recursive stack

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
// Using DFS keeping track of process start and process end, serves the purpose of visited and recStack in the above code 

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
