// https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/

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
