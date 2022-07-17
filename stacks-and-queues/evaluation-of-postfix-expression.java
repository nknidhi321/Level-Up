// https://practice.geeksforgeeks.org/problems/evaluation-of-postfix-expression1735/1

class Solution {

    public static int evaluatePostFix(String s) {
        int n = s.length();
        Stack<Integer> stack = new Stack<>();
        
        for(int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if(ch == '*' || ch == '/' || ch == '+' || ch == '-') {
                int val2 = stack.pop();
                int val1 = stack.pop();
                
                int res;
                if(ch == '*') res = val1 * val2; 
                else if(ch == '/') res = val1 / val2;
                else if(ch == '+') res = val1 + val2;
                else res = val1 - val2; //  if(ch == '-') 
                
                stack.push(res);
            }
            else stack.push(ch - '0');
        }
        return stack.pop();
    }
    
}
