// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/

class Solution {
    
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
        return arr[ei]; // Either return si or ei, both will obvio point to same bnda
    }
    
}
