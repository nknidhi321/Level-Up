// https://practice.geeksforgeeks.org/problems/topological-sort/1#

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
