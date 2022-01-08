// https://leetcode.com/problems/rotate-array/
// https://www.youtube.com/watch?v=xP1iY5mIvmw


// Intuitive

// Reverse All
// Reverse k elements from starting
// Reverse the rest k + 1 to last

```
class Solution {
    
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        
        /* 
          If k would be negative then 
            -> do +n extra to make k positive
            -> % n is not necessarily needed because (-ve number + n) would always keep you in range [0 to n-1]
        */
        
        // Below formula will handle both postive and negative k
        k = ((k % n) + n) % n; 
        
        
        /* 
            When you see the rotated output array, you can observe
            Reverse pattern of the array + Maintanance of their relative order is required accordingly
        */
        
        // Reverse whole array 
        reverse(0, n - 1, nums);  
        
        // Now, reverse the relative Order
        reverse(0, k - 1, nums);  
        reverse(k, n - 1, nums);
    }
    
    public static void reverse(int si, int ei, int[] nums) {
        while(si < ei) {
            int temp = nums[si];
            nums[si++] = nums[ei];
            nums[ei--] = temp;
        }
    }
}
```
--------------------------------------------------------------------------------------------------------------------

// Similar approach, but not intuitive
// Ignore

// Reverse k elements from last
// Reverse first to k  - 1 elements
// Reverse All

```
class Solution {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;    
        if(k == 0 || nums.length == 1)
            return;        
        reverse(nums, nums.length - k, nums.length - 1);      
        reverse(nums, 0, nums.length - k - 1);
        reverse(nums, 0, nums.length - 1);
    }
    
    public static void reverse(int[] nums, int i, int j) {
        while(i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }
}
```

----------------------------------------------------------------------------------------------------------------------------
