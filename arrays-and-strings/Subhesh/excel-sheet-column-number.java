// https://leetcode.com/problems/excel-sheet-column-number/

class Solution {
    
    public int titleToNumber(String s) {
        return fetchColumnIdx1(s);
    }
    
    public static int fetchColumnIdx(String s) {
        int sum = 0;
        int n = s.length();
        for(int i = 0; i < n; i++) {
            sum *= 26;
            sum += s.charAt(i) - 'A' + 1;
        }
        return sum;
    }
    
    public static int fetchColumnIdx1(String s) {
        int n = s.length();
        int sum = 0;
        int power = 0;
        for(int i = n - 1; i >= 0; i--) {
            sum += (s.charAt(i) - 'A' + 1) * (Math.pow(26, power++));
        }
        return sum;
    }
    
}
