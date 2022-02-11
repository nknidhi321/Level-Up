// https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
// Intuition :- To make the string valid with minimum removals, we need to get rid of all parentheses that do not have a matching pair.

// Rajneesh Bhaiya
// Stack me sara invalid brackets rahega i.e '(' + ')' jiske pairs nai mile

/*
	  Approach:-
	  --------
	  1) Push char index into the stack when we see '(' 
	  2) Pop from the stack when we see ')' and stack top is '(' 
	  		else If the stack is empty, push ')' without the pair => invalid bracket 

	  In the end, the stack will contain indexes of '(' and ')' without the pair, 
	  If any we need to remove all of them.
*/

class Solution {
    
    // Why using LL as Stack instead of Stack ?? *
    
    public String minRemoveToMakeValid(String s) {
        int n = s.length();
        LinkedList<Integer> stackll = new LinkedList<>();  // Using LL in the form of stack
        
        // Add all invalid brackets to the stack
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if(c == '(') {
                stackll.addFirst(i); // '(' mile toh add kar do
            }
            else if(c == ')') {
                if(stackll.size() > 0 && s.charAt(stackll.getFirst()) == '(') stackll.removeFirst();  // Jiska pair milte jaaye usko pop kar do
                else stackll.addFirst(i); // ')' v add kar do agar stack k top pe uka pair nahi mila toh
            }
        }
        
        // Now, stack contains all the invalid brackets idx
        // Ab string pe iterate karo, jo v invalidIdx hai unko skip karte jaao, ans banate jaao
        
        // * Because we need to iterate stack from bottom to top
        // and there's no way to iterate stack in such a manner
        // So using LL in the form of stack, doing push pop operation from head in O(1)
        int invalidIdx = stackll.size() - 1; // Sbse bada invalidIdx stack k top pe hoga
        
        StringBuilder sb = new StringBuilder();
        
        // s ko 0 idx se check kar rahe hai that's why stack ko bottom to top iterate krna hoga(small to large)
        for(int i = 0; i < n; i++) { 
            if(invalidIdx >= 0 && stackll.get(invalidIdx) == i) invalidIdx--;
            else sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}

---------------------------------------------------------------------------------------------------------------------------------------------------------

// Leetcode Discuss section, using * to replaceAll invalid idx
// Using Pair class, it's good to store idx to like above instead of Pair class
  
class Solution {
    
    public static class Pair {
        char c;
        int idx;
        
        public Pair(char c, int idx) {
            this.c = c;
            this.idx = idx;
        }
    }
    
    public String minRemoveToMakeValid(String s) {
        
        // Created Pair class, because we have to store both bracket and their idx position
        Stack<Pair> stack = new Stack<>();  
        char[] ch = s.toCharArray();
        
        for(int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if(c == '(') {
                stack.push(new Pair('(', i));
            }
            else if(c == ')') {
                if(!stack.isEmpty() && stack.peek().c == '(') stack.pop();
                else stack.push(new Pair(')', i));
            }
        }
        
        // Stack contains all the invalid brackets Pair
        
        // Replace all invalid idx with '*'
        StringBuilder sb = new StringBuilder(s);
        for(Pair pair : stack) {
            sb.setCharAt(pair.idx, '*');
        }
        
        // Replace all invalid '(' and ')' which was marked as '*' from the string
        return sb.toString().replaceAll("\\*", "");
    }
}

----------------------------------------------------------------------------------------------------------------------------------------------
  
// Mine, With Extra Space of Set 
// Faltu
  
class Solution {
    
    public static class Pair {
        char c;
        int idx;
        
        public Pair(char c, int idx) {
            this.c = c;
            this.idx = idx;
        }
    }
    
    public String minRemoveToMakeValid(String s) {
        
        // Created Pair class, because we have to store both bracket and their idx position
        Stack<Pair> stack = new Stack<>();  
        char[] ch = s.toCharArray();
        
        for(int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if(c == '(') {
                stack.push(new Pair('(', i));
            }
            else if(c == ')') {
                if(!stack.isEmpty() && stack.peek().c == '(') stack.pop();
                else stack.push(new Pair(')', i));
            }
        }
        
        // Stack contains all the invalid brackets Pair
        
        // Adding all invalid bracket idx in HashSet to get it in O(1)
        Set<Integer> invalidIdx = new HashSet<>(); 
        for(Pair pair : stack) {
            invalidIdx.add(pair.idx);
        }
        
        // Now escape all the idx which is present in HashSet 
        // and form answer from the original given string in question
        StringBuilder sb = new StringBuilder();
        for(int idx = 0; idx < ch.length; idx++) {
            if(!invalidIdx.contains(idx)) {
                sb.append(ch[idx]);        
            }
        }
        
        return sb.toString();
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Stack me sirf invalid '(' rahega, without pair
/*
	  Approach:-
	  --------
	  1) Push char index into the stack when we see '('
	  2) Pop from the stack when we see ')'
	  3) If the stack is empty, then we have ')' without the pair => invalid bracket => replace

	  In the end, the stack will contain indexes of '(' without the pair, if any. 
	  We need to remove all of them too.
*/

class Solution {
    
    public String minRemoveToMakeValid(String s) {
        
        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> stack = new Stack<>(); // Stack will contain all invalid '(' idx  
        
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(') {
                stack.push(i); // Add in stack, so that when ')' comes, it can pop out '(' and balance
            }
            else if(c == ')') {
                if(!stack.isEmpty()) stack.pop(); // If there exists something, you know that it is always '('
                else sb.setCharAt(i, '*'); // If you do not have anything in stack and ')' comes then it is invalid
            }
        }
        
        // Stack will contain all '(' invalid idx
        // NOTE: All invalid ')' is already replaced by '*'
        
        // Now replace all invalid '(' with '*'
        while(!stack.isEmpty()) {
            sb.setCharAt(stack.pop(), '*');
        }
        
        // Replace all invalid '(' and ')' which was marked as '*' from the string
        return sb.toString().replaceAll("\\*", "");
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
