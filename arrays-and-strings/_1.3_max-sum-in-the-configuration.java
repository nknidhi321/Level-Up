// https://practice.geeksforgeeks.org/problems/max-sum-in-the-configuration/1/
// Check Rajneesh Bhaiya's SS notes for observation

/*      
	n = 5
	arr[i] = {a, b, c, d, e}  
	
	sum = a + b + c + d + e
	sumWithIndex = sum1 = 0*a + 1*b + 2*c + 3*d + 4*e
 
  	sum2 : (0*a + 1*b + 2*c + 3*d + 4*e) - (a + b + c + d + e) + (5*a) => 0*b + 1*c + 2*d + 3*e + 4*a
  	sum3 : (0*b + 1*c + 2*d + 3*e + 4*a) - (a + b + c + d + e) + (5*b) => 0*c + 1*d + 2*e + 3*a + 4*b
   	sum4 : (0*c + 1*d + 2*e + 3*a + 4*b) - (a + b + c + d + e) + (5*c) => 0*d + 1*e + 2*a + 3*b + 4*c
    	sum5 : (0*d + 1*e + 2*a + 3*b + 4*c) - (a + b + c + d + e) + (5*d) => 0*e + 1*a + 2*b + 3*c + 4*d

     	Therefore, sumWithIndex = sumWithIndex - sum + (n * arr[i - 1])
*/
class GfG {
    
    int max_sum(int arr[], int n) {
        
        int sum = 0, sumWithIndex = 0;
        for(int i = 0; i < n; i++) {
            sum += arr[i];
            sumWithIndex += (i * arr[i]);
        }
        
        // 0th/nth rotation has been checked
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
