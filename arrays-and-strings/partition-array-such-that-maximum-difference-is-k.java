// https://leetcode.com/problems/partition-array-such-that-maximum-difference-is-k/

/*
    NOTE : min number of subsequences bnane hai
    Sort krne se subsquence ka order toh maintain nai rahega,
    but final ans me subsequence ka order matter nahi karega, kuki count btana h, na ki subsequence

    Approach :-
    --------
    Sort the array.
    Keep two pointers, itr will iterate and ptr will point
    Check the diff between itr and ptr values if it is <= k, you know that from [itr, ptr]
    we can form a subsequence because we just checked the difference between the max and min of the curr
    subsequence.
    
    Note, we have to find minimum number of subsequences
    
    So, now we will think of broadning our range to left i.e. decrementing our itr
    The so decrease your itr until you do not full fill <= k condition
    The moment you do not fullfill the condition you know that
    from (itr, ptr] is my one of the possible subsequence, 
    So simply increase your count and now the next group of subsequnece will start from itr to the left       
    rest of the elements, beacause an ele can be a part of only one subsequence.
    At last there be a +1 subseuence, for the last chunk of group
*/

```
class Solution {
    
    public int partitionArray(int[] nums, int k) {
        int n = nums.length;
        
        Arrays.sort(nums);
        
        int ptr = n - 1, itr = n - 1;
        int subSeqCount = 0;
        
        while(itr >= 0) {
            if(nums[ptr] - nums[itr] <= k) { // If i'm fulfilling the condition 
                itr--; // I'm trying to broden range
            }
            else { // Agar itr wala bnda he "k" se zyada ho raha hai => itr se piche waale bnde ptr k saath wale bnde se kvi v apna ans nahi bna saktey
                subSeqCount++;
                ptr = itr; // An ele can be part of one subseq
            }
        }
        return subSeqCount + 1; // for the last group of subseq
    }
    
}
```
-----------------------------------------------------------------------------------------------------------------------------------------------------------
