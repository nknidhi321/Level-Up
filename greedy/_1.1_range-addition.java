// https://www.lintcode.com/problem/903/

/*
    Query based question :-
        Is range se, is range tak, itne se inc/dec kar do

    prefixSum nikalne k liye :-
        1) start idx pe jaake +val kar do
        2) end+1 idx pe jaake -val kar do. Why ?
        Taaki jab prefixSum calculate krenge toh jaha tak inc/dec karna tha
        uske next wala idx balance out ho jaaye, humne jo -val kiya hai uske kaaran

    Ab prefixSum calculate kar lo
    
    Now, you have your query based answer in prefixSum.
*/

// Best Approach,  TC : O(2N)

public class Solution {
    
    public int[] getModifiedArray(int n, int[][] updates) {
        int[] prefixSum = new int[n];
        
        for(int[] update : updates) {
            int start = update[0];
            int end = update[1];
            int val = update[2];

            prefixSum[start] += val;
            if(end+1 < n) prefixSum[end + 1] += -val;    
        }
        
        for(int i = 1; i < n; i++) {
            prefixSum[i] += prefixSum[i - 1];
        }
        return prefixSum;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Brute Force

public class Solution {
    
    public int[] getModifiedArray(int n, int[][] updates) {
        int[] arr = new int[n];

        for(int[] update : updates) {
            int start = update[0];
            int end = update[1];
            int inc = update[2];
            for(int i = start; i <= end; i++) {
                arr[i] += inc;
            }
        }
        return arr;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
