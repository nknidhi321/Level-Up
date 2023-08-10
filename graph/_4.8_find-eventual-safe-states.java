// https://leetcode.com/problems/find-eventual-safe-states/

/*
Initially the terminal nodes are those who have outdegree 0 
but after reversal the terminal nodes becomes those which have indegree 0 
so we can apply Kahn's algo to find all the nodes connected to it  which have linear dependency on the terminal node or is on the path which leads to terminal node 
so if the nodes is a part of a cycle or points to a cycle , that path cannot lead to terminal node as each node in that  path will have cyclic dependency
*/

/*
NOTE : 
1) A cycle will always remains cycle even if you reverse the edges
2) "Few Nodes(Example 2, 4 was also part of cycle but it is safe node how because it had indegree of 0)" which are part of cycle, they can never be resolved, because they cannot have linear dependency
3) So resolve wahi honge jinki linear dependency h
4) Note you have give output safe node and not terminal node
5) Aapse nikalta hua path k nodes ek cycle ka part ho skte h, but aap safe node v ho how ??
6) Kuki edges ulte kr diye gye the, aapse kvi koi path nikla he nahi, that's why you are safe node
*/

class Solution {
    
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        
        // Reverse the graph edges
        ArrayList<Integer>[] reverseGraph = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            reverseGraph[i] = new ArrayList<>();
        }
        for(int i = 0; i < n; i++) {
            int[] nbrs = graph[i];
            for(int nbr : nbrs) {
                reverseGraph[nbr].add(i);
            }
        }
        
        // Cal. indegree
        int[] indegree = new int[n];
        for(int i = 0; i < n; i++) {
            for(int nbr : reverseGraph[i]) {
                indegree[nbr]++;
            }
        }
        
        // Add 0 indegree in queue
        LinkedList<Integer> q = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            if(indegree[i] == 0) {
                q.add(i);
            }
        }
        
        // Kahn's        
        List<Integer> ans = new ArrayList<>();
        while(!q.isEmpty()) {
            int node = q.remove();
            ans.add(node);
            
            for(int nbr : reverseGraph[node]) {
                if(--indegree[nbr] == 0) {
                    q.add(nbr);
                }
            }
        }
        
        // As expected in sorted order
        Collections.sort(ans);
        return ans;
    }
    
}
