// https://leetcode.com/problems/furthest-building-you-can-reach/

```
class Solution {
    
    public int furthestBuilding(int[] heights, int bricks, int ladders) {

//    Accio Jobs Solution :-        
//    PriorityQueue<Integer> pq = new PriorityQueue<>();
// 		for(int i = 1;i<height.length;i++){
// 			int diff = height[i] - height[i-1];
// 			if(diff<0) continue;
// 			pq.add(diff);
// 			if(pq.size()>ladders){
// 				bricks-= pq.remove();
// 			}
// 			if(bricks<0) return i-1;
// 		}
// 		return height.length-1;
        
        int n = heights.length;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < n - 1; i++) {
            int diff = heights[i + 1] - heights[i];
            if(diff <= 0) { // downhill or equal level => 0 required bricks
                continue;
            }
            if(diff > 0 && diff <= bricks) { // uphill and sufficient bricks hai
                bricks -= diff;
                maxHeap.add(diff);
            }
            else if(ladders > 0) { // uphill but sufficient bricks nai h
                if(!maxHeap.isEmpty() && maxHeap.peek() >= diff) {                    
                    bricks += maxHeap.remove(); // replace maxBridge with ladder
                    ladders--; 

                    maxHeap.add(diff); // build bridge for the current to next building
                    bricks -= diff;
                }
                else { // Jo maxBridge hai ab tak ka wo currentBridge k bricks se kam hai ya fir
                       // Remaining bricks are not sufficient
                    ladders--;
                }
            }
            else {
                return i; // Safar khatam
            }
        }
        return n - 1; // Why n - 1 ?? Kuki jumps ka count chahiye
    }
    
}
```
