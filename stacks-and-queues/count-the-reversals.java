// https://practice.geeksforgeeks.org/problems/count-the-reversals0401/1

class Sol {
    
    int countRev (String s) {
        int n = s.length();
        int count = 0;
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            
            if(stack.isEmpty() && c == '}') { // Invalid closing brackets ko flip kar do
                count++;
                stack.push('{');
            }
            else if(c == '}') { // Valid pair ko pop kr do
                stack.pop();
            }
            else { // Invalid opening brackets stack me sab hoga
                stack.push('{');
            }
        } 
        if(stack.size() % 2 != 0) return -1;
        return count + stack.size() / 2;
    }
    
}
