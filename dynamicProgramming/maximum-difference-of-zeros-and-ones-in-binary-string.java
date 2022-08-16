// https://practice.geeksforgeeks.org/problems/maximum-difference-of-zeros-and-ones-in-binary-string4111/1

// Simple Kadan's
class Solution {
    
    int maxSubstring(String s) {
        int n = s.length();
        int max = -(int)1e9;
        int ssf = 0;
        for(int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';
            if(digit == 0) digit = 1; // 0 ko 1 kar do 
            else if(digit == 1) digit = -1; // 1 ko -1 kar do 
            
            // NOTE : 0 ko 1 kiya kuki (0's - 1's) ka count nikalna tha
            
            if(ssf < 0) {
                ssf = digit;
            }
            else {
                ssf += digit; 
            }
            
            max = Math.max(max, ssf);
        }
        return max;
    }
    
}
