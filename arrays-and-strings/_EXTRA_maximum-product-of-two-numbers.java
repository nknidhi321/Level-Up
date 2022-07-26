// https://practice.geeksforgeeks.org/problems/maximum-product-of-two-numbers2730/1

// One pass
// Works for both +ve and -ve elements
// Keep track of maxSoFar and minSoFar while iterating to form ans with the currEle * max && currEle * min and compete with your ans

class Solution {
    
    int maxProduct(int arr[], int n) {
        int ans = arr[0] * arr[1];
        int max = Math.max(arr[0], arr[1]);
        int min = Math.min(arr[0], arr[1]);
        
        for(int i = 2; i < n; i++) {
            if(arr[i] * max > ans) {
                ans = arr[i] * max;
            }
            max = Math.max(max, arr[i]);
            if(arr[i] * min > ans) {
                ans = arr[i] * min;
            }
            min = Math.min(min, arr[i]);
        }
        return ans;
    }
    
}

//===========================================================================================

// Works only for +ve elements
// Find max and secMax and get your ans
class Solution {
    
    int maxProduct(int arr[], int n) {
        int[] a = maxSecMax(arr);
        return a[0] * a[1];
    }
    
    public int[] maxSecMax(int[] arr) {
        int max = -(int)1e9, secMax = -(int)1e9;
        int n = arr.length;
        for(int i = 0; i < n; i++) {
            if(arr[i] >= max) {
                secMax = max;
                max = arr[i];
            }
            else if(arr[i] > secMax) {
                secMax = arr[i];
            }
        }
        return new int[] {max, secMax};
    }
    
}
