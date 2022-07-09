// https://practice.geeksforgeeks.org/problems/largest-subarray-with-0-sum/1/#

class GfG {

    public int maxLen(int arr[], int n) {

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
