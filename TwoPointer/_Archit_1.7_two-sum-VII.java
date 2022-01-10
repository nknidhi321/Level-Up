// https://www.lintcode.com/problem/1879/description

// HashMap Solution
// Try out using 2 ptr

public class Solution {
   
    public List<List<Integer>> twoSumVII(int[] nums, int target) {
        // {Integer, idx}
        HashMap<Integer, Integer> map = new HashMap<>();
        List<List<Integer>> ans = new ArrayList<>();
         
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            int comp = target - nums[i]; 
            if(map.containsKey(comp)) {
                List<Integer> smallAns = new ArrayList<>();
                smallAns.add(map.get(comp));
                smallAns.add(i);
                ans.add(smallAns);
            }
            else map.put(nums[i], i);
        }
        return ans;
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
