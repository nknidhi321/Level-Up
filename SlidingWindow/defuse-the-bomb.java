// https://leetcode.com/problems/defuse-the-bomb/

class Solution {
    
    public int[] decrypt(int[] code, int k) {
        
        int n = code.length; 
        if(k == 0) return new int[n]; // When k == 0
        
        int start = 1, end = k; // When k > 0
        
        if(k < 0) { // When k < 0
            k = -k; // Making k positive
            
            // Think like circular array,
            // so for 1st idx the answer will be previous k elements
            start = code.length - k; 
            end = code.length - 1;
        }
        
        // Maintaining First Window Sum
        int sum = 0;
        for(int i = start; i <= end; i++) {
            sum += code[i];
        }
        
        // Maintaing next.. next.. window sum
        int[] ans = new int[n];
        for(int i = 0; i < n; i++) {
            ans[i] = sum;  // Forming ans of each idx
            
            // Sliding Window
            sum = sum - (code[start++ % n]);
            sum = sum + (code[++end % n]);
        }
        
        return ans;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
