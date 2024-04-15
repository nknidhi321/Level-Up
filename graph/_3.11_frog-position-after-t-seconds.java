// https://leetcode.com/problems/frog-position-after-t-seconds/

/*
    Simple BFS when we found target during BFS search, we can immediately return the result
    There are 3 cases to consider:
    t == 0, we return probability[target]
    t > 0 && target has no children. Frog will be trapped at target and keep jumping, we return probability[target]
    t > 0 && target has children. Frog will jump to the children node, we return 0.0
*/

class Solution {
    
    static class Pair {
        int node; // node
        double probability; // probability to reach that node
        Pair(int node, double probability) {
            this.node = node; 
            this.probability = probability; 
        }
        public String toString() {
            return this.node + " " + this.probability;
        }
    } 
    
    public double frogPosition(int n, int[][] edges, int t, int target) {
        if(n == 1 && t >= 1 && target == 1) return 1f; 
        
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        for(int[] edge : edges) { 
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v); // u -> v
            graph[v].add(u); // v -> u
        }
        
        LinkedList<Pair> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        queue.add(new Pair(1, 1f));
        visited[1] = true;
        
        while(!queue.isEmpty() && t >= 0) {
            int size = queue.size();
            while(size-- > 0) {
                Pair pair = queue.poll();
                int unvisitedNbrs = 0;
                for(int nbr : graph[pair.node]) {
                    if(!visited[nbr]) {
                        unvisitedNbrs++; // visited nbrs pe kvi nai jaaoge, so don't count them in your probability
                    }
                }
                for(int nbr : graph[pair.node]) {
                    if(pair.node == target) { // Found target
                        if(t == 0 || unvisitedNbrs == 0) return pair.probability; // keep jumping on same node
                        else return 0.0; // passing the target, so can never reach back to the visited target
                    }
                    if(!visited[nbr]) {
                        queue.add(new Pair(nbr, pair.probability / unvisitedNbrs));
                        visited[nbr] = true;
                    }
                }
            }
            t--;
        }
        return 0f; // target was very far, and timer was very less, cannot reach
    }
    
}
