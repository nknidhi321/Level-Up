// https://leetcode.com/problems/excel-sheet-column-title/
// There's an edge case for 'z'

class Solution {
    
    public String convertToTitle(int columnNumber) {
        return fetchColumnString(columnNumber);
    }
    
    public static String fetchColumnString(long num) {
        StringBuilder sb = new StringBuilder();
        
        // If u take (n - 1) 'z' edge case if also handled
        while(num > 0) {
            int rem = (int)((num - 1) % 26); 
            sb.append((char)(rem + 'A'));
            num = (num - 1) / 26;
        }
        return sb.reverse().toString();        
    }
    
}
