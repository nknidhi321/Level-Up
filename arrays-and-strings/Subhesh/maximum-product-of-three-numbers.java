// https://leetcode.com/problems/maximum-product-of-three-numbers/

// Approach:-
// 2 most negative and 1 most positive can give you the max OR 3 sbse max numbers can give you the maximum
// Math.max((min1 * min2 * max3), (max1 * max2 * max3))

// Ex TC : nums = [-12, -10, 1, 3, 5, 8]
// Here max will be -12 * -10 * 8  {2 sabse minimum and ek sbse maximum}

// TC : O(N) 
// Finding mins and maxs in one pass
public class Solution {
    
    public int maximumProduct(int[] nums) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        for (int n: nums) {
            
            // Updating mins
            if (n <= min1) {            // n is lesser than min1 and min2
                min2 = min1;
                min1 = n;
            } else if (n <= min2) {     // n lies between min1 and min2
                min2 = n;
            }
            
            // Updating maxs
            if (n >= max1) {            // n is greater than max1, max2 and max3
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n >= max2) {     // n lies betweeen max1 and max2
                max3 = max2;
                max2 = n;
            } else if (n >= max3) {     // n lies betwen max2 and max3
                max3 = n;
            }
            
        }
        
        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }
    
}
 
//===========================================================================================================================================
// Using sorting and finding mins and maxs

class Solution {
    
    public int maximumProduct(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        return Math.max(nums[0] * nums[1] * nums[n - 1], nums[n - 1] * nums[n - 2] * nums[n - 3]);
    }
    
}

//===========================================================================================================================================
