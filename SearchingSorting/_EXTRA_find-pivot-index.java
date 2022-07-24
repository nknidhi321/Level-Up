// https://leetcode.com/problems/find-pivot-index/

// Find prefixSum and suffixSum without taking O(N) space
// prefixSum can be found while u iterate over the array, 
// suffixSum can be found by subtracting overallSum - prefixSum - nums[i]
// TC : O(N), SC : O(1)
```
class Solution {
    
    public int pivotIndex(int[] nums) {
        int n = nums.length;

        int overallSum = 0;
        for(int i = 0; i < n; i++) {
            overallSum += nums[i];
        }
        
        // NOTE : overallSum - prefixSum - nums[i] will act as suffixSum
        
        int prefixSum = 0;
        for(int i = 0; i < n; i++) {
            if(prefixSum == overallSum - prefixSum - nums[i]) return i;
            prefixSum += nums[i];
        }
        return -1;
    }
}
```
-------------------------------------------------------------
// Find prefixSum && suffixSum and find your ans
// TC : O(N) 
// SC : 2O(N) 
```
class Solution {
    
    public int pivotIndex(int[] nums) {
        int n = nums.length;

        int[] prefixSum = new int[n];
        int[] suffixSum = new int[n];
        
        prefixSum[0] = nums[0];
        suffixSum[n - 1] = nums[n - 1]; 
        
        for(int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i]; 
            suffixSum[n - i - 1] = suffixSum[n - i] + nums[n - i - 1];
        }
        
        for(int i = 0; i < n; i++) {
            int left = i == 0 ? 0 : prefixSum[i - 1];
            int right = i == n - 1 ? 0 : suffixSum[i + 1];
            if(left == right) return i;
        }
        return -1;
    }
}
```
