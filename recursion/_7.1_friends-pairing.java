//https://www.pepcoding.com/resources/online-java-foundation/dynamic-programming-and-greedy/friends-pairing-official/ojquestion

//People on level
//Always pass idx + 1

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean[] visited = new boolean[n];
        System.out.println(pairing(0, n, visited));
    }
    
    public static int pairing(int idx, int n, boolean[] visited) {
        if(idx == n) return 1;
        
        int count = 0;
        
        //Your fate is already decided by someone, aage badh jaao
        if(visited[idx]) count += pairing(idx + 1, n, visited);
        
        else {
            visited[idx] = true;
            
            //Akele jaane ki choice
            count += pairing(idx + 1, n, visited);
            
            //Kisi k saath jaane ki choice
            for(int i = idx + 1; i < n; i++) { //We don't have to generate permutation so make sure not to start loop from 0
                if(!visited[i]) {
                    visited[i] = true;
                    count += pairing(idx + 1, n, visited);  //idx + 1 will be passed and not i + 1, because we have individual level for each people
                    visited[i] = false;
                }
            }
            visited[idx] = false;
        }
        return count;
    } 

}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
