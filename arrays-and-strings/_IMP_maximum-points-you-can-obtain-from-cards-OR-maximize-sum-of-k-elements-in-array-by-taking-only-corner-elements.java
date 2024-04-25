// https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/

// https://www.geeksforgeeks.org/maximize-sum-of-k-elements-in-array-by-taking-only-corner-elements/
// Will work for -ve numbers also

// If we take k - x elements from the start of the array
// then take x elements from the end of the array

class Solution {
    
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int curr = 0;
        
        // Take window of size k from starting
        for(int i = 0; i < k && i < n; i++) {
            curr += cardPoints[i];
        }
        
        int max = curr;
        
        // Now keep subtracting an element from ending of k size window 
        // And keep adding an end element from the cardPoints
        // And compare the overall max
        int j = cardPoints.length - 1;
        for(int i = k - 1; i >= 0; i--) {
            curr = curr - cardPoints[i] + cardPoints[j--];
            max = Math.max(max, curr);
        }
        return max;
    }
    
}
