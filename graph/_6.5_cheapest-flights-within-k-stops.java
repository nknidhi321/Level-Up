// https://leetcode.com/problems/cheapest-flights-within-k-stops/
// You will have to use Bellman Ford inorder to pass this question
// Not implemented here

// Simple Dijkstra // Correct but TLE

// Note : If you use Dijkstra, you cannot use dis[] array kuki k ka check usko sahi answer nai laane dega, 
// If you use dis[] array then, jab tak poora algo process nai ho jata cannot say ki ye minimum hoga ya nahi
// And yaha poora algo chalane ka koi sense nai hai because of k ka check

// But if you do not use dis[] then koi v node jab niklega then you can be sure ki ye minimum cost k saath he nikla hai
// So here we are not using dis[]

// You cannot use vis here why ??

class Solution {
    
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
    
        // Initialize graph
        ArrayList<int[]>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        
        // Add edges
        for(int[] flight : flights) graph[flight[0]].add(new int[] {flight[1], flight[2]}); // u -> {v, w}
        
        PriorityQueue<int[]> pqueue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pqueue.offer(new int[] {src, 0, 0});
        
        while(!pqueue.isEmpty()) {
            int[] vtxArray = pqueue.poll();
            int vtx = vtxArray[0];
            int costSoFar = vtxArray[1];
            int stop = vtxArray[2];
            
            if(vtx == dst) return costSoFar;
            
            if(stop > k) continue; // Agar saare k kharch ho gaya hai toh stop + 1 daalne ka koi sense nai hai 
            
            for(int[] e : graph[vtx]) {
                pqueue.offer(new int[] {e[0], e[1] + costSoFar, stop + 1});
            }
        }
        return -1;
    }
}
