// https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum/

// Sliding Window
// TC : O(N), SC : O(1)

```
class Solution {
    
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
    
        int n = nums.length;
        int itr = 0, ptr = 0; // ptr is needed to calculate no subarrays from [ptr, itr]
        int ans = 0, nosmbom = 0; // number of subarrays made because of me "apne kaaran" = itr - ptr + 1 
        
        while(itr < n) {
            
            // This will be a break point bcoz ye bnda
            // kisi k saath v jaaega wo subarray valid nai hoga 
            // kuki ye khud toh right range se bhr hai,
            // Saare chote bndo k saath aaega tb toh max yahi rahega so subarray not valid, else 
            // agar khud se bade bndo k saath aata hai, tb toh us bade bndo k kaaran wo subarray valid nai rahega
            if(nums[itr] > right) { 
                nosmbom = 0;
                itr++;
                ptr = itr;
            }
            
            // mai left range se v choti hu toh mere akele ka koi existence nai hai
            // mai kisi k piche he jud k ek valid subarray bna sakti hu
            else if(nums[itr] < left) {
                // Jitna subarray mere se pichle bnde ne "apne kaaran" bnaya tha
                // mai un sbke piche jud jaaungi. Why "apne kaaran only ??"
                // kuki subarray bnana hai, contigous hona must h
                ans += nosmbom;
                itr++;
            }
            else { // nums[itr] >= left && nums[itr] <= right  [ Within the range ] 
                nosmbom = itr - ptr + 1;  // mere kaaran kitna subarray bnega
                ans += nosmbom;
                itr++;
            }
        }
        return ans;
    }
    
}
```

-----------------------------------------------------------------------------------------------------------------------------------

// Generating all the subarrays and checking the range of max of each subarray
// Total subarray count = (n*(n + 2)) / 2 subarrays
// TC : O(N^2)

```
class Solution {
    
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int n = nums.length;
        int count = 0;
        for(int i = 0; i < n; i++) {
            int max = Integer.MIN_VALUE;
            for(int j = i; j < n; j++) {
                max = Math.max(max, nums[j]);
                if(max >= left && max <= right) {
                    count++;
                }
            }
        }
        return count;
    }
}
```

-----------------------------------------------------------------------------------------------------------------------------------
