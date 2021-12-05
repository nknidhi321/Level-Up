Google Question 

//https://leetcode.com/problems/first-bad-version/

/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

//Intuition : if you get the 1st isBadVersion true move to left search space because you have to find isBadVersion

public class Solution extends VersionControl {
    
    public int firstBadVersion(int n) {
        int low = 1;
        int high = n;
        int potentialAnswer = -1;
        
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(isBadVersion(mid)) {
                potentialAnswer = mid;
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        return potentialAnswer;
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
