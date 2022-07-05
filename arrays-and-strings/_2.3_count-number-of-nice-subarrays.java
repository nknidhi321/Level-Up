// https://leetcode.com/problems/count-number-of-nice-subarrays/

class Solution {
    
    public int numberOfSubarrays(int[] arr, int k) {
        // Suppose k is 4
        // So, atMostK(arr, k)     => will fetch answers for k = 0, 1, 2, 3, 4
        // And atMostK(arr, k - 1) => will fetch answers for k = 0, 1, 2, 3
        // (0 + 1 + 2 + 3 + 4) - (0 + 0 + 1 + 2 + 3) => 4 = k
        return atMostK(arr, k) - atMostK(arr, k - 1);  
    }
    
    public int atMostK(int[] arr, int k) {
        int n = arr.length;
        int si = 0, ei = 0;
        int oddCount = 0, ans = 0;
        
        while(ei < n) {
            if((arr[ei] & 1) != 0) { // arr[ei] % 2 == 1 : Odd
                oddCount++;
            }
            
            while(oddCount > k) {
                if((arr[si++] & 1) != 0) { // arr[si++] % 2 == 1 : Odd
                    oddCount--;
                }
            }
            
            // At this point [si, ei] me "atmost" k odd bnde hai, note k can be 0 also,
            // end pt. ko pakro and left se ek ek kam karte jaao to obtain subarrays 
            ans += ei - si + 1; // Total number of subarrays with atmost k odds

            ei++;
        }
        return ans;
    }
    
}
