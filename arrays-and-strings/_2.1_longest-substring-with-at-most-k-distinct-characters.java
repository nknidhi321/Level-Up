// https://www.lintcode.com/problem/386/
// Map me unique ele and unki freq rakh lo, since sirf k unique char hone chahiye substr/window me 
// so, jab v map ka size k se zyada ho jaaye us se just pichle window ka answer nikalwa lo and 
// si ko tab tak badhao jab tak window/map me sirf k unique charcter nahi bachte

public class Solution {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(k == 0) return 0;
        
        int n = s.length();
        int si = 0, ei = 0;
        int maxLen = 0; 
        Map<Character, Integer> map = new HashMap<>();
        
        while(ei <= n) { // handling the last character here only
            if(ei != n) map.put(s.charAt(ei), map.getOrDefault(s.charAt(ei), 0) + 1); // increasing freq 
            if(ei == n || map.size() > k) {
                // Jab v 2 se zyada unique character mile, then mere se just phele tak k window ka len compete karwa lo
                maxLen = Math.max(maxLen, ei - si); // Khud pe khare hoke apne prev window ka answer form karo [si, ei)
            }
            
            // si ko tab tak badhao jab tak map ka size 2 na ho jaaye 
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
