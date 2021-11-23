//https://www.hackerrank.com/challenges/journey-to-the-moon/problem?isFullScreen=true
//Rajneesh 
Efficeient

class Result {

    //=============================================================================================================
    public static long journeyToMoon(int n, List<List<Integer>> edges) {
      ArrayList<Integer>[] graph = new ArrayList[n];
      for(int i = 0; i < n; i++) {
          graph[i] = new ArrayList<>();
      }
      
      for(List<Integer> edge : edges) {
          int u = edge.get(0);
          int v = edge.get(1);
          graph[u].add(v);
          graph[v].add(u);
      }
      
      //=================Important===========================	
      long sum = 0, ans = 0;
      boolean[] vis = new boolean[n];
      for(int i = 0; i < n; i++) {
          if(!vis[i]) {
              long size = dfs_component_size(i, vis, graph);
              ans += size * sum;
              sum += size;
          }
      }
      //====================================================
      return ans; 
   }
   
   //Returns individual components size	
   public static long dfs_component_size(int src, boolean[] vis, ArrayList<Integer>[] graph) {
       vis[src] = true;
       long size = 0;
       for(int nbr : graph[src]) {
           if(!vis[nbr]) {
               size += dfs_component_size(nbr, vis, graph);
           }
       }
       return size + 1;
   }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

//YouTube
Good approach but TLE in 1 TC in Java 

/*		
	    Youtube, nC2 - Same Country Pair
	
		nCr     =         n!			
			      -----------
			       (n - r)! r! 
		
		
		nC2 	=   n * (n - 1) / 2
		
		totalCombination => 5C2 => 10  => (AB + AC + AD + AE) + (BC + BD + BE) + (CD + CE) + (DE)


									A				  C
									|				/   \
									B		   	       D     E

	 	Same Country Pair =>	   				AB 		             CD + CE + DE	


		totalCombination	 =>      (AB + AC + AD + AE) + (BC + BD + BE) + (CD + CE) + (DE) - [ (AB) ] - [ (CD + CE + DE) ]  =>   6
*/

class Result {

    //============================================================================================================================
    public static long journeyToMoon(int n, List<List<Integer>> edges) {
      ArrayList<Integer>[] graph = new ArrayList[n];
      for(int i = 0; i < n; i++) {
          graph[i] = new ArrayList<>();
      }
      
      for(List<Integer> edge : edges) {
          int u = edge.get(0);
          int v = edge.get(1);
          graph[u].add(v);
          graph[v].add(u);
      }
   
      //Constratint n = 10 ^ 5 => (10 ^ 5) ^ 2 = 10 ^ 10  //And java can execute 10 ^ 9 in 1 sec, Therefore TLE
		
      long totalCombination = n * (n - 1) / 2;   //n logo me se 2 logo ko choose karna hai, nC2 
      boolean[] vis = new boolean[n];
      for(int i = 0; i < n; i++) {
          if(!vis[i]) {
              long size = dfs_component_size(i, vis, graph);
              totalCombination -= size * (size  - 1) / 2;   //nC2 me se apne same country k pair ko subtract krte jaao
          }
      }
      return totalCombination;	 //resultant is only different country pair
   }
  
   //Similar to Generic tree ka size 
   public static long dfs_component_size(int src, boolean[] vis, ArrayList<Integer>[] graph) {
       vis[src] = true;
       long size = 0;
       for(int nbr : graph[src]) {
           if(!vis[nbr]) {
               size += dfs_component_size(nbr, vis, graph);
           }
       }
       return size + 1;
   }
  //======================================================================================================================================
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

//https://pepcoding.com/resources/online-java-foundation/graphs/perfect-friends-official/ojquestion
//Sumeet Malik 
Not Efficient


	     Country 1		    Country 2		   Country 3		   Country 4
	-------------------	-----------------	-----------------	-----------------
	-		  -	-		-	-		-	-		-
	-	10	  -	-	20	-	-	15	-	-	5	-		where 10, 20, 15, 5 is gcc size
	-		  -	-		-	-		-	-		-
	-------------------	-----------------	-----------------	-----------------
	
	For country 1:-
		
	1 person of country 1 can be mapped in 20 different person(ways) of country 2     =>    10 person of country 1 can be mapped in 10 * 20 different ways of country 2
				+												+
	1 person of country 1 can be mapped to 15 different person(ways) of country 3	  =>	10 person of country 1 can be mapped to 10 * 15 different ways of country 3
				+												+
	1 person of country 1 can be mapped to 5 different person(ways) of country 4	  =>    10 person of country 1 can be mapped to 10 * 5 different ways of country 4
	
	For country 2:-
	so on...
		
		
	pairCount = 	
	(10 * 20) + (10 * 15) + (10 * 5) +
	(20 * 15) + (20 * 5) +
	(15 * 5)
	

import java.io.*;
import java.util.*;

public class Main {

   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      int n = Integer.parseInt(br.readLine());
      int k = Integer.parseInt(br.readLine());
      
      ArrayList<Integer>[] graph = new ArrayList[n];
      for(int i = 0; i < n; i++) {
          graph[i] = new ArrayList<>();
      }
      
      for(int i = 0; i < k; i++) {
          String[] parts = br.readLine().split(" ");
          int u = Integer.parseInt(parts[0]);
          int v = Integer.parseInt(parts[1]);
          graph[u].add(v);
          graph[v].add(u);
      }
      
      ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
      boolean[] vis = new boolean[n];
      for(int i = 0; i < n; i++) {
          if(!vis[i]) {
              comps.add(dfs_component(i, vis, new ArrayList<>(), graph));
          }
      }
      
      int pairCount = 0;
      for(int i = 0; i < comps.size(); i++) { // (A, B) pair is same as (B, A) pair => We need combination
          for(int j = i + 1; j < comps.size(); j++) { // We need combination, not permutation so loop will always start from i + 1
              pairCount += comps.get(i).size() * comps.get(j).size();
          }
      }
      
      System.out.println(pairCount);
   }
   
   public static ArrayList<Integer> dfs_component(int src, boolean[] vis, ArrayList<Integer> comp, ArrayList<Integer>[] graph) {
       vis[src] = true;
       comp.add(src);
       for(int nbr : graph[src]) {
           if(!vis[nbr]) {
               dfs_component(nbr, vis, comp, graph);
           }
       }
       return comp;
   }

}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
