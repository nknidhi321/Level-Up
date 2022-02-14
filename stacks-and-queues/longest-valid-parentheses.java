// https://leetcode.com/problems/longest-valid-parentheses/

class Solution {
    
    public int longestValidParentheses(String s) {
        int n = s.length();
        int[] array_01 = minRemoveToMakeValid(n, s);
        return findMaxConsecutiveOnes(array_01); 
    }
        
    public static int[] minRemoveToMakeValid(int n, String s) {
        LinkedList<Integer> stackll = new LinkedList<>();  // Using LL in the form of stack
        for(int i = 0; i < n; i++) { // Add all invalid brackets to the stack
            char c = s.charAt(i);
            if(c == '(') {
                stackll.addFirst(i); // '(' mile toh add kar do
            }
            else if(c == ')') {
                // Jiska pair milte jaaye usko pop kar do
                if(stackll.size() > 0 && s.charAt(stackll.getFirst()) == '(') stackll.removeFirst();  
                // ')' v add kar do agar stack k top pe uka pair nahi mila toh
                else stackll.addFirst(i);
            }
        }
        
        int invalidIdx = stackll.size() - 1; // Sbse bada invalidIdx stack k top pe hoga, mtlb stackll k 0th idx pe        
        StringBuilder sb = new StringBuilder();
        
        // s ko 0 idx se check kar rahe hai that's why stack ko bottom to top iterate krna hoga(small to large)
        // invalidIdx ko 0 se represent kar do and valid idx ko 1 se
        int[] array_01 = new int[n];
        for(int i = 0; i < n; i++) { 
            if(invalidIdx >= 0 && stackll.get(invalidIdx) == i) {
                array_01[i] = 0;
                invalidIdx--;
            }
            else array_01[i] = 1;
        }
        return array_01;
    }
    
    public static int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length, max = 0, counter = 0;
        for(int i = 0; i < n; i++) {
            if(nums[i] == 0) counter = 0; // 0 aaya then reinitiaize counter with 0
            else counter++; // 1 aaya then simply keep on increasing counter
            max = Math.max(max, counter); // Har baar max find karte jaao saare idx k liye
        }
        return max;
    }
    
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
