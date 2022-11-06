// https://leetcode.com/problems/contains-duplicate/description/

// The idea is will store both x and -x on the same index
/*
        'x' -> empty
        '-' -> only -ve bnda
        '+' -> only +ve bnda
        '&' -> both +ve -ve bnda
  
        Since this question includes both -ve && +ve nos. 
        so sirf +ve numbers k array ka size bnao 
        
        Now, ek char[] array bna lo and
        -ve no hai toh - sign assign kr do 
        +ve no hai toh + sign assign kr do
        Dono +ve -ve mil gye h toh & assign kr do

        Ab, conditions lga lo duplicacy ka
        NOTE : 10 ^ 7 se zyada ka array nahi bn paa raha, mermory limit exceeded
        The sol. is correct but there is space constraints.
*/

class Solution {
    
    public boolean containsDuplicate(int[] nums) {
        int n = nums.length;
        char[] arr = new char[(int)1e9 + 1];  // nums[i]    
        Arrays.fill(arr, 'x'); // To check if it was ever filled

        for(int i = 0; i < n; i++) {
            int num = Math.abs(nums[i]); // index cannot be -ve

            // Got duplicate => Kahani khatam
            if(arr[num] == '&' ||
               arr[num] == '-' && nums[i] < 0 || 
               arr[num] == '+' && nums[i] >= 0) {
                return true;
            }

            // Got -ve no for the first time
            else if(arr[num] == 'x' && nums[i] < 0) {
                arr[num] = '-';
            }

            // Got +ve no for the first time
            else if(arr[num] == 'x' && nums[i] >= 0) {
                arr[num] = '+';
            }

            // Got both +ve and -ve no.
            else if(arr[num] == '-' && nums[i] > 0 ||
                    arr[num] == '+' && nums[i] <= 0) {
                arr[num] = '&';
            }
        }
 
        // Displaying 
        // for(int i = 0; i < (int)1e7; i++) {
        //     System.out.println(i + " " + arr[i]);
        // }

        return false;
    }
}
