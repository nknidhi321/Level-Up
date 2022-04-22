// https://practice.geeksforgeeks.org/problems/mother-vertex/1/#
/*
	//Same as Topo DFS
	//There might exist more than 1 mother vtx(which will the members of the cycle of that mothervtx), return smallest motherVtx 
	//The top most element of the Stack/ArrayList can be mother Vtx or it cannot
	//But if top most element is not the mother vtx, then 0 motherVtx exist in the graph
	//Because we always add our child node first in the answer list and then the parent
	//Same reason as for TopoDFS
*/

class Solution {

    public int findMotherVertex(int N, ArrayList<ArrayList<Integer>> graph) {
        Stack<Integer> stack = new Stack<>();
		boolean[] vis = new boolean[N];
		
		//TopoDFS
		for(int i = 0; i < N; i++) {
		    if(!vis[i]) {
		        dfs_PostOrder(i, vis, stack, graph);
		    }
		}
	
		//Cross checking if we can actually visit all nodes from mother vtx
		int probaleMotherVtx = stack.peek();
		vis = new boolean[N];
		int count = dfs(probaleMotherVtx, vis, graph);
		
		return count == N ? probaleMotherVtx : -1; 
    }
    
    //TopoDFS
    public static void dfs_PostOrder(int src, boolean[] vis, Stack<Integer> stack, ArrayList<ArrayList<Integer>> graph) {
        vis[src] = true;
        for(Integer nbr : graph.get(src)) {
            if(!vis[nbr]) {
                dfs_PostOrder(nbr, vis, stack, graph);
            }
        }
        stack.push(src);
    }
    
    //Counting nodes of tree //Size of graph
    public static int dfs(int src, boolean[] vis, ArrayList<ArrayList<Integer>> graph) {
        vis[src] = true;
        int count = 0;
        for(Integer nbr : graph.get(src)) {
            if(!vis[nbr]) {
                count += dfs(nbr, vis, graph);
            }
        }
        return count + 1;
    }
    
}

//-------------------------------------------------------------------------------------------------------------------------------------
// same sol. on pep
// https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/graphs/mother-vertex-official/ojquestion

import java.io.*;
import java.util.*;

public class Main{
    	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] st = br.readLine().split(" ");
		int n = Integer.parseInt(st[0]);
		int m = Integer.parseInt(st[1]);

		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}
			
		for (int i = 0; i < m; i++) {
			st = br.readLine().split(" ");
			int u = Integer.parseInt(st[0]) - 1;
			int v = Integer.parseInt(st[1]) - 1;
			graph.get(u).add(v);
		}
			
		System.out.println(findMotherVertex(n, graph));
	}
	
	
	//=======================================================================================================================
	
    	public static int findMotherVertex(int N, ArrayList<ArrayList<Integer>> graph) {
	
		ArrayList<Integer> ans = new ArrayList<>();
		boolean[] vis = new boolean[N];
		
		//TopoDFS
		for(int i = 0; i < N; i++) {
		    if(!vis[i]) {
		        dfs_PostOrder(i, vis, ans, graph);
		    }
		}
	
		//Cross checking if we can actually visit all nodes from mother vtx
		int probaleMotherVtx = ans.get(ans.size() - 1);
		vis = new boolean[N];
		int count = dfs(probaleMotherVtx, vis, graph);
		
		//Nodes are from [1 to N] so return +1, if there exists a mother Vtx 
		return count == N ? probaleMotherVtx + 1: -1; 
    }
    
    //TopoDFS
    public static void dfs_PostOrder(int src, boolean[] vis, ArrayList<Integer> ans, ArrayList<ArrayList<Integer>> graph) {
        vis[src] = true;
        for(Integer nbr : graph.get(src)) {
            if(!vis[nbr]) {
                dfs_PostOrder(nbr, vis, ans, graph);
            }
        }
        ans.add(src);
    }
    
    //Counting nodes of tree //Size of graph
    public static int dfs(int src, boolean[] vis, ArrayList<ArrayList<Integer>> graph) {
        vis[src] = true;
        int count = 0;
        for(Integer nbr : graph.get(src)) {
            if(!vis[nbr]) {
                count += dfs(nbr, vis, graph);
            }
        }
        return count + 1;
    }
}
//==============================================================================================================================================
