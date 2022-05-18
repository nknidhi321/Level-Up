// https://www.codingninjas.com/codestudio/problems/highway-billboards_3125967?leftPanelTab=0

 
public class Solution {

	public static int highwayBillboard(int[] billboards, int[] profit, int m, int dis) {
		int n = billboards.length;
		int[][] bills = new int[n][2];	// {billboard, profit}	
		
		// Creating pair, so as the corresponding details, remain intact 
		for(int i = 0; i < n; i++) { 
			bills[i][0] = billboards[i];
			bills[i][1] = profit[i];
		}
		
		// Yaha sorted hone ya na hone se koi fark nai prta
		// Kuki yaha mko mere se piche bus "dis" distance apart logo ko dekhna hai
		// LIS lga do on billboards pe by using "dis" comparison
		// dp me mere pe khatam hone wala LIS ka ab taj ka maximum generated profit hoga  
		int overAllMaxProfit = 0;
        int[] dp = new int[n]; // Mere pe kahatam hone wala LIS ka max. profit  
        for(int i = 0; i < n; i++) {
            int[] bill = bills[i];
            int currBillboard = bill[0];
            int currProfit = bill[1];
            
            int maxPrevProfit = 0;
            for(int j = i - 1; j >= 0; j--) { // Mere se piche waale 
                int prevBillboard = bills[j][0];
                if(currBillboard - prevBillboard > dis) { // mere se "dis" distance door hona chahiye
                     maxPrevProfit = Math.max(maxPrevProfit, dp[j]); // Max of all the profits find karo
                }
            }
            dp[i] = maxPrevProfit + currProfit; // Aur unke piche chipak jaao
            
            // dp pe last me iterate k bajay, yahi overAllMaxProfit nikalte chalo
            overAllMaxProfit = Math.max(overAllMaxProfit, dp[i]);
        }
        return overAllMaxProfit;
	}

}
