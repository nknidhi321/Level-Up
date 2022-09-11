// https://leetcode.com/problems/non-overlapping-intervals/

// Ulta of https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/

class Solution {
    
    public int eraseOverlapIntervals(int[][] pair) {
        int n = pair.length;
        Arrays.sort(pair, (a, b) -> Integer.compare(a[1], b[1])); // end ko fix kr diya
        
        int startSoFar = pair[0][0];
        int endSoFar = pair[0][1];
        int meetingsCount = 0;
        
        for(int i = 1; i < n; i++) {  
            int currStart = pair[i][0];
            int currEnd = pair[i][1];
            
            if(endSoFar <= currStart) { // no overlapping
                startSoFar = currStart;
                endSoFar = currEnd;
            }
            else { // overlapping 
                meetingsCount++;
            }
        }
        return meetingsCount;
    }
    
}
