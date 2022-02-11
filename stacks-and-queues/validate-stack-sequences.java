// https://leetcode.com/problems/validate-stack-sequences/

class Solution {
    
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length;
        int j = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < n; i++) {
            stack.push(pushed[i]);
            while(!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();    
    }
}
