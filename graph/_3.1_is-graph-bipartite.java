//https://leetcode.com/problems/is-graph-bipartite/

/*
  NOTE: If every vertex of graph is in connected component then :
    1) Every non cyclic graph is bipartite, you can check this by BFS traversal on that graph.
    2) In every cyclic graph :
         a) If total number of vertex in a graph is even, then the graph is bipartite.
         b) If total number of vertex in a graph is odd, then the graph is not bipartite.*

    Here, the idea is to Mark source vertex with 0 and all it's neighbour with 1, and again mark neighbours neighbour with 0 and so on..
    If you find any vertex which is already visited and is marked with say 0 and you want to mark it with 1 then we can say graph is not bipartite.
    In simple terms, If there is conflict in marking a vertex then it is not bipartite.
*/

//Mine // BFS
//Checking visited and color before adding in the queue
//More efficient

class Solution {
//     Not visited : -1
//     Red : 0, visited
//     Green : 1, visited
    public boolean isBipartite(int[][] graph) {

        int[] visitedOrMarkedColor = new int[graph.length];
        Arrays.fill(visitedOrMarkedColor, -1);
        
        for(int i = 0; i < graph.length; i++) {
            if(visitedOrMarkedColor[i] == -1 && !isComponentBipartite(i, visitedOrMarkedColor, graph)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isComponentBipartite(int src, int[] visitedOrMarkedColor, int[][] graph) {
       
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(src);
        visitedOrMarkedColor[src] = 0;
        
        int color = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                int curr = queue.poll();
                
                for(int neigh : graph[curr]) {                        
                    if(visitedOrMarkedColor[neigh] != -1 && visitedOrMarkedColor[neigh] != color) {
                        return false;
                    }
                    if(visitedOrMarkedColor[neigh] == -1) {
                        queue.offer(neigh);
                        visitedOrMarkedColor[neigh] = color;
                    }
                }
            }
            color = (color + 1) % 2;
        }
        return true;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

//Rajneesh //BFS
//Checking visited and color after adding in the queue

class Solution {
//     Not visited : -1
//     Red : 0, visited
//     Green : 1, visited
   public boolean isBipartite(int[][] graph) {

        int[] visitedOrMarkedColor = new int[graph.length];
        Arrays.fill(visitedOrMarkedColor, -1);
        
        for(int i = 0; i < graph.length; i++){
            if(visitedOrMarkedColor[i] == -1 && !isComponentBipartite(i, visitedOrMarkedColor, graph)){
                return false;
            }
        }
        return true;
    }
    
    public static boolean isComponentBipartite(int src, int[] visitedOrMarkedColor, int[][] graph){
       
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(src);
      
        int color = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size-- > 0){
                int curr = queue.poll();
                
                if(visitedOrMarkedColor[curr] != -1) {
                    if(visitedOrMarkedColor[curr] != color){
                        return false;
                    }
                    continue;
                }
                
                visitedOrMarkedColor[curr] = color;
                for(int neigh : graph[curr]){
                    if(visitedOrMarkedColor[neigh] == -1){
                        queue.offer(neigh);
                    }
                }
            }
            color = (color + 1) % 2;
        }
        return true;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

//Mine //DFS

class Solution {
//     Not visited : -1
//     Red : 0, visited
//     Green : 1, visited
    public boolean isBipartite(int[][] graph) {
        
        int[] visitedOrMarkedColor = new int[graph.length];
        Arrays.fill(visitedOrMarkedColor, -1);  // Marking not visited
            
        for(int i = 0; i < graph.length; i++) { // Checking for all component
            if(visitedOrMarkedColor[i] == -1 && !isComponentBipartite(i, 0, visitedOrMarkedColor, graph)) {
                return false;
            }
        }
        return true;
    }
    public static boolean isComponentBipartite(int src, int color, int[] visitedOrMarkedColor, int[][] graph) {
        visitedOrMarkedColor[src] = color; // Marking visited or assigned color
        color = (color + 1) % 2; // Toggling to check for neigh's
        
        for(int neigh : graph[src]) {                        
            // Already visited + different color => Conflict
            if(visitedOrMarkedColor[neigh] != -1 && visitedOrMarkedColor[neigh] != color) { 
                return false;
            } 
            if(visitedOrMarkedColor[neigh] == -1) { // Not visited, so visit it and pass the toggled color
                if(!isComponentBipartite(neigh, color, visitedOrMarkedColor, graph)) {
                    return false;
                }
            }
        }
        return true;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
