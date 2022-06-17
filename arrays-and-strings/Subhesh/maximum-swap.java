// https://leetcode.com/problems/maximum-swap/

/*
    NOTE :-
    ----
    Sbse max digit ko 0th idx pe daal denge, this is wrong logic 
    Ex TC : 98368  Here 3 and last 8 should be swapped

    Approach:-
    --------
    SuffixMax bna lo and iterate from left jis pehle bnde ne bola ki mera max mai khud nahi hu usko uske max k saath swap kar do
*/

```
class Solution {
    
    public int maximumSwap(int num) {
    
        StringBuilder sb = new StringBuilder(""+num); // Put it as a String
        int n = sb.length();

        // Calculating suffixMaxIdx, it will contain idx of max bnda so far from right
        int[] suffixMaxIdx = new int[n];
        for(int i = n - 1; i >= 0; i--) {
            if(i == n - 1) suffixMaxIdx[i] = n - 1;
            else {
                // Why not >= ? Ex : 1993, Here 2nd wale 9 ko 1 se swap hona chahiye to form maximum
                // So suffixMaxIdx = [2, 2, 2, 3]
                // If you would have taken >= then 1 and first 9 swap ho jata, which is wrong
                if(sb.charAt(i) > sb.charAt(suffixMaxIdx[i + 1])) suffixMaxIdx[i] = i; 
                else suffixMaxIdx[i] = suffixMaxIdx[i + 1];
            }
        }
        

        // In suffixMaxIdx, jo v aisa pehla bnda hai jo keh raha hai mera max mere right me hai 
        // Us nums[ith] bnde ko swap krna hai, nums[suffixMaxIdx[i]] pe jo bnda rakha hai us se 
        for(int i = 0; i < n; i++) {
            
            // Agar mai suffixMaxIdx[i] k equal nahi hu and
            // mujhe jis se swap hona hai wo mere jaisa nahi hai toh he swap karo // Ex : 98368
            // If mere jaise ka chcek nahi lgate toh 8 and 8 apas me swap ho jaatey
            if(i != suffixMaxIdx[i] && sb.charAt(i) != sb.charAt(suffixMaxIdx[i])) {
                int idx = suffixMaxIdx[i];
                char currChar = sb.charAt(i);
                sb.setCharAt(i, sb.charAt(idx));
                sb.setCharAt(idx, currChar);
                break; // Atmost 1 swap so break
            }
        }
        return Integer.parseInt(sb.toString());
    }
    
}
```
---------------------------------------------------------------------------------------------------------------------
