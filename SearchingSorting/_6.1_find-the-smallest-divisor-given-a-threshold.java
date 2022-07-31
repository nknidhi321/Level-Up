// https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/
// Similar to Koko eating Bananas

```
class Solution {
    
    public int smallestDivisor(int[] arr, int threshold) {
        int n = arr.length;
        int max = -(int)1e9;
        for(int i = 0; i < n; i++) {
            max = Math.max(max, arr[i]);
        }
        
        // si : min divisor 1 toh hoga he
        // ei : max divisor sbse max bnda hoga array ka 
        int si = 1, ei = max, probAns = -1;
        while(si <= ei) {
            int probDiv = si + (ei - si) / 2;
            if(isPossible(arr, probDiv, threshold)) {
                probAns = probDiv;
                ei = probDiv - 1;
            }
            else {
                si = probDiv + 1;
            }
        }
        return probAns;
    }
    
    public static boolean isPossible(int[] arr, long probDiv, int threshold) {
        int n = arr.length;
        long sum = 0;
        for(int i = 0; i < n; i++) {
            sum += Math.ceil(arr[i] / (probDiv * 1.0));
            if(sum > threshold) {
                return false;
            }
        }
        return true;
    } 
    
}
```
