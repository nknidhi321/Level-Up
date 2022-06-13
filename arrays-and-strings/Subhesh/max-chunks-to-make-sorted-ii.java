// https://leetcode.com/problems/max-chunks-to-make-sorted-ii/

// TC : N(logN)

```
class Solution {
 
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        
        // Deep copy of arr
        int[] copyArr = new int[n];
        for(int i = 0; i < n; i++) {
            copyArr[i] = arr[i];
        }
        Arrays.sort(copyArr); // Sorting deepCopied array
        
        int countChunk = 0;
        int originalSum = 0, sortedSum = 0;
        
        for(int i = 0; i < n; i++) {
            originalSum += arr[i];  // Summing non-sorted group
            sortedSum += copyArr[i]; // Summing sorted group
            if(originalSum == sortedSum) { // Group of sorted and non-sorted sum will always be same
                countChunk++; // So that can make a group 
                originalSum = sortedSum = 0; // For next upcoming group [Nai v karoge toh v chalega]
            }
        }
        return countChunk;
    }
    
}
```

--------------------------------------------------------------------------------------------------------------

// TC : O(N)

```
class Solution {
    
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        
        // Left se abtak ka max bnda will be stored in leftMax
        int[] leftMax = new int[n];
        int max = 0;
        for(int i = 0; i < n; i++) {
            leftMax[i] = max = Math.max(max, arr[i]); 
        }
        
        // Right se abtak ka min bnda will be stored in rightMin
        int[] rightMin = new int[n + 1]; // Why n+1 ? To compare the last xth leftMax index with last (x+1)th rightMin index
        int min =  Integer.MAX_VALUE;
        rightMin[n] = min; // Setting the last cell to Integer.MAX_VALUE, so that it can be counted as a chunk 
        for(int i = n - 1; i >= 0; i--) {
            rightMin[i] = min = Math.min(min, arr[i]);
        }
        
        int chunkCount = 0;
        for(int i = 0; i < n; i++) {
            if(leftMax[i] <= rightMin[i + 1]) {
                // Agar rightside ka most minimum bnda v agar mere leftSide k max se bada ya equal hai => 
                // rightMin ka (i+1)th bnda ith point k left me kvi nai jaaega jab poora array sorted form me hoga =>
                // This implies a group can be formed
                chunkCount++;
            }
        }
        return chunkCount;
    }
    
}
```

---------------------------------------------------------------------------------------------------------------------------------------
