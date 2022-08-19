// https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/

class Solution {
    
    class Edge {
        int vtx;
        int time;
        public Edge(int vtx, int time) {
            this.vtx = vtx;
            this.time = time;
        }
    }
    
    class Pair implements Comparable<Pair>{
        int vtx;
        int wsf;
        int py;
        String s = "";
        public Pair(int vtx, int wsf, String s, int py) {
            this.vtx = vtx;
            this.wsf = wsf;
            this.s = s;
            this.py = py;
        }
        public int compareTo(Pair o){
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
        dis[0] = 0;

        int[] dp = new int[n];
        dp[0] = 1;

        boolean[] vis = new boolean[n];
        
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(0, 0, ""+0, 1));
       
       // boolean flag = false;
       // int minTime = Integer.MAX_VALUE;
        while(pq.size() != 0){
            Pair rem = pq.remove();
            //System.out.println(rem.s);
            // System.out.println(rem.vtx);
            // if(flag){
            //     if(rem.vtx == n-1 && rem.wsf == minTime){
            //        // System.out.println(rem.s);
            //         count++;
            //     }
            // }
            //if(rem.vtx == n - 1){
                //System.out.println(rem.s);
                
            //}
            // if(dis[rem.vtx] != -1) {
            //     if(dis[rem.vtx] == rem.wsf){
            //        // System.out.println(rem.vtx + " "+ rem.wsf + " " +dp[rem.py]);
            //         if(rem.vtx == 0) System.out.println("Hello 0 if");
            //         dp[rem.vtx] += dp[rem.py];
            //     }
            // }else{
            //     if(rem.vtx == 0) System.out.println("Hello 0");
            //     dis[rem.vtx] = rem.wsf;
            //     dp[rem.vtx] += dp[rem.py];
            // }
            // dis[rem.vtx] = Math.min(dis[rem.vtx], rem.wsf);
            // if(dis[rem.vtx] == rem.wsf) {
            //     dp[rem.vtx] += 1;
            // }
            
            if(vis[rem.vtx]) continue;
            
            vis[rem.vtx] = true;
            
            for(Edge nbr : graph[rem.vtx]) {               
                if(!vis[nbr.vtx]) {
                    if(dis[nbr.vtx] > rem.wsf + nbr.time) {
                        dis[nbr.vtx] = rem.wsf + nbr.time;
                        dp[nbr.vtx] = (dp[rem.vtx]) % mod;
                    }
                    else if(dis[nbr.vtx] == rem.wsf + nbr.time) {
                        dp[nbr.vtx] = (dp[nbr.vtx] + dp[rem.vtx]) % mod;
                    }
                    pq.add(new Pair(nbr.vtx, rem.wsf + nbr.time, rem.s + nbr.vtx, 0));
                }
            }
        }
        
//         for(int i = 0; i < n; i++) {
//            System.out.println(i + "-> cost-> " + dis[i] + " ways-> " + dp[i]);
//         }
        
        return dp[n - 1];
    }
    
}
