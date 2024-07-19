// https://leetcode.com/problems/longest-chunked-palindrome-decomposition/

class Solution {
    public int longestDecomposition(String text) {
        int n = text.length();
        String start = "";
        String end = "";
        int count = 0;
        for(int i = 0; i < n; i++) {
            start += text.charAt(i);
            end = text.charAt(n - i - 1) + end;
            if(start.equals(end)) {
                count++;
                start = "";
                end = "";
            }
            
        }
        return count;
    }
}
