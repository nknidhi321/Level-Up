// https://www.codingninjas.com/codestudio/problems/longest-decreasing-subsequence_800300?leftPanelTab=0
// Just Start from end for LDS

import java.util.*;

public class Solution {
    
	public static int LDS(int[] nums) {
        List<Integer> list = new ArrayList<>(); 
        for(int i = nums.length - 1; i >= 0; i--) {                          // [Just Start from end for LDS]
            int ele = nums[i];
            int insertPos = insertPos(0, list.size() - 1, ele, list);
            if(insertPos == list.size()) list.add(ele);
            else list.set(insertPos, ele);
        }
        return list.size();
    }
    
    // List will never contain duplicate ele. Why ??
    // Because everytime a duplicate element comes as tar, it gets overridden 
    public static int insertPos(int si, int ei, int tar, List<Integer> list) {
        while(si <= ei) {
            int mid = si + (ei - si) / 2;
            if(list.get(mid) <= tar) si = mid + 1;
            else ei = mid - 1;
        }
        int insertPos = si;
        int duplicateEle = si - 1; 
        
        // If duplicate ele exists, we will override at the duplicate ele pos 
        // Else return the proper insertPos
        return si - 1 >= 0 && list.get(si - 1) == tar ? duplicateEle : insertPos;
    }
    
}
