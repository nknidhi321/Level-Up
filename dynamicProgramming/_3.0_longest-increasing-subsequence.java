// https://leetcode.com/problems/longest-increasing-subsequence/ 

/*
    DP will store mere "pe" khatam hone wala LIS ka length, na ki mere "tak" LIS ka length
    So that if I see an element less than myself, I can be assure that if I stick to the end of this chain, 
    I can form one of the possible answers of LIS jo mere "pe" khatam ho
*/

// Sumeet Sir
// Follow this only

```
class Solution {
    
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        Integer[] dp  = new Integer[n];
        
        dp[0] = 1; // ek length ki longest hamesha rahegi
        int omax = 1; // avi tak ek length he max hai
        
        for(int i = 1; i < n; i++) { // Sare elements k liye check karo
            
            // max 0 rahenge kuki, agar mere se pehle koi chota nai mila 
            // toh last me mere naam ka +1 store kar lenge dp me
            int max = 0; 
            
            // Mere se pehle jo v chote bnde hai mere se, unme se max length wala find karo unke dp se
            for(int j = 0; j < i; j++) { // Mere se pehle
                if(nums[j] < nums[i]) {  // Jo v chote bnde hai
                    max = Math.max(dp[j], max); // max find karo dp unke respective dp se
                }
            }
            dp[i] = max + 1; // Aur us max length wale k baad khud chipak jaao //mere naam ka +1
            omax = Math.max(omax, dp[i]); // Ye overall max find karne k liye
        }
        
        return omax; // return overall max
    }
}
```
-------------------------------------------------------------
