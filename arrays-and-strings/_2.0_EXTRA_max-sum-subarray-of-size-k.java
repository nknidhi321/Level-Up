// https://practice.geeksforgeeks.org/problems/max-sum-subarray-of-size-k5313/1/#
// Maintain window of size K and keep cal your ans

class Solution {
    
    public static long maximumSumSubarray(int K, ArrayList<Integer> list, int n){
        int si = 0, ei = 0;
        long sum = 0, max = 0;
        while(ei < n) {
            sum += list.get(ei); // Jab v aao khud ko sum me jor lo
            if(ei - si + 1 == K) { // Agar window ki criteria me aare ho toh 
                max = Math.max(max, sum); // ab tak k ans se compete krwa lo
                sum -= list.get(si); // Removing starting ele from sum, for the next upcoming widow
                si++;
            }
            ei++;
        }
        return max;        
    }
    
}
