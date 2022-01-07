//https://leetcode.com/problems/possible-bipartition/

/*
	Make the given dislike people as each others neighbour.
	Now it's a simple graph coloring or graph bipartition problem.
	Check 3.1.1 for detailed explantion for Bipartite Graph
*/

// BFS (Cycle wali) 

class Solution {
//     Not visited : -1
//     Red : 0, visited
//     Green : 1, visited
    public boolean possibleBipartition(int n, int[][] dislikes) {
        
        // graph ranges from [1 - n], so size will be n + 1, where graph[0] will be a waste 
		
	// Graph creation
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int[] dislike : dislikes) {
            graph[dislike[1]].add(dislike[0]);
            graph[dislike[0]].add(dislike[1]);
        }
        
	// Keeping visited and marked color in the  same array
        int[] visOrColor = new int[n + 1];
        Arrays.fill(visOrColor, -1); // Initially marking all nodes as unvisited 
        
        for(int i = 1; i <= n; i++) {
            if(visOrColor[i] == -1 && !isComponentBipartite(i, visOrColor, graph)) {  //Check for all components
                return false;
            }
        }
        return true;
    }
    
    public static boolean isComponentBipartite(int src, int[] visOrColor, ArrayList<Integer>[] graph) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);
        
        int color = 0; // Source node color will be Red
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                int currVtx = queue.poll();
				
		// Already visited
                if(visOrColor[currVtx] != -1) { 
                    if(visOrColor[currVtx] != color) { // Already visited with different color => Conflict => Odd edge cycle
                        return false; // That's the end return 
                    }
					
		    // Already visited with same color => No conflict => Even edge cycle => Further process your BFS   
                    // NOTE : The already visited nodes have already added it's nbr so why to waste the below for loop check, so continue
		    continue; // Don't forget this line else it will increase the complexity		
                }
                
                visOrColor[currVtx] = color; // Marking visited or assigned color
                for(int nbr : graph[currVtx]) { //Adding unvisited nbr's in the queue
                    if(visOrColor[nbr] == -1) {
                        queue.add(nbr);
                    }
                }
            }
            color = (color + 1) % 2; // Toggling color to mark the next level nodes with different color
        }
        return true;
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
