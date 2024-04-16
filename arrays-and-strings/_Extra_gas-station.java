// https://leetcode.com/problems/gas-station/

```
// Kind of maximum sum subarray, karja of hereditary family wala sumit sir ka question
// Bus yaha total trip lga paaega ya nahi ye btana hai.
// Pehle k bndo ko chorte jaao agar karje me jaa rahe ho toh
// apni new family start karo
// To NOTE extra :
// If there exists a solution, it is guaranteed to be unique
// totalGas - totalCost >= 0 : then only it's possible to make complete round trip

class Solution {
    
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int remainingFuel = 0;
        int total_surplus = 0;
        int ans = 0;
        
        for(int i = 0; i < n ; i++) {
            total_surplus += gas[i] - cost[i]; // total surplus
            if(remainingFuel + gas[i] - cost[i] < 0) {
                remainingFuel = 0; // piche se negative aara hai, so lene ka koi fayada nai
                ans = i + 1; // so start from next new chain 
            }
            else {
                remainingFuel += gas[i] - cost[i]; // probable ans se rem fuel
            }
        }

        return total_surplus < 0 ? -1 : ans; // poora round lga paaega ya nahi
        // Suppose an array [0, 1, 2, 3, 4, 5]
        // if your ans is index 3, and 4, 5 is satisfying the conditions
        // then why didn't we go back in round again to check index 0, 1, 2
        // Because we already checked 0, 1, 2 and that could not be possible start index,
        // Now if totalGas - totalCost >= 0, this means trip can be done for sure from a specific index
        // so it's given for sure that we have a solution, if trip is possible
        // so no need of checking back index 0 1 2 again.
    }
    
}
```
