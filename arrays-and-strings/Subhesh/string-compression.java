// https://leetcode.com/problems/string-compression/

class Solution {
    
    public int compress(char[] chars) {
        String str = String.valueOf(chars);
        int n = str.length();
		String ans = "";
		int counter = 1;
		
		for(int i = 1; i <= n; i++) { 
		    if((i == n) || (str.charAt(i - 1) != str.charAt(i))) { // (i == n) handling the last character here only
		        ans += str.charAt(i - 1); // computing ans of prev char(i-1) by standing at i
		        if(counter > 1) ans += counter;
		        
                counter = 1; // setting counter for current character(i) to 1
		    }
		    else {  // str.charAt(i - 1) == str.charAt(i)
		        counter++;  // simply increase counter
		    }
		}
        
        // As given in the question, you have to return your final ans in the input array itself
        int i = 0;
        for(char c : ans.toCharArray()) {
            chars[i++] = c;
        }
        
        return ans.length();
	}
    
}
