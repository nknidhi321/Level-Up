// https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/dynamic-programming/count-brackets-official/ojquestion [Count btana hai]
// https://leetcode.com/problems/generate-parentheses/ [Sare tareeke display krwane hai]

// At pep
// Saare tareeke ka count btana hai tab toh dp socho
// It's an application of catalan
// 1st Approach

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println((int) findCatalan(n));
    }

    public static long findCatalan(int n) {

        long[] catlanDP = new long[n + 1];
        catlanDP[0] = catlanDP[1] = 1;

        for (int i = 2; i <= n; i++) {
            int catlan = 0;
            for (int j = 0; j < i; j++) {
                catlan += catlanDP[j] * catlanDP[i - 1 - j];
            }
            catlanDP[i] = catlan;
        }

        // Printing catalan DP
        // for(int i = 0; i <= n; i++) {
        //     System.out.println(catlanDP[i]);
        // }

        return catlanDP[n];
    }

}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// 2nd approach, saara tareeke ka length return krwa do
// Approach Not feasible if it's dp question

import java.io.*;
import java.util.*;

public class Main{

public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(Systemn.in);
    int n = sc.nextInt();
    System.out.println(generateParenthesis(n));
 }
 
 public int generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        
        // For n == 3, 3 maximum "(" && 3 maximum ")"
        generateParenthesis_Rec(0, 0, n, new StringBuilder(), ans);
        return ans.length;
    }
    
    public static void generateParenthesis_Rec(int open, int close, int max, StringBuilder sb, List<String> ans) {
        if(open == max && close == max) {
            ans.add(sb.toString());
            return;
        }
        
        // Opening should be less than maximum, that is n, so check if(open < max) 
        if(open < max) {
            sb.append("(");
            generateParenthesis_Rec(open + 1, close, max, sb, ans);
            sb.deleteCharAt(sb.length() - 1);
        }
        
        // Any closing cannot come before opening, so check if(close < open)
        if(close < open) {
            sb.append(")");
            generateParenthesis_Rec(open, close + 1, max, sb, ans); 
            sb.deleteCharAt(sb.length() - 1);
        }
    }

}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
