// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/

class Solution {
    
    public int findMin(int[] nums) {
        return findMin(0, nums.length - 1, nums);
    }
    
    public int findMin(int si, int ei, int[] arr) {
        while(si < ei) {
            int mid = si + (ei - si) / 2;
            if(arr[mid] < arr[ei]) ei = mid; // sorted
            else if(arr[mid] > arr[ei]) si = mid + 1; // unsorted  
            else ei--; // escaping duplicated from right 
        }
        return arr[ei];
    }
    
}
