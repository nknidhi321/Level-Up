// https://leetcode.com/problems/merge-sorted-array/
// Simple merging of 2 sorted array from end

// Approach:-
// Since array sorted hai, so sbse bada bnda dono array k last me he exist krta hoga
// NOTE : Front se nai kr sakte kuki nums1 pe ans bnate waqt values override ho jaaengi
// So, compare from end of both the array and keep on forming the ans

```
class Solution {
    
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1;
        int k = nums1.length - 1;
        
        // Merging of the array till anyone of the array exhausts
        while(i >= 0 && j >= 0) {
            if(nums1[i] >= nums2[j]) nums1[k--] = nums1[i--];
            else nums1[k--] = nums2[j--];
        }
        
        // Copying the rest of the elements if it exists
        while(i >= 0) {
            nums1[k--] = nums1[i--];    
        }
        
        // Copying the rest of the elements if it exists
        while(j >= 0) {
            nums1[k--] = nums2[j--];
        }
        
    }
    
}
```
--------------------------------------------------------------------------
