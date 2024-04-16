// https://leetcode.com/problems/candy/

class Solution {
    
    public int candy(int[] ratings) {
        int n = ratings.length;
        if(n == 1) return 1;
        
        int [] minCandyNeeded = new int[n];
        Arrays.fill(minCandyNeeded, 1); // sabko pehle ek ek candy dedo
        
        // Now, Checking in prefix manner
        for(int i = 1; i < n; i++) {
            if(ratings[i - 1] < ratings[i]) {
                // Apne pehle wale bnde se ek candy zyada lelo
                minCandyNeeded[i] = minCandyNeeded[i - 1] + 1; 
            }
        }
        
        // Printing
        // for(int i = 0; i < n; i++) System.out.print(minCandyNeeded[i] + " ");
        
        // Checking in suffix manner
        for(int i = ratings.length - 2; i >= 0; i--) {
            if(ratings[i + 1] < ratings[i]) { // Why you need Math.max ? Ex TC : [1,3,4,5,2]
                // Apne aage wale bnde se ek candy zyada lelo
                minCandyNeeded[i] = Math.max(minCandyNeeded[i], minCandyNeeded[i + 1] + 1); 
            }
        }
        
        // Printing
        // System.out.println();
        // for(int i = 0; i < n; i++) System.out.print(minCandyNeeded[i] + " ");
        
        // Now calculate the overall candy needed from the minCandyNeeded array
        int ans = 0;
        for(int i = 0; i < n; i++) ans += minCandyNeeded[i];
        return ans;
    }
}
