// https://leetcode.com/problems/network-delay-time/
// Simple Dijkstra

class Solution {

    public int networkDelayTime(int[][] times, int N, int src) {

        ArrayList < int[] > [] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList < > (); // 0th idx is useless

        for (int[] arr: times) graph[arr[0]].add(new int[] { arr[1], arr[2] }); // u -> {vtx, weight}

        PriorityQueue < int[] > pqueue = new PriorityQueue < > ((a, b) - > a[1] - b[1]); // sorting on wsf
        pqueue.add(new int[] { src, 0 }); // src node is the starting point so wsf for starting node will be 0

        int[] dis = new int[N + 1];
        Arrays.fill(dis, (int) 1e8); // Filling this as max, since we have to get minimum cost/time
        dis[src] = 0; // src node pe pahuchne ka cost i.e src node ka wsf = 0

        boolean[] vis = new boolean[N + 1];

        while (!pqueue.isEmpty()) {
            int[] currNode = pqueue.poll();
            int currVtx = currNode[0];
            int currWsf = currNode[1];

            if (vis[currVtx]) continue; // Is dijkstra k question me visited ki zaroorat nahi hai, kuki dis wala array bacha lega
            // Par rakh loge vis toh koi harm nai hai, // Similarly path find krne k liye par array v maintain kar k chal saktey the

            vis[currVtx] = true;
            for (int[] nbr: graph[currVtx]) {
                int nbrVtx = nbr[0];
                int nbrWeight = nbr[1];
                if (currWsf + nbrWeight < dis[nbrVtx]) {
                    dis[nbrVtx] = currWsf + nbrWeight;
                    pqueue.add(new int[] { nbrVtx, currWsf + nbrWeight });
                    // NOTE : PQ me daalte he true mark nai kar sakte kuki koi dusra path aa sakta hai lower cost k saath
                }
            }
        }

        int maxTime = 0;
        for (int i = 1; i <= N; i++) {
            maxTime = Math.max(dis[i], maxTime);
        }

        // Agar koi node unvisited raha hoga => uske dis array me wsf aaj v (int)1e8 se initialized hoga
        return maxTime == (int) 1e8 ? -1 : maxTime;
    }

}
