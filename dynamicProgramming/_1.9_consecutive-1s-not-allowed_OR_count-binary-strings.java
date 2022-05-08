// https://practice.geeksforgeeks.org/problems/consecutive-1s-not-allowed1912/1/#
// consecutive 1's nahi hona chahiye 

class Solution {

    public long countStrings(int n) {
        long mod = (long)1e9 + 7;

        long[] dp0 = new long[n + 1]; // Ending at 0
        long[] dp1 = new long[n + 1]; // Ending at 1
        
        // NOTE : dp[0] is waste
        // dp0[i] will store number of ways, string ending with 0 of length i
        // dp1[i] will store number of ways, string ending with 1 of length i		
        // ans of n will be calculated by dp0[i] + dp1[i]  
		
	dp0[1] = dp1[1] = 1;  
		
        for(int i = 2; i <= n; i++) {
            dp0[i] = (dp0[i - 1] + dp1[i - 1]) % mod;
            dp1[i] = dp0[i - 1];
        }
        return (dp0[n] + dp1[n]) % mod;
    }
    
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
// https://www.pepcoding.com/resources/online-java-foundation/dynamic-programming-and-greedy/count-binary-strings-official/ojquestion
// consecutive 0's nahi hona chahiye

import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println(countStrings(sc.nextInt()));    
     }
     
    public static long countStrings(int n) {
        long[] dp0 = new long[n + 1]; // Ending at 0
        long[] dp1 = new long[n + 1]; // Ending at 1
        
        // NOTE : dp[0] is waste
        // dp0[i] will store number of ways, string ending with 0 of length i
        // dp1[i] will store number of ways, string ending with 1 of length i		
        // ans of n will be calculated by dp0[i] + dp1[i]  
    	
        dp0[1] = dp1[1] = 1;  
    	
        for(int i = 2; i <= n; i++) {
            dp0[i] = dp1[i - 1];
            dp1[i] = dp0[i - 1] + dp1[i - 1];
        }
        return dp0[n] + dp1[n];
    }

}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
