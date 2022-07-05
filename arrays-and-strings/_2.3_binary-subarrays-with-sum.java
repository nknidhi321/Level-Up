// https://leetcode.com/problems/binary-subarrays-with-sum/

class Solution {
    
    public int numSubarraysWithSum(int[] arr, int tar) {
        // Suppose k is 4
        // So, atMostK(arr, k)     => will fetch answers for k = 0, 1, 2, 3, 4
        // And atMostK(arr, k - 1) => will fetch answers for k = 0, 1, 2, 3
        // (0 + 1 + 2 + 3 + 4) - (0 + 0 + 1 + 2 + 3) => 4 = k
        return atMostK(arr, tar) - (tar == 0 ? 0 : atMostK(arr, tar - 1));  
    }
    
    public int atMostK(int[] arr, int tar) {
        int n = arr.length;
        int si = 0, ei = 0;
        int sum = 0, ans = 0;
        
        while(ei < n) {
            sum += arr[ei];
            
            while(sum > tar) { // Jab tak sum bada aara hai tar se, apni window ko chota karo
                sum -= arr[si++];
            }
            
            // At this point [si, ei] me "atmost" [si, ei] ka sum <= tar hoga, note tar can be 0 also,
            // end pt. ko pakro and left se ek ek kam karte jaao to obtain subarrays 
            ans += ei - si + 1; // Total number of subarrays with atmost [si, ei] whose sum <= tar

            ei++;
        }
        return ans;
    }
    
}
