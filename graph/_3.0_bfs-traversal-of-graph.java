// https://practice.geeksforgeeks.org/problems/bfs-traversal-of-graph/1

// Cyclic BFS
// poll kiya tab pooch lo agar visited ho toh uske child ko q dobara daaloge
// Agar vis nahi ho toh apna kaam kar lo and vis mark kar do, and queue me daalne se pehle pooch lo visited ho ya nahi, agar visited nahi ho tab he daalo queue me.

class Solution {

    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> ans = new ArrayList<>();

        boolean[] vis = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                int vtx = queue.poll();
                if(vis[vtx]) continue;
                
                vis[vtx] = true;
                ans.add(vtx);
                
                ArrayList<Integer> nbrs = adj.get(vtx);
                for(int nbr : nbrs) {
                    if(!vis[nbr]) {
                        queue.add(nbr);
                    }
                }
            }
        }
        return ans;
    }
    
}

----------------------------------------------------------------------------------------------------------------------------------------------------

// Non cyclic BFS
// Kisi v node pe visit krte he mark kar do, and queue me daalne se pehle he pooch lo visited ho ya nahi, agar visited nahi ho tab he daalo queue me.

class Solution {

    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> ans = new ArrayList<>();

        boolean[] vis = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        vis[0] = true;
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                int vtx = queue.poll();
                ans.add(vtx);
                
                ArrayList<Integer> nbrs = adj.get(vtx);
                for(int nbr : nbrs) {
                    if(!vis[nbr]) {
                        queue.add(nbr);
                        vis[nbr] = true;
                    }
                }
            }
        }
        return ans;
    }
    
}

-----------------------------------------------------------------------------------------------------------------------------------------------------------
