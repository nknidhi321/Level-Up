// https://leetcode.com/problems/min-cost-to-connect-all-points/

// Kruskal's
```
class Solution {

    public class Pair {
        int u;
        int v;
        int w;
        public Pair(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
    
    int[] par;
    int[] size;
    
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        
        par = new int[n];
        size = new int[n];
        ArrayList<Pair> nodes = new ArrayList<>();
        
        for(int i = 0; i < n; i++) {
            par[i] = i;
            size[i] = 1;

            int[] pointA = points[i];
            int x1 = pointA[0];
            int y1 = pointA[1];
            
            for(int j = i + 1; j < n; j++) {
                int[] pointB = points[j];
                int x2 = pointB[0];
                int y2 = pointB[1];
                int w = Math.abs(x2 - x1) + Math.abs(y2 - y1); // manhattan dis
                nodes.add(new Pair(i, j, w)); // {u, v, w}
            }
        }
        
        Collections.sort(nodes, (a, b) -> a.w - b.w); 
        
        int ans = 0;
        for(Pair node : nodes) {
            int u = node.u;
            int v = node.v;
            int w = node.w;
            if(union(u, v)) ans += w;
        }
        return ans;
    }
    

    public int find(int x) {
        if(par[x] == x) return x;
        return par[x] = find(par[x]);
    }
    
    public boolean union(int x, int y) {
        int lx = find(x);
        int ly = find(y);
        if(lx != ly) {
            if(size[lx] > size[ly]) {
                par[ly] = lx;
                size[lx] += size[ly];
            } 
            else {
                par[lx] = ly;
                size[ly] += size[lx];
            }
            return true;
        }
        return false;
    }
    
}
```

-----------------------------------------------------------------------------

// Prims
```
class Solution {

    public class Pair {
        int vtx;
        int w;
        public Pair(int vtx, int w) {
            this.vtx = vtx;
            this.w = w;
        }
    }
    
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        ArrayList<Pair>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        
        for(int i = 0; i < n; i++) {
            int[] pointA = points[i];
            int x1 = pointA[0];
            int y1 = pointA[1];
            
            for(int j = i + 1; j < n; j++) {
                int[] pointB = points[j];
                int x2 = pointB[0];
                int y2 = pointB[1];
                int w = Math.abs(x2 - x1) + Math.abs(y2 - y1); // manhattan dis
                graph[i].add(new Pair(j, w)); // u-> {v, w}
                graph[j].add(new Pair(i, w)); // v-> {u, w}
            }
        }
        
        boolean[] vis = new boolean[n];
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.w - b.w);
        pq.add(new Pair(0, 0));
        
        int ans = 0;
        while(!pq.isEmpty()) {
            Pair p = pq.poll();
            if(vis[p.vtx]) continue;
            
            vis[p.vtx] = true;
            ans += p.w;
            for(Pair nbr : graph[p.vtx]) {
                if(!vis[nbr.vtx]) {
                    pq.add(nbr);
                }
            }
        }
        return ans;
    }
    
}
```
