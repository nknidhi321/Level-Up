// https://www.lintcode.com/problem/587

public class Solution {

    public int twoSum6(int[] nums, int target) {

        int n = nums.length;
        Arrays.sort(nums);

	// Two Pointer
        int count = 0;
        int start = 0, end = nums.length - 1;
        while(start < end) {
            int sum = nums[start] + nums[end];
	    
	    // Got one of the target
            if(sum == target) {
                count++;
               
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
        return count;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Same question only difference is return unique pairs in form of list

// https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/arrays-and-strings/2-sum-target-sum-unique-pairs/ojquestion

import java.util.*;

public class Main {

  //==============================================================================================
  public static List<List<Integer>> twoSum(int[] nums, int target) {
    List<List<Integer>> ans = new ArrayList<>();
    int n = nums.length;
    Arrays.sort(nums);

    // Two Pointer
    int count = 0;
    int start = 0, end = nums.length - 1;
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
  //=========================================================================================================

  
  // ~~~~~~~~~~Input Management~~~~~~~~~~
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = scn.nextInt();
    }
    int target = scn.nextInt();
    List<List<Integer>> res = twoSum(arr, target);
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
