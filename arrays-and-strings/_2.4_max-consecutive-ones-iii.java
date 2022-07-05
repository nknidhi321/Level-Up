// https://leetcode.com/problems/max-consecutive-ones-iii/

class Solution {
    
    public int longestOnes(int[] arr, int k) {
        int n = arr.length, max = 0, zeroCount = 0;
        int si = 0, ei = 0;
        
        while(ei < n) {
            if(arr[ei] == 0) zeroCount++;
            
            while(zeroCount > k) { // Jaise he k se zyada 0's aa gaya, window reduce karo
                if(arr[si++] == 0) {
                    zeroCount--;
                }
            }
            
            max = Math.max(max, ei - si + 1);
            ei++;
        }

        return max;
    }
    
}
