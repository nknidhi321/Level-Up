//https://practice.geeksforgeeks.org/problems/number-of-occurrence2259/1#

class Solution {
    
    int count(int[] arr, int n, int target) {
        
        int firstOcc = firstOccurence(arr, target);
        int lastOcc = lastOccurence(arr, target);
        
        if(firstOcc == -1 || lastOcc == -1) return 0; // If target not present
        else return lastOcc - firstOcc + 1; // Target is present 
    }
    
    // Move to left even if you find your target
    // Because you might find your target to the left again
    // If you do not find target to left again then the previous potentialAnswer is your answer
    public static int firstOccurence(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int potentialAnswer = -1;
        
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(arr[mid] == target) {
                potentialAnswer = mid;
                high = mid - 1;
            }
            else if(target < arr[mid]) {
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        return potentialAnswer;
    }
        
  
    // Move to right even if you find your target
    // Because you might find your target to the right again
    // If you do not find target to right again then the previous potentialAnswer is your answer
    public static int lastOccurence(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int potentialAnswer = -1;
        
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(arr[mid] == target) {
                potentialAnswer = mid;
                low = mid + 1;
            }
            else if(arr[mid] < target) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }
        return potentialAnswer;
    }
    
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
