// https://www.lintcode.com/problem/920/

/**
 * Definition of Interval:
 * public class Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

public class Solution {
    
    public boolean canAttendMeetings(List<Interval> intervals) {
        if(intervals.isEmpty()) return true;

        int n = intervals.size();
        Collections.sort(intervals, (a, b) -> { // Sort at end 
            // jab dono intervals ka end pt. same ho then jiska sbse pehle start pt. ho
            // usko pehle rakhoge toh overlapping hone wala answer "jaldi" milega
            // Ye nai v karoge toh v chalega, just for dimag ki batti
            if(a.end == b.end) return a.start - b.start;
            else return a.end - b.end;
        }); 
        
        int startSoFar = intervals.get(n - 1).start;
        int endSoFar = intervals.get(n - 1).end;
        
        for(int i = n - 2; i >= -1; i--) { // End pe sort kiya, so end se ans bnao
            if(i == -1) { // (i == -1) Last interval ka ans
                // Merge intervals me yaha ans bnta tha, keeping this just for clearity
                continue;
            }
            int currStart = intervals.get(i).start;
            int currEnd = intervals.get(i).end;
            
            if(currEnd <= startSoFar) { // Non overlapping
                startSoFar = currStart; // For next iteration
                endSoFar = currEnd; // For next iteration
            }
            else {  // currEnd >= startSoFar   // overlapping, so merge in same
                return false; // Merge hoke jo v chota bnda hoga wo startPoint rahega, wo jeetega
            }
        }
        return true;
    }
}
