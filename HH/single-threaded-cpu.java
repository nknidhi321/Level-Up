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
        int currTime = process[0].startTime;
        int idx = 0, ansIdx = 0;

        // O(n) 
        while(ansIdx < n) {
            
            // If coming processes startTime is less than currTime
            // Add all of them in the pq
            while(idx < n) { 
                ProcessN p = process[idx];
                if(p.startTime <= currTime) {
                    pq.add(p);
                    idx++;
                }
                else { // No need to check further because it's already sorted 
                    break;
                }
            }
            
            // After processing each process add that processes duration in the currentTime
            // Now since the currentTime has changed so check all the remaining processes again to push in the pq
            if(!pq.isEmpty()) {
                ProcessN p = pq.poll();
                ans[ansIdx++] = p.id;
                currTime += p.duration;
            }
            else {
                currTime = process[idx].startTime; // pq is empty and prev process ended at t1 and next process starts at t5
            }
        }
        return ans;
    }
    
}
