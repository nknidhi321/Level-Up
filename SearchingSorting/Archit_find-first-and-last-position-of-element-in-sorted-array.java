// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

// Modified Binary Search 
// O(logN)

class Solution {
    
    public int[] searchRange(int[] arr, int target) {
        return new int[] {firstOccurence(arr, target), lastOccurence(arr, target)};
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

//O(n)

class Solution {
	
    public int[] searchRange(int[] nums, int target) {
        int first = -1;
        int last = -1;
        int[] arr = new int[2];
		
        //Search linearly from start for firstOccurence
		for(int i =0; i<nums.length; i++){
            if(target == nums[i]){
                first = i;
                break;
            }
        }
		
		//Search linearly from end for lastOccurence
        for(int i = nums.length-1; i>=0; i--){
            if(target == nums[i]){
                last = i;
                break;
            }
        }
        arr[0] = first;
        arr[1] = last;
        return arr;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
