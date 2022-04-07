// https://leetcode.com/problems/sort-an-array/

// MergeSort
// Taking extra space, not inPlace
// Archit

```
class Solution {

    public int[] sortArray(int[] arr) {
        int n = arr.length;
        return mergeSort(0, n - 1, arr);
    }

    public static int[] mergeSort(int lo, int hi, int[] arr) {
        if (lo > hi) return new int[0];
        if (lo == hi) return new int[] {arr[lo]}; // Creating new array and adding that one element

        int mid = lo + (hi - lo) / 2;
        int[] a = mergeSort(lo, mid, arr);
        int[] b = mergeSort(mid + 1, hi, arr);
        
        return mergeTwoSortedArrays(a, b);
    }

    //used for merging two sorted arrays
    public static int[] mergeTwoSortedArrays(int[] a, int[] b) {
        int i = 0, j = 0, k = 0;
        int[] ans = new int[a.length + b.length];
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                ans[k] = a[i];
                i++;
                k++;
            } else {
                ans[k] = b[j];
                j++;
                k++;
            }
        }

        while (i < a.length) {
            ans[k] = a[i];
            k++;
            i++;
        }

        while (j < b.length) {
            ans[k] = b[j];
            k++;
            j++;
        }

        return ans;
    }

}
```

//---------------------------------------------------------------------------------------------------------------------------------

// QuickSort
// Rajneesh

```
// Avg case TC : O(NlogN), when pivot will be near to mid of (si+ei)/2 
// Your left half will be nearly equal to right half in size so, same like merge sort, so O(nlogn)

// Worst case TC : O(N^2), when input array is in descending order because around the pivot,
// Your left half array will be of size 0, and right half will be (n - 1) elements, so O(N^2)

class Solution {
    
    public int[] sortArray(int[] arr) {
        int n = arr.length;
        quickSort(0, n - 1, arr);
        return arr;
    }
    
    public static void quickSort(int si, int ei, int[] arr) {
        if(si >= ei) return;

    // If you want to keep your pivotIdx as any randomIdx
    /*
        int len = ei - si + 1;
        
        // Generates random number in the range [0, 99]
        int randomNumber = (int)Math.random() * 100;
        
        // Bring the randomNumber within your curr array length range
        int withinRange = randomNumber % len; // [0, len - 1]
        
        // Bring withinRange in your curr array index range[si ei] by adding si
        int pivotIdx = withinRange + si; 
    */
        
        int pivotIdx = ei; // Fixing the pivotIdx as the last element  
         
        int ptrIdx = segregateDataOnPivot(si, ei, pivotIdx, arr);
        
        // Now, I'm at my correct position
        // So I'll ask my left and right half to get in their correct poition

        quickSort(si, ptrIdx - 1, arr);
        quickSort(ptrIdx + 1, ei, arr);
        
        // left half is at correct position + I'm at my correct position + right half is at correct position
        // So, I'm also sorted
    }
    
    
    public static int segregateDataOnPivot(int si, int ei, int pivotIdx, int[] arr) {
        // Inorder to bring pivotIdx ele at the pivot position first swap the element with the last index
        // Because last ele will be compared the last and then it will always point to the last idx of the ptrIdx
        // So simply return the ptrIdx, because that pivot is now at it's correct position
        
        swap(ei, pivotIdx, arr);
        int pivotEle = arr[ei];
        
        // Simple two pointer swapping, but it's better to start both the ptr from start because 
        // You can use the same technique in LL, where if you would have kept another ptr at the end then
        // Iterating it from end would be a tough task
        int ptrIdx = si - 1, curr = si;
        while(curr <= ei) {
            if(arr[curr] <= pivotEle) {
                swap(++ptrIdx, curr, arr);
            }
            curr++;
        }
        return ptrIdx;
    }
    
    
    public static void swap(int idx1, int idx2, int[] arr) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }
    
}
```

//---------------------------------------------------------------------------------------------------------------------------------
