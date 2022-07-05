// https://www.lintcode.com/problem/883/

public class Solution {

    public int findMaxConsecutiveOnes(int[] arr) {
        int n = arr.length, max = 0, zeroCount = 0;
        int si = 0, ei = 0;
        
        while(ei < n) {
            if(arr[ei] == 0) zeroCount++;
            
            while(zeroCount > 1) { // Jaise h 2 0's aa gaya, window reduce karo
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
