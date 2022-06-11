// https://leetcode.com/problems/squares-of-a-sorted-array/

/*
    TC : O(N), SC : O(1)
    Two pointer Approach 
    
    Approach :- 
    --------
    The maximum element after squaring can be found at 1st or last position (if it is sorted), 
    store the max of the 2 elements and shift the respective pointer and so on..
*/

```
class Solution {
    
    public int[] sortedSquares(int[] nums) {
        int len = nums.length;
        int left = 0, right = len - 1;
        int k = len - 1;
        int[] ans = new int[len];
    
        while(left <= right) {
            int leftSq = nums[left] * nums[left];
            int rightSq = nums[right] * nums[right];            
            if(leftSq >= rightSq) { 
                ans[k--] = leftSq;
                left++;
            }
            else if(rightSq > leftSq) {
                ans[k--] = rightSq;
                right--;
            }
        }
        return ans;
    }
    
}
```
--------------------------------------------------------------------------------------------------
