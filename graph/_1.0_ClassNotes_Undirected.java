/*
 HashMap TC: O(lambda)
 
 If range is less than 10^3 to 10^4 is then it's TC is O(1)
 If range exceeds the above threshold then there exists a lots of collisions and O(lambda) tends to O(N)
 So try to use Array in replacement of HashMap as much possible
 
 For Adjacency List:
 Maximum times the nodes will be given as int (say 0 to N) => Always use Array
 If the nodes are in character or String format then only go for HashMap 
*/

package graph;

import java.util.ArrayList;

public class _1_Undirected {
	
	public static class Edge {
		int v, w;
		
		Edge(int v, int w){
			this.v = v;
	 		this.w = w;
		}
	}
	
	public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
		graph[u].add(new Edge(v, w));
		graph[v].add(new Edge(u, w));
	}
	
	//	TC : O(2E) such that E = V^2 for Dense graph(All nodes connected to all other nodes)
	public static void display(ArrayList<Edge>[] graph, int V) {
		for(int i = 0; i < V; i++) {
			System.out.println(i + " -> ");
			for(Edge e: graph[i]) {
				System.out.println("(" + e.v + "," + e.w + ") ");
			}
			System.out.println();
		}
	}
	
	public static int findEdge(ArrayList<Edge>[] graph, int u, int v) {
		for(int i = 0; i < graph[u].size(); i++) {
			Edge e = graph[u].get(i);
			if(e.v == v) {
				return i;
			}
		}
		return -1;
	}
	
	//NOTE: In removeEdge if you remove an Edge from the Adjacency list,
	//it will take worst TC of O(e)=> O(1) in removing and O(e) in shifting of the elements,
	//so to escape shifting, swap the toBeRemoved Edge with the lastEdge element of the ArrayList
	//now remove the last element, hence no shifting.
	//The above idea is not implemented here
	public static void removeEdge(ArrayList<Edge>[] graph, int u, int v) {
		int idx1 = findEdge(graph, u, v);
		graph[u].remove(idx1);
		
		int idx2 = findEdge(graph, v, u);
		graph[v].remove(idx2);	
	}
	
	public static void removeVtx(ArrayList<Edge>[] graph, int u) {
		//To avoid shifting start removing from end
		for(int i = graph[u].size() - 1; i >= 0; i--) {
			Edge e = graph[u].get(i);
			//removeEdge will remove both from u -> v and v -> u
			removeEdge(graph, u, e.v);
		}
	}
	
	
	//=========================================================================================================================
	
	/*
	 DFS Pseudo Code:
	 1. Mark
	 2. For all unvisited nbr's
	 	2.1 DFS(nbr's)
	 */
	
	//TC:O(2E)=>O(E) such that E = V^2 for Dense graph(All nodes connected to all other nodes)
	public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis) {
		if(src == dest) return true;
		
		vis[src] = true;
		for(Edge e : graph[src]) {
			if(!vis[e.v]) {
				if(hasPath(graph, e.v, dest, vis)) {
					return true;
		 		}
			}
		}
		//NOTE: Don't mark false while returning because this is not "All path" question
		return false;
	}
	
	
	//=============================================================================================================================
	
	/*
	 AllPath DFS Pseudo Code:
	 1. Mark
	 2. For all unvisited nbr's
	 	2.1 DFS(nbr's)
	 3. Unmark 
	 */
	
	public static int AllPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis, String psf) {
		if(src == dest) {
			System.out.println(psf + dest);
			return 1;
		}
		
		int count = 0;
		vis[src] = true;
		for(Edge e : graph[src]) {
			if(!vis[e.v]) {
				count += AllPath(graph, e.v, dest, vis, psf + src);
			}
		}
		//NOTE: Mark false while backtracking because this is "All path" question
		vis[src] = false;
		return count;
	}
	
	
	//=================================================================================================================================
	
	
	public static void preOrder(ArrayList<Edge>[] graph, int src, boolean[] vis, int wsf, String psf) {
		System.out.println(src + " -> " + (psf + src) + "@" + wsf);
		vis[src] = true;
		for(Edge e : graph[src]) {
			if(!vis[e.v]) {
				preOrder(graph, e.v, vis, wsf + e.w, psf + src);
			}
		}
		vis[src] = false;
	}
	
	public static void postOrder(ArrayList<Edge>[] graph, int src, boolean[] vis, int wsf, String psf) {
		vis[src] = true;
		for(Edge e : graph[src]) {
			if(!vis[e.v]) {
				postOrder(graph, e.v, vis, wsf + e.w, psf + src);
			}
		}
		System.out.println(src + " -> " + (psf + src) + "@" + wsf);
		vis[src] = false;
	}

	
	//===================================================================================================================================

	
	public static class Pair {
		int heavyPath = 0;
		String psf = "";
		
		public Pair(){}
		public Pair(int heavyPath, String psf) {
			this.heavyPath = heavyPath;
			this.psf = psf;
		}	
	}
	
	public static Pair heavyPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis) {
		if(src == dest) {
			return new Pair(0, src + "");
		}
		
		vis[src] = true;
		Pair myAns = new Pair(-1, "");
		for(Edge e : graph[src]) {
			if(!vis[e.v]) {
				Pair smallAns = heavyPath(graph, e.v, dest, vis);
				
				//NOTE: This check smallAns.heavyPath != -1 is very important to know if the base case was hit or not
				//If you do not make this check, then also you will get answer but that would be a wrong answer because -1 + something > -1
				if(smallAns.heavyPath != -1 && smallAns.heavyPath + e.w > myAns.heavyPath) {
					myAns.heavyPath = smallAns.heavyPath + e.w;
					myAns.psf = src + smallAns.psf;
				} 
			}
		}
		vis[src] = false;
		return myAns;
	}

	
	//=========================================================================================================================================
	
	
	 public static class Pair2 {
	        int lightPath = 0;
	        String psf = "";

	        public Pair2(){}
	        public Pair2(int lightPath, String psf) {
	            this.lightPath = lightPath;
	            this.psf = psf;
	        }
	 }
	
 	 public static Pair2 lightestPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis) {
 		if(src == dest) {
			return new Pair2(0, src + "");
		}
		
		vis[src] = true;
		Pair2 myAns = new Pair2((int)1e8, "");
		for(Edge e : graph[src]) {
			if(!vis[e.v]) {
				Pair2 smallAns = lightestPath(graph, e.v, dest, vis);
				
				//NOTE: This check smallAns.lightPath != (int)1e8 is very important to know if the base case was hit or not
				if(smallAns.lightPath != (int)1e8 && smallAns.lightPath + e.w < myAns.lightPath) {
					myAns.lightPath = smallAns.lightPath + e.w;
					myAns.psf = src + smallAns.psf;
				} 
			}
		}
		vis[src] = false;
		return myAns;
     }

 	 
     //==========================================================================================================================================

 	 
     public static class Pair3 {
        int length = 0;
        String psf = "";

        public Pair3(){}
        public Pair3(int length, String psf) {
            this.length = length;
            this.psf = psf;
        }
     }

     public static Pair3 longestPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis) {
		if(src == dest) {
			return new Pair3(0, src + "");
		}
		
		vis[src] = true;
		Pair3 myAns = new Pair3(-1, "");
		for(Edge e : graph[src]) {
			if(!vis[e.v]) {
				Pair3 smallAns = longestPath(graph, e.v, dest, vis);
				
				//NOTE: This check smallAns.lightPath != -1 is very important to know if the base case was hit or not
				if(smallAns.length != -1 && smallAns.length + 1 > myAns.length) {
					myAns.length = smallAns.length + 1;
					myAns.psf = src + smallAns.psf;
				} 
			}
		}
		vis[src] = false;
		return myAns;
     }

     
	//============================================================================================================================================
     
	/*
	  What is Hamiltonian Path ?
	  1. Single Source 
	  2. Should travel through all the nodes only once in any sequence
	  
	  What is Hamiltonian Cycle(Mark with *) ?
	  1. Single Source 
	  2. Should travel through all the nodes only once in any sequence
	  3. The last node should have direct edge with the original source node 
	 */
	
	// E = v - 1
	public static void hamiltonainPathAndCycle(ArrayList<Edge>[] graph, int src, int osrc, int edgeCount, boolean[] vis, String psf, ArrayList<String> ans) {
		//Found Hamiltonian Path
		if(edgeCount == graph.length - 1) {
            psf += src;
            
            //osrc is required to find directEdge for Hamiltonian Cycle when Hamiltonian Path is found
            int idx = findEdge(graph, src, osrc);
            if (idx != -1) { //Found a direct edge
                psf += '*'; //Adding mark for Hamiltonian Cycle
            }
            else { //Not found a direct edge
                psf += ".";  //Adding mark for Hamiltonian Path
            }
           
            //NOTE: We have psf as String so no backtracking needed
            //But if it would have been of type ArrayList 
            //then you would be backtracking from the list and removing the last node to explore Allpaths
            ans.add(psf); //Adding ans to our list
            return;
        }

        vis[src] = true;
        for(Edge e : graph[src]) {
            if(!vis[e.v]) {
            	hamiltonainPathAndCycle(graph, e.v, osrc, edgeCount + 1, vis, psf + src, ans);
            }
        }
        vis[src] = false;
    }

	
	//=======================================================================================================

	//get connected component
	//TC : O(V + E) 
	public static int gcc(ArrayList<Edge>[] graph) {
		int N = graph.length;
		boolean[] vis = new boolean[N];
		int componentCount = 0;
		
		//Saare nodes se calls lgane ki kosish ho rahi h isliye(V + E)
		for(int i = 0; i < N; i++) {
			if(!vis[i]) {
				dfsComponent(graph, vis, i);
				componentCount++;
			}
		}
		return componentCount;
	}
	
	//TC: O(E) //Ek dfs ki compelexity O(E) hogi
	public static void dfsComponent(ArrayList<Edge>[] graph, boolean[] vis, int src) {
		vis[src] = true;
		for(Edge e: graph[src]) {
			if(!vis[e.v]) {
				dfsComponent(graph, vis, e.v);
			}
		}
	}
	
	
      //=======================================================================================================
	
      /*
	 # BFS
	    Moves Radially
	    Shorest path Algorithm
	    Single Source Algorithm
	    Cycle can be detected, but number of cycles cannot be detected
      */
      --------------------------------------------------------------------
		
       /*
	   Normal BFS with Cycle 
	   Use this BFS with Cycle Algo where cycle matters
	   This BFS is used in Dijkstra & Prims with little modifications
        */
	      
	public static void bfs(ArrayList<Edge>[] graph, int src, boolean[] vis) {
		LinkedList<Integer> que = new LinkedList<>();
		que.add(src);

		int level = 0;
		while(!que.isEmpty()) {
			int size = que.size();
			System.out.print("Level: " + level + " ->");

			while(size-- > 0) {
				int vtx = que.removeFirst();
				if(vis[vtx]) {
					System.out.println("cycle");
					continue;
				}

				System.out.print(vtx + ", ");

				vis[vtx] = true;
				for(Edge e : graph[vtx]) {
					if(!vis[e.v]) {
						que.addLast(e.v);
					}
				}
			}
			level++;
			System.out.println();
		}
	}

	
	//===============================================================================================
	
	
	/*
	    BFS without cycle
	    Use this BFS without cycle Algo where cycle does not matter
            Used in rotten Oranges kind of qusetions
	*/
	
	public static void bfs_withouCycle(ArrayList<Edge>[] graph, int src, boolean[] vis) {
		LinkedList<Integer> que = new LinkedList<>();
		que.add(src);
		vis[src] = true;

		int level = 0;
		while(!que.isEmpty()) {
			int size = que.size();
			System.out.print("Level: " + level + " ->");

			while(size-- > 0) {
				int vtx = que.removeFirst();
				System.out.print(vtx + ", ");

				for(Edge e : graph[vtx]) {
					if(!vis[e.v]) {
						vis[e.v] = true;
						que.addLast(e.v);
					}
				}
			}
			level++;
			System.out.println();
		}
	}

	
	//==================================================================================================================================
	
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
									
	
	
										
										
	//==================================================================================================================================
	
	
      public static void constructGraph() {
	int N = 7;
	ArrayList<Edge>[] graph = new ArrayList[N];
	for(int i = 0; i < N; i++) {
		graph[i] = new ArrayList<>();
	}
		
        addEdge(graph, 0, 1, 10);
        addEdge(graph, 0, 3, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 40);

      //  addEdge(graph, 2, 7, 2);
      //  addEdge(graph, 2, 8, 4);
      //  addEdge(graph, 7, 8, 3);

        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 4, 6, 8);
        addEdge(graph, 5, 6, 3);

      //  addEdge(graph, 0, 6, 3);
        
        boolean[] vis = new boolean[7];
        System.out.println(AllPath(graph, 0, 6, vis, ""));
        
        vis = new boolean[7];
        Pair pair = heavyPath(graph,0, 6, vis);
        System.out.println(pair.psf + "@" + pair.heavyPath);
        
        vis = new boolean[7];
        Pair2 pair2 = lightestPath(graph,0, 6, vis);
        System.out.println(pair2.psf + "@" + pair2.lightPath);
        
        vis = new boolean[7];
        Pair3 pair3 = longestPath(graph,0, 6, vis);
        System.out.println(pair3.psf + "@" + pair3.length);
	}
	
	public static void main(String[] args) {
		constructGraph(); 
		
	}

}
