//https://leetcode.com/problems/search-insert-position/

//Rajneesh
// Works both for duplicate and non duplicate

class Solution {
    
    // Algo works for duplicate elements as well
    
    public int searchInsert(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        
        while(low <= high) {
            int mid = low + (high - low) / 2;
            
            // Even if you get your target go to right
            // Because here the idea is to find the ceil and then find your answer
            // If target does not exist, the sorted pos where it will exist will be the ceil of target
            if(arr[mid] <= target) 
                low = mid + 1;
            else // target < arr[mid] 
                high = mid - 1;
        }
        
        // insertPos == ceil of target
        // low for unsuccessful search => ceil of target
        int insertPos = low; 
        int lastIndex = low - 1; // So if target exists, it will be at position previous to its ceil idx
        
        // Check if (low - 1) => last index of target is not outOfBounds,
        // if idx is valid && if the data at (low - 1)th idx is equal to your target return lastIndex
        // else return insertPos => ceil   
        return (lastIndex >= 0 && arr[lastIndex] == target) ? lastIndex : insertPos;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Archit 

/*
    NOTE : Elements are distinct, below code will only work when elements are distinct.

    Intuition:
    Target mil gaya toh bht sahi, return that index, aur nai mila toh search karte raho jab tak low aur high cross nai kar jaatey.

    Target nahi mila toh target ka ceil is your answer :- 
        => low will always point to target ka ceil for unsuccessful target search return that
        => high will always point to target ka floor for unsuccessful target search [INFO]
*/

class Solution {
    public int searchInsert(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(arr[mid] == target) return mid;
            else if(target < arr[mid]) high = mid - 1;
            else low = mid + 1;
        }
        return low;
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
