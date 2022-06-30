// https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/

class Solution {
    
    public int maxVowels(String s, int K) {
        int n = s.length();
        int si = 0, ei = 0;
        int count = 0, max = 0;
    
        while(ei < n) {
            // Jab v aao khud ko check krwa lo
            char inc = s.charAt(ei);
            if(inc == 'a' || inc == 'e' || inc == 'i' || inc == 'o' || inc == 'u') count++; 
            
            if(ei - si + 1 == K) { // Agar window ki criteria me aare ho toh 
                max = Math.max(max, count); // Form ur ans
                
                char exc = s.charAt(si); // Removing starting ele from window, for the next upcoming window
                if(exc == 'a' || exc == 'e' || exc == 'i' || exc == 'o' || exc == 'u') count--;
                si++;
            }
            ei++;
        }
        return max;        
    }
    
}
