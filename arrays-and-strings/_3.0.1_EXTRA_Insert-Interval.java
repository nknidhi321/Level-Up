// https://leetcode.com/problems/insert-interval/?envType=study-plan-v2&envId=top-interview-150

/*
    You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] 
    represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. 
    You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
    
    Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and 
    intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
    
    Return intervals after the insertion.
    
    Note that you don't need to modify intervals in-place. You can make a new array and return it.
    
     
    
    Example 1:
    
    Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
    Output: [[1,5],[6,9]]
    Example 2:
    
    Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
    Output: [[1,2],[3,10],[12,16]]
    Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
*/

// Complexity : O(N), Linear
// NOTE : We could have used BS also, but complexity would have remained same because we are returning a new array 
// so we have to copy all the 1st half remaining array + merged array + last half remaining array

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
