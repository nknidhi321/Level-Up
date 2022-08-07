// https://leetcode.com/problems/search-in-rotated-sorted-array-ii/

class Solution {

    public boolean search(int[] nums, int target) {
        return search_Iterative(nums, 0, nums.length - 1, target);
    }
    
    public static boolean search_Iterative(int[] nums, int left, int right, int target) {
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target || nums[left] == target || nums[right] == target) return true;
          
            // Left half's si is strictly smaller than mid
            else if(nums[left] < nums[mid]) { // Do not use equalto, since u have to check on strictly
                if(nums[left] <= target && target < nums[mid]) right = mid - 1; // tar exists in [si, mid)
                else left = mid + 1;
            }

            // Right half's ei is strictly greater than mid
            else if(nums[mid] < nums[right]) { // Do not use equalto, since u have to check on strictly
                if(nums[mid] < target && target <= nums[right]) left = mid + 1; // tar exists in (mid, right]
                else right = mid - 1;
            }
            else {  // To handle duplicates
                // NOTE : If u have only single element which is not equalTo tar, u will cross the range
                // You will iterate one by one until when u find strictly sorted range
                left++;
                right--;
            }
        }
        return false;
    }
    
}
```
