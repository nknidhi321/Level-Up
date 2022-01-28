// https://www.codingninjas.com/codestudio/problems/smallest-equivalent-string_1381859?leftPanelTab=0

// Rajneesh 
// treating everything in int

public class Solution {
    
    public static int[] par;
    
    public static int findPar(int v) {
		if (par[v] == v) return v;
		return par[v] = findPar(par[v]);
	}
    
	public static String smallestString(String s, String t, String str) {
        par = new int[26];  // Create Parent array 
        for(int i = 0; i < 26; i++) {
            par[i] = i;  // Parent of itself
        }
        
        for(int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i); char c2 = t.charAt(i);
            int p1 = findPar(c1 - 'a'); int p2 = findPar(c2 - 'a');  // Find parent
            
            if(p1 < p2) par[p2] = p1;  // MergeOrUnion
            else par[p1] = p2;  	   // Global parent hamesha lex min wala char hoga
        }
        
        // Find global Parent for all the chars of str and form answer
        StringBuilder sb = new StringBuilder();
        for(char c : str.toCharArray()) {
            int p = findPar(c - 'a');
            sb.append((char)(p + 'a')); 
        }
        return sb.toString();
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Mine
// Same as above but treating everything in char

public class Solution {
    
    public static char[] par;
    
    public static char findPar(char v) {
	    if(par[v - 'a'] == v) return v;
	    return par[v - 'a'] = findPar(par[v - 'a']);
	}
    
    public static String smallestString(String s, String t, String str) { 
        par = new char[26];		    // Create Parent array
        char alpha = 'a';
        for(int i = 0; i < 26; i++) {
            par[i] = alpha++;		// Parent of itself
        }
        
        for(int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i); char c2 = t.charAt(i);
            char p1 = findPar(c1); char p2 = findPar(c2);     // Find parent
            
            if(p1 < p2) par[p2 - 'a'] = p1;		 // Merge
            else par[p1 - 'a'] = p2; 			 // Global parent hamesha lex min wala char hoga
        }
        
        // Find global Parent for all the chars of str and form answer
        String ans = "";
        for(char c : str.toCharArray()) {
            ans += findPar(c); 
        }
        return ans;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
