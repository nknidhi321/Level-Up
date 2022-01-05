// https://leetcode.com/problems/4sum/

// Seeresh

class Solution {
    
    public static int n;
    
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ansList = new ArrayList<>();
        n = nums.length;
        if(n < 4) return ansList;
        
        Arrays.sort(nums);
        for(int i = 0; i <= n - 4; i++) { // Fixing the ith element and calling 3 pointer from [i+1 to n-1]
            if(i != 0 && nums[i] == nums[i - 1]) continue; // Skipping duplicates
            
            List<List<Integer>> lists = threeSum(i + 1, target - nums[i], nums);
            for(List<Integer> list : lists) {
                list.add(0, nums[i]); // Shifting will take place, since adding at 0th idx in ArrayList
                ansList.add(list);
            }
        }
        return ansList;
    }

    public List<List<Integer>> threeSum(int si, int target, int[] nums) {
        List<List<Integer>> ansList = new ArrayList<>();
        
        for(int i = si; i <= n - 3; i++) { // Fixing the ith element and calling 2 pointer from [i+1 to n-1]
            if(i != si && nums[i] == nums[i - 1]) continue; // Skipping duplicates
            
            List<List<Integer>> lists = twoSum(i + 1, n - 1, target - nums[i], nums);
            for(List<Integer> list : lists) {
                list.add(0, nums[i]); // Shifting will take place, since adding at 0th idx in ArrayList
                ansList.add(list);
            }
        }
        return ansList;
    }
    
    public static List<List<Integer>> twoSum(int start, int end, int target, int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int si = start;
        // Two Pointer
        while(start < end) {
            // Since unique pairs is asked so surpass all the elements from start which are same
            if(start != si && nums[start] == nums[start - 1]) {
                start++;
                continue;
            }
            int sum = nums[start] + nums[end];
	    
	        if(sum == target) { // Got one of the target
                List<Integer> asf = new ArrayList<>();
                asf.add(nums[start]); asf.add(nums[end]);
                ans.add(asf);
                start++; end--;
            }
            else if(sum < target) start++;
            else end--;
        }
        return ans;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
