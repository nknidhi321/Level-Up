// https://practice.geeksforgeeks.org/problems/count-occurences-of-anagrams5839/1#

class Solution {

    public int search(String t, String s) {
        int ns = s.length();
        int nt = t.length();
        
        int[] freq = new int[256];
        for(int i = 0; i < nt; i++) {
            freq[t.charAt(i)]++;
        }
        
        int count = nt;
        int si = 0, ei = 0;
        int ans = 0;
        while(ei < ns) {
    
            // acquire
            if(freq[s.charAt(ei)] > 0) count--;
            freq[s.charAt(ei)]--;
            
            // release
            while(count == 0) {
                if(ei - si + 1 == nt) ans++;
                if(freq[s.charAt(si)] == 0) count++;
                freq[s.charAt(si)]++;
                si++;
            }
            ei++;
        }
        return ans;
    }
    
}
