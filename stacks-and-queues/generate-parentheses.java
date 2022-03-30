// https://leetcode.com/problems/generate-parentheses/

/*
    Intuition and Algorithm
    ----------------
    Instead of adding '(' or ')' every time as in Approach 1, let's only add them when we know it will remain a valid sequence. 
    We can do this by keeping track of the number of opening and closing brackets we have placed so far.
    We can start an opening bracket if we still have one (of n) left to place. 
    And we can start a closing bracket if it would not exceed the number of opening brackets.
*/

// Using StringBuilder

```
class Solution {
    
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        
        // For n == 3, 3 maximum "(" && 3 maximum ")"
        generateParenthesis_Rec(0, 0, n, new StringBuilder(), ans);
        return ans;
    }
    
    public static void generateParenthesis_Rec(int open, int close, int max, StringBuilder sb, List<String> ans) {
        if(open == max && close == max) {
            ans.add(sb.toString());
            return;
        }
        
        // Opening should be less than maximum, that is n, so check if(open < max) 
        if(open < max) {
            sb.append("(");
            generateParenthesis_Rec(open + 1, close, max, sb, ans);
            sb.deleteCharAt(sb.length() - 1);
        }
        
        // Any closing cannot come before opening, so check if(close < open)
        if(close < open) {
            sb.append(")");
            generateParenthesis_Rec(open, close + 1, max, sb, ans); 
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    
}
```

---------------------------------

// Same as above but using String

```
class Solution {
    
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        
        // For n == 3, 3 maximum "(" && 3 maximum ")"
        generateParenthesis_Rec(0, 0, n, "", ans);
        return ans;
    }
    
    public static void generateParenthesis_Rec(int open, int close, int max, String ssf, List<String> ans) {
        if(open == max && close == max) {
            ans.add(ssf);
            return;
        }
        
        // Opening should be less than maximum, that is n, so check if(open < max) 
        if(open < max) generateParenthesis_Rec(open + 1, close, max, ssf + "(" , ans);
        
        // Any closing cannot come before opening, so check if(close < open)
        if(close < open) generateParenthesis_Rec(open, close + 1, max, ssf + ")" , ans);
    }
    
}

```

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Brute Force
// Checking isValid() using balance

/*
    We can generate all 2^{2n} sequences of '(' and ')' characters. Then, we will check if each one is valid.
    Time Complexity : O(2^{2n}n)
    For each of 2^{2n} sequences, we need to create and validate the sequence, which takes O(n) work.
*/

```
class Solution {
    
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        
        // For n == 3, 6 opening closing brackets aaenge, so boxCount == 3 * 2
        generateParenthesis_Rec(n * 2, "", ans);
        return ans;
    }
    
    public static void generateParenthesis_Rec(int boxCount, String ssf, List<String> ans) {
        if(boxCount == 0) {
            if(isValid(ssf)) ans.add(ssf);
            return;
        }
        
        generateParenthesis_Rec(boxCount - 1, ssf + "(" , ans);
        generateParenthesis_Rec(boxCount - 1, ssf + ")" , ans);
    }
    
    public static boolean isValid(String str) {
        int balance = 0;
        for (char c : str.toCharArray()) {
            if (c == '(') balance++;
            else balance--;
            if (balance < 0) return false; // balance < 0 hoga, jab closing brackets zyada honge
        }
        return (balance == 0); // balance != 0 hai, mtlb opening brackets zyada hai
    }
    
}
```

----------------------------------------------------

// Same as above, but Checking isValid() using stack

```
class Solution {
    
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        
        // For n == 3, 6 opening closing brackets aaenge, so boxCount == 3 * 2
        generateParenthesis_Rec(n * 2, "", ans);
        return ans;
    }
    
    public static void generateParenthesis_Rec(int boxCount, String ssf, List<String> ans) {
        if(boxCount == 0) {
            if(isValid(ssf)) ans.add(ssf);
            return;
        }
        
        generateParenthesis_Rec(boxCount - 1, ssf + "(" , ans);
        generateParenthesis_Rec(boxCount - 1, ssf + ")" , ans);
    }
    
    public static boolean isValid(String str) {
        Stack<Character> stack = new Stack<>();
        
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            // Jo v opening brackets aaega push kar do
            if(ch == '(') stack.push(ch);
            
            // Koi opening bracket nai hai stack me and koi closing bracket aa gaya => Order mismatched
            else if(stack.isEmpty() && ch == ')') return false; 
            
            // Ek specific type k opening bracket k corresponding wahi specific closing bracket mila gaya stack k top pe then
            // Ek sahi pair mil gaya => pop kar do stack k top ko => Discard that pair kuki end me sirf galat log stack me bachenge
            else stack.pop();
        }
        
        // Kuki saare sahi pairs ko humne stack se pop karwa diya tha 
        // => Ab jo stack me bache hai wo sirf '(' opening brackets honge
        // jinke pairs nahi mile 
        // So check karo agar stack khali hai mtlb sbke pairs mil gaye honge 
        // Agar khali nahi hai then sbke pairs nahi mile
        return stack.isEmpty() ? true : false;
    }
    
}
```

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
