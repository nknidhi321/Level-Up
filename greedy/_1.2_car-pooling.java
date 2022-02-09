// https://leetcode.com/problems/car-pooling/

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
