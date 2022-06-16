// https://leetcode.com/problems/maximize-distance-to-closest-person/

// TC : O(3N), SC : O(2N)

```
class Solution {
    
    public int maxDistToClosest(int[] arr) {
        int n = arr.length;
        
        // Mere se piche jo v last 1 tha uske aur mere bich ka dis will be in leftDis[] array
        int last1Idx = Integer.MAX_VALUE;
        int[] leftDis = new int[n];
        for(int i = 0; i < n; i++) {
            if(arr[i] == 0) leftDis[i] = last1Idx == Integer.MAX_VALUE ? Integer.MAX_VALUE : i - last1Idx;
            else last1Idx = i; // Last 1's idx for next coming 1's
        }
        
        // Mere se aage jo v last 1 tha uske aur mere bich ka dis will be in rightDis[] array
        last1Idx = Integer.MAX_VALUE;
        int[] rightDis = new int[n];    
        for(int i = n - 1; i >= 0; i--) {
            if(arr[i] == 0) rightDis[i] = last1Idx == Integer.MAX_VALUE ? Integer.MAX_VALUE : last1Idx - i;
            else last1Idx = i; // Last 1's idx for next coming 1's
        }

        // maximum of all minimums
        int max = 0;
        for(int i = 0; i < n; i++) { // NOTE : (arr[i] == 1) se hame koi mtlb nahi h kuki waha already koi baitha hua h 
            if(arr[i] == 0) { // leftDis && rightDis me, jo v minimum hoga wahi deciding factor hoga
                max = Math.max(max, Math.min(leftDis[i], rightDis[i])); // Kuki dono taraf se max se max dis hona chahiye 
            }
        }
        return max;
    }
    
}
```
--------------------------------------------------------------------------------------------------------------------------
