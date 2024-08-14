// https://leetcode.com/problems/first-unique-character-in-a-string/

```
class Solution {
    
    public int firstUniqChar(String s) {
        char[] ch = s.toCharArray();
        int n = ch.length;
        int[] freq = new int[26];
        for(int i = 0; i < n; i++) {
            freq[ch[i] - 'a']++;
        }

        for(int i = 0; i < n; i++) {
            if(freq[ch[i] - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
    
}
```
