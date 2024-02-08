// https://leetcode.com/problems/first-missing-positive/

class Solution {
    
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            while(i + 1 != nums[i] && nums[i] > 0 && nums[i] <= n) {
                int index = nums[i];
                if(index > 0 && index <= n) {
                    if(nums[index- 1] == index){
                        break;
                    }
                    swap(i, index - 1, nums);
                }
            }
        }
        
        for(int i = 0; i < n; i++) {
            if(i + 1 != nums[i]) {
                return i + 1;
            }
        }
        return n + 1;
    }
    
    public void swap(int a, int b, int[] nums) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
    
}
