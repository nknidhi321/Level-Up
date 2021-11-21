//https://leetcode.com/problems/subsets/

//Pick, Not Pick

class Solution {
    
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        subsetsUtil(0, nums, new ArrayList<>(), ans);
        return ans;
    }
    
    public static void subsetsUtil(int idx, int[] nums, List<Integer> asf, List<List<Integer>> ans){
        if(idx == nums.length){
            ans.add(new ArrayList<>(asf));
            return;
        }
        
        asf.add(nums[idx]);
        subsetsUtil(idx + 1, nums, asf, ans);
        asf.remove(asf.size() - 1);
        subsetsUtil(idx + 1, nums, asf, ans);
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

//For Loop

class Solution {
    
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        combinationSingleCoins(0, nums, new ArrayList<>(), ans);
        return ans;
    }
    
    public static void combinationSingleCoins(int idx, int[] nums, List<Integer> asf, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(asf));
        for(int i = idx; i < nums.length; i++) {
            asf.add(nums[i]);
			combinationSingleCoins(i + 1, nums, asf, ans);
            asf.remove(asf.size() - 1);
		}
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
