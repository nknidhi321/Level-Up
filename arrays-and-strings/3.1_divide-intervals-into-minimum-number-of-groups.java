// https://leetcode.com/problems/divide-intervals-into-minimum-number-of-groups/

class Solution {
    
    public int minGroups(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // sort on starting pt.
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]); // sort on ending pt.
        int count = 1;
        
        for(int i = 0; i < n; i++) {
            int[] currInterval = intervals[i];
            int currStart = currInterval[0];
            if(!pq.isEmpty()) {
                int[] pair = pq.peek(); // pair with least ending time
                int leastEndingTime = pair[1];
                if(currStart <= leastEndingTime) {
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
