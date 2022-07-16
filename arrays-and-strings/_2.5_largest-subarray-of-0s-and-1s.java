// https://practice.geeksforgeeks.org/problems/largest-subarray-of-0s-and-1s/1

class Solution {

    public int maxLen(int[] arr, int N) {
        // Agar saare 0's ko -1 kar diya
        // Toh equal number of -1's and 1's => Poore subarray ka sum 0 hoga
        for(int i = 0; i < N; i++) {
            if(arr[i] == 0) arr[i] = -1;
        }
        // Now, the question is largestSubarrayWith0Sum
        return largestSubarrayWith0Sum(arr, N);
    }
    
    public static int largestSubarrayWith0Sum(int arr[], int n) {
        int ei = 0, preFixSum = 0, max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // Agar dobara prefix sum 0 mila then shuru se sbko lelo
        while(ei < n) {
            preFixSum += arr[ei];
            if(!map.containsKey(preFixSum)) map.put(preFixSum, ei); // First time jo v aaya wahi sbse largest subarray bna paaega
            else max = Math.max(max, (ei - map.get(preFixSum))); // largest find krna h islye update nai kr rahe h //handles both +ve && -ve
            ei++;
        }
        return max;
    }
    
}
