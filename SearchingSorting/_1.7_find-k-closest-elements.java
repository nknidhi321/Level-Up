// https://leetcode.com/problems/find-k-closest-elements/

// Using lastInsertPos, BS
// Rajneesh

```
class Solution {
    
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        
        int lastInsertPos = lastInsertPos(0, arr.length - 1, x, arr);        
		// Now you know closest ele from x will be at lastInsertPos or lastInsertPos-1
				
        List<Integer> ans = new ArrayList<>();
        int left = lastInsertPos - 1;
        int right = lastInsertPos;

        // Apna range left and right k taraf expand karo, and pick acc.
        while(left >= 0 && right < arr.length && k-- > 0) {
            if(x - arr[left] <= arr[right] - x) ans.add(arr[left--]);        
            else ans.add(arr[right++]);
        }
        
        // Right k wazah end hua tha upar ka loop && k baaki hai avi
        while(left >= 0 && k-- > 0) ans.add(arr[left--]);
        
        // Left k wazah end hua tha upar ka loop && k baaki hai avi
        while(right < arr.length && k-- > 0) ans.add(arr[right++]);
        
        Collections.sort(ans); // Answer is expected in sorted order
        return ans;
    }
    
    public int lastInsertPos(int si, int ei, int x, int[] arr) {
        while(si <= ei) {
            int mid = si + (ei - si) / 2;
            // Last insert pos chahiye, so mil jaaye tb v right me jaao 
            if(arr[mid] <= x) si = mid + 1; 
            else ei = mid - 1;
        }
        return si; // si hamesha lastInsertPos pe point karega
    }
     
}
```
----------------------------------------------------------------------
// YT pepcoding Sol. is wrong
// This will not give you the closestIdx
// Ex : [1,5,10]  k = 1, x = 4   
// Here closestIdx should be 1 i.e. 5, but we will get 0 i.e. 1
public int binarySearchClosestIdx(int si, int ei, int x, int[] arr) {
    int mid = -1;
    while(si <= ei) {
        mid = si + (ei - si) / 2;
        if(arr[mid] == x) return mid;
        else if(arr[mid] < x) si = mid + 1;
        else ei = mid - 1;
    }
    return mid;
}
    
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Using PQ
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
