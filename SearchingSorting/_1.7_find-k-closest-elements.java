// https://leetcode.com/problems/find-k-closest-elements/
/*
    Saare elements ka diff nikal lo given x se, and is diff k basis pe maxHeap ka Comparable likh do.

    Note the below condition :
    -------------------------
    An integer a is closer to x than an integer b if
    |a - x| < |b - x|, or => Agar a ka diff chota hai, toh a closer hai
    |a - x| == |b - x| and a < b  =>  Agar a b dono ka diff same hai, then smaller element will be considered as closer element =>
    Smaller ele should stay in the maxHeap and larger ele should be popped out.
    So maxHeap se bade element ko nikalna hoga.
    
    TC : N(logK) for PQ addition removal + K(logK) for sorting
*/

```
class Solution {
    
    public static class Pair implements Comparable<Pair> {
        int ele;
        int diff;
        
        public Pair(){};
        
        public Pair(int ele, int diff) {
            this.ele = ele;
            this.diff = diff;
        }
        
        @Override
        public int compareTo(Pair o) {
            if(o.diff - this.diff == 0) return o.ele - this.ele;
            else return o.diff - this.diff;
        }
    }
    
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>();
        
        for(int ele : arr) {
            maxHeap.add(new Pair(ele, Math.abs(x - ele)));
            if(maxHeap.size() > k) maxHeap.remove();
        }
        
        List<Integer> ans = new ArrayList<>();
        while(!maxHeap.isEmpty()) {
            ans.add(maxHeap.remove().ele);
        }
        
        Collections.sort(ans);
        return ans;
    }
    
}
```
----------------------------------------------------------------------------
