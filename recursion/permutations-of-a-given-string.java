// https://practice.geeksforgeeks.org/problems/permutations-of-a-given-string2041/1
// Order of ans is wrong approach is correct

class Solution {
    
    public List<String> find_permutation(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder(s);
        List<String> ans = new ArrayList<>();
        find_permutation(0, n - 1, n, s, ans);
        return ans;
    }
    
  
    // Using StringBuilder 
    // public void find_permutation(int si, int ei, int n, StringBuilder sb, List<String> ans) {
    //     if(si == ei) {
    //         ans.add(new String(sb.toString()));
    //         return;
    //     }
        
    //     for(int i = si; i <= ei; i++) {
    //         swap(si, i, sb);
    //         find_permutation(si + 1, ei, n, sb, ans);
    //         swap(si, i, sb);
    //     }
    // }
    
    // public void swap(int si, int i, StringBuilder sb) {
    //     //System.out.println(si + "\t");
    //     char ch1 = sb.charAt(si);
    //     char ch2 = sb.charAt(i);
    //     sb.setCharAt(si, ch2);
    //     sb.setCharAt(i, ch1);
    // }
    
  
    // Using String
    private void find_permutation(int si, int ei, int n, String str, List<String> ans) { 
        if(si == ei) {
            ans.add(str);
            return;
        }
        
        for(int i = si; i <= ei; i++) {
            str = swap(si, i, str);
            find_permutation(si + 1, ei, n, str, ans);
            str = swap(si, i, str);
        }
    }
    
     public String swap(int i, int j, String a) { 
        char temp; 
        char[] charArray = a.toCharArray(); 
        temp = charArray[i] ; 
        charArray[i] = charArray[j]; 
        charArray[j] = temp; 
        return String.valueOf(charArray); 
    }
    
}
