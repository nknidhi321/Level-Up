// https://www.lintcode.com/problem/533/description
// TC : nlogn

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
