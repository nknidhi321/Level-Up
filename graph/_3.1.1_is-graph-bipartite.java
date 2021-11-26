//https://leetcode.com/problems/is-graph-bipartite/

	
	Bipartite
	NOTE : If Graph is Non Bipartite => there is odd length cycle
		
		
	
									Odd
							----------------------------------> Non Bipartite
							|
				       BFS		|
				------------------> Cyclic Graph   
				|			|
				|			|
				|			----------------------------------> Bipartite
				|				  	Even
				|				
			      Graph 
				|				
				|
				|
				|
				-----------------> Non Cyclic Graph-----------------------> Bipartite
							(Tree)
									
	
	
										


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


// Rajneesh Bhaiya // BFS
// Checking visited and color after adding in the queue [Best Solution]

class Solution {
//     Not visited : -1
//     Red : 0, visited
//     Green : 1, visited
   public boolean isBipartite(int[][] graph) {

        int[] visitedOrMarkedColor = new int[graph.length];
        Arrays.fill(visitedOrMarkedColor, -1); // Marking not visited
        
        for(int i = 0; i < graph.length; i++) {
            if(visitedOrMarkedColor[i] == -1 && !isComponentBipartite(i, visitedOrMarkedColor, graph)) { // Checking every component
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
                
		// Already visited
                if(visitedOrMarkedColor[curr] != -1) {
                    if(visitedOrMarkedColor[curr] != color) { // Already visited with different color => Conflict => Odd edge cycle
                        return false; // That's the end return
                    }
		    // Already visited with same color => No conflict => Even edge cycle => Further process your BFS 
                    continue; // Don't forget this line else it will increase the complexity
                }
                
                visitedOrMarkedColor[curr] = color; // Marking visited or assigned color
                for(int neigh : graph[curr]) { //Adding unvisited nbr's in the queue
                    if(visitedOrMarkedColor[neigh] == -1) {
                        queue.offer(neigh);
                    }
                }
            }
            color = (color + 1) % 2; // Toggling color to mark the next level nodes with different color
        }
        return true;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

/*
      Mine BFS
      Checking visited and color before adding in the queue but it is Less efficient. Why ?? Read below..

      Not adding same nodes multiple times in the queue, that is good but it is less efficient because
      at every node you are askibg for all it's nbr's whether there is a conflict,
      and in worst case there could be O(N - 1) such checks for every node, so complexity increases.

      Note : Either way we cannot escape the for loop for nbr, but we can reduce the conflict check to O(1), 
      if we would be asking the same question after poping from the queue like the above Rajneesh Bhaiya's code.
*/

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
