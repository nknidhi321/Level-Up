// https://leetcode.com/problems/optimize-water-distribution-in-a-village/ [Locked]
// https://www.codingninjas.com/codestudio/problems/water-supply-in-a-village_1380956?leftPanelTab=0

/*
      Intuition :
      If there was no well then it was simple MST of pipes, you can obiously observe from the question
      Wells is a problems, so what if you can somehow convert all the wells into pipes.

      Approach :
      Covert all wells into pipes by placing an extra node say 0 to all the n nodes with weight that of given well digging cost.
      Now, its simple MST problem. Use Prims or Kruskals
      
      Obervation :  [Copied the statement from CN editor]
      or pipe in water from another "well" to it => Minimum ek well toh lgega he, mtlb 0 hamesha connect ho jaaega tumhare final MST graph se.
      Kuki minimum koi ek node hamesha aisa hoga jo well se paani lega, ek node se zyada nodes v well se paani le saktey hai, but atleast ek toh hamesgha lega he.
*/

// Kruskal

import java.util.Arrays;

public class Solution {

    public static int[] par;
    public static int[] height;

    public static int supplyWater(int n, int k, int[] wells, int[][] pipes) {
      
        // Create new array to include extra n edges from 0 to n other nodes
        int[][] oldNewPipes = new int[pipes.length + n][3];
      
              
        int idx = 0;
        for (int i = 0; i < wells.length; i++) {  // Adding edges from 0 to n nodes in the new array 
            int v1 = 0;  // From 0
            int v2 = i + 1;  // Other n nodes
            oldNewPipes[idx++] = new int[] {v1, v2, wells[i]};  // With weight of given wells
        }

        for (int i = 0; i < pipes.length; i++) {  // Adding the given n edges of pipes in the new array
            int[] pipe = pipes[i];
            int v1 = pipe[0];
            int v2 = pipe[1];
            int w = pipe[2];
            oldNewPipes[idx++] = new int[] {v1, v2, w}; // Just coping the pipes[][] data into the new array 
        }

        // Now, simply apply Kruskal's Algo
        par = new int[n + 1];
        height = new int[n + 1];

        for (int i = 0; i < n + 1; i++) {
            par[i] = i;
            height[i] = 1;
        }

        Arrays.sort(oldNewPipes, (a, b) -> a[2] - b[2]);  // Sorting on edge wt.

        int totalMinCost = 0;
        for (int[] pipe: oldNewPipes) { // note saare edges ko process karna hai
            int v1 = pipe[0];
            int v2 = pipe[1];
            int w = pipe[2];

            boolean isEdgeIncluding = union(v1, v2);
            if (isEdgeIncluding) { // If that edge is including, add that edge weight in your ans
                totalMinCost += pipe[2];
            }
        }
        return totalMinCost;
    }

    public static int find(int x) {
        if (par[x] == x) return x;
        return par[x] = find(par[x]);
    }

    public static boolean union(int x, int y) {
        int lx = find(x);
        int ly = find(y);
        if (lx != ly) { // dono nbr hai and different leader aaya merge them
            if (height[lx] > height[ly]) {
                par[ly] = lx;
            } else if (height[ly] > height[lx]) {
                par[lx] = ly;
            } else {
                par[lx] = ly;
                height[ly]++;
            }
            return true;
        } else { // Cycle nahi bnana hai, so return false
            return false;
        }
    }

}

//===================================================================================================================================================================

// Prims

import java.util.*;

public class Solution {

    public static int supplyWater(int n, int k, int[] wells, int[][] pipes) {

        ArrayList < int[] > [] graph = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) graph[i] = new ArrayList < > ();

        int idx = 0;
        for (int i = 0; i < wells.length; i++) { // Adding edges from 0 to n nodes in the new array 
            int v1 = 0; // From 0
            int v2 = i + 1; // Other n nodes
            graph[v1].add(new int[] {v2, wells[i]});  // With weight of given wells
			graph[v2].add(new int[] {v1, wells[i]});
        }

        for (int i = 0; i < pipes.length; i++) { // Adding the given n edges of pipes in the new array
            int[] pipe = pipes[i];
            int v1 = pipe[0];
            int v2 = pipe[1];
            int w = pipe[2];
            graph[v1].add(new int[] {v2, w}); // Just coping the pipes[][] data into the new array 
        	graph[v2].add(new int[] {v1, w}); 
        }

        // Now, simple Prim's
        boolean[] vis = new boolean[n + 1];
        PriorityQueue < int[] > pq = new PriorityQueue < > ((a, b) -> a[1] - b[1]);
        pq.add(new int[] {0, 0}); // {par, vtx, wt}
		
        int totalMinCost = 0;
        while (pq.size() != 0) {
            int[] p = pq.remove();
            int vtx = p[0];
            int w = p[1];
            if (vis[vtx]) continue;

		totalMinCost += w;                                                // [NOTE : Nikalte waqt he answer bnega in Prim's && Dijkstra]
		vis[vtx] = true;
		for (int[] e: graph[vtx]) {
                  if (!vis[e[0]]) {
                    pq.add(new int[] {e[0], e[1]});
			}
            }
        }
        return totalMinCost;
    }

}
//===================================================================================================================================================================
