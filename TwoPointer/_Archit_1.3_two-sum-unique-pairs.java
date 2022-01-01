// https://www.lintcode.com/problem/587

public class Solution {

    public int twoSum6(int[] nums, int target) {

        int n = nums.length;
        Arrays.sort(nums);

	      // Two Pointer
        int count = 0;
        int start = 0, end = nums.length - 1;
        while(start < end) {
            int sum = nums[start] + nums[end];
	    
	          // Got one of the target
            if(sum == target) {
                count++;
               
                // Since unique pairs is asked so surpass all the elements from end which are same   
                while(end - 1 >= 0 && (nums[end - 1] == nums[end])) end--;
                end--;
              
                // Since unique pairs is asked so surpass all the elements from start which are same  
                while(start + 1 < n && (nums[start + 1] == nums[start])) start++;
                start++;
            }
            else if(sum < target) start++;
            else end--;
        }
        return count;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
