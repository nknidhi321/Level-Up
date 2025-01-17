// https://leetcode.com/problems/find-the-kth-largest-integer-in-the-array/

/*
You are given an array of strings nums and an integer k. 
Each string in nums represents an integer without leading zeros.

Return the string that represents the kth largest integer in nums.

Note: Duplicate numbers should be counted distinctly. 
For example, if nums is ["1","2","2"], "2" is the first largest integer, 
"2" is the second-largest integer, and "1" is the third-largest integer.

Example 1:

Input: nums = ["3","6","7","10"], k = 4
Output: "3"
Explanation:
The numbers in nums sorted in non-decreasing order are ["3","6","7","10"].
The 4th largest integer in nums is "3".
Example 2:

Input: nums = ["2","21","12","1"], k = 3
Output: "2"
Explanation:
The numbers in nums sorted in non-decreasing order are ["1","2","12","21"].
The 3rd largest integer in nums is "2".
Example 3:

Input: nums = ["0","0"], k = 2
Output: "0"
Explanation:
The numbers in nums sorted in non-decreasing order are ["0","0"].
The 2nd largest integer in nums is "0".
*/

// Approach :
// PQ can hold duplicate values also, it will first pop the val which was added first 
// Simple String sorting

class Solution {
    
    public String kthLargestNumber(String[] nums, int k) {
        PriorityQueue<String> minPQ = new PriorityQueue<>((a, b) -> { // Simple string sorting
            if(a.length() == b.length()) return a.compareTo(b); // if same length, sort on val
            else return a.length() - b.length(); // if diff length, sort based on len
        });
        
        for(int i = 0; i < nums.length; i++) {
            minPQ.add(nums[i]);
            if(minPQ.size() > k) {
                minPQ.poll();
            }
        }
        return minPQ.poll();
    }
    
}
