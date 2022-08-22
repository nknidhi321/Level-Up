// https://leetcode.com/problems/queue-reconstruction-by-height/

// Intuition :-
// Sort on desc height and insert on indexes like "insertion sort"

// Approach :-
// Sbse pehle sbse bade bando ko jaha baithna chahte h baitha do
// Now, kisi v bnde ko us se aage kitne chote bnde h is se fark nahi prta
// Islye desc order me sort kiya height pe.
// So, now from sorted array, ek ek kar k bnde nikalo,
// jaha place hona chahte hai kar do.
// Notice that baad me chote chote bnde he niklenge, agar already placed koi bnda h
// us particular idx pe, and mera next chota bnda ko v usi idx pe place hona chahta hai toh 
// already placed bnde ki shifting kar denge to the right by 1 idx.
// and chote bnde ko uski jagah baitha denge.
// Kuki bade bnde ko koi fark nahi parega uske aage kitne v chote log aa jaaye, 
// bs bade bnde se dikkat thi.

class Solution {
    
    public int[][] reconstructQueue(int[][] people) {
        int n = people.length;
        
        // sort on 0th(height) idx in desc order 
        // then on 1st(count) idx in asc order
        Arrays.sort(people, (a, b) -> {
            if(b[0] - a[0] == 0) return a[1] - b[1];
            else if(b[0] - a[0] < 1) return -1;
            else return 1;
        });    
        
        List<int[]> list = new ArrayList<>();
        
        // Using ArrayList kuki wo default shifting kr dega
        // Agar add() me idx mentined hai toh.
        for(int[] arr : people) {
            int idx = arr[1];
            list.add(idx, arr);
        }
        
        // Copying to prepare our ans
        int[][] ans = new int[n][n];
        for(int i = 0; i < n; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
    
}
