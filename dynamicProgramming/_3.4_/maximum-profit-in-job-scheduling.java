// https://leetcode.com/problems/maximum-profit-in-job-scheduling/

// Same like russian doll par yaha = to v allowed hai and non overlapping hona chahiye
// O(N^2)

```
class Solution {
    
    public static class Job implements Comparable<Job> {
        int startTime, endTime, profit;
        
        public Job(int startTime, int endTime, int profit) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.profit = profit;
        }        
        
        // Equal to v allowed hai so asc. order me dono ko rakh lo
        public int compareTo(Job o) {
            if(this.startTime - o.startTime == 0) return this.endTime - o.endTime;
            else return this.startTime - o.startTime;
        }
    }
    
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = profit.length;
        Job[] jobs = new Job[n];
        
        for(int i = 0; i < n; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);                        
        }
        
        Arrays.sort(jobs);

        // for(int i = 0; i < n; i++) {
        //     System.out.println(jobs[i].startTime + " " +  jobs[i].endTime +  " " + jobs[i].profit);
        // }
        
        int overAllMaxProfit = 0;
        int[] dp = new int[n]; // Mere pe kahatam hone wala LIS ka max. profit  
        for(int i = 0; i < n; i++) {
            Job currJob = jobs[i];
            int currStartTime = currJob.startTime;
            int currEndTime = currJob.endTime;
            int currProfit = currJob.profit;
            
            int maxPrevProfit = 0;
            for(int j = i - 1; j >= 0; j--) { // Apne se piche 
                int prevEndTime = jobs[j].endTime;
                if(prevEndTime <= currStartTime) { // apne startTime se chote ya = prevEndTime waale pe jaao
                    maxPrevProfit = Math.max(maxPrevProfit, dp[j]); // Max of all the profits find karo
                }
            }
            dp[i] = maxPrevProfit + currProfit; // Aur unke piche chipak jaao
            
            // dp pe last me iterate k bajay, yahi overAllMaxProfit nikalte chalo
            overAllMaxProfit = Math.max(overAllMaxProfit, dp[i]);
        }
        return overAllMaxProfit;
    }
    
}
```
