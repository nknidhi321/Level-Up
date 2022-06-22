// https://practice.geeksforgeeks.org/problems/first-negative-integer-in-every-window-of-size-k3345/1/#

class Compute {
    
    public long[] printFirstNegativeInteger(long arr[], int n, int K) {
        int si = 0, ei = 0, idx = 0;
        long[] ans = new long[n - K + 1];
        List<Integer> list = new ArrayList<>(); // All Negative Element idx list
        
        while(ei < n) {
            if(arr[ei] < 0) list.add(ei); // Adding all negative ele idx in list  
            
            if(ei - si + 1 == K) { // Agar window ki criteria me aare ho toh 
                if(!list.isEmpty() && list.get(0) >= si) { // If list contains idx which is within my curr win   
                    ans[idx++] = arr[list.get(0)]; // Form curr window ans
                    
                    if(list.get(0) == si) list.remove(0); // for the next upcoming widow
                }
                else {
                    ans[idx++] = 0; // No -ve ele found in curr window
                }
                si++;
            }
            
            ei++;
        }
        return ans;        
    }
    
}
