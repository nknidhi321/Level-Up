// https://www.lintcode.com/problem/443

/*
      Example : nums = [1, 3, 4, 10, 12, 15]       target = 14
      
      When start is at element 1 and end is at 15, calculate sum : 1 + 15 = 16, which is greater than 14
      then 15 can make pair with elements like 1, 3, 4, 10, 12 which is strictly greater than 14
      
      (15, 1)   >    14
      (15, 3)   >    14
      (15, 4)   >    14
      (15, 10)  >    14
      (15, 12)  >    14
      
      NOTE : end sbke saath pair bnaega till start
      
      So, if I'm making pair of (15, 1) then it is obvious that 1 se bade and 15 se chote ya equal saare he target = 14 se zyada aaenge,
      therefore, count += end - right 
*/

// TC : O(N)

public class Solution {

    public int twoSum2(int[] nums, int target) {
    
        int n = nums.length;
        Arrays.sort(nums);

        int count = 0;
        int start = 0, end = n - 1;
        while(start < end) {
            int sum = nums[start] + nums[end];
            if(sum > target) {
                count += end - start;
                end--;
            }
            else start++;
        }
        return count;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
