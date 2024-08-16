// https://www.geeksforgeeks.org/reduce-the-string-by-removing-k-consecutive-identical-characters/
// No repeat burst length onto the new array after each iteration
// It has to be done only once

public class Main {

    public static String reducedString(String s, int k) {
        StringBuilder ans = new StringBuilder();
        
        int i = 0;
        while(i < s.length()) {
            int j = i;
            StringBuilder temp = new StringBuilder();
            while(j < s.length() && s.charAt(i) == s.charAt(j)) {
                temp.append(s.charAt(i));
                j++;
            }
            if(j - i != k) ans.append(temp);
            i = j;
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        int k = 2;
        String str = "geeksforgeeks";
        String reducedString = reducedString(str, k);
        System.out.println("Reduced string: " + reducedString);
    }
}

//------------------------------------------------------------------------------------

// Follow up
// You have to repeat removal of burst length onto the new array after each iteration

/*
    Given an input length, String array and burst length(>0) as input, 
    the output should be an array which is a shrunk input array such that
    the sequentially repeating elements equal to burst length should be removed. 
    This has to be repeated till the array cannot be shrunk any further. 
    Use single characters in the string array for simplicity as shown in sample test cases
*/

// Approach : Keep a stack with <Character, consecutive Count> and keep iterating and checking with the incoming val
import java.util.*;

class GFG {

    static class Pair {
        char c;
        int ctr;
        Pair(char c, int ctr) {
            this.c = c;
            this.ctr = ctr;
        }
    }
    
    public static String reduced_String(int k, String s) {
        if(k == 1) return "";

        Stack<Pair> st = new Stack<Pair>();
        int n = s.length();
        int ctr = 0;

        for(int i = 0; i < n; i++) {
            // if stack is empty then simply add the
            // character with count 1 else check if
            // character is same as top of stack
            if(st.size() == 0) {
                st.push(new Pair(s.charAt(i), 1));
                continue;
            }

            // if character at top of stack is same as
            // current character increase the number of
            // repetitions in the top of stack by 1
            if(st.peek().c == s.charAt(i)) {
                Pair p = st.peek();
                st.pop();
                p.ctr += 1;
                if(p.ctr == k) continue;
                else st.push(p);
            }
            else {
                st.push(new Pair(s.charAt(i), 1));
            }
        }

        // iterate through the stack
        // append characters in String
        StringBuilder output = new StringBuilder();

        while (st.size() > 0) {
            char c = st.peek().c;
            int cnt = st.peek().ctr;
            // If frequency of a character is cnt, then
            // append that character to cnt times in String
            while (cnt-- > 0) {
                output.append(String.valueOf(c));
            }
            st.pop(); // why pop ?
        }
        output.reverse();
        return output.toString();
    }

}
