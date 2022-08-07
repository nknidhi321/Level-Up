// https://leetcode.com/problems/search-in-rotated-sorted-array/

// Either your left half is sorted or right half is sorted, 
// TAKE YOUR DECISION BASED ON SORTED REGION, like BS.
// suppose your target lies in sorted region, then
// you move into that sorted region then no such thing exists like,
// either left half is sorted or right half is sorted, now that's just simple BS
// You step into any "else if" or "else" below both are actually same.
// Whereas, if your target lies in unsorted region, then
// You will keep exploring a sorted region and an unsorted region. 
        

// Iterative // Binary Search

```
class Solution {
    
    public int search(int[] nums, int target) {
        return search_Iterative(nums,0, nums.length - 1, target);
    }
    
    public static int search_Iterative(int[] nums, int left, int right, int target){
        while(left <= right) {
            
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) return mid;
            
            // Now, either left half is sorted or right half is sorted, 
            // TAKE YOUR DECISION BASED ON SORTED REGION, like BS.
            // suppose your target lies in sorted region, then
            // you move into that sorted region then no such thing exists like,
            // either left half is sorted or right half is sorted, now that's just simple BS
            // You step into any "else if" or "else" below both are actually same.
            // Whereas, if your target lies in unsorted region, then
            // You will keep exploring a sorted region and an unsorted region. 
        

            // Left haf is sorted
            else if(nums[left] <= nums[mid]) { // equalto is important, when you have only single element
                if(nums[left] <= target && target < nums[mid]) right = mid - 1; // check if your target lies in this range
                else left = mid + 1;
            }

            // Right half is sorted
            else {
                if(nums[mid] < target && target <= nums[right]) left = mid + 1; // check if your target lies in this range
                else right = mid - 1;
            }
        }
        return -1;
    }
    
}
```

--------------------------------------------------------------------------------------------------------------------------------------------------

// Recursive // Binary Search

```
class Solution {
    
    public int search(int[] nums, int target) {
        return search_Rec(nums,0, nums.length - 1, target);
    }
    
    public static int search_Rec(int[] nums, int left, int right, int target) {
        if(left > right) return -1;
        
        int mid = left + (right - left) / 2;       
        if(nums[mid] == target) return mid;

        // Now, either left half is sorted or right half is sorted, 
        // TAKE YOUR DECISION BASED ON SORTED REGION, like BS.
        // suppose your target lies in sorted region, then
        // you move into that sorted region then no such thing exists like,
        // either left half is sorted or right half is sorted, now that's just simple BS
        // You step into any "else if" or "else" below both are actually same.
        // Whereas, if your target lies in unsorted region, then
        // You will keep exploring a sorted region and an unsorted region. 
        
        // Left half is sorted
        else if(nums[left] <= nums[mid]) { // here equalto is important, when you have only single element
            if(nums[left] <= target && target < nums[mid]) return search_Rec(nums, left, mid - 1, target); // check if your target lies in this range
            else return search_Rec(nums, mid + 1, right, target); // tar is already checked with mid, so no need to write target <= nums[mid] 
        }
        
        // Right half is sorted
        else {
            if(nums[mid] < target && target <= nums[right]) return search_Rec(nums, mid + 1, right, target); // check if your target lies in this range
            else return search_Rec(nums, left, mid - 1, target); // tar is already checked with mid, so no need to write nums[mid] <= target
        }
    }
    
}
```
----------------------------------------------------------------------------------------------------------------------------------------------------------
