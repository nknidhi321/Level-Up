// https://leetcode.com/problems/largest-number/

// Sort in lexicographical order(in string form)   [NOTE : Concatinate and then use compareTo()]
// instead of normal sorting which is done on value(in integer form)

```
class Solution {
    
    public String largestNumber(int[] nums) {
        int n = nums.length;
        String[] str = new String[n];
        
        for(int i = 0; i < n; i++) {
            str[i] = Integer.toString(nums[i]);
        }
        
        // Ex : [332, 33] 
        // If we simply use compareTo(), then 332 is lex greater than 33
        // So, 332 will be considered as the greter no, and 32 will be considered as the smaller no
        // So, 33233 will come out to be your ans.
        // But if you would have placed 33 first and then 332 then your answer would be 33332  
        // And, obviously 33332 is larger than 33233
        // So, why does this happen, because there's diff in length of the strings
        // So, inorder to make them of same length just concatinate both the input string,
        // to be compared, vice-versly. And now use compareTo()
        // Now, you will get lex order exactly what you want.
        
        Arrays.sort(str, (s1, s2) -> {
            String ob1 = s1 + s2;
            String ob2 = s2 + s1;
            return ob2.compareTo(ob1); // dec order // other - this
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
```
---------------------------------------------------------------------------------------------------------------
