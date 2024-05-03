// https://www.geeksforgeeks.org/problems/morning-assembly3038/1

class Solution {
    static int sortingCost(int N, int arr[]) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < N; i++) { // LongestConsecutiveSubsequence ending with arr[i]
            max = Math.max(max, LongestConsecutiveSubsequence(arr[i], map));
        }
        return N - max;
    }
    public static int LongestConsecutiveSubsequence(int val, Map<Integer, Integer> map) {
        if(map.containsKey(val - 1)) map.put(val, map.get(val - 1) + 1);
        else map.put(val, 1);
        
        return map.get(val);
    }
}
