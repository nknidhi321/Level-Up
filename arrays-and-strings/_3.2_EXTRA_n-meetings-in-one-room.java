// https://practice.geeksforgeeks.org/problems/n-meetings-in-one-room-1587115620/1#

// Similar to burst ballon

class Solution {

    public static int maxMeetings(int start[], int end[], int n) {
        int[][] pair = new int[n][2];
        for(int i = 0; i < n; i++) {
            pair[i][0] = start[i];
            pair[i][1] = end[i];
        }
        
        Arrays.sort(pair, (a, b) -> a[1] - b[1]);
        
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
            if(endSoFar < currStart) { // no overlapping
                meetingsCount++;
                startSoFar = currStart;
                endSoFar = currEnd;
            }
            else { // overlapping
                // Do nothing  
            }
        }
        
        return meetingsCount;
    }
    
}
