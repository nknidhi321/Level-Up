// https://leetcode.com/problems/longest-increasing-subsequence/ 

// No dp, using simple BS
// TC : nlogn
// The LIS pattern might not necessarily be correct in subsequence terms
// But the length would be 100% correct

```
class Solution {
    
    public int lengthOfLIS(int[] nums) {
        List<Integer> list = new ArrayList<>(); 
        for(int ele : nums) {
            int insertPos = insertPos(0, list.size() - 1, ele, list);
            if(insertPos == list.size()) list.add(ele);
            else list.set(insertPos, ele);
        }
        return list.size();
    }
    
    // List will never contain duplicate ele. Why ??
    // Because everytime a duplicate element comes as tar, it gets overridden 
    public int insertPos(int si, int ei, int tar, List<Integer> list) {
        while(si <= ei) {
            int mid = si + (ei - si) / 2;
            if(list.get(mid) <= tar) si = mid + 1;
            else ei = mid - 1;
        }
        int insertPos = si;
        int duplicateEle = si - 1; 
        
        // If duplicate ele exists, we will override at the duplicate ele pos 
        // Else return the proper insertPos
        return si - 1 >= 0 && list.get(si - 1) == tar ? duplicateEle : insertPos;
    }
    
}
```

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

/*
    DP will store mere "pe" khatam hone wala LIS ka length, na ki mere "tak" LIS ka length
    So that if I see an element less than myself, I can be assure that if I stick to the end of this chain, 
    I can form one of the possible answers of LIS jo mere "pe" khatam ho
*/

// Sumeet Sir // Tabulation
// Refer this to understanding

```
class Solution {
    
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        Integer[] dp  = new Integer[n];
        int omax = 1;
        
        for(int i = 0; i < n; i++) { // Sare elements k liye check karo
            if(i == 0) {
                dp[0] = 1;  // ek length ki longest hamesha rahegi
                omax = 1;  // avi tak ek length he max hai        
                continue; 
            }
                
            // max 0 rahenge kuki, agar mere se pehle koi chota nai mila 
            // toh last me mere naam ka +1 store kar lenge dp me
            int max = 0; 
            
            // Mere se pehle jo v chote bnde hai mere se, unme se max length wala find karo unke dp se
            for(int j = 0; j < i; j++) { // Mere se pehle // You can start with either of the dir, it's all about getting max
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

-----------------------------------------------------------------------------------------------------------------------------------

// Recursion

```
class Solution {
    
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        
        // Saare idx se Memo ki call lgegi kuki tumhara recursive call,
        // sirf khudse choto elements pe jata hai.
        // So, tumse pehle agar koi bada idx hai, to wo skip ho jaaega,
        // Par us bade wale idx se v toh max LIS generate ho sakta hai
        // Islye saare idx se Memo call karo
            
        int max = 0;
        for(int i = 0; i < nums.length; i++) {
            max = Math.max(max, LIS_Rec(nums, i)); 
        }
    
        return max;
    }
    
    public static int LIS_Rec(int[] nums, int idx) {
        if(idx == 0) return 1;
        
        int max = 0;
        for(int i = idx - 1; i >= 0; i--) {
            if(nums[i] < nums[idx]) {
                max = Math.max(max, LIS_Rec(nums, i));
            }
        }
        return max + 1;
    }
    
}

```

-------------------------------------------------------------------------------------------------------------

// Memoization

```
class Solution {
    
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        Integer[] dp = new Integer[n];
        
        // Saare idx se Memo ki call lgegi kuki tumhara recursive call,
        // sirf khudse choto elements pe jata hai.
        // So, tumse pehle agar koi bada idx hai, to wo skip ho jaaega,
        // Par us bade wale idx se v toh max LIS generate ho sakta hai
        // Islye saare idx se Memo call karo
            
        int max = 0;
        for(int i = 0; i < nums.length; i++) {
            max = Math.max(max, Memo(nums, i, dp)); 
        }
    
        return max;
    }
    
    public static int Memo(int[] nums, int idx, Integer[] dp) {
        if(idx == 0) return dp[idx] = 1;
        
        if(dp[idx] != null) return dp[idx];

        int max = 0;
        for(int i = idx - 1; i >= 0; i--) {
            if(nums[i] < nums[idx]) {
                max = Math.max(max, Memo(nums, i, dp));
            }
        }
        return dp[idx] = max + 1;
    }
    
}
```

-----------------------------------------------------------------------------------------------------------

// Tabulation

```
class Solution {
    
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        Integer[] dp = new Integer[n];
        Tab(nums, n - 1, dp);
        
        int max = 0;
        for(int i = 0; i < nums.length; i++) {
            max = Math.max(max, dp[i]);  // NOTE : Tab me sara dp[i] fill rahega kuki 0 se start kar rahe in Tab() method
        }
    
        return max;
    }
    
    
    public static int Tab(int[] nums, int IDX, Integer[] dp) {
        
        // Tumhara dp 0 se iterate krte krte saare idx pe jaaega,
        // par Memo me ye nahi hoga kuki piche se call lgri hai lengthOfLIS() se
        // And sirf khudse choto pe he jaaega
        for(int idx = 0; idx <= IDX; idx++) {  
            if(idx == 0) {
                dp[idx] = 1;
                continue;
            }

            int max = 0;
            for(int i = idx - 1; i >= 0; i--) {
                if(nums[i] < nums[idx]){
                    max = Math.max(max, dp[i]); // Memo(nums, i, dp));
                }
            }
            dp[idx] = max + 1;
        }
        
        return dp[IDX];
    }
    
}

```

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Brute Force, Recursion: O(2^N)
// Unknown Source // Yet to see

```
class Solution {
    
    public int lengthOfLIS(int[] nums) {
        return Util(nums, -1, 0);
    }
    
    public static int Util(int[] nums, int prev, int current){
        if(current == nums.length)
            return 0;
        
        int leftMax = 0;
        if(prev == -1 || nums[prev] < nums[current])
             leftMax = Util(nums, current, current + 1) + 1;
        
        int rightMax = Util(nums, prev, current + 1);
        
        return Math.max(leftMax, rightMax);
    }
    
}
```

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
