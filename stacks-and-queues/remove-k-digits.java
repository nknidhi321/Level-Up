// https://leetcode.com/problems/remove-k-digits/

// Modified NSOL
class Solution {
    
    public String removeKdigits(String s, int k) {
        int n = s.length();
        // Using ArrayList as stack, since we have to iterate the bottom of the stack as well
        List<Character> stackal = new ArrayList<>(); 
        
        // To make the digit smaller, aage se jitna bade se bade digit milta hai usko hatna hoga
        // Kind of modified NSOL
        for(int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            while(stackal.size() > 0 && k > 0 && stackal.get(stackal.size() - 1) > ch) {  // Bada mile pop kar do
                stackal.remove(stackal.size() - 1);
                k--;
            }
            stackal.add(ch); // Push yourself
        }
        
        // Now, starting se jitna bade se bada digit hat sakta tha, sab hat chuka hai
        // So, ab agar k bacha hai then sabse bada digit avi stack k top pe he hoga
        
        // Ex : "123456789", k = 2 
        // Here modified NSOL kuch nai kar paaega, So stack = [123456789] hai
        // Isme sbse bada digit stack k top(last) me hai
        // So, start deleting from stack's top, until k becomes 0
        while(k-- > 0) { // k will always be <= n (given), so skip this check stackal.size() > 0
            stackal.remove(stackal.size() - 1);
        }
        
        
        // Ex : "10200", k = 1 
        // ans should be 200 and not 00200
        // So firstNonZeroDigit k aage jitne v 0's hai sabko ura do
        boolean firstNonZeroDigit = false;
        StringBuilder sb = new StringBuilder();
        for(char ch: stackal) {
            if(ch == '0' && !firstNonZeroDigit) continue; // starting k sab 0's ignore karo
            else { // firstNonZeroDigit k aane se firstNonZeroDigit true ho gaya, ab if kvi v nai chalega
                firstNonZeroDigit = true;
                sb.append(ch);
            }
        }
        
        // Ex: "10", k = 2, After NSOL, Stack = [0], So firstNonZeroDigit will never become true
        // Therefore, sb khali hogi, par expected hai ek 0, so return like this
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
