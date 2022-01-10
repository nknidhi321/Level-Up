// https://www.codingninjas.com/codestudio/problems/smallest-equivalent-string_1381859?leftPanelTab=0

// Wrong

public class Solution {
    
    public static char[] par;
    
    public static char findPar(char v) {
		if (par[v - 'a'] == v) return v;
		return par[v - 'a'] = findPar(par[v - 'a']);
	}
    
	public static String smallestString(String s, String t, String str) {
        
        // Create Parent array 
        par = new char[26];
        char alpha = 'a';
        // Parent of itself
        for(int i = 0; i < 26; i++) {
            par[i] = alpha++;
        }
        
        for(int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i); char c2 = t.charAt(i);
            
            // Find parent
            char p1 = findPar(c1); char p2 = findPar(c2); 
            
            // Merge // Global parent hamesha lex min wala char hoga
            if(p1 < p2) par[c2 - 'a'] = p1;
            else par[c1 - 'a'] = p2; 
        }
        
        // Find global Parent for all the chars of str and form answer
        String ans = "";
        for(char c : str.toCharArray()) {
            ans += findPar(c); 
        }
        return ans;
    }
}

