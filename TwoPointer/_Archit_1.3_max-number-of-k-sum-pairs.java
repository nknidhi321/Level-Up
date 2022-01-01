// https://leetcode.com/problems/max-number-of-k-sum-pairs/

```
class Solution {
    
    public int maxOperations(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);

		    // Two Pointer
        int count = 0;
        int start = 0, end = nums.length - 1;
        while(start < end) {
            int sum = nums[start] + nums[end];
            if(sum == target) {
                count++;
                start++;
                end--;
            }
            else if(sum < target) start++;
            else end--;
        }
        return count;
    }
}
```
-------------------------------------------------------------
