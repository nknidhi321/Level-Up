// https://practice.geeksforgeeks.org/problems/minimum-number-of-deletions-to-make-a-sorted-sequence3248/1/#

class Solution {

	public int minDeletions(int arr[], int n) { 
	    return n - LIS(n, arr); 
	} 
	
	public static int LIS(int n, int[] arr) {
	    List<Integer> list = new ArrayList<>();
	    for(int ele : arr) {
	        int idx = insertPos(0, list.size() - 1, ele, list);
	        if(idx == list.size()) list.add(ele);
	        else list.set(idx, ele);
	    }
	    return list.size();
	}
	
	public static int insertPos(int si, int ei, int tar, List<Integer> list) {
	    while(si <= ei) {
	        int mid = si + (ei - si) / 2;
	        if(list.get(mid) <= tar) si = mid + 1;
	        else ei = mid - 1;
	    }
        int insertPos = si;
        int dulicatePos = si - 1;
        return dulicatePos >= 0 && list.get(dulicatePos) == tar ? dulicatePos : insertPos;
	}
	
}
