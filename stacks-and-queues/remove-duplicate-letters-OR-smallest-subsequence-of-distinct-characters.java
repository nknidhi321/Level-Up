// https://leetcode.com/problems/remove-duplicate-letters/
// https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/

class Solution {
    
    public String removeDuplicateLetters(String s) {
        int n = s.length();
        
        // Creating freq array
        int[] freq = new int[26];
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            freq[c - 'a']++;
        }
        
        // Keep visited_OR_isPresentInStack so that your stack never has repeated elements
        boolean[] visited_OR_isPresentInStack = new boolean[26];
        Stack<Integer> stack = new Stack<>();    
        
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            freq[c - 'a']--; // Aate he apni freq ghata do, mere jaise mere "aage" aur kitne log hai 
            if(visited_OR_isPresentInStack[c - 'a']) continue; // Already existing in stack, so filhal uska koi kaam nahi
            
            // NSOL find karo, mtlb mere se jo v bada hai stack me, usko pop kar do provided the below condition
            // stack ka top bolega, mere baad mere jaisa koi hai ya nahi? Agar nahi hai to mujhe rehne do,
            // Kuki saare elements ko atleast ek baar toh aana he hai
            while(!stack.isEmpty() && s.charAt(stack.peek()) > c && freq[s.charAt(stack.peek()) - 'a'] > 0) {
                visited_OR_isPresentInStack[s.charAt(stack.peek()) - 'a'] = false; // Stack's top will be deleted, so mark unvisited
                stack.pop(); // deleted stack's top
            }
            stack.push(i); // Add yourself(idx)
            visited_OR_isPresentInStack[s.charAt(stack.peek()) - 'a'] = true; // Stack me add ho gaye, so mark yourself as visited
        }
        
        // Ab stack me jo v hai, bottom to top wo lexo smaller to larger hai
        StringBuilder sb = new StringBuilder();
        for(int idx : stack) {
            sb.append(s.charAt(idx));
        }
        return sb.toString();
    }
}
