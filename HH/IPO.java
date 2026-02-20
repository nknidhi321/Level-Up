// https://leetcode.com/problems/ipo/?envType=study-plan-v2&envId=top-interview-150
/* 
    Ex : 1    
    k = 1
    w = 0
    CP = [0,1][1,2][2,3]
*/

/* 
    Ex : 2 - When no profit can be chosen scenario test case
    k = 2
    w = 0
    profits = [1,2,3]
    capital = [1,2,3]
*/


class Solution {

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;

        // Pair capital and profit
        int[][] projects = new int[n][2];
        for (int i = 0; i < n; i++) {
            projects[i][0] = capital[i];
            projects[i][1] = profits[i];
        }

        // Sort projects by required capital in ascending
        Arrays.sort(projects, (a, b) -> Integer.compare(a[0], b[0]));

        // Max heap to store profits, sort by desc for profit
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        int index = 0;

        // We can choose at most k projects
        for (int count = 0; count < k; count++) {

            // Add all affordable projects to heap
            while (index < n && projects[index][0] <= w) {
                maxHeap.offer(projects[index][1]);
                index++;
            }

            // If no project can be selected, stop early
            if (maxHeap.isEmpty()) break;

            // Pick the most profitable project
            w += maxHeap.poll();
        }

        return w;
    }

}

