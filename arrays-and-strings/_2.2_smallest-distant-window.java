// https://practice.geeksforgeeks.org/problems/smallest-distant-window3132/1/#
// Same as https://leetcode.com/problems/minimum-window-substring/
// Here in extra you have to form your t string from the given s, where t will be all the unique chars of s

class Solution {
    
    public int findSubString(String s) {
        int ns = s.length(); 
        StringBuilder t = new StringBuilder();
        
        // To find all unique chars in s and their count
        int[] freqAll = new int[128];
        for(int i = 0; i < ns; i++) {
            freqAll[s.charAt(i)]++;
        }
        for(int i = 0; i < 128; i++) {
            if(freqAll[i] >= 1) { // unique chars
                t.append((char)i); 
            }
        }
        int nt = t.length(); // count

        if(ns < nt) return 0;
        
        int si = 0, ei = 0; 
        int count = nt; // Intial count is len of t
        
        // count jab v 0 hoga that means mujhe t k saare chars mil gayi hai s me
        // suppose count 5 hai, so 5 se -- karte krte 0 pe aaenge
        // Jis din 0 pe aa gaye mtlb sab chars mil gaya 
        
        // Acc. to the Algo if a char:
        // freq > 0, there is requirement for that char in your window
        // freq == 0, you have exactly count number of chars in your substr window
        // freq < 0, you have char in excess, in that window
        
        // When you acquire dec your freq
        // When you release inc your freq
        
        // Freq of t
        int[] freq = new int[128];
        for(int i = 0; i < nt; i++) {
            freq[t.charAt(i)]++;
        }
        
        int minLen = Integer.MAX_VALUE;
        
        while(ei < ns) {
            
            // if freq > 0 => requirement hai is bnde ki so acquire
            // So you take that char in your window and dec count and
            if(freq[s.charAt(ei)] > 0) count--; // Kuki count jis din 0 hoga usi din kahenge ki sara char mil gaya 
            freq[s.charAt(ei)]--; // freq to dec hoga he kuki acqiure kiya hai
            
            // Form your answer and release
            while(count == 0) {
                if(ei - si + 1 < minLen) {
                    minLen = ei - si + 1; // form ans
                }
                
                // release => Increase freq
                // count agar 0 hai then ye wahi bnda jo hona chahiye tha
                // So, you will now form the next window from the very next si, jab v count 0 milega
                // Kuki yaha pe count incrtease ho jaaega and your loop will break
                if(freq[s.charAt(si)] == 0) count++; 
                freq[s.charAt(si)]++;
                si++;
            }
            ei++;
        }
        
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
    
}
