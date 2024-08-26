// https://leetcode.com/problems/minimum-time-to-complete-trips/

/*
    Binary Search on Answers. First we need to see that what can be the range of time. 
    The range of time will be lowest value of the time array and 
    highest value will be minimum value in the time array multiplied by totalTrips because at worst case 
    the bus with min time will do all trips which will be minimum time taken to do all trips or 
    you can take maximum value in time array multiplied by totalTrips 
    if you are thinking in this way that at worstcase the bus with highest time will do all the trips. 
    As the question is asking for minimum time so first one makes more sense than later one. 
    But both are correct because obviously it will be eliminating 
    right half if it gets totalTrips done with that particular time. 
    Then we just traverse through the ranges and using Binary Search 
    we check if the totalTrips is possible to do in that time or not. 
*/

```
class Solution {
    
    public long minimumTime(int[] time, int totalTrips) {
        int n = time.length;
        long low = Long.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            low = Math.min(low, (long)time[i]);
        }
        long hi = low * totalTrips;
        long probableAns = -1;
        
        while(low <= hi) {
            long mid = low + (hi - low) / 2;
            if(isPossible(mid, totalTrips, time)) {
                probableAns = mid;
                hi = mid - 1;    
            }
            else {
                low = mid + 1;
            }
        }
        return probableAns;
    }
    
    public boolean isPossible(long mid, int totalTrips, int[] time) {
        long currTotalTrips = 0;
        for(int i = 0; i < time.length; i++) {
            currTotalTrips += (mid / (long)time[i]);
        }
        return currTotalTrips >= totalTrips;
    }
    
}
```
