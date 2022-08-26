// https://leetcode.com/problems/make-array-zero-by-subtracting-equal-amounts/

// Jitne unique numbers honge utni operation lgegi usko 0 bnane me

// Think sbse chote number ko sbse pehle 0 bnate ho, fir us se "just bade" ko 0 bnaoge
// Note "just bade" agax x times aa raha hai toh un saare x ko 0 bna doge, 
// So, simple jitne unique bnde honge utni operation lgegi

class Solution {
    
    public int minimumOperations(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0 ; i < nums.length; i++) {
            if(nums[i] == 0) continue;
            set.add(nums[i]);
        }
        return set.size();
    }
}
