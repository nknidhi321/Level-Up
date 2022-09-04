// https://leetcode.com/problems/maximum-number-of-robots-within-budget/

class Solution {
    
    public int maximumRobots(int[] charge, int[] cost, long budget) {
        int n = cost.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int i = 0, j = 0, size = 0;
        long max = -(long)1e9, subarraySum = 0;
        
        while(j < n) {
            subarraySum += cost[j];
            pq.add(charge[j]);
            int subarrayMax = pq.peek(); // max. bnda of subarray
            long subarrayTotalCost = subarrayMax + (j - i + 1) * subarraySum; // acc. to given formula
            
            while(i <= j && subarrayTotalCost > budget) {
                subarraySum -= cost[i]; // Jo win se bhr ho rahe usko, usko apne sum se nikal do
                pq.remove(charge[i]); // Jo win se bhr ho rahe usko pq se nikal do
                if(pq.size() > 1) {
                    subarrayMax = pq.peek(); // max. bnda of subarray
                    subarrayTotalCost = subarrayMax + (j - i + 1) * subarraySum; // acc. to given formula
                }
                i++; // shrinking win
            }
            size = Math.max(size, j - i + 1); // compete
            j++; // expand win
        }
        return size;
    }
    
}
