// https://practice.geeksforgeeks.org/problems/check-if-two-strings-are-k-anagrams-or-not/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article

class Solution {
    
    // Creating two freq array and solving
    boolean areKAnagrams1(String str1, String str2, int k) {
        int n = str1.length();
		int m = str2.length();
		if(n != m) return false;
		int[] freq1 = new int[26];
		int[] freq2 = new int[26];
        for(int i = 0; i < n; i++) {
            freq1[str1.charAt(i) - 'a']++;
            freq2[str2.charAt(i) - 'a']++;
        }
        
        int count = 0;
        for(int i = 0; i < 26; i++) {
            if(freq1[i] != 0) {
                count = count + ((freq1[i] - freq2[i]) > 0 ? (freq1[i] - freq2[i]) : 0);
            }
        }
        if(count <= k) return true;
		return false;
    }
    
    
    // Better Approach. ===================================================================
    // Creating one freq array and cancelling out the effect
    boolean areKAnagrams(String str1, String str2, int k) {
        int n = str1.length();
		int m = str2.length();
		if(n != m) return false;
		int[] freq1 = new int[26];
        for(int i = 0; i < n; i++) {
            freq1[str1.charAt(i) - 'a']++;
            freq1[str2.charAt(i) - 'a']--;
        }
        
        int count = 0;
        for(int i = 0; i < 26; i++) {
            if(freq1[i] >= 0) {
                count += freq1[i];
            }
        }
        if(count <= k) return true;
		return false;
    }
    
}
