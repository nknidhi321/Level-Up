// https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/

// DFS
// Considered it as a graph
```
class Solution {
    
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        ArrayList<Integer>[] graph = new ArrayList[n];
        
        for(int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }
        
        boolean[] vis = new boolean[n];
        int time = dfs(0, hasApple, vis, graph);
        
        // Tumhara root node v apne parent k liye +2 bhej dega, 
        // apne se aane jaane ka time, agar tumse niche se kuch time aara hoga tab
        // Islye usko -2 kar do final answer me
        return time > 0 ? time - 2 : time;   
    }
    
    public static int dfs(int node, List<Boolean> hasApple, boolean[] vis, ArrayList<Integer>[] graph) {
        vis[node] = true;
        
        int time = 0;
        for(int child : graph[node]) {
            if(!vis[child]) {
                time += dfs(child, hasApple, vis, graph);
            }
        }
        
        // Niche se kuch apples aa rahe hai, usko parent tak pahuchana hai so +2 mere se aane jaane ka time  
        // agar mere pe v apples honge tab v mko +2 time he lgega apne parent tak pahuchane me
        if(time > 0) return time + 2; // NOTE : root node v +2 bhej dega yaha se
        
        else { // (time == 0) => Niche se koi apples nahi aaya ya fir tumhara koi child nahi tha
            if(hasApple.get(node)) return 2; // Niche se koi apple nahi aaya and tumhare node pe apple hai
            else return 0; // tumhara koi child nahi tha, leaf node will send 0
        }
    }
    
}
```

-----------------------------------------------------------------------------------------------------------------------

// BFS
// Considered it as a tree
```
class Solution {
    
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
//        ArrayList<Integer>[] graph = new ArrayList[n];
        
//        for(int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        
        int[] par = new int[n];
        Arrays.fill(par, -1);
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            // graph[u].add(v);
            // graph[v].add(u);
            if(par[v] == -1) par[v] = u;
            else par[u] = v; // To handle one special case
            
        }
        
        int m = hasApple.size();
        boolean [] vis = new boolean[n];
        LinkedList<Integer> queue = new LinkedList<>();
        for(int i = 0; i < m; i++) {
            if(hasApple.get(i)) {
                queue.add(i);
                vis[i] = true;
            }
        }

        int timer = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                int node = queue.removeFirst();
                if(node == 0) break;
                else timer += 2;
                
                if(!vis[par[node]]) { 
                    queue.add(par[node]);
                    vis[par[node]] = true;
                }
            }
        }
        
        return timer;
    }
    
}
```
