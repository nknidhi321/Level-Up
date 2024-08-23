// https://leetcode.com/problems/last-moment-before-all-ants-fall-out-of-a-plank/

class Solution {
    
    public int getLastMoment(int n, int[] left, int[] right) {
        int maxLeftPos = Integer.MIN_VALUE;
        int minRightPos = Integer.MAX_VALUE;
        
        // From Left array cal maxPos, From Right array cal minPos 
        for(int i = 0; i < left.length || i < right.length; i++) {
            if(i < left.length) maxLeftPos = Math.max(maxLeftPos, left[i]);
            if(i < right.length) minRightPos = Math.min(minRightPos, right[i]);
        }
        
        // Calculate distance = b - a, 
        int rightMaxDistance = n - minRightPos; // how much time leftmost ant going towards right will take to fall
        int leftMaxDistance = maxLeftPos - 0; // how much time rightmost ant going towards left will take to fall
        
        // Now, rightmost or leftmost ant who is taking the maxTime
        return Math.max(rightMaxDistance, leftMaxDistance);
    }
    
}
