//https://leetcode.com/problems/letter-combinations-of-a-phone-number/

/*
  void Type
  Using StringBuilder, hence also performing backtracking
  Efficient Approach
*/

class Solution {
    
    public List<String> letterCombinations(String digits) {     
        String[] words = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> ans = new ArrayList<>();
        StringBuilder asf = new StringBuilder("");
        letterCombinations_Util(0, digits, asf, words, ans);
        return (ans.size() == 1 && ans.get(0).equals("")) ? new ArrayList<>() : ans;
    }
    
    public static void letterCombinations_Util(int idx, String digits, StringBuilder asf, String[] words, List<String> ans) {
        if(idx == digits.length()) {
            ans.add(asf.toString());
            return;
        }
        
        String word = words[digits.charAt(idx) - '0'];
        for(char c : word.toCharArray()) {
            asf.append(c);
            letterCombinations_Util(idx + 1, digits, asf, words, ans);
            asf.deleteCharAt(asf.length() - 1);  //Backtrack
        }
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

/*
  void Type
  No backtracking changes performed in String
*/

class Solution {
    
    public List<String> letterCombinations(String digits) {     
        String[] words = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> ans = new ArrayList<>();
        letterCombinations_Util(0, digits, "", words, ans);
        return (ans.size() == 1 && ans.get(0).equals("")) ? new ArrayList<>() : ans;
    }
    
    public static void letterCombinations_Util(int idx, String digits, String asf, String[] words, List<String> ans) {
        if(idx == digits.length()) {
            ans.add(asf);
            return;
        }
        
        String word = words[digits.charAt(idx) - '0'];
        for(char c : word.toCharArray()) {
            letterCombinations_Util(idx + 1, digits, asf + c, words, ans);
        }
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

/*
  get Type
  No backtracking since changes performed in String
  Worst performance
*/

class Solution {
    
    public List<String> letterCombinations(String digits) {     
        String[] words = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> ans = letterCombinations_Util(0, digits, words);
        return (ans.size() == 1 && ans.get(0).equals("")) ? new ArrayList<>() : ans;
    }
    
    public static List<String> letterCombinations_Util(int idx, String digits, String[] words) {
        if(idx == digits.length()) {
            List<String> base = new ArrayList<>();
            base.add("");
            return base;
        }   
        
        List<String> ans = new ArrayList<>();
        List<String> smallAns = letterCombinations_Util(idx + 1, digits, words);
        String word = words[digits.charAt(idx) - '0'];
        for(char c : word.toCharArray()) {
            for(String s : smallAns) {
                ans.add(c + s);
            }
        }
        return ans;
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
