// https://practice.geeksforgeeks.org/problems/inversion-of-array-1587115620/1
// You just have to count sort krne k liye kitne baar idhar udhar karoge elements ko

// Bubble Sort // TLE

class Solution {

    public static long inversionCount(long arr[], long n) {
        long count = 0;
        for(int i = 0; i < n - 1; i++) {
            for(int j = 0; j < n - i - 1; j++) {
                if(arr[j] > arr[j + 1]) {
                    count++;                  // Kitne baar idhar udhar karoge elements ko
                    swap(j, j + 1, arr);
                }
            }
        }
        return count;
    }
    
    public static void swap(int idx1, int idx2, long[] arr) {
        long temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }
    
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Merge Sort // Array merge krte waqt answer bna lo

class Solution {

    public static long inversionCount(long arr[], long n) {
        long[] count = new long[1];
        mergeSort(0, n - 1, arr, count);
        return count[0];
    }

    public static long[] mergeSort(long lo, long hi, long[] arr, long[] count) {
        if (lo == hi) return new long[] {arr[(int)lo]}; // Creating new array and adding that one element

        long mid = lo + (hi - lo) / 2;
        long[] a = mergeSort(lo, mid, arr, count);
        long[] b = mergeSort(mid + 1, hi, arr, count);
        
        return mergeTwoSortedArrays(a, b, count);
    }

    //used for merging two sorted arrays
    public static long[] mergeTwoSortedArrays(long[] a, long[] b, long[] count) {
        int i = 0, j = 0, k = 0;
        long[] ans = new long[a.length + b.length];

        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) { // ith idx wala bnda chota hai
                ans[k++] = a[i++];
            } else { // jth idx wala bnda chota hai
                // mtlb jth idx wala bnda, ith idx se, ith idx k end tak k sbhi bndo k saath inversion count bnaega
                count[0] += a.length - i; 
                ans[k++] = b[j++];
            }
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
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
