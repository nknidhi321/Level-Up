// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/

// Kind of include exclude 
// Note since, we depend only on the previous day, so that could be handled using 2 variable, by replacing dp 

```
class Solution {
    
    public int maxProfit(int[] prices, int fee) {
        
        int n = prices.length;
        
        int[] obsp = new int[n]; // dp of old Bought State Profit
        int[] ossp = new int[n]; // dp of old Sold State Profit
        
        // Bechoge tvi jab ek stock buy kara hua hai

        // Initially 0 rupee hai wallet me, toh kuch kharioge toh -ve me he jaaoge
        obsp[0] = -prices[0]; 
        
        // Curent state se pehle na kuch kharida, na kuch becha, so sell state ki profit will be 0 
        ossp[0] = 0; // Same day pe buy sell karne ka sense nai bnta hai, kuki 0 profit hogi
        
        
        for(int i = 1; i < n; i++) {
            
            int nbsp = 0; // New bought state profit 
            int nssp = 0; // New sold state profit
            
            // will you buy on the current day ?? // Buy karoge toh wallet k paise ghatenge
            if(ossp[i - 1] - prices[i] > obsp[i - 1]) { // current buy hamesha pichla sold state se compare hoga
                nbsp = ossp[i - 1] - prices[i]; // kuki b b is not allowed, agar zyada profit bnri hai toh rakh lo
            }
            else { // Else jo stock already kharid rakha tha wahi rehne do
                nbsp = obsp[i - 1]; // kuki usi se max profit milra h
            }
            
            // will you sell on the current day ?? // Sell karoge toh wallet k paise badhenge, par fee dena prega
            if(obsp[i - 1] + prices[i] - fee > ossp[i - 1]) { // current sold hamesha pichla bought state se compare hoga
                nssp = obsp[i - 1] + prices[i] - fee; // kuki ss is not allowed, agar zyada profit bnri hai toh rakh lo  
            }
            else { // Else hum aaj nai bechenge
                nssp = ossp[i - 1]; // kuki pichle se he max profit bnri hai
            }
            
            obsp[i] = nbsp;
            ossp[i] = nssp;
        }
        
        // System.out.println("obsp" + "\t" + "ossp");
        // for(int i = 0; i < n; i++) {
        //     System.out.println(obsp[i] + "\t" + ossp[i]);
        // }
        
        return ossp[n - 1];
    }
    
}
```

-------------------------------------------------------------------------------------------------------------------------------------------

// Replaced Dp using 2 variables
// Kind of include exclude 
// Bechoge tvi jab ek stock buy kara hua hai
// NOTE: Same day pe buy sell karne ka sense nai bnta hai, kuki 0 profit hogi

```
class Solution {
    
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        
        // Initially 0 rupee hai wallet me, toh kuch kharioge toh -ve me he jaaoge
        int obsp = -prices[0]; // old Bought State Profit
        
        // Curent state se pehle na kuch kharida, na kuch becha, so sell state ki profit will be 0 
        int ossp = 0; // old Sold State Profit
        
        for(int i = 1; i < n; i++) {
            
            // will you buy on the current day ?? // Buy karoge toh wallet k paise ghatenge 
            // Agar Buy krte ho toh pichle state k sell pe karoge // Kuki Buy Buy is not allowed
            int nbsp = Math.max(obsp, ossp - prices[i]); // Ya toh apna pichla state hold kr lo ya buy kr lo
            
            // will you sell on the current day ?? // Sell karoge toh wallet k paise badhenge, par fee dena prega
            // Agar sell krte ho toh pichle state k buy pe karoge // Kuki Sell Sell is not allowed
            int nssp = Math.max(ossp, obsp + prices[i] - fee); // Ya toh apna pichla state hold kr lo ya sell kr lo
            
            obsp = nbsp;
            ossp = nssp;
        }
        
        return ossp; // Max hamesha sold state pe he milega, kuki buy krte waqt -price[i] krte hai
    }
    
}
```

--------------------------------------------------------------------------------------------------------------------------------
