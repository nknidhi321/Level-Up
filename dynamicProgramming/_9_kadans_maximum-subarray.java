// https://leetcode.com/problems/maximum-subarray/

/*
  Subset k jahah subarray smjhna, subset is wrong term here, subarray hoga
  NOTE: Kadane's Algorithm doesn't work for all negative pool of array, atleast one element shoud be positive for Kadane's to work smoothly.
  Hence, when all elements are negative, then for those test cases, you need revised Kadane's Algo(Kadane's Algo with some modifications).

  //Pepcoding //Sumeet Sir Solution //Revised Kadane's Algo //Best
  //https://www.youtube.com/watch?v=VMtyGnNcdPw

  The idea is karja(-ve) me pichla pidhi ho, toh usko chor denge aur apni family shuru karenge.

  Toh question is bich me kvi kvi -ve ko q le lete h ?  
  Kuki currSubarraySum ko umeed hoti h ki aage maybe koi bht bada number mil jaaye jo avi tak k currSubarraySum ko bada bana de, 
  kuki contigous rehna chahiye islye kisi negative element ko aise he discard kar dena kuki wo negative h, won't result in correct answer.
  Aur agar currSubarraySum ko aage koi bht bada number nahi v mil paaya aur kisi pichle negative number k aane k wazah se overall currSubsetSum kam ho gaya h,
  toh v koi baat nahi, kuki har iteration pe hm maxSubarraySum maintain kar k rakh rahe h.

  NOTE: Beshak negative apna family shuru karega par, agla negative bolega, tum already karja me ho, hm apna family shuru karenge. 
  Ex : -12, -2, 3
  Is example me -12 apna family bnate huye aaega, par jab -2 ki baari hogi toh wo bolega tum -12 k karja me ho, toh mai apni family bnaunga, 
  Fir -2 is the currSubarraySum and jab 3 ki baari aaegi toh dekhega ki -2 karje me h currSubarraySum toh 3 apna family banaega.
*/

```
class Solution {
    
    public int maxSubArray(int[] nums) {    
        int n = nums.length;
        int currSubarraySum = 0;
        int maxSubarraySum = Integer.MIN_VALUE;
        
        for(int start = 0; start < n; start++) {       
            if(currSubarraySum >= 0) currSubarraySum += nums[start];
            else currSubarraySum = nums[start];
            maxSubarraySum = Math.max(maxSubarraySum, currSubarraySum);
        }
        
        return maxSubarraySum;
    }
}
```
-------------------------------------------------------------

/*
  //Anuj Bhaiya Solution // Above one is easy to understand
  Kadane's Algorithm : TC : O(N), SC : O(1), Accepted Solution

  So, Kadane's idea is to keep on adding the elements till the subArray is > 0 else if it is < 0 then discard the previous SubArray sum, 
  beacuse adding them(negative subArray), would lessen the overall sum, so reinitialize the currSum to 0 if the overall sum is < 0, 
  to make a fresh start from the next element and follow the same procedure for the rest unexplored array. Finally you will land up with the maximum subArray sum, 
  simply return.

  Now, comes how to tackle when their is pool of negative elements, notice that wheneven currSubsetSum < 0, Kadane's reinitialize the currSubsetSum to 0, 
  so when all elements are negative then the maximum subArray sum will be 0, since it is taking max of currSubsetSum.

  So, the solution to this problem is when all the elements of the nums are negative then the max_value_of_all_negative would be your answer. Why so ?
  Because adding any extra elements from the pool of all negative numbers will eventually make the overall sum as more negative. 
  Hence, max_value_of_all_negative is your desired answer.
*/

```
class Solution {
    public int maxSubArray(int[] nums) {
        
        int max_value_of_all_negative = Integer.MIN_VALUE;
        int maxSubsetSum = Integer.MIN_VALUE;
        int currSubsetSum = 0;
        for(int start = 0; start < nums.length; start++){
            max_value_of_all_negative = Math.max(max_value_of_all_negative, nums[start]);
            currSubsetSum += nums[start];
            if(currSubsetSum < 0) currSubsetSum = 0;
            maxSubsetSum = Math.max(maxSubsetSum, currSubsetSum);
        }
        return maxSubsetSum > 0 ? maxSubsetSum : max_value_of_all_negative;
    }
}
```
-------------------------------------------------------------

/*
  TC : O(N^2), SC : O(1), Accepted Solution
  Here the idea is why to generate All Subsets, more precisely why to keep the 3rd loop, which starts from the start and goes till end, end +1, end + 2, and so on..
  every time.
  You can simply keep track of the explored nums sum starting from a particular element of the nums array and go on filtering the maximum SubArray
  till the last value of the nums array.
  Similary for the next pass (start + 1) and so on..........
  NOTE: When all elements of nums would be negative then also this solution would run smoothy.
*/
```
class Solution {
    public int maxSubArray(int[] nums) {
        
        int maxSubsetSum = Integer.MIN_VALUE;
        for(int start = 0; start < nums.length; start++){
            int currSubsetSum = 0;
            for(int end = start; end < nums.length; end++){
                currSubsetSum += nums[end];
                maxSubsetSum = Math.max(maxSubsetSum, currSubsetSum);
            }
        }
        return maxSubsetSum;
    }
}
```
-------------------------------------------------------------

/*
  TC: O(N^3) , SC: O(1), TLE Solution 
  Here the idea is to generate All Subsets, and then decide which one is the maximum Subset. 
  NOTE: When all elements of nums would be negative then also this solution would run smoothy apart from TLE
*/

```
class Solution {
    public int maxSubArray(int[] nums) {
        
        int maxSubsetSum = Integer.MIN_VALUE;
        for(int start = 0; start < nums.length; start++){
            for(int end = start; end < nums.length; end++){
                int currSubsetSum = 0;
                for(int k = start; k <= end; k++){
                    currSubsetSum = currSubsetSum + nums[k];
                }
                maxSubsetSum = Math.max(maxSubsetSum, currSubsetSum);
            }
        }
        return maxSubsetSum;
    }
}
```
