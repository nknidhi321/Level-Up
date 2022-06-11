// https://leetcode.com/problems/maximum-product-of-three-numbers/

// Approach:-
// 2 most negative and 1 most positive can give you the max OR 3 sbse max numbers can give you the maximum
// Math.max((min1 * min2 * max3), (max1 * max2 * max3))

// Ex TC : nums = [-12, -10, 1, 3, 5, 8]
// Here max will be -12 * -10 * 8  {2 sabse minimum and ek sbse maximum}
        
class Solution {
    
    public int maximumProduct(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        return Math.max(nums[0] * nums[1] * nums[n - 1], nums[n - 1] * nums[n - 2] * nums[n - 3]);
    }
    
}
