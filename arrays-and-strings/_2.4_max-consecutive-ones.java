// https://leetcode.com/problems/max-consecutive-ones/

// O(n)

```
class Solution {

    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length, max = 0, counter = 0;
        
        for(int i = 0; i < n; i++) {
            if(nums[i] == 0) counter = 0; // 0 aaya then reinitiaize counter with 0
            else counter++; // 1 aaya then simply keep on increasing counter
            max = Math.max(max, counter); // Har baar max find karte jaao saare idx k liye
        }
        return max;
    }
}
```

-------------------------------------------------------------------------------------------

// Sliding Window // O(2n)

```
class Solution {

    public int findMaxConsecutiveOnes(int[] arr) {
        int n = arr.length, max = 0, zeroCount = 0;
        int si = 0, ei = 0;
        
        while(ei < n) {
            if(arr[ei] == 0) zeroCount++;
            
            while(zeroCount > 0) {
                if(arr[si++] == 0) {
                    zeroCount--;
                }
            }
            
            max = Math.max(max, ei - si + 1);
            ei++;
        }

        return max;
    }
    
}
```
------------------------------------------------------------------------------------------------
