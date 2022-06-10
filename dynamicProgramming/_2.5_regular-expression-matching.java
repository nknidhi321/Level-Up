// https://leetcode.com/problems/regular-expression-matching/

/* 
 
 Invalid test cases :-
    # s can never be "" empty
    # p can never only be "*" because it's clearly said * matches with it's prev char [It will be atleast 2 chars]

    NOTE : You will never reach a state where you land up only with "*" in the pattern, as per your code
           atleast m will always be of 2 length
           because 0 times map hoga toh 2 charaters kha jaaega, ex : "a*" -> ""  
           and agar kisi ko map kar dega tab v alive rahega
        
    NOTE : Here both true and false is an answer so have a third value,
           to note that xth cell was never calculated say null, if you take Boolean(not boolean)

    NOTE : * means multiply the prev charcter with 0 or 1 or 2 or 3 or so on.. 
        1) Ex1 : "a*"
                0 means => Your prev char comes 0 times => ""
                1 means => Your prev char comes 1 times => "a"
                2 means => Your prev char comes 2 times => "aa"

        2) Ex2 : .* 
                0 means => Your prev char comes 0 times => ""
                1 means => Your prev char comes 1 times => "."
                2 means => Your prev char comes 2 times => ".."

        Kind of replace * with 0 or 1 or 2 or 3..
*/

class Solution {

    public boolean isMatch(String s, String p) {
        p = p.replaceAll("\\*+", "*");
        
        // Ex: s = "ab"; p = "******************";
        // After replaceAll method, p = "*"
        // So it is not of 2 length, but here we assume that pattern should minimum be of length 2
        // And * code is handled as per the above criteria
        if(p.length() == 1 && p.charAt(0) == '*') return false; 

        int n = s.length();  // string
        int m = p.length();  // pattern
        
        Boolean[][] dp = new Boolean[n + 1][m + 1]; // {vertically-> string, horizontally-> pattern}
        
        return isMatch_Memo(n, s, m, p, dp);
    }
    
    public boolean isMatch_Memo(int n, String s, int m, String p, Boolean[][] dp) {
        if(n == 0 && m == 0) return dp[n][m] = true; // Dono 0 ho gaye
        if(m == 0) return dp[n][m] = false; // Sirf pattern 0 hua, sirf n 0 hua is handled in * code
        
        if(dp[n][m] != null) return dp[n][m];
        
        // Both characters will match and cancel out
        if(n >= 1 && m >= 1 && (s.charAt(n - 1) == p.charAt(m - 1) || p.charAt(m - 1) == '.')) { 
            return isMatch_Memo(n - 1, s, m - 1, p, dp); 
            // NOTE isko poori pattern chahiye so subsequence k jaise call mat lgana
            // like by leaving the last char of s and again by leaving the last char of p
            // Iske pass bs ek he choice hai agar equal hai toh map ho jaao
        }
        else if(p.charAt(m - 1) == '*') { // Jab pattern * ho tab cases bnenge map ho v sakta hai, nahi v ho sakta hai
            boolean res = false;
            if(n >= 1 && m >= 2 && (p.charAt(m - 2) == '.' || p.charAt(m - 2) == s.charAt(n - 1))) { // map hone ka case 
                // Dono n && m bacha hai and match ho gaye
                // Why n - 1 ?? because it is mapped // Why m ?? because pattern is mapped but still alive
                res = res || isMatch_Memo(n - 1, s, m, p, dp); // character is mapped,pattern multiplied with0 
            }
            // NOTE : Agar upar ki call se false aaegi sirf tvi nichw ki call lgegi
            // In a way dono ki call lg sakti hai, and that is correct, kuki choices hai
            
            // map nahi hone ka case
            // Case1) n = 0, n khatam ho gaya and sirf m bacha hai
            // Case2) pattern k map nahi krne ki choice hai

            // aapko map nahi hona toh apne aage wale ko chance do //ex : "a*" -> ""
            // Now, why (m - 2) ?? Kuki * ka relation ka uske prev. charcter k wazah se he hai
            // So, jab tak uska prev char and aap alive ho string choti nai ho sakti, so both of u die
            return dp[n][m] = res || isMatch_Memo(n, s, m - 2, p, dp); // does not want to map => pattern multiplied with 0
            
            // NOTE : Yaha pe choices hai * k pass like we do in subsequence because it can come 0 or 1 or 2..
            // But ye subsequence jaise calls sirf * k liye lgegi kuki sirf * k pass choices hai baaki char k pass nai
        }
        else { // Both characters mismatched
            return dp[n][m] = false; 
        }
    }

}
