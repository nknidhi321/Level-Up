// https://leetcode.com/problems/sort-array-by-parity/

NOTE :
-----
Here relative place order of even numbers would be maintained,
but odd numbers place order would not be maintained.
  
Why ?? Because negative numbers toh piche se aage apne sahi position pe aa jaa rahe hai, but positive wale ko aage se piche v kiya jaa raha hai swapping k waqt.

/*
	Region :-
        ------
        [0 - ptr] => Even
        [ptr+1 - itr-1] => Odd
        [itr - n-1] => Undefined 
        
        
        Apporach:-
        --------
        Iterator will check for even numbers, if found  
            -> first increase the ptr and swap(ptr, itr)

        Increase itr (At every iteration itr will be increased)

        Why ??

        Beacuse ptr increase karne k baad he, itr k saath swap kiya jaa raha hai => ptr+1 pe definitely odd number para hoga
        since region is already defined, so why to check at the itr again after swapping, so increase itr.

        And odd  hai toh simply apna region expand karo so itr++
 */

/*
        NOTE:
        -----
        ptr will always point to the last even number upto the itr checked index, the value at ptr++ will always be an odd number, 
        so if we swap ptr++ and itr value, the itr will be pointing to an odd number, hence we need not check for the same itr index value in the next pass, 
        hence itr will increase in every iteration.

        We reach to a conclusion that :-
          ->from first to ptr we have even numbers and
          ->from ptr+1 to itr-1, we have all  odd numbers.
*/
  
```
class Solution {

    public int[] sortArrayByParity(int[] nums) {
        
        int n = nums.length, ptr = -1, itr = 0;
        
        while(itr < n) {
            if(nums[itr] % 2 == 0) {
                swap(++ptr, itr, nums);
            }
            itr++;
        }
        return nums;
    }
    
    public static void swap(int ptr, int itr, int[] nums) {
        int temp = nums[ptr];
        nums[ptr] = nums[itr];
        nums[itr] = temp;
    }
}
```
------------------------------------------------------------------------------------------------------------------------------------------------------------
