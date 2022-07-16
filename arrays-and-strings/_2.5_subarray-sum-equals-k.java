// https://leetcode.com/problems/subarray-sum-equals-k/

// current state pe (preFixSum - k) nikalo, and agar wo exist krta hai,
// then jaha jaha v (preFixSum - k) aaya, uske next bnde se tum tak ek subarray milega.

class Solution {
 
    public int subarraySum(int[] arr, int k) {
        int n = arr.length, preFixSum = 0, ans = 0;
        Map<Integer, Integer> map = new HashMap<>(); // {prefixSum, freq}
        map.put(0, 1); // shuru se sbko lelo
        for(int i = 0; i < n; i++) {
            preFixSum += arr[i];
            if(map.containsKey(preFixSum - k)) ans += map.get(preFixSum - k);
            map.put(preFixSum, map.getOrDefault(preFixSum, 0) + 1);
        }
        return ans;
    }
    
}
