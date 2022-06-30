// https://www.lintcode.com/problem/386/
// Map me unique ele and unki freq rakh lo, since sirf k unique char hone chahiye substr/window me 
// so, jab v map ka size k se kam ya equal mile, window ka answer nikalwa lo and 
// si ko tab tak badhao jab tak window/map me sirf k unique charcter nahi bachte

// Correct 

public class Solution {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(k == 0) return 0;
        
        int n = s.length();
        int si = 0, ei = 0;
        int maxLen = 0; 
        Map<Character, Integer> map = new HashMap<>();
        
        while(ei < n) {
            map.put(s.charAt(ei), map.getOrDefault(s.charAt(ei), 0) + 1); // increasing freq 
            if(map.size() <= k) { // Jab v map ka size k se kam ya equal mile => unique character
                maxLen = Math.max(maxLen, ei - si + 1); // Form ur ans
            }
            
            // si ko tab tak badhao jab tak map ka size k se zyada ho 
            // => Window me sirf 2 unique char hone chahiye
            while(si < ei && map.size() > k) {
                int freq = map.get(s.charAt(si));
                if(--freq == 0) map.remove(s.charAt(si)); // Jiska freq 0 ho jaaye, usko map se uda do
                else map.put(s.charAt(si), freq);
                si++;
            }
            
            ei++;
        }
        
        return maxLen;
   }

}
