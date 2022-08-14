// https://leetcode.com/problems/house-robber-ii/

// churana => pichla nahi churana + mai
// nahi churana hai => max(pichla churana, pichla nahi churana)

class Solution {
    
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 1) return nums[0];
        
        int max1 = max(0, n - 2, nums); // first ghr se, last ghr - 1 tak find karna
        int max2 = max(1, n - 1, nums); // second ghr se, last ghr tak find krna 
        
        return Math.max(max1, max2); // Ab in dono me jo v max hoga, wo mera ans hoga
    }
    
    public int max(int start, int end, int[] nums) {
        int inc = nums[start];
        int exc = 0;
        
        for(int i = start + 1; i <= end; i++) {
            int currInc = exc + nums[i];
            int currExc = Math.max(inc, exc);
            
            inc = currInc;
            exc = currExc;
        }
        return Math.max(inc, exc);
    }
    
}
