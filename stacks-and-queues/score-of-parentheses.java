// https://leetcode.com/problems/score-of-parentheses/

// Best approach can be done without stack, it is not implemented here

class Solution {
    
    public int scoreOfParentheses(String s) {
        int n = s.length();
        Stack<Integer> stack = new Stack<>();
        
        for(int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if(ch == '(') stack.push(-1); // Representing '(' opening with -1
            else { // s.charAt(i) == ')'
                if(stack.peek() == -1) { // If I'm closing and stack's top is opening, solve and push
                  stack.pop();
                  stack.push(1);  // Acc. to given rule
                }
                else { // If I'm closing and stack's top is not opening, then add all elemens from stack's top until I get an opening
                    int sum = 0;
                    while(!stack.isEmpty() && stack.peek() != -1) { // You can skip !stack.isEmpty(), since s will always be balanced
                        sum += stack.pop();
                    }
                    stack.pop(); // Remove the last left opening bracket
                    stack.push(2 * sum); // Acc. to given rule
                }
            }
        }
        
        int score = 0;
        while(!stack.isEmpty()) {
            score += stack.pop();
        }
        return score;
    }
}
