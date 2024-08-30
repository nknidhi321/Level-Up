// https://leetcode.com/problems/sort-colors/

/*
	My Region :-
        ---------
        [0 - ptr] => 0
        [ptr+1 - itr-1] => 1
        [itr - ptr-1] => Undefined
        [ptr - n-1] => 2
        
        
        Apporach:-
        --------
        Iterator will check for 0 and 2, if  
            -> 0 found, throw to the left
            -> 2 found throw at the right side
            -> 1 found increase your region
            
        NOTE :
        -----
        Loop will terminate when itr == ptr2, because ptr2 is pointing at it's own valid start region of 2,
        and if you don't terminate and try to swap then first ptr2 will get decremented and then swap(pt2, itr)
        And this will mess your beautiful segregation done until a step before.
        
        FYI : 
        ----
        Rajneesh Bhaiya's region was different
        Rajneesh Bhaiya's ptr2 was pointing at n-1, at the end of undefined region
        So, he is continuing his loop at itr == pt2, because that is undefined and needs to be chceked.
    */
		
```
class Solution {
    
    public void sortColors(int[] nums) {
        int n = nums.length, ptr1 = -1, itr = 0, ptr2 = n;
        
        while(itr < ptr2) {
            if(nums[itr] == 0) {
                swap(++ptr1, itr++, nums);
            }
            else if(nums[itr] == 2) {
                swap(--ptr2, itr, nums);
            }
            else itr++;
        }
    }
    
    public static void swap(int ptr, int itr, int[] nums) {
        int temp = nums[ptr];
        nums[ptr] = nums[itr];
        nums[itr] = temp;
    }
}
```
----------------------------------------------------------------------------------------------------------
// Youtube
// Ignore
```
class Solution {
   
    public void sortColors(int[] nums) {     
        int red = 0;
        int white = 0;
        int blue = nums.length - 1;
      
        while(white <= blue){
            if(nums[white] == 0)
                swap(nums, white++, red++);
            else if(nums[white] == 2)
                swap(nums, white, blue--);
            else 
                white++;
        }
    }
    
    public static void swap(int[] nums, int white, int random){
        int temp = nums[white];
        nums[white] = nums[random];
        nums[random] = temp;
     }
}
```
--------------------------------------------------------------------------------------------------------------
