// https://leetcode.com/problems/subarrays-with-k-different-integers/

class Solution {
    
    public int subarraysWithKDistinct(int[] arr, int k) {
        // Suppose k is 4
        // So, atMostK(arr, k)     => will fetch answers for k = 0, 1, 2, 3, 4
        // And atMostK(arr, k - 1) => will fetch answers for k = 0, 1, 2, 3
        // (0 + 1 + 2 + 3 + 4) - (0 + 0 + 1 + 2 + 3) => 4 = k
        return atMostK(arr, k) - atMostK(arr, k - 1);
    }
    
    public int atMostK(int[] arr, int k) {
        if(k == 0) return 0;
        
        int n = arr.length;
        int si = 0, ei = 0;
        int count = 0; 
        Map<Integer, Integer> map = new HashMap<>();
        
        while(ei < n) {
            map.put(arr[ei], map.getOrDefault(arr[ei], 0) + 1); // increasing freq 
            
            // si ko tab tak badhao jab tak map ka size k na ho jaaye 
            // => Window me sirf k unique char hone chahiye
            while(si < ei && map.size() > k) {
                int freq = map.get(arr[si]);
                if(--freq == 0) map.remove(arr[si]); // Jiska freq 0 ho jaaye, usko map se uda do
                else map.put(arr[si], freq);
                si++;
            }
            
            // At this point [si, ei] me "atmost" k diff bnde hai, note k can be 0 also
            // end pt. ko pakro and left se ek ek kam karte jaao to obtain subarrays 
            count += ei - si + 1; // Total number of subarrays with atmost k diff
            ei++;
        }
        
        return count;
   }
   
}
