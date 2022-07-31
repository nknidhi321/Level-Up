// https://leetcode.com/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/

```
class Solution {
    
    int mod = (int)(1e9 + 7);
    
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        int n = horizontalCuts.length;
        int m = verticalCuts.length;
        
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        
        long maxHori = horizontalCuts[0];
        long maxVert = verticalCuts[0];
        
        for(int i = 1; i < n; i++) {
            maxHori = Math.max(maxHori, horizontalCuts[i] - horizontalCuts[i - 1]);
        }
        
        for(int i = 1; i < m; i++) {
            maxVert = Math.max(maxVert, verticalCuts[i] - verticalCuts[i - 1]);
        }
        maxHori = Math.max(maxHori, h - horizontalCuts[n - 1]);
        maxVert = Math.max(maxVert, w - verticalCuts[m - 1]);

        return (int)((maxHori * maxVert) % mod);
    }
    
}
```
