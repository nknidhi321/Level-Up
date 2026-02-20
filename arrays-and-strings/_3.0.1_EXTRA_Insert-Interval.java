// https://leetcode.com/problems/insert-interval/?envType=study-plan-v2&envId=top-interview-150
// Ex : intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]

// Complexity : O(N), Linear

class Solution {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // Add all intervals before newInterval
        while (i < n && intervals[i][1] < newInterval[0]) {
            list.add(intervals[i]);  // [1,2]
            i++;
        }

        // Merge overlapping intervals
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]); 
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);  
            i++;
        }

        list.add(newInterval);

        // Add remaining intervals
        while (i < n) {
            list.add(intervals[i]);
            i++;
        }

        return list.toArray(new int[list.size()][]);
    }

}
