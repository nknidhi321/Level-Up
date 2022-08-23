// https://leetcode.com/problems/median-of-two-sorted-arrays/

// Merge 2 sorted arrays fir median nikal lo
```
class Solution {
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] ans = mergeTwoSortedArrays(nums1, nums2);
        int len = ans.length;
        if(ans.length % 2 == 0) {
            int mid1 = ans[len/2 - 1];
            int mid2 = ans[len/2];
            return (mid1 + mid2) / 2.0;
        }
        return ans[len/2] * 1.0;
    }
    
    // used for merging two sorted arrays
    public int[] mergeTwoSortedArrays(int[] a, int[] b){
        int i = 0, j = 0, k = 0;
        int[] ans = new int[a.length + b.length];
        while(i < a.length && j < b.length){
            if(a[i] <= b[j]){
              ans[k] = a[i];
              i++;
              k++;
            }else{
              ans[k] = b[j];
              j++;
              k++;
            }
        }

        while(i < a.length){
          ans[k] = a[i];
          k++;
          i++;
        }

        while(j < b.length){
          ans[k] = b[j];
          k++;
          j++;
        }

        return ans;
    }
    
}
```

-------------------------------------------------------------------------------------

// Binary Search
```
class Solution {
    
    public double findMedianSortedArrays(int[] arr1, int[] arr2) {
        // sec arr will always have be the larger
        if(arr1.length > arr2.length) {
            int[] temp = null;
            temp = arr1;
            arr1 = arr2;
            arr2 = temp;
        }

        int n1 = arr1.length;
        int n2 = arr2.length;

        int lo = 0, hi = n1;
        
        while(lo <= hi) {
            int cut1 = lo + (hi - lo) / 2; // No. of elements from arr1 => [0, cut1 - 1] 
            int cut2 = (n1 + n2 + 1) / 2 - cut1; // rem no. of elememys from arr2 => [0, cut2 - 1]
        
            int left1 = cut1 == 0 ? -(int)1e9 : arr1[cut1 - 1];
            int left2 = cut2 == 0 ? -(int)1e9 : arr2[cut2 - 1];
                
            int right1 = cut1 == n1 ? (int)1e9 : arr1[cut1];
            int right2 = cut2 == n2 ? (int)1e9 : arr2[cut2];
            
            if(left1 <= right2 && left2 <= right1) {
                if((n1 + n2) % 2 == 0) {
                    int max1 = Math.max(left1, left2);
                    int max2 = Math.min(right1, right2);
                    return (max1 + max2) / 2.0;
                }
                else {
                    return Math.max(left1, left2);
                }
            }
            else if(left1 > right2) {
                hi = cut1 - 1;
            }
            else {
                lo = cut1 + 1;
            }
        }
        
        return -1.0;
    }
    
}
```
------------------------------------------------------------------------------------------------
