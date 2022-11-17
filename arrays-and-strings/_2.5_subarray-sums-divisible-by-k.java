// https://leetcode.com/problems/subarray-sums-divisible-by-k/

/*
        -------------------------------------------------
        <--------->
            s1
                   <-------------->
                           s
        <-------------------------->
                    s2
                    
                    
        Suppose, both the eqs. leaves remainder x
        
        dividend = quotient × divisor + remainder
        
        s1 = q1 * k + x         ----- eq.1
        s2 = q2 * k + x         ----- eq.2
        
        To find out s, subtract eq.1 from eq2. 
        
        s2 - s1 = k (q2 - q1)       [x got cancelled]
        s = k (q2 - q1)
        
        Therefore, s is a multiple of k and agar ye k ka multiple hai toh k se toh divisible hoga he hoga
        So, include that subarray in your answer.
        
        preFixSum[j] % k == preFixSum[i - 1] % k    , where i & j is the starting and ending idx of subarray && i <= j && s means preFixSum 
        HashMap me (preFixSum % k) ka val(key) and freq(value) store hoga
*/

class Solution {
    
    public int subarraysDivByK(int[] arr, int k) {
        int n = arr.length;
        int ei = 0, preFixSum = 0, ans = 0;
        int[] remFreq = new int[k]; // rem store krna hai so rem range will be [0, k-1]
        remFreq[0] = 1;
        while(ei < n) {
            preFixSum += arr[ei++];
            int rem = (preFixSum % k + k) % k; // handles both +ve && -ve

            // Suppose ab tak 5 ka freq 3 aaya hai , so apne se pichle 2 5 ka saath curr 5,                             
            // number of subarray, ek ek baar pair bnaega  
            ans += remFreq[rem]; // Ye curr rem, 'apne jaise' saare piche k rem k saath subarray bnaega
            remFreq[rem]++;         }
        return ans;
    }
    
}
