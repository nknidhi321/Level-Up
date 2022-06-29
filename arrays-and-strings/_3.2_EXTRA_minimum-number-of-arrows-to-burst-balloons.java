// https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/

// READ : https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/discuss/887875/If-you-cannot-pass-2147483646-214748364521474836462147483647

// NOTE : One special TC, prevent overflow by using Integer.compare()
// [[-2147483646,-2147483645],[2147483646,2147483647]]
// Read old codes for comments

// Sorting on end pt.
```
class Solution {
    
    public int findMinArrowShots(int[][] pair) {
        int n = pair.length;
        Arrays.sort(pair, (a, b) -> Integer.compare(a[1], b[1]));
        
        int startSoFar = pair[0][0];
        int endSoFar = pair[0][1];
        int meetingsCount = 0;
        
        for(int i = 1; i <= n; i++) {
            if(i == n) {
                meetingsCount++;
                continue;
            }
            int currStart = pair[i][0];
            int currEnd = pair[i][1];
            if(endSoFar < currStart) { // no overlapping // Need a new arrow
                meetingsCount++;
                startSoFar = currStart;
                endSoFar = currEnd;
            }
            else { // overlapping // Same arrow can be used, place the arrow at the end
                // Do nothing  
            }
        }
        
        return meetingsCount;
    }
    
}
```

-----------------------------------------------------------------------------------------------------

// Old Code // Same logic, as above 
// Sorting on endpoint, traversing from start
```
class Solution {
    
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        //Arrays.sort(points, (a, b) -> a[1] - b[1]); // Causes overflow, because of subtraction
        Arrays.sort(points, (a,b)->Integer.compare(a[1],b[1])); //Prevents overflow,no subtract
        int arrowPos = points[0][1]; // End pt. pe sbse pehla arrow rahega
        int arrowCnt = 1;
        for (int i = 1; i < points.length; i++) {
            if (arrowPos >= points[i][0]) { // Jinki v start pt. mere end pt. se chooti h wo sab foot jaaenge mere curr arrow se
                continue; // Islye continue kar jaao
            }
            arrowCnt++; // Jinka start pt. mere end pt. se zyada hai uske liye koi dusri arrow ki zarrorat paregi
            arrowPos = points[i][1]; // End pt. pe apna next arrow rakho
        }
        return arrowCnt;
    }
}
```

----------------------------------------------------------------------------------------------------------------------

// Sorting on start, traversing from back 
```
class Solution {
    
    public int findMinArrowShots(int[][] points) {
        int n = points.length;
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));
        int arrowPos = points[n - 1][0];
        int arrowCnt = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (arrowPos <= points[i][1]) {
                continue;
            }
            arrowCnt++;
            arrowPos = points[i][0];
        }
        return arrowCnt;
    }
}
```
----------------------------------------------------------------------------------------------------------------------------
