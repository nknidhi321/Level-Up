// https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/

// Constrains too high to pass using array DP
// TLE
```
class Solution {
    
    public int minOperations(int[] nums, int tar) {
        int n = nums.length;
        Integer[][][] dp = new Integer[n][n][tar + 1];
        int ans = memo(0, n - 1, nums, tar, dp);
        return ans == (int)1e9 ? -1 : ans;
    }
    
    public int memo(int si, int ei, int[] nums, int tar, Integer[][][] dp) {
        if(tar == 0) return 0;
        if(si > ei) return (int)1e9;
        
        if(dp[si][ei][tar] != null) return dp[si][ei][tar];
        
        int currMin, min1 = (int)1e9, min2 = (int)1e9;
        if(tar - nums[si] >= 0) min1 = memo(si + 1, ei, nums, tar - nums[si], dp);
        if(tar - nums[ei] >= 0) min2 = memo(si, ei - 1, nums, tar - nums[ei], dp);
        
        if(min1 == (int)1e9 && min2 == (int)1e9) currMin = (int)1e9;
        else currMin = Math.min(min1, min2) + 1;
        return dp[si][ei][tar] = currMin;
    }
    
}
```
-------------------------------------------------------------
// Constrains too high to pass using map DP
// TLE
```
class Solution {
    
    public int minOperations(int[] nums, int tar) {
        int n = nums.length;
        Map<String, Integer> dp = new HashMap<>();
        int ans = memo(0, n - 1, nums, tar, dp);
        return ans == (int)1e9 ? -1 : ans;
    }
    
    public int memo(int si, int ei, int[] nums, int tar, Map<String, Integer> dp) {
        if(tar == 0) return 0;
        if(si > ei) return (int)1e9;
        
        if(dp.containsKey(si+"*"+ei+"*"+tar)) return dp.get(si+"*"+ei+"*"+tar);
        
        int currMin, min1 = (int)1e9, min2 = (int)1e9;
        if(tar - nums[si] >= 0) min1 = memo(si + 1, ei, nums, tar - nums[si], dp);
        if(tar - nums[ei] >= 0) min2 = memo(si, ei - 1, nums, tar - nums[ei], dp);
        
        if(min1 == (int)1e9 && min2 == (int)1e9) currMin = (int)1e9;
        else currMin = Math.min(min1, min2) + 1;
        dp.put(si+"*"+ei+"*"+tar, currMin);
        return dp.get(si+"*"+ei+"*"+tar);
    }
    
}
```
-------------------------------------------------------------
// TC : O(N)
// Passed
// Subarray
// Find largest subarray that forms (Sum - X)
// If there's no such array return -1
```
class Solution {
    
    public int minOperations(int[] arr, int x) {
        int n = arr.length;
        int sum = 0;
        for(int i = 0; i < n; i++) {
            sum += arr[i];
        }
        
        // Note all bnde are +ve's, 
        // So koi chota subarry ka chance he nahi h
        // So sbko choose krna parega 
        if(sum == x) return n; 
        
        int subarray_sum_equals_k = subarraySum(arr, sum - x);
        return subarray_sum_equals_k == -1 ? -1 : n - subarray_sum_equals_k;
    }
        
    // https://leetcode.com/problems/subarray-sum-equals-k/
    // There was count, here it's largest subarray
    public int subarraySum(int[] arr, int k) {
        int n = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        
        int ans = -1;
        int prefixSum = 0;
        for(int i = 0; i < n; i++) {
            prefixSum += arr[i];
            if(map.containsKey(prefixSum - k)) {
                ans = Math.max(ans, i - map.get(prefixSum - k));
            }
            if(!map.containsKey(prefixSum)) {
                map.put(prefixSum, i);
            }
        }
        return ans;
    }
    
}
// k = 11 - 5 = 6
// 1 1 4 2 3 arr
// 1 2 6 8 11 prefix
```
-------------------------------------------------------------
