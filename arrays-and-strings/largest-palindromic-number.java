// https://leetcode.com/problems/largest-palindromic-number/

```
class Solution {
    
    public String largestPalindromic(String num) {
        int n = num.length();
        StringBuilder firstHalf = new StringBuilder();
        StringBuilder lastHalf = new StringBuilder();
        int[] freq = new int[10];
        
        for(Character c : num.toCharArray()) { // freq array
            freq[(int)(c - '0')]++;
        }
        
        if(freq[0] == n) return "0"; // All digits were 0, edge case
        
        // Printing
        // for(int i = 0; i < 10; i++) {
        //     System.out.println(i + " " + freq[i]);
        // }
        
        boolean middleFlag = false;
        String middleValue = "";
        
        // take out the max 2 digits and append it at the insides of both ends
        // and the remaining max digit if it has 1 length will be the middle guy 
        for(int i = 9; i >= 0; i--) { 
            while(freq[i] >= 2) {
                firstHalf.append(i);
                lastHalf.append(i); 
                freq[i] = freq[i] - 2;
            }
            if(!middleFlag && freq[i] == 1) {
                middleValue = i + "";
                middleFlag = true;
            }
        }
        
        if(firstHalf.length() > 0 && firstHalf.charAt(0) == '0') return middleValue; // remove front last zeros
        return firstHalf.toString() + middleValue + lastHalf.reverse().toString(); 
    }
}
```
