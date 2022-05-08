// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/

// Keep a min variable for minimum price, from the current point to all the previous points 
// and update maxProfit accordingly  for the current price. 

```
class Solution {
    
    public int maxProfit(int[] prices) {
        int n = prices.length;
        
        // Note : You cannot buy && sell a stock at the same day
        // So, consider you bought a stock at the 0th day *
        int minSoFar = prices[0]; // minSoFar is price at which stock was bought
        
        int maxProfitSoFar = 0;
        
        // Consider each day as selling point
        // * And you start selling your stock from 1st day
        for(int i = 1; i < n; i++) {
            
            // Jis din minSoFar se minimum value aaegi,
            // aap minSoFar ko prices[i] se upadate krwa dete ho
            minSoFar = Math.min(minSoFar, prices[i]);
            
            // And maxProfitSoFar prices[i] - minSoFar se nikalwa lete ho
            // Acc. question you cannot buy && sell a stock at the same day  
            // Above condition does not hold if you find your answer like above
            // But note prices[i] - minSoFar will be == 0 => This can be assumed like no transaction was done
            maxProfitSoFar = Math.max(maxProfitSoFar, prices[i] - minSoFar);
        }
        return maxProfitSoFar;
    }
    
}
```
-------------------------------------------------------------
