// https://leetcode.com/problems/maximize-distance-to-closest-person/

// (staringZeros, endingZeros, bichKzeros/2) jo v max hai that is your ans 
// TC : O(N), SC : O(1)

```
class Solution {
    
    public int maxDistToClosest(int[] arr) {
        int n = arr.length;
        
        // Counting starting 0's
        int startingZeros = 0;
        for(int i = 0; i < n; i++) {
            if(arr[i] == 0) startingZeros++;
            else break;
        }
        
        // Counting ending 0's
        int endingZeros = 0;
        for(int i = n - 1; i >= 0; i--) {
            if(arr[i] == 0) endingZeros++;
            else break;
        }
        
        // Finding max dis or counting 0's between two 1's 
        int ptr = -1, itr = 0;
        int maxDisBetween2Ones = 0;
        while(itr < n) {
            if(arr[itr] == 1) { // Jab pehla 1 milega toh kiske saath dis nikaloge? Nothing !! So just point
                if(ptr != -1) maxDisBetween2Ones = Math.max(maxDisBetween2Ones, itr - ptr); // else cal dis (b - a)
                ptr = itr;
            }
            itr++;
        }
        
        // Agar startingZeros max hai mtlb 0th idx pe Alex ko baitha do
        // Agar endingZeros max hai mtlb n-1 pe idx pe Alex ko baitha do
        // Agar 2 1's ki bich jo consecutive 0's hai/2 agar wo max hai toh toh Alex waha baithega
        // Why /2 ?? Kuki bich me baithna h from left and right, even odd would not make any difference  
        return Math.max(Math.max(startingZeros, endingZeros), maxDisBetween2Ones/2);
    }
    
}
```

--------------------------------------------------------------------------------------------------------------------------

// Prefix1's Suffix1's nikalo
// ith index pe min value he deciding factor bnega, so max of min nikal lo
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
