// https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/

// More efficient
// Without Using Stack
// TC : O(N), SC : O(1)

class Solution {
    
    public int minAddToMakeValid(String s) {
        int n = s.length();
        int openClose_OR_isBal = 0, invalidClosingBrackets = 0;
        
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == '(') {
                openClose_OR_isBal++; // Increasing opening count
            }
            else if(s.charAt(i) == ')') {
                if(openClose_OR_isBal == 0) invalidClosingBrackets++; // Everything was balanced, and you got closing bracket
                else openClose_OR_isBal--; // Got closing? Balance out the opening count by cancelling out the effect i.e decrement
            }
        }
        
        // Now, openClose_OR_isBal will contain all invalid '(' opening brackets
        int invalidOpeningBrackets = openClose_OR_isBal;
        
        return invalidClosingBrackets + invalidOpeningBrackets; 
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Using Stack
// TC : O(N), SC : O(N)

class Solution {
    
    public int minAddToMakeValid(String s) {
        int n = s.length();
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < n; i++) { // Add all invalid brackets to the stack
            char c = s.charAt(i);
            if(c == '(') {
                stack.push(i); // '(' mile toh add kar do
            }
            else if(c == ')') {
                // Jiska pair milte jaaye usko pop kar do
                if(!stack.isEmpty() && s.charAt(stack.peek()) == '(') stack.pop();  
                // ')' v add kar do agar stack k top pe uka pair nahi mila toh
                else stack.push(i);
            }
        }
        return stack.size();
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
