// https://www.lintcode.com/problem/928/ [Coded here]
// https://leetcode.com/problems/fruit-into-baskets/

// Map me unique ele and unki freq rakh lo, since sirf 2 unique char hone chahiye substr/window me 
// so, jab v map ka size <= 2 ho apne window ka answer nikalwa lo and 
// si ko tab tak badhao jab tak window/map me sirf 2 unique charcter nahi bachte

public class Solution {

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int n = s.length();
        int si = 0, ei = 0;
        int maxLen = 0; 
        Map<Character, Integer> map = new HashMap<>();
        
        while(ei < n) {
            map.put(s.charAt(ei), map.getOrDefault(s.charAt(ei), 0) + 1); // increasing freq 
            if(map.size() <= 2) { // at most 2 distinct characters
                maxLen = Math.max(maxLen, ei - si + 1); // Apne [si, ei] window ka answer form karwa lo
            }
            
            // si ko tab tak badhao jab tak map ka size 2 na ho jaaye 
            // => Window me sirf 2 unique char hone chahiye
            while(si < ei && map.size() > 2) {
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
