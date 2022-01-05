// https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/arrays-and-strings/k-sum-target-sum-unique-set/ojquestion// 

import java.util.*;

public class Main {

  public static int n;
  
  
  //==============================================================================================================
  public static List<List<Integer>> kSum(int[] nums, int target, int k) {
        List<List<Integer>> ansList = new ArrayList<>();
        n = nums.length;
        if(n < k) return ansList;
        
        Arrays.sort(nums);
        return kSum(0, target, k, nums);
  }
        
  public static List<List<Integer>> kSum(int si, int target, int k, int[] nums) {
        if(k == 2) return twoSum(si, n - 1, target, nums);
        
        List<List<Integer>> ansList = new ArrayList<>();
        for(int i = si; i <= n - k; i++) { // Fixing the ith element and calling (k-1)Sum from [i+1 to n-1]
            if(i != si && nums[i] == nums[i - 1]) continue; // Skipping duplicates
            
            List<List<Integer>> lists = kSum(i + 1, target - nums[i], k - 1, nums);
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
    //=====================================================================================================
    
    

  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    n = scn.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = scn.nextInt();
    }
    int target = scn.nextInt();
    int k = scn.nextInt();
    List<List<Integer>> res = kSum(arr, target, k);
    ArrayList<String> finalResult = new ArrayList<>();
    for (List<Integer> list : res) {
      Collections.sort(list);
      String ans = "";
      for (int val : list) {
        ans += val + " ";
      }
      finalResult.add(ans);
    }
    Collections.sort(finalResult);
    for (String str : finalResult) {
      System.out.println(str);
    }
  }

}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
