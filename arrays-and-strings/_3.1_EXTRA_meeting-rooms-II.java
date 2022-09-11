// https://www.lintcode.com/problem/919/
// Using PQ

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
    
    public int minMeetingRooms(List<Interval> intervals) {
        int n = intervals.size();
        Collections.sort(intervals, (a, b) -> a.start - b.start); // sort on starting pt.
        PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.end - b.end); // sort on ending pt.
        int count = 1;
        
        for(int i = 0; i < n; i++) {
            Interval currInterval = intervals.get(i);
            int currStart = currInterval.start;
            if(!pq.isEmpty()) {
                Interval pair = pq.peek(); // pair with least ending time
                int leastEndingTime = pair.end;
                if(currStart < leastEndingTime) {
                    count++;
                }
                else {
                    pq.remove();
                }
            }
            pq.add(currInterval);
        }
        return count;
    }
}

//================================================================================================================
// Without using PQ

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
   
    public int minMeetingRooms(List<Interval> intervals) {
        int n = intervals.size();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = intervals.get(i).start;
        }

        int[] dep = new int[n];
        for(int i = 0; i < n; i++) {
            dep[i] = intervals.get(i).end;
        }

        // Kisi v moment pe max. kitney number of platforms available hai, that is answer
        // So, trains ki arrival and departure time saath me leke chalne ka koi sense nai bnta hai

        // Koi v train depart huyi then new train lga do, else nayi platform leke aao 
        Arrays.sort(arr); // Sort both of them individually
        Arrays.sort(dep); // Sort both of them individually
        
        int platform = 1; // For the 0th idx train
        
        for(int i = 1, j = 0; i < n; i++) { // starting from 1st idx
            if(dep[j] > arr[i]) platform++; // Agar departureTime zyada hai curr arrival time se then you req. a new platform
            else j++; // Agar departureTime kam hai then next depatureTime pe point krwa do
        }
        return platform;
    }
    
}   
