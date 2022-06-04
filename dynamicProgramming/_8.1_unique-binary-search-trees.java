// https://leetcode.com/problems/unique-binary-search-trees/
// Simple Catalan

```
class Solution {
  
    public int numTrees(int n) {
        return findCatalan(n);    
    }
    
    public static int findCatalan(int n) {
        int[] catlanDP = new int[n + 1];
        catlanDP[0] = catlanDP[1] = 1;
        
        for(int i = 2; i <= n; i++) {
            int catlan = 0;
            for(int j = 0; j < i; j++) {
                catlan +=  catlanDP[j] * catlanDP[i - 1 - j];  
            }
            catlanDP[i] = catlan; 
        }
        
        // Printing catalan DP
        // for(int i = 0; i <= n; i++) {
        //     System.out.println(catlanDP[i]);
        // }
        
        return catlanDP[n];
    }
    
}
```
