// https://leetcode.com/problems/search-in-rotated-sorted-array/

// Either your left half is sorted or right half is sorted, 
// TAKE YOUR DECISION BASED ON SORTED REGION, like BS.
// suppose your target lies in sorted region, then
// you move into that sorted region then no such thing exists like,
// either left half is sorted or right half is sorted, now that's just simple BS
// You step into any "else if" or "else" below both are actually same.
// Whereas, if your target lies in unsorted region, then
// You will keep exploring a sorted region and an unsorted region. 
        
// TC : logN
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
// TC : logN
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
// TC : O(2logN)
// First logN to find minIdx and 2nd logN to find the target  

```
class Solution {
    
    public int search(int[] nums, int target) {
        int minIdx = findMin(nums);
        if(nums[minIdx] == target) return minIdx;
        
        int idx1 = search_Iterative(nums, 0, minIdx - 1, target);
        if(idx1 != -1) return idx1;
        
        int idx2 = search_Iterative(nums, minIdx + 1, nums.length - 1, target);
        if(idx2 != -1) return idx2;
        
        return -1;
    }
    
    public int findMin(int[] arr) {
        int n = arr.length;
        int si = 0, ei = n - 1;
        while(si < ei) { // NOTE : = mat lgana
            int mid = si + (ei - si) / 2;
            // Compare mid bnda with "rightmost" bnda
            if(arr[mid] <= arr[ei]) { // right region is sorted => Udhar mko min nahi milega
                ei = mid; // But mid can be your min so assign with mid and not mid - 1
            }
            else { // left region is sorted
                si = mid + 1; 
            }
        }
        return ei; // Either return si or ei, both will obvio point to same bnda
    }
    
    // Simple BS
    public static int search_Iterative(int[] nums, int left, int right, int target){
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) return mid;
            else if(nums[mid] < target) left = mid + 1;                
            else right = mid - 1;
        }
        return -1;
    }
    
}
```
--------------------------------------------------------------------------------------------------------------------------------------------------------
