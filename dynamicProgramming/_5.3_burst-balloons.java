// https://leetcode.com/problems/burst-balloons/

// Cut based question :-
// The idea is [si, ei] me khud ko sbse last me phoroge,
// taaki is baat ki assurity rahe ki tumhara left and right boundary alive hai 
// [NOTE : Left and right boundary us poore array me se chhose hoga na ki sirf -1 && n index]
// Note : Aap yaha kisi v order me phor saktey ho balloon, incase agar aap visited leke chalte ho toh, 
// kuki jab aap khud ko phoroge toh aap apne left and right chcek karne jaao, ki mere se mera first left kon hai,
// and mere se mera first right kon hai, un dono ko pick kar lo, and for do and khud ko phuta hua mark kar do.

```
class Solution {

    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        return maxCoins(nums, 0, n - 1, dp);
    }
    
    public int maxCoins(int[] nums, int si, int ei, int[][] dp) {
        if(dp[si][ei] != 0) return dp[si][ei];

        int lele = si == 0 ? 1 : nums[si - 1]; // si k pehle wala left element => left boundary 
        int rele = ei == nums.length - 1 ? 1 : nums[ei + 1]; // ei k baad wala right element => right boundary

        int maxCoins = 0;
        // Mere [si, ei] tak k range me, saare balloon foot chuke hai, mai sbse last phootungi
        // Note : [si, ei] k bhr jo v elements hai, wo aaj v alive hai
        for(int cut = si; cut <= ei; cut++) { // [si, ei] cut says mujhe sbse last me phoro
            int lcost = cut == si ? 0 : maxCoins(nums, si, cut - 1, dp); // Agar si pe cut lgaogi toh leftCost 0 aaega
            int rcost = cut == ei ? 0 : maxCoins(nums, cut + 1, ei, dp); // Agar ei pe cut lgaogi toh rightCost 0 aaega
            
            int currCost =  lcost + (lele * nums[cut] * rele) + rcost; // Tmhare phootne ka cost 

            maxCoins = Math.max(maxCoins, currCost); // Overall max of all 
        }
        return dp[si][ei] = maxCoins;
    }
    
}
```
-------------------------------------------------------------
