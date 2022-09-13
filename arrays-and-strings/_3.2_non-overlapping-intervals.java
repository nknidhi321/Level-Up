// https://leetcode.com/problems/non-overlapping-intervals/

/*
        The idea is to sort all intervals on starting pt. 
        Now, compare the intervals, while chosing any interval
        always keep the interval with the least ending pt.[discard the interval with the greater ending pt.],
        so that it has less possibility of overlapping with the upcoming intervals


        1)       -----------------------    Discard this larger interval
                        --------            => Lesser ending pt. [Keep this]


        2)        -------------------       => Lesser ending pt. [Keep this]
                                ----------  Discard this interval 
*/


class Solution {
    
    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // sort on starting pt.
        
        int start = intervals[0][0];
        int end = intervals[0][1];
        
        int count = 0;
        for(int i = 1; i < n; i++) {
            int currStart = intervals[i][0];
            int currEnd = intervals[i][1];
            if(end > currStart) { // overlapping
                count++;
                end = Math.min(end, currEnd); // jiski ending pt. kam h usko rakh lo
            }
            else { // non overlapping
                start = currStart;
                end = currEnd;
            }
        }
        return count;
    }
    
}


//================================================================================================================

// Using same technique of bus Ulta of https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
// Complicated // Ignore

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
