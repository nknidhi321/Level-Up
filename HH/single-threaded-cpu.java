// https://leetcode.com/problems/single-threaded-cpu/
// TC : nlog(n)

class Solution {
    
    static class ProcessN {
        int id;
        int startTime;
        int duration;
        
        ProcessN(int id, int startTime, int duration) {
            this.id = id;
            this.startTime = startTime;
            this.duration = duration;
        }
        
        public String toString(){
            return id + " : " + startTime + " : " + duration;
        }
    }
    
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        ProcessN[] process = new ProcessN[n];
        for(int i = 0; i < n; i++) {
            process[i] = new ProcessN(i, tasks[i][0], tasks[i][1]);
        }
        
        // Sort so that you can pick up the process with the least starting time
        Arrays.sort(process, (a, b) -> a.startTime - b.startTime);

        // If multiple tasks have the same shortest processing time
        // it will choose the task with the smallest index
        PriorityQueue<ProcessN> pq = new PriorityQueue<>((a, b) -> {
            if(a.duration == b.duration) return a.id - b.id;
            else return a.duration - b.duration;
        });
        
        int[] ans = new int[n];
        int prevProcessEndTime = process[0].startTime; // process will start from first processes start time 
        int idx = 0, ansIdx = 0;
        
        while(ansIdx < n) {
            // If coming processes startTime is less than prevProcessEndTime
            // Add all of them in the pq
            while(idx < n) { 
                ProcessN p = process[idx];
                if(p.startTime <= prevProcessEndTime) {
                    pq.add(p);
                    idx++;
                }
                else { 
                    break;
                }
            }
            
            // After processing each process add that processes duration in the prevProcessEndTime
            // Now since the prevProcessEndTime has changed so check all the remaining processes again to push in the pq
            if(!pq.isEmpty()) {
                ProcessN p = pq.poll();
                ans[ansIdx++] = p.id;
                prevProcessEndTime += p.duration;
            }
            else { // pq is empty and prev process ended at t1 and next process starts at t5
                prevProcessEndTime = process[idx].startTime; 
            }
        }
        return ans;
    }
    
}
