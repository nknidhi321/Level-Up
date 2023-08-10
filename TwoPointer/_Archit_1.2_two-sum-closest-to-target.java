// https://www.lintcode.com/problem/533/description
// TC : nlogn
/*
        Given an int array nums and an int target. Find two integers in nums such that the sum is closest to target.
        Example 1:
        
        Input: nums = [1, 2, 3, 4, 5], target = 10
        Output: [4, 5]
        Example 2:
        
        Input: nums= [-1, 2, 1, -4], target = 4
        Output: [2, 1]
*/

    
public class Solution {

    public int twoSumClosest(int[] nums, int target) {
    
        int n = nums.length;
        Arrays.sort(nums);

        int probableAns = Integer.MAX_VALUE;
        int start = 0, end = n - 1;
        while(start < end) {
            int sum = nums[start] + nums[end];
            if(sum < target) start++;
            else end--;
            probableAns = Math.min(probableAns, Math.abs(sum - target));
        }
        return probableAns;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
