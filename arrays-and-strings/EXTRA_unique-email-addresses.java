// https://leetcode.com/problems/unique-email-addresses/

// Using regex
class Solution {
    
    public int numUniqueEmails(String[] emails) {
        
        // used to save simplified email address, cost O(n) sapce.
        Set<String> set = new HashSet<>(); 
        
        for (String email : emails) {
            
            // split into local and domain parts
            String[] parts = email.split("@"); 
            
             // split local by '+'
            String[] local = parts[0].split("\\+");
            
            // remove all '.', and concatenate '@' and domain
            set.add(local[0].replace(".", "") + "@" + parts[1]);      
        }
        
        return set.size();
    }

}

// --------------------------------------------------------------------------------

// Using conditions
class Solution {
    
    public int numUniqueEmails(String[] emails) {
        
        int n = emails.length;
        Set<String> set = new HashSet<>();
        
        for(String email : emails) {
            StringBuilder sb = new StringBuilder();
            boolean foundAtTheRate = false;
            boolean foundPlus = false;
            for(int i = 0; i < email.length(); i++) {
                if(foundAtTheRate) {
                    sb.append(email.charAt(i));
                }
                else if(email.charAt(i) == '.') {
                    continue;
                }
                else if(email.charAt(i) == '+') {
                    foundPlus = true;
                }
                else if(email.charAt(i) == '@') {
                    foundAtTheRate = true;
                    foundPlus = false;
                    sb.append(email.charAt(i));
                }
                else if(foundPlus == false) {
                    sb.append(email.charAt(i));
                }
            }
            if(sb.length() > 0) set.add(sb.toString());
        }
        return set.size();
    }
    
}
