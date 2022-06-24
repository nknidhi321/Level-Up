// https://www.codingninjas.com/codestudio/problems/first-negative-integer-in-every-window-of-size-k_1164406?leftPanelTab=0
// Don't use list, since removing from 0th idx would make shifting
// Use queue instead

import java.util.*;

public class Solution  {
    public static ArrayList<Integer> firstNegativeInteger(ArrayList<Integer> arr, int K, int n) {
        int si = 0, ei = 0, idx = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>(); // All Negative Element idx list
        
        while(ei < n) {
            if(arr.get(ei) < 0) queue.add(ei); // Adding all negative ele idx in list  
            
            if(ei - si + 1 == K) { // Agar window ki criteria me aare ho toh 
                if(!queue.isEmpty() && queue.peek() >= si) { // If list contains idx which is within my curr win   
                    ans.add(arr.get(queue.peek())); // Form curr window ans
                    
                    if(queue.peek() == si) queue.poll(); // for the next upcoming widow
                }
                else {
                    ans.add(0); // No -ve ele found in curr window
                }
                si++;
            }
            
            ei++;
        }
        return ans;        
    }
}
