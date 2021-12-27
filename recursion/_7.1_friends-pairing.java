// https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/recursion-and-backtracking/friends-pairing-2-official/ojquestion

// People on level
// Always pass idx + 1 in next call

// Har call untimately n tak pahuchegi, doesn't matter if you have already formed your answer, 
// You will be propargating to the next call -> then next -> .... until you reach n, so base case that is idx == n pe hamesha reach karoge aap
// And jis din reach kar jaaoge idx == n pe => Ek tareeka mil gaya

// Isme idx k terms me answer bnana, par friends-pairing dp wale question me n k terms me answer bnana.

// Isme idx k terms me answer q bnana ? Kuki loop lgani h, and apne se aage wale k saath he aap pair bna saktey ho jo display v karni hai
// Remember to generate combination and not permuatiom

import java.io.*;
import java.util.*;

public class Main {
    
   public static int serialNo = 1;

   public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean[] visited = new boolean[n];
        pairing_Rec(0, n, visited, "");
   }
  
    
   // Levels pe har ek frnd hoga ek baar, pehle 0, 1, 2, so on...
   public static int pairing_Rec(int idx, int n, boolean[] visited, String ans) {
        if(idx == n)  {
            System.out.println(serialNo++ + "." + ans);
            return 1; // Sbko process kar liya => Ek tareeka mil gaya 
        }
        
        int count = 0; // Total number of ways
        
        // Your fate is already decided by someone, aage badh jaao,
        // someone already made pair with you from prev calls
        if(visited[idx]) count += pairing_Rec(idx + 1, n, visited, ans);
        
        // Tumhe kvi kisi ne nahi choona => Ab tumhare pass 2 choice hai 1)akele jaao 2) Kisi k saath jaao
        else {
            visited[idx] = true; // Mark yourself as visited
            
            // Akele jaane ki choice
            count += pairing_Rec(idx + 1, n, visited, ans + "(" + (idx + 1) + ") ");
            
            // Kisi k saath jaane ki choice
            for(int i = idx + 1; i < n; i++) {
                if(!visited[i]) { // Jiske saath jaana chahte ho wo visited nahi h
                    visited[i] = true; // Toh tum uske saath chale jaao
                    
                    // Here idx + 1 he pass hoga kuki har ek ka apna level hoga 0, 1, 2, so on..
                    count += pairing_Rec(idx + 1, n, visited, ans + "(" + (idx + 1) + "," + (i + 1) + ") "); 
                    visited[i] = false; // Backtrack the frnd, jiske saath gaye the
                }
            }
            visited[idx] = false; // Backtrack, unmark yourself
        }
        return count; // Jitne v tareeke aaye uska count 
    } 
    
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
