// https://leetcode.com/problems/maximum-total-importance-of-roads/

// Dry run
// 0 -> {(0, 1), (0, 2)}  degree = 2
// 1 -> {(1, 0), (1, 2), (1, 3)}    degree = 3
// 2 -> {(2, 1), (2, 0), (2, 3), (2,4)}   degree = 4
// 3 -> {(3, 1), (3,2)}   degeree = 2 
// 4 -> {(4, 2)}   degree = 1

class Solution {
    
    static class Pair {
        long node;
        long degree;
        
        Pair(long node, long degree) {
            this.node = node;
            this.degree = degree;
        }
        
        public String toString() {
            return node + " " + degree;
        }
    }
    
    public long maximumImportance(int n, int[][] roads) {
        // this is just denoting connection of nodes not indegree of Khans Algo as it is applied on DAG
        long[] degree = new long[n]; 
        for(int[] road : roads) {
            int u = road[0];
            int v = road[1];
            degree[u]++;
            degree[v]++;
        }
        
        Pair[] pairs = new Pair[n];
        for(int i = 0; i < n; i++) {
            pairs[i] = new Pair(i, degree[i]); // {node, degree}
        }
        
        // desc order sorting based on degree
        Arrays.sort(pairs, (a, b) -> Long.compare(b.degree, a.degree)); 
        
        // Assign maximum importance to each node based on degree
        long importance = n;
        Map<Long, Long> map = new HashMap<>();
        for(Pair pair : pairs) { // fetching nodes in sorted order from pair acc to degree
            map.put(pair.node, importance--); 
        }
        
        // cal total importance
        long sum = 0;
        for(int[] road : roads) {
            int u = road[0];
            int v = road[1];
            sum += map.get((long)u);
            sum += map.get((long)v);
        }
        return sum;
    }
}
