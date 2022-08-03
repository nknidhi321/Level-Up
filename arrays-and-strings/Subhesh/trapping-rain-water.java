// https://leetcode.com/problems/trapping-rain-water/

// form prefix array of leftMaxHeightSoFar => "Your" left boundary will be the tallest pillar from left
// form suffix array of rightMaxHeightSoFar => "Your" right boundary will be the tallest pillar from right
// Now, take out your contribution by subtracting your height * width(1) from the left boundry and right boundary area, and keep summing up for your ans.
class Solution {
    
    public int trap(int[] height) {
        int n = height.length;
        
        int leftMaxHeightSoFar = 0;
        int[] leftMaxHeight = new int[n];
        for(int i = 0; i < n; i++) {
            leftMaxHeight[i] = leftMaxHeightSoFar = Math.max(leftMaxHeightSoFar, height[i]);
        }
        
        int rightMaxHeightSoFar = 0;
        int[] rightMaxHeight = new int[n];
        for(int i = n - 1; i >= 0; i--) {
            rightMaxHeight[i] = rightMaxHeightSoFar = Math.max(rightMaxHeightSoFar, height[i]);
        }
        
        int sum = 0;
        for(int i = 0; i < n; i++) {
            sum += Math.min(leftMaxHeight[i], rightMaxHeight[i]) - height[i]; // Your contri
        }
        
        return sum;
    }
    
}
