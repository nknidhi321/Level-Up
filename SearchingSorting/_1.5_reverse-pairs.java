// https://leetcode.com/problems/reverse-pairs/

class Solution {
   
    public int reversePairs(int[] arr) {
        int n = arr.length;
        long[] count = new long[1];
        mergeSort(0, n - 1, arr, count);
        return (int)count[0];
    }

    public static long[] mergeSort(int lo, int hi, int[] arr, long[] count) {
        if (lo == hi) return new long[] {arr[lo]}; // Creating new array and adding that one element

        int mid = lo + (hi - lo) / 2;
        long[] a = mergeSort(lo, mid, arr, count);
        long[] b = mergeSort(mid + 1, hi, arr, count);
       
        return mergeTwoSortedArrays(a, b, count);
    }

    // used for merging two sorted arrays
    public static long[] mergeTwoSortedArrays(long[] a, long[] b, long[] count) {
        
        // This question would have been exactly same as count inversion if
        // nums[i] > 2 * nums[j] condition would not have been there
        // NOTE : We cannot find this answer in while loop of merging 2 sorted array
        // Ex : a = [1, 2, 3]   b = [1, 3] Dry run on this 2 sorted array TC 
        // if you want to perform your count in while loop of merging 2 sorted array
        // It will fail on (2, 1) [1th idx, 0th idx] 
        // 2 is not > 2 * 1, so you don't count this and inc your j for merging
        // but if you dec your j, you will the pair (3, 1) [2nd idx, 0th idx] 
        // because 3 > 2 * 1 fulfills the condition
        // So, you will have to count this in extra while loop
        
        // Checking the reverse pair condition and forming the ans
        int i = 0, j = 0;
        while(i < a.length && j < b.length) { // j check hone jaaega saare i se if there is any possible pair
            if(a[i] > 2 * b[j]) {
                count[0] += a.length - i; // j k saath saare rest of i's ek ek baar pair bna saktey hai
                j++; // now move the next j 
            }
            else { // Agar j, i k saath pair nahi bna paaya 
                i++; // then inc i, kya pta next wale i k saath, same j pair bna paaye
            }
        }

        // merging 2 sorted array
        i = 0; j = 0;
        int k = 0;
        long[] ans = new long[a.length + b.length];

        while(i < a.length && j < b.length) {
            if(a[i] <= b[j]) ans[k++] = a[i++]; // ith idx wala bnda chota hai
            else ans[k++] = b[j++]; // jth idx wala bnda chota hai
        }

        while (i < a.length) {
            ans[k++] = a[i++];
        }

        while (j < b.length) {
            ans[k++] = b[j++];
        }

        return ans;
    }    
   
}
