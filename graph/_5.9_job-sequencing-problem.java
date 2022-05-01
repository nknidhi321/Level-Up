// https://practice.geeksforgeeks.org/problems/job-sequencing-problem-1587115620/1#
// Can be solved using DSU, only to optimize the deadline loop

// Greedy Approach

// Sort on profit, and pick jobs at it's deadline day.
// The idea is to procastinate and do it in the last day, deadline day

class Solution {

    public int[] JobScheduling(Job arr[], int n) {
        
        // Sorting on profit
        Arrays.sort(arr, (a, b) -> b.profit - a.profit);

        // Take out maxDeadline day         
        int maxDeadline = -1;
        for(Job job : arr) {
            maxDeadline = Math.max(maxDeadline, job.deadline);
        }
        
        // There are no jobId == 0, so you can leave default as 0
        int[] deadlineArray = new int[maxDeadline + 1]; // 0 idx is waste
        
        int count = 0; // JobCount       
        int psf = 0;  // ProfitSoFar
        for(Job job : arr) {
            int id = job.id;
            int profit = job.profit;
            int deadline = job.deadline;
            
            for(int i = deadline; i >= 1; i--) { // 0th idx is waste
                if(deadlineArray[i] == 0) { // If it is not occupied already
                    deadlineArray[i] = id;
                    count++;
                    psf += profit;
                    break;
                }
            }
        }
        return new int[] {count, psf};
    }
    
}
