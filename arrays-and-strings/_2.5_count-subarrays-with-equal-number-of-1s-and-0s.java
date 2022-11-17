// https://practice.geeksforgeeks.org/problems/count-subarrays-with-equal-number-of-1s-and-0s-1587115620/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article

// Just convert all 0 to -1, now this ques. is simple all subarrays with sum equals 0

class Solution {

  static int countSubarrWithEqualZeroAndOne(int arr[], int n) {
        int prefixSum = 0, ans = 0;
        
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for(int i = 0; i < n; i++) {
            if(arr[i] == 0) prefixSum += -1;
            else prefixSum += arr[i];
            if(map.containsKey(prefixSum)) {
                ans += map.get(prefixSum);
            }
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        return ans;
    }
  
}
