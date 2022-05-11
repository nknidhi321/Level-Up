// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
// Not a DP question

```
// Take every rising peaks[si, ei] profit in your answer

class Solution {
    
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int si = 0;
        int ei = 0;
        int currPeakProfit = 0;
        for(int i = 0; i < n - 1; i++) { // Every ith idx will be your selling point
            if(prices[i] <= prices[i + 1]) { // rising or equal peak
                ei++;
            }
            else { // downfall 
                currPeakProfit += prices[ei] - prices[si]; 
                si = i + 1;
                ei = i + 1;
            }
        }
        
        // How do we find it's a peak ?? 
        // Every time we see a fall at i+1 standing at i, 
        // we decide to take that rising peak[si,ei] in our answer.
        // Now, Suppose the lastPeak never dropped and the array ended,
        // so to take that lastPeak into account do prices[ei] - prices[si] at last
        
        // Else if there will be downfall, we will get 0 as the lastPeak
        int lastPeakIfExists = prices[ei] - prices[si];
        
        return currPeakProfit + lastPeakIfExists;
    }
    
}
```
-------------------------------------------------------------
