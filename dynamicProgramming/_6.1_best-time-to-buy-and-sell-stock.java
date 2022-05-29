// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
// Not a DP question

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

---------------------------------------------------------------------------------------------------------------------------

// Buy Sell Stock formula solution 
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
        int ossp = 0; // old Sold State Profit
        
        for(int i = 1; i < n; i++) {
            
            // will you buy on the current day ?? // Buy karoge toh wallet k paise ghatenge 
            // Agar Buy krte ho toh pichle state k sell pe "nahi" karoge // Buy buy is not allowed
            // Ya toh apna pichla state hold kr lo ya "new" buy kr lo
            int nbsp = Math.max(obsp, 0 - prices[i]); 
            
            // will you sell on the current day ?? // Sell karoge toh wallet k paise badhenge
            // Agar sell krte ho toh pichle state k buy pe karoge // Kuki Sell Sell is not allowed
            // Ya toh apna pichla state hold kr lo ya sell kr lo 
            // NOTE : Yaha pe "abtak jitne paise kamaye uspe" sell kr lo is not included
            // kuki obsp hamare pichle x transaction ko include karte huye "nahi" bna hai  [0 - prices[i]]
            int nssp = Math.max(ossp, obsp + prices[i]); 
            
            obsp = nbsp;
            ossp = nssp;
        }
        
        return ossp; // Max hamesha sold state pe he milega, kuki buy krte waqt -price[i] krte hai
    }
    
}
```
-----------------------------------------------------------------------------------------------------------------------------------
