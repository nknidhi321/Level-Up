// https://leetcode.com/problems/magnetic-force-between-two-balls/
// Ditto Aggressive cows // Maximize distance

```
class Solution {
    
    public int maxDistance(int[] stalls, int k) {
        int n = stalls.length;
        
        // Sort krna imp hai to traverse in isPossible() method
        // Taki jab 2 pole ka diff kam ya zyada aaye toh hame pta hona chahiye like kis taraf move krna h
        Arrays.sort(stalls);
        
        // Max distance kya ho sakta hai kisi 2 cows k bich me ?
        // Largest - smallest pole k bich ka distance he max distance ho skta hai so, set ei = diff 
        // and smallest dis can be 0, so si = 0
        int si = 1, ei = stalls[n - 1] - stalls[0];
        
        int probableAns = -1;
        while(si <= ei) {
            int mid = si + (ei - si) / 2; // Max dis = mid agar kare toh kya hum k cows baitha paaenge ? 
            if(isPossible(mid, stalls, k)) {
                probableAns = mid; 
                si = mid + 1;
            }
            else {
                ei = mid - 1;
            }
        }
        return probableAns;        
    }
    
    public static boolean isPossible(int minDis, int[] stalls, int k) {
        int n = stalls.length;
        int si = 0, ei = 0;
        
        // Jab ek window ka minDis mil jaayega toh waha 2 cows baithenge on each corner
        // So, setting it to 1 initially, aur kvi minDis na v mile,
        // toh ek size ka toh array hoga he wahi pe cow ko baitha do
        int cows = 1; 
        while(ei < n) {
            if(stalls[ei] - stalls[si] < minDis) {
                ei++; // Apni window tab tak badhao ki atleast minDis wo dis k barabar ho jaaye
            }
            else if(stalls[ei] - stalls[si] >= minDis) {
                cows++;
                si = ei; // Why not ei+1 ? Kuki same pole aage k pole se diff bna sakta hai
            }
        }
        return cows < k ? false : true; // Agar k cows nahi baitha paaye us minDis ko set kar k 
        // So, return false else return true
    }
    
}
```
