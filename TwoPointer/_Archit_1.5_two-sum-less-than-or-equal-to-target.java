// https://www.lintcode.com/problem/609

/*
      Example : nums = [1, 3, 4, 10, 12, 15]       target = 14
      
      When start is at element 1 and end is at 12, calculate sum : 1 + 12 = 13, which is less than 14
      then 1 can make pair with elements like 3, 4, 10, 12 which is lesser than equal to 14
      So, if I'm making pair of (1, 12) then it is obvious that 1 se bade and 12 se chote ya equal saare he target = 14 se kam aaenge,
      therefore, count += end - right 
*/

// TC : O(N)

public class Solution {
 
    public int twoSum5(int[] nums, int target) {
 
        int n = nums.length;
        Arrays.sort(nums);

        int count = 0;
        int start = 0, end = n - 1;
        while(start < end) {
            int sum = nums[start] + nums[end];
            if(sum <= target) {
                count += end - start;
                start++;
            }
            else end--;
        }
        return count;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Brute Force
// O(N^2)

public class Solution {
 
    public int twoSum5(int[] nums, int target) {
 
        int n = nums.length;
        int count = 0;
        for(int i = 0; i < n - 1; i++) {
            for(int j = i + 1; j < n; j++) {  // j will start from i + 1, inorder to exclude permutation of pairs like (1, 12) and (12, 1) both are same here
                if(nums[i] + nums[j] <= target) {
                    count++;
                }
            }
        }
        return count;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
