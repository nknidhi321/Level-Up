// https://leetcode.com/problems/longest-substring-without-repeating-characters/

// Sliding Window

```
class Solution {
    
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int si = 0, ei = 0;
        int maxLen = 0; 
        boolean haveDuplicates = false;
        int[] freq = new int[128];
        
        while(ei < n) {
            freq[s.charAt(ei)]++; // increasing freq 
            
            if(freq[s.charAt(ei)] > 1) { // xth ele ki freq agar 1 se zyada aa gayi
                haveDuplicates = true; // => xth ele k wazah se duplicates aa gayi window me
                // Jab v duplicate mile, then mere se just phele tak k window ka len compete karwa lo
                maxLen = Math.max(maxLen, ei - si); // Khud pe khare hoke apne prev wibdow ka answer form karo [si, ei)
            }
            
            // si ko tab tak badhao jab tak duplicateElement window se chala na jaaye
            while(si < ei && haveDuplicates) {
                freq[s.charAt(si)]--; // Agar xth ele ka count decrease
                if(freq[s.charAt(si)] == 1) { // krne k baad v count 1 hai 
                    haveDuplicates = false; // => usi ele k wazah se window me duplicate ele aayi thi
                }
                si++;
            }
            
            ei++;
        }
        
        maxLen = Math.max(maxLen, ei - si); // Jab koi v duplicate ele nai hoga poore string me OR last waali window ka length max ho jaaye [si, ei)
        return maxLen;
    }
    
}
```
