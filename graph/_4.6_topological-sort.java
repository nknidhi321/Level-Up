// https://practice.geeksforgeeks.org/problems/topological-sort/1#

// TopoDFS, 1st step of Kosaraju

class Solution {

    public static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
    
       boolean[] vis = new boolean[V];
                
        // Step 1 : DFS (Random) of Kosaraju
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < V; i++) {
            if(!vis[i]) {
                DFS_1(i, vis, adj, stack);
            }
        }

        // Itertate from back, because u is at the top of the stack 
        int i = 0; 
        int[] ans = new int[V];
        while(!stack.isEmpty()) {
            ans[i++] = stack.pop();
        }   
        return ans;
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
    
}

//================================================================================================================================================================

// Kahn's Algo, kind of BFS

class Solution {

    public static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
    
        int[] indegree = new int[V];
        for(int i = 0; i < adj.size(); i++) {
            for(int nbr : adj.get(i)) {
                indegree[nbr]++;
            }
        }

        int ansIdx = 0;                
        int[] ans = new int[V];
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0)  {
                queue.add(i);
                ans[ansIdx++] = i;
            }
        } 
    
        while(!queue.isEmpty()) {
            int vtx = queue.poll();
            
            for(int nbr : adj.get(vtx)) {
                if(--indegree[nbr] == 0) {
                    queue.add(nbr);
                    ans[ansIdx++] = nbr;
                }
            }
        }
        return ans;
    }
    
}


//================================================================================================================================================================
