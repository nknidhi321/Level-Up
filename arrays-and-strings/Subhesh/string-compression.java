// https://leetcode.com/problems/string-compression/

class Solution {

    public int compress(char[] chars) {
        String str = String.valueOf(chars);
        String ans = "";
        int counter = 1;

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i - 1) != str.charAt(i)) {
                ans += str.charAt(i - 1); // computing ans of prev char(i-1) by standing at i
                if (counter > 1) ans += counter;

                counter = 1; // setting counter for current character(i) to 1
            } else { // str.charAt(i - 1) == str.charAt(i)
                counter++; // simply increase counter
            }
        }

        // handling the last character, bcoz i pe (i-1)th char ka answer nikalte hai hum
        // So, last char bacha reh gaya tha waha
        ans += str.charAt(str.length() - 1);
        if (counter > 1) ans += counter;

        // As given in the question, you have return your final ans in the input array itself
        int i = 0;
        for (char c: ans.toCharArray()) {
            chars[i++] = c;
        }

        return ans.length();
    }

}
