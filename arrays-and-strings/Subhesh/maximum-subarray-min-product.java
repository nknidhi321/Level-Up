// https://leetcode.com/problems/maximum-subarray-min-product/

// TC : O(N)
// Solving using prefixSum, nstl, nstr 

```
class Solution {
    
    int mod = (int)1e9 + 7;
    
    public int maxSumMinProduct(int[] arr) {
        int n = arr.length;
        
        // Left se loop
        
        long[] prefixSum = new long[n];
        prefixSum[0] = arr[0];
        
        int[] nstl = new int[n]; // next smaller to left
        Stack<Integer> sLeft = new Stack<>();
        sLeft.push(-1);
        
        for(int i = 0; i < n; i++) { // Cal. prefixSum, next smaller to left
            // Cal. next smaller to left
            while(sLeft.peek() != -1 && arr[sLeft.peek()] >= arr[i]) { // mere se bada bnda hai pop
                sLeft.pop();
            }
            nstl[i] = sLeft.peek();
            sLeft.push(i);
            
            // Cal. prefix sum
            if(i > 0) prefixSum[i] = (prefixSum[i - 1] + arr[i]); 
        }
 
        
        // Right se loop 
        
        int[] nstr = new int[n]; // next smaller to right
        Stack<Integer> sRight = new Stack<>();
        sRight.push(n);
        
        for(int i = n - 1; i >= 0; i--) { // Cal. next smaller to right
            // Cal. next smaller to right
            while(sRight.peek() != n && arr[sRight.peek()] >= arr[i]) { // mere se bada bnda hai pop
                sRight.pop();
            }
            nstr[i] = sRight.peek();
            sRight.push(i);
        }
        
        // Considering each array element as the minimum at its iteration
        // If you got to be the minimum, then you have to make sure ki 
        // aapko include karte huye jo v subaray aap bnaoge, usme aapse chote bnde nahi aane chahiye
        // So, islye nstl and nstr nikali hu taaki mai aapna subarray ka left and right boundary nikal paau
        // prefixSum && suffixSum ki help se aap apne subarray ka sum O(1) me nikal loge
        
        long max = -(int)1e9;
        for(int i = 0; i < n; i++) {
            int min = arr[i];
            int leftBoundaryIdx = nstl[i]; // Subarray's left boundary index
            int rightBoundaryIdx = nstr[i]; // Subarray's right boundary index
            long leftSum = nstl[i] == -1 ? 0 : prefixSum[leftBoundaryIdx]; // Left part to be discarded
            long subarraySum = prefixSum[rightBoundaryIdx - 1] - leftSum; // My subarray sum
            max = Math.max(max, subarraySum * min); // Competing with overall max
        }
        return (int)(max % mod);
    }
    
}
```

--------------------------------------------------------------------------------------------------------------------------

// TC : O(N)
// Solving using Calculating prefixSum, suffixSum, nstl, nstr

```
class Solution {
    
    int mod = (int)1e9 + 7;
    
    public int maxSumMinProduct(int[] arr) {
        int n = arr.length;
        
        // Left se loop
        
        long[] prefixSum = new long[n];
        prefixSum[0] = arr[0];
        
        int[] nstl = new int[n]; // next smaller to left
        Stack<Integer> sLeft = new Stack<>();
        sLeft.push(-1);
        
        long sum = 0;
        for(int i = 0; i < n; i++) { // Cal. sum, prefixSum, next smaller to left
            sum = (sum + arr[i]); // Cal. sum
            
            // Cal. next smaller to left
            while(sLeft.peek() != -1 && arr[sLeft.peek()] >= arr[i]) { // mere se bada bnda hai pop
                sLeft.pop();
            }
            nstl[i] = sLeft.peek();
            sLeft.push(i);
            
            // Cal. prefix sum
            if(i > 0) prefixSum[i] = (prefixSum[i - 1] + arr[i]); 
        }
 
        
        // Right se loop 
        
        long[] suffixSum = new long[n];
        suffixSum[n - 1] = arr[n - 1];
        
        int[] nstr = new int[n]; // next smaller to right
        Stack<Integer> sRight = new Stack<>();
        sRight.push(n);
        
        for(int i = n - 1; i >= 0; i--) { // Cal. suffixSum, next smaller to right
            // Cal. next smaller to right
            while(sRight.peek() != n && arr[sRight.peek()] >= arr[i]) { // mere se bada bnda hai pop
                sRight.pop();
            }
            nstr[i] = sRight.peek();
            sRight.push(i);
            
            // Cal. suffix sum
            if(i < n - 1) suffixSum[i] = (suffixSum[i + 1] + arr[i]);
        }
        
        // Considering each array element as the minimum at its iteration
        // If you got to be the minimum, then you have to make sure ki 
        // aapko include karte huye jo v subaray aap bnaoge, usme aapse chote bnde nahi aane chahiye
        // So, islye nstl and nstr nikali hu taaki mai aapna subarray ka left and right boundary nikal paau
        // prefixSum && suffixSum ki help se aap apne subarray ka sum O(1) me nikal loge
        
        long max = -(int)1e9;
        for(int i = 0; i < n; i++) {
            int min = arr[i];
            int leftBoundaryIdx = nstl[i]; // Subarray's left boundary index
            int rightBoundaryIdx = nstr[i]; // Subarray's right boundary index
            long leftSum = nstl[i] == -1 ? 0 : prefixSum[leftBoundaryIdx]; // Left part to be discarded
            long rightSum = nstr[i] == n ? 0 : suffixSum[rightBoundaryIdx]; // Right part to be discarded
            long subarraySum = sum - leftSum - rightSum; // My subarray sum
            max = Math.max(max, subarraySum * min); // Competing with overall max
        }
        return (int)(max % mod);
    }
    
}
```
