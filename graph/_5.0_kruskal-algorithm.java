// https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/graphs/kruskal-algorithm-official/ojquestion

import java.util.*;
import java.io.*;

public class Main {

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int v = Integer.parseInt(br.readLine());
    int e = Integer.parseInt(br.readLine());

    int[][] edges = new int[e][3];
    for (int i = 0; i < e; i++) {
      String[] st = br.readLine().split(" ");
      edges[i][0] = Integer.parseInt(st[0]);
      edges[i][1] = Integer.parseInt(st[1]);
      edges[i][2] = Integer.parseInt(st[2]);
    }

    System.out.println(minCostToSupplyWater(v, edges));
  }


  //================================================================================================
  public static int[] par;
  public static int[] height;

  public static int minCostToSupplyWater(int n, int[][] pipes) {
    par = new int[n];
    height = new int[n];

    for(int i = 0; i < n; i++) {
        par[i] = i;
        height[i] = 1;
    }
    
    Arrays.sort(pipes, (a, b) -> a[2] - b[2]);
    
    int totalMinCost = 0;
    for(int[] pipe : pipes) { // note saare edges ko process karna hai
        int v1 = pipe[0];
        int v2 = pipe[1];
        int w = pipe[2];
        
        boolean isEdgeIncluding = union(v1, v2);
        if(isEdgeIncluding) { // If that edge is including, add that edge weight in your ans
            totalMinCost += pipe[2];
        }
    }
    return totalMinCost;    
  }
  
    public static int find(int x) {
        if(par[x] == x) return x;
        return par[x] = find(par[x]);
    }
    
    public static boolean union(int x, int y) {
        int lx = find(x);
        int ly = find(y);
        if(lx != ly) { // dono nbr hai and different leader aaya merge them
            if(height[lx] > height[ly]) {
                par[ly] = lx;
            }
            else if(height[ly] > height[lx]) {
                par[lx] = ly;
            }
            else {
                par[lx] = ly;
                height[ly]++;
            }
            return true;
        }
        else { // Cycle nahi bnana hai, so return false
            return false;
        }
    }
    //================================================================================================

}
