// https://practice.geeksforgeeks.org/problems/minimum-cost-of-ropes-1587115620/1

class Solution {

    long minCost(long arr[], int n)  {
        
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for(int i = 0; i < n; i++) {
            pq.add(arr[i]);
        }
        
        long ans = 0;    
        while(pq.size() >= 2) {
            long min1 = pq.poll();
            long min2 = pq.poll();
            long sum = min1 + min2;
            ans += sum;
            pq.add(sum);
        }
        
        // Ek bnda last me bachega pq me, that is the final rope.
        // Ignore that, that is of no use.
        return ans; 
    }
    
}
