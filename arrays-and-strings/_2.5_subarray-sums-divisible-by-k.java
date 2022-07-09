// https://leetcode.com/problems/subarray-sums-divisible-by-k/

class Solution {
    
    public int subarraysDivByK(int[] arr, int k) {
        int n = arr.length;
        int ei = 0, preFixSum = 0, ans = 0;
        int[] remFreq = new int[k];
        remFreq[0] = 1;
        while(ei < n) {
            preFixSum += arr[ei++];
            int rem = (preFixSum % k + k) % k; // handles both +ve && -ve

            // Suppose ab tak 5 ka freq 3 aaya hai , so apne se pichle 2 5 ka saath curr 5,                             // number of subarray, ek ek baar pair bnaega  
            ans += remFreq[rem]; // Ye curr rem, 'apne jaise' saare piche k rem k saath subarray bnaega
            remFreq[rem]++;         }
        return ans;
    }
    
}
