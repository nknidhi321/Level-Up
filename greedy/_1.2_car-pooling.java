// https://leetcode.com/problems/car-pooling/

// Approach 1
/*
 Query based question :-
        Is range se, is range tak, itne se inc/dec kar do

    prefixSum nikalne k liye :-
        1) start idx pe jaake +val kar do
        2) end+1 idx pe jaake -val kar do. Why ?
        Taaki jab prefixSum calculate krenge toh jaha tak inc/dec karna tha
        uske next wala idx balance out ho jaaye, humne jo -val kiya hai uske kaaran

    Ab prefixSum calculate kar lo.
    
    Now, you have your query based answer in prefixSum.

    For this question :- Ab bs itna dekhna hai ki kisi idx pe val, capacity se exceed toh nahi kar rahi na
*/

class Solution {
    
    public boolean carPooling(int[][] trips, int capacity) {
        int maxDestinationIdx = Integer.MIN_VALUE;
        for(int i = 0; i < trips.length; i++) {
            maxDestinationIdx = Math.max(maxDestinationIdx, trips[i][2]);
        }
        
        int[] prefixSum = new int[maxDestinationIdx + 1];
        int n = prefixSum.length;
        
        for(int[] trip : trips) {
            int val = trip[0];
            int start = trip[1];
            int end = trip[2];
            
            prefixSum[start] += val;
            if(end < n) prefixSum[end] += -val;    
        }
        
        if(capacity < prefixSum[0]) return false;
        for(int i = 1; i < n; i++) {
            prefixSum[i] += prefixSum[i - 1];
            if(capacity < prefixSum[i]) return false;
        }
        return true;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
// Question like trains on platform 
// Approach 2

class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int n = trips.length;
        
        int[][] starts = new int[n][2]; // boadingPoint, NumberOfPeople
        int[][] ends = new int[n][2]; // vaccatingPoint, NumberOfPeople
        for(int i = 0; i < n; i++) {
            starts[i][0] = trips[i][1]; // starts
            ends[i][0] = trips[i][2]; // ends
            starts[i][1] = ends[i][1] = trips[i][0]; // NumberOfPeople
        }
        
        Arrays.sort(starts, (a, b) -> a[0] - b[0] > 0 ? 1 : -1); // sort start
        Arrays.sort(ends, (a, b) -> a[0] - b[0] > 0 ? 1 : -1); // sort end
        
        // Printing start end array
        // for(int i = 0; i < n; i++) System.out.println("start : " + starts[i][0] + " " + starts[i][1] + " ");
        // for(int i = 0; i < n; i++) System.out.println("end : " + ends[i][0] + " " + ends[i][1] + " ");
        
        // Keep one ptr at start and the other at end,
        // Jisko cadhana hai cadhao, utarna hai utaro,
        // Just keep on checking with the total capacity whenever you board.
        int i = 0, j = 0;
        int totalPassengers = 0;
        for(int k = 0; k < n + n; k++) { // n + n kuki all starts + all ends
            if(i == n) break; // all passengers boarded, so no need to check further.
            if(starts[i][0] < ends[j][0]) { // boarding
                totalPassengers += starts[i++][1];
                if(totalPassengers > capacity) return false; 
            }
            else { // vaccating
                totalPassengers -= ends[j++][1];
            }
        }
        return true;
    }
}
