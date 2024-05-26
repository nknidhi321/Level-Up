// https://leetcode.com/problems/longest-absolute-file-path/

class Solution {
    
    public int lengthLongestPath(String input) {
        int maxLen = 0, currLen = 0;
        Stack<Integer> stack = new Stack<>();
        String[] tokens = input.split("\n");
        
        for(int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            int level = countLevel(token);
            
            // if current directory/file depth is lower
            // that the top directory/file on the stack, pop from stack
            while(!stack.isEmpty() && level < stack.size()) {
                currLen -= stack.pop();
            }    
            
            // +1 here because a "/" needs to be counted following each diretory
            int len = token.replaceAll("\t", "").length() + 1;
            currLen += len;
            
            // if s contains ".", we have found a file!
            if(token.contains(".")) {
                maxLen = Math.max(maxLen, currLen);    
            }
            stack.add(len);
        }
        return maxLen == 0 ? 0 : maxLen - 1; // Removing extra \
    }
    
    // The depth of the directory/file is calculated by counting how many "\t"s are there.
    public int countLevel(String s) {
        String replacedt = s.replaceAll("\t", "");
        return s.length() - replacedt.length();
    }
    
}
