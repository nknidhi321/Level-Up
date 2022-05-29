// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
// Not a DP question
// Take every rising peaks[si, ei] profit in your answer

```
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
--------------------------------------------------------------------------------------------------------------

// BuySell stock formula Solution
// Kind of include exclude 
// Bechoge tvi jab ek stock buy kara hua hai
// NOTE: Same day pe buy sell karne ka sense nai bnta hai, kuki 0 profit hogi

```
class Solution {
    
    public int maxProfit(int[] prices) {
        
        int n = prices.length;
        
        // Initially 0 rupee hai wallet me, toh kuch kharioge toh -ve me he jaaoge
        int obsp = -prices[0]; // old Bought State Profit
        
        // Curent state se pehle na kuch kharida, na kuch becha, so sell state ki profit will be 0
        // Agar aaj he buy kiya and aaj he sell krna h, tb v 0 profit he bnega
        int ossp = 0; // old Sold State Profit
        
        for(int i = 1; i < n; i++) {
            
            // will you buy on the current day ?? // Buy karoge toh wallet k paise ghatenge 
            // Agar Buy krte ho toh pichle state k sell pe karoge // Kuki Buy buy is not allowed
            // Ya toh apna pichla state hold kr lo ya "abtak jitne paise kamaye uspe" buy kr lo
            int nbsp = Math.max(obsp, ossp - prices[i]); 
            
            // will you sell on the current day ?? // Sell karoge toh wallet k paise badhenge
            // Agar sell krte ho toh pichle state k buy pe karoge // Kuki Sell Sell is not allowed
            // Ya toh apna pichla state hold kr lo ya sell kr lo 
            // NOTE : Yaha pe "abtak jitne paise kamaye uspe" sell kr lo is included
            // kuki obsp hamare pichle x transaction ko include karte huye bna hai  [ossp - prices[i]]
            int nssp = Math.max(ossp, nbsp + prices[i]); // You can sell on the same day, so nbsp + prices[i]
            
            obsp = nbsp;
            ossp = nssp;
        }
        
        return ossp; // Max hamesha sold state pe he milega, kuki buy krte waqt -price[i] krte hai
    }
        
}
```
----------------------------------------------------------------------------------------------------------------------
