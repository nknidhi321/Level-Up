// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

// Most Intuitive
// Archit
// Binary Search
// Time Complexity : O(logN)
 
/*
	Approach
   ---------	
	Ek potentialAnswer leke chalo, aur jab v tumko apna taget mil jaaye keh do, for the time being tum he mere answer ho,
	aur agar first Occurence find kar rahe hai toh left move kar jaaenge ya last Occurence find kar rahe hai toh right move kar jaaenge.
*/

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

//Rajneesh
// Binary Search
// Time Complexity : O(logN)

/*
	Approach
	--------
	
    For first Occurence :-
    Jab target mil jaaye toh khud pe khare hoke apne se pehle element ko check kar lo :-
			-> Agar equal hai toh left move kar jaao, to find 1st idx
			-> Agar equal nai hai toh tum he mere answer ho

    For last Occurence :-
    Jab target mil jaaye toh khud pe khare hoke apne se baad wale element ko check kar lo :-
			-> Agar equal hai toh right move kar jaao, to find last idx
			-> Agar equal nai hai toh tum he mere answer ho
*/

class Solution {
    
    public int[] searchRange(int[] arr, int target) {
        return new int[] {firstOccurence(arr, target), lastOccurence(arr, target)};
    }  
        
    public static int firstOccurence(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(arr[mid] == target) {
                if(mid - 1 >= 0 && arr[mid - 1] == arr[mid]) high = mid - 1;
                else return mid;
            }
            else if(target < arr[mid]) {
                high = mid - 1;
            }
            else { // arr[mid] < target
                low = mid + 1;
            }
        }
        return -1;
    }
        
    public static int lastOccurence(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(arr[mid] == target) {
                if(mid + 1 < arr.length && arr[mid] == arr[mid + 1]) low = mid + 1;
                else return mid;
            }
            else if(arr[mid] < target) {
                low = mid + 1;
            }
            else { // target < arr[mid]
                high = mid - 1;
            }
        }
        return -1;
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
