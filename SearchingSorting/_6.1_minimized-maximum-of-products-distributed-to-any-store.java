// https://leetcode.com/problems/minimized-maximum-of-products-distributed-to-any-store/

```
class Solution {
    
    public int minimizedMaximum(int shops, int[] pages) {
        int n = pages.length;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            max = Math.max(max, pages[i]);
        }
        
        int low = 0;
        int high = max;
        int probableAns = -1;
        
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(isPossible(mid, shops, pages)) {
                high = mid - 1;
                probableAns = mid;
            }
            else {
                low = mid + 1;
            }
        }
        return probableAns;
    }
    
    public static boolean isPossible(int fixedPage, int shops, int[] pages) {
        if(fixedPage == 0) return false;
        int i = 0, n = pages.length;
        int itemX = pages[0]; 
        int count = 0;
       
        while(i < n) {
            if(itemX < fixedPage ) {
                i++;
                count++;
                if(i < n) itemX = pages[i];
                else break;
                continue;
            }
            
            itemX -= fixedPage;
            if(itemX > 0) {
                count++;
            }
            else if(itemX == 0) {
                i++;
                count++;
                if(i < n) itemX = pages[i];
                else break;
            }
        }
        return count <= shops;
    }
    
}
```
