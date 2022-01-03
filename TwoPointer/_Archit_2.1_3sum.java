// https://leetcode.com/problems/3sum/

// Two Pointer 
// Mine
// TC : O(N^2)

class Solution {
    
    public static int n;
    
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ansList = new ArrayList<>();
        n = nums.length;
        if(n < 3) return ansList;
        
        Arrays.sort(nums);
        for(int i = 0; i <= n - 3; i++) { // Fixing the ith element and calling 2 pointer from i+1 to n-1
            if(i != 0 && nums[i] == nums[i - 1]) continue; // Skipping duplicates
            
            int target = 0 - nums[i];
            List<List<Integer>> lists = twoSum(i + 1, n - 1, target, nums);
            for(List<Integer> list : lists){
                list.add(nums[i]);
                ansList.add(list);
            }
        }
        return ansList;
    }
    
    public static List<List<Integer>> twoSum(int start, int end, int target, int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        
        // Two Pointer
        while(start < end) {
            int sum = nums[start] + nums[end];
	    
	        // Got one of the target
            if(sum == target) {
                List<Integer> asf = new ArrayList<>();
                asf.add(nums[start]); asf.add(nums[end]);
                ans.add(asf);
                
                // Since unique pairs is asked so surpass all the elements from end which are same   
                while(end - 1 >= 0 && (nums[end - 1] == nums[end])) end--;
                end--;
              
                // Since unique pairs is asked so surpass all the elements from start which are same  
                while(start + 1 < n && (nums[start + 1] == nums[start])) start++;
                start++;
            }
            else if(sum < target) start++;
            else end--;
        }
        return ans;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Brute Force
// TC : O(N^3) 
// TLE

class Solution {
    
  public List<List<Integer>> threeSum(int[] nums) {
        if(nums.length < 3) return new ArrayList<>();
        
        HashSet<List<Integer>> set = new HashSet<>();
        Arrays.sort(nums); // Sorting just to have the sorted ilist, so that unique triplets can be generated when we add that ilist in set
        
        for(int i = 0; i < nums.length - 2; i++){
            for(int j = i + 1; j < nums.length - 1; j++){
                for(int k = j + 1; k < nums.length; k++){
                    if(nums[i] + nums[j] + nums[k] == 0){
                        List<Integer> ilist = new ArrayList<>();
                        ilist.add(nums[i]);
                        ilist.add(nums[j]);
                        ilist.add(nums[k]);
                        set.add(ilist);
                    }
                }
            }
        }
        return new ArrayList<>(set);
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
