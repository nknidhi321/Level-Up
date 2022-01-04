// https://leetcode.com/problems/3sum-closest/

// TC : O(N ^ 2)

class Solution {
    
    public static int n;
    
    public int threeSumClosest(int[] nums, int target) {
        n = nums.length;
        Arrays.sort(nums);
        
        int ans = 0, min = Integer.MAX_VALUE;
        for(int i = 0; i <= n - 3; i++) { // Fixing the ith element and calling 2 pointer from [i+1 to n-1]
            int twoSumClosest = twoSumClosest(i + 1, n - 1, target - nums[i], nums);
            int threeSumClosest = nums[i] + twoSumClosest;
            
            int diff = Math.abs(threeSumClosest - target);  // To find the closeness
            if(diff < min) {
                min = diff;
                ans = threeSumClosest;
            }
        }  
        return ans;
    }
    
    
    public int twoSumClosest(int start, int end, int target, int[] nums) {
        int ans = 0, min = Integer.MAX_VALUE;
        
        // Two pointer
        while(start < end) {
            int sum = nums[start] + nums[end];
            if(sum < target) start++;
            else end--;
            
            int diff = Math.abs(sum - target); // To find the closeness
            if(diff < min) {
                min = diff;
                ans = sum;
            }
        }
        return ans;
    }
    
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
