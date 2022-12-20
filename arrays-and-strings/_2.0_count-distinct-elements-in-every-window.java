// https://practice.geeksforgeeks.org/problems/count-distinct-elements-in-every-window/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article

class Solution {
    
    ArrayList<Integer> countDistinct(int A[], int n, int k) {
        int si = 0, ei = 0;
        Map<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> ans = new ArrayList<>();
        while(si <= ei && ei < n) {
            map.put(A[ei], map.getOrDefault(A[ei], 0) + 1);
            if(ei - si + 1 > k) {
                int freq = map.get(A[si]);
                if(freq - 1 == 0) map.remove(A[si]);
                else map.put(A[si], freq - 1);
                si++;
            }
            if(ei - si + 1 == k) {
                ans.add(map.size());
            }
            ei++;
        }
        return ans;
    }
    
}
