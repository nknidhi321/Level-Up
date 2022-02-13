// https://leetcode.com/problems/asteroid-collision/

class Solution {
    
    public int[] asteroidCollision(int[] asteroids) {
        int n = asteroids.length - 1;
        Stack<Integer> stack = new Stack<>();
        
        for(int ele : asteroids) {
            if(ele > 0) stack.push(ele); // positive ele
            else { // negative ele
                
                // (+ve top and -ve ele) && (|top| < |ele|)
                while(!stack.isEmpty() && stack.peek() > 0 && Math.abs(stack.peek()) < Math.abs(ele)) { 
                    stack.pop(); // only top will explode
                }
                
                // Agar ab kuch bacha hai then check (+ve top and -ve ele) && (|top| >= |ele|)
                if(!stack.isEmpty() && stack.peek() > 0 && Math.abs(stack.peek()) >= Math.abs(ele)) {
                    if(Math.abs(stack.peek()) == Math.abs(ele)) stack.pop(); // both ele and top will explode
                    else if(Math.abs(stack.peek()) > Math.abs(ele)) {}  // only ele will explode
                }
                else stack.push(ele);
            }
        }
        
        int i = 0;
        int[] ans = new int[stack.size()];
        for(int val : stack) {
            ans[i++] = val;    
        }
        return ans;
    }
}
