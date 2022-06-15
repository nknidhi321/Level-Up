// https://www.lintcode.com/problem/903/

/*
    Query based question :-
        Is range se, is range tak, itne se inc/dec kar do

    prefixSum nikalne k liye :-
        1) start idx pe jaake +val kar do
        2) end+1 idx pe jaake -val kar do. Why ?
        Taaki jab prefixSum calculate krenge toh jaha tak inc/dec karna tha
        uske just next wala idx balance out ho jaaye, humne jo -val kiya hai uske kaaran => "Just next" wala bnda prefix sum me bal out ho gaya
        mtlb ab uske aage k bnde v bal out ho jaaenge prefixSum me kuki ye "prefixSum"(Current index pe jo v kahani karoge aage k saare bndo pe inpact hoga) hai.

    Ab prefixSum calculate kar lo
    
    Now, you have your query based answer in prefixSum.
*/

// Best Approach,  TC : O(2N)
// In worst case number of querries can be N
// So N times upar wala loop chalega and N times niche wala => O(2N)

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
// Jaisa kaha hai kar do

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
