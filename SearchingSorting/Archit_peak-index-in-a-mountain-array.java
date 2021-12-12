//https://leetcode.com/problems/peak-index-in-a-mountain-array/

class Solution {
    
    public int peakIndexInMountainArray(int[] arr) {
        int n = arr.length;
        int left = 0;
        int right = arr.length - 1;
        
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(mid - 1 >= 0 && mid + 1 < n && arr[mid - 1] < arr[mid] && arr[mid] > arr[mid + 1]) return mid;
            else if(mid + 1 < n && arr[mid + 1] < arr[mid]) right = mid - 1;
            else left = mid + 1;
        }
        return -1;
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
