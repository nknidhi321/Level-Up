// https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/

// Dijikstra
// Updating dpCount ans while exploring neighbours
// This is working because pq me saare cycle waale paths add ho he rahe hai,
// bs updation dp array me ho raha hai
// NOTE : This is "not without Cycle", this is "with Cycle"
// Dijikstra will always require cycle and it is being taken care by the pq
```
class Solution {
    
    class Edge {
        int vtx;
        int w;
        public Edge(int vtx, int w) {
            this.vtx = vtx;
            this.w = w;
        }
    }
    
    class Pair implements Comparable<Pair> {
        int vtx;
        int wsf;
        public Pair(int vtx, int wsf) {
            this.vtx = vtx;
            this.wsf = wsf;
        }
        public int compareTo(Pair o) {
            return this.wsf - o.wsf;
        }
    }

    int mod = (int)1e9 + 7;
    
    public int countPaths(int n, int[][] roads) {
        ArrayList<Edge>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        
        for(int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int w = road[2];
            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
        }
        
        int[] dis = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);

        int[] dp = new int[n];
        dp[0] = 1;

        boolean[] vis = new boolean[n];
        
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(0, 0));
       
        while(!pq.isEmpty()) {
            Pair curr = pq.remove();
            
            if(vis[curr.vtx]) continue;
            
            vis[curr.vtx] = true;
            
            for(Edge nbr : graph[curr.vtx]) {               
                if(!vis[nbr.vtx]) {
                    if(dis[nbr.vtx] > curr.wsf + nbr.w) {
                        dis[nbr.vtx] = curr.wsf + nbr.w;
                        dp[nbr.vtx] = dp[curr.vtx];
                    }
                    else if(dis[nbr.vtx] == curr.wsf + nbr.w) {
                        dp[nbr.vtx] = (dp[nbr.vtx] + dp[curr.vtx]) % mod;
                    }
                    pq.add(new Pair(nbr.vtx, curr.wsf + nbr.w));
                }
            }
        }
        
//         for(int i = 0; i < n; i++) {
//            System.out.println(i + "-> cost-> " + dis[i] + " ways-> " + dp[i]);
//         }
        
        return dp[n - 1];
    }
    
}
```

---------------------------------------------------------------------------------------------------------

// Dijikstra
// Nikalte waqt dpCount ans bna rahe hai

```
class Solution {
    
    class Edge {
        int vtx;
        int w;
        public Edge(int vtx, int w) {
            this.vtx = vtx;
            this.w = w;
        }
    }
    
    class Pair implements Comparable<Pair> {
        int vtx;
        int wsf;
        int par;
        public Pair(int vtx, int wsf, int par) {
            this.vtx = vtx;
            this.wsf = wsf;
            this.par = par;
        }
        public int compareTo(Pair o) { // Sort in asc. order
            return this.wsf - o.wsf;
        }
    }

    int mod = (int)1e9 + 7;
    
    public int countPaths(int n, int[][] roads) {
        ArrayList<Edge>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        
        for(int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int w = road[2];
            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
        }
        
        int[] dis = new int[n];
        Arrays.fill(dis, (int)1e9);

        int[] dp = new int[n];
        boolean[] vis = new boolean[n];
        
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(0, 0, -1));
       
        while(!pq.isEmpty()) {
            Pair curr = pq.remove();
            
            if(dis[curr.vtx] == (int)1e9) { // Pehli baar nikla, min wt. k saath he nikalega
                dis[curr.vtx] = curr.wsf;
                if(curr.par == -1) dp[curr.vtx] = 1; // 0th node ka 1 rasta hai
                else dp[curr.vtx] = dp[curr.par]; // Any other node
            }
            else if(dis[curr.vtx] == curr.wsf) { // Agar is min wt. k jitna aur wt. hai, update ans
                dp[curr.vtx] = (dp[curr.par] + dp[curr.vtx]) % mod;
            }
            
            if(vis[curr.vtx]) continue;
            
            vis[curr.vtx] = true;
            for(Edge nbr : graph[curr.vtx]) {               
                if(!vis[nbr.vtx]) {
                    pq.add(new Pair(nbr.vtx, curr.wsf + nbr.w, curr.vtx));
                }
            }
        }
        
        // for(int i = 0; i < n; i++) {
        //    System.out.println(i + "-> cost-> " + dis[i] + " ways-> " + dp[i]);
        // }
        
        return dp[n - 1];
    }
    
}
```
--------------------------------------------------------------------------------------------------------
