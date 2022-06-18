// https://leetcode.com/problems/largest-number/

class Solution {
    
    public String largestNumber(int[] nums) {
        int n = nums.length;
        String[] str = new String[n];
        
        for(int i = 0; i < n; i++) {
            str[i] = Integer.toString(nums[i]);
        }
        
        Arrays.sort(str, new Comparator<String>() {
           public int compare(String s1, String s2) {
               String ob1 = s1 + s2;
               String ob2 = s2 + s1;
               return ob2.compareTo(ob1);
           } 
        });
        
        int countZeros = 0;
        StringBuilder sb = new StringBuilder();
        for(String s : str) {
            sb.append(s);
            if(s.equals("0")) countZeros++;
        }
        
        return countZeros == str.length ? "0" : sb.toString();
    }
    
}
