// https://www.lintcode.com/problem/918

public class Solution {
    
    public static int n;

    public int threeSumSmaller(int[] nums, int target) {
        n = nums.length;
        Arrays.sort(nums);
        int count = 0;
        for(int i = 0; i <= n - 3; i++) {
            count += twoSumSmaller(i + 1, n - 1, target - nums[i], nums);
        }
        return count;
    }

    public int twoSumSmaller(int start, int end, int target, int[] nums) {
        int count = 0;
        while(start < end) {
            int sum = nums[start] + nums[end];
            if(sum < target) {
                count += end - start;
                start++;
            }
            else end--;
        }
        return count;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
