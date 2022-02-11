// https://leetcode.com/problems/validate-stack-sequences/

class Solution {
    
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length;
        int j = 0;
        Stack<Integer> stack = new Stack<>();
        
        for(int i = 0; i < n; i++) {
            stack.push(pushed[i]);
        
            // Jab v push karo toh stack me check karo ki kya stack k top pe wahi element hai
            // jisko hame pop karwana hai popped array se check kar k, if yes then pop
            
            while(!stack.isEmpty() && stack.peek() == popped[j]) {  
                stack.pop();
                j++;
            }
        }
        
        // Agar sbko push and krwane k baad stack empty hai => Validated (Possible)
        // Ya fir ye dekh lo ki jth pointer n tak pahuch paaya ya nahi => Agar nahi pahuch paaya that means
        // stack me ab v kuch elements hai jo ki pop nahi ho paaenge popped array k sequence me
        
        return stack.isEmpty();    // return j == n;
    }
}
