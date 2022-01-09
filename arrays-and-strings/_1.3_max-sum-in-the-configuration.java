// https://practice.geeksforgeeks.org/problems/max-sum-in-the-configuration/1/
// Check Rajneesh Bhaiya's SS notes for observation

class GfG {
    
    int max_sum(int arr[], int n) {
        
        int sum = 0, sumWithIndex = 0;
        for(int i = 0; i < n; i++) {
            sum += arr[i];
            sumWithIndex += (i * arr[i]);
        }
        
        // 0th rotation has been checked
        int max = sumWithIndex;  
        
        // Now check from 1 to n-1 rotation
        for(int i = 1; i < n; i++) {
            
            // Derive each rotation sumWithIndex from the previously calculated sumWithIndex
            // Formula :  sumWithIndex - sum + (n*arr[i-1])     [From observation]          
            sumWithIndex = sumWithIndex - sum + (n*arr[i-1]);
            max = Math.max(max, sumWithIndex); 
        }
        
        return max;
    }	
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Brute Force 
// TC : O(N^2)
// TLE 

// Idea is 1 se n tak ek ek baar sabko rotate karo sum find karo and then find max.

class GfG {
    
    int max_sum(int nums[], int n) {
        int max = Integer.MIN_VALUE;
        
	    for(int k = 0; k < n; k++) {
	        rotate(nums, k + 1);
	        int sum = 0;
	        for(int i = 0; i < n; i++) {
	            sum = sum + (i * nums[i]);
	        }
	        max = Math.max(max, sum);
	    }
	    return max;
    }
    
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        
        /* 
          If k would be negative then 
            -> do +n extra to make k positive
            -> % n is not necessarily needed because (-ve number + n) would always keep you in range [0 to n-1]
        */
        
        // Below formula will handle both postive and negative k
        k = ((k % n) + n) % n; 
        
        
        /* 
            When you see the rotated output array, you can observe
            Reverse pattern of the array + Maintanance of their relative order is required accordingly
        */
        
        // Reverse whole array 
        reverse(0, n - 1, nums);  
        
        // Now, reverse the relative Order
        reverse(0, k - 1, nums);  
        reverse(k, n - 1, nums);
    }
    
    public static void reverse(int si, int ei, int[] nums) {
        while(si < ei) {
            int temp = nums[si];
            nums[si++] = nums[ei];
            nums[ei--] = temp;
        }
    }
    
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
