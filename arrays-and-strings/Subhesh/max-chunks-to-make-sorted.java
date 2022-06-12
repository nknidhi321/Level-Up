// https://leetcode.com/problems/max-chunks-to-make-sorted/

// You have to divide the complete array into groups of subarrays such that
// if you sort the subarrays within the group, the whole array becomes sorted.

// Now observe that the numbers wil be from [0, n-1] range
// So, xth bnda would want to be at the xth idx place if the group of array will be sorted
// So, if max ele of the group can be placed at the correct position then that becomes one chunk

class Solution {
    
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int max = -1;
        int chunkCount = 0;
        
        for(int i = 0; i < n; i++) {
            max = Math.max(max, arr[i]);
            if(max == i) { // xth max bnda at xth idx place pe pahuch gaya
                chunkCount++; // So, ye ek group form kar sakta hai
                max = -1; // For the next group of chunks //Nai v karoge toh v chalega, writing just for clearity
            }
        }
        return chunkCount;   
    }
    
}
