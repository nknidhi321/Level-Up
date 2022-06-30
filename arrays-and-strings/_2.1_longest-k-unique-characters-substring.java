// https://practice.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/1/#
// NOTE : Here it's == K
// https://www.lintcode.com/problem/386/  //But in this question it's atmost K 

class Solution {
   
    public int longestkSubstr(String s, int k) {
        if(k == 0) return 0;
        
        int n = s.length();
        int si = 0, ei = 0;
        int maxLen = -1; 
        Map<Character, Integer> map = new HashMap<>();
        
        while(ei < n) {
            map.put(s.charAt(ei), map.getOrDefault(s.charAt(ei), 0) + 1); // increasing freq 
            if(map.size() == k) { // Jab v k ka window mile compete karwa lo
                maxLen = Math.max(maxLen, ei - si + 1); // Form ur ans [si, ei]
            }
            
            // si ko tab tak badhao jab tak map ka size k na ho jaaye 
            // => Window me sirf k unique char hone chahiye
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
