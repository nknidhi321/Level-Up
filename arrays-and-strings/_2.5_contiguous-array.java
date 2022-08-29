// https://leetcode.com/problems/contiguous-array/

class Solution {
    
    public int findMaxLength(int[] arr) {
        int n = arr.length;
        for(int i = 0; i < n; i++) {
            if(arr[i] == 0) arr[i] = -1; // -1 , 1 kat k zero ho jaaega
        }
        return maxLen(arr, n);
    }
    
    // largest subarray with sum == 0
    public int maxLen(int arr[], int n) {

        int ei = 0, preFixSum = 0, max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // Agar dobara prefix sum 0 mila then shuru se sbko lelo
        while(ei < n) {
            preFixSum += arr[ei];
            // First time jo v aaya wahi sbse largest subarray bna paaega
            if(!map.containsKey(preFixSum)) map.put(preFixSum, ei); 
            // largest find krna h islye update nai kr rahe h //handles both +ve && -ve
            else max = Math.max(max, (ei - map.get(preFixSum))); 
            ei++;
        }
        return max;
    }
    
}
