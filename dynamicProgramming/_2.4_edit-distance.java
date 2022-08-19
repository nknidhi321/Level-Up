// https://leetcode.com/problems/edit-distance/
// https://www.youtube.com/watch?v=tooMn-xfYCU&t=661s

// Minimum number of opeartions required ?
// [Assumption : each operation of insert, delete, replace costs 1 rupee]
// Do checkout follow up question at last !!

/*
	Si  ==  Sj ,  Toh kya?
	Si  !=  Sj ,  Toh kya?
*/

// Memoization

class Solution {
    
    public int minDistance(String s, String tar) {
        int n = s.length();
        int m = tar.length();
        Integer[][] dp = new Integer[n + 1][m + 1];
        return memo(n, s, m, tar, dp);
    }
    
    public int memo(int n, String s, int m, String tar, Integer[][] dp) {
        if(m == 0 || n == 0) return dp[n][m] = n == 0 ? m : n;
        
        if(dp[n][m] != null) return dp[n][m];
        
        char c1 = s.charAt(n - 1);
        char c2 = tar.charAt(m - 1);
        int min = (int)1e9;
        if(c1 == c2) { // char equal
            min = memo(n - 1, s, m - 1, tar, dp);
        }
        else { // char unequal
            min = Math.min(min, memo(n, s, m - 1, tar, dp) + 1); // insert
            min = Math.min(min, memo(n - 1, s, m, tar, dp) + 1); // delete
            min = Math.min(min, memo(n - 1, s, m - 1, tar, dp) + 1); // replace
        }
        return dp[n][m] = min;
    }
    
}
------------------
// SAame code old
	
class Solution {
    
    public int minDistance(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        
        Integer[][] dp = new Integer[len1 + 1][len2 + 1];
        return minDistance_Memo(len1, len2, s1, s2, dp);
    }
    
    public static int minDistance_Memo(int len1, int len2, String s1, String s2, Integer[][] dp) {
        
        if(len1 == 0 || len2 == 0) { //len1 -> exhaust -> insert | len2 -> exhaust -> delete 
           
            // Agar len2 = 0 ho gaya toh len1 me jitne characters bachenge sabko delete kar denge 
            // => delete krne me jitne number of operations hoga wo len1 k equal hoga
            
            // Agar len1 = 0 ho gaya toh len2 me jitne characters bachenge sabko insert kar denge lenge len1 me 
            // => insert krne me jitne number of operations hoga wo len2 k equal hoga
            return dp[len1][len2] = (len1 == 0 ? len2 : len1);
        }
        
        if(dp[len1][len2] != null) return dp[len1][len2];
        
        if(s1.charAt(len1 - 1) == s2.charAt(len2 - 1)) { // Agar si == sj hai toh yahi se min milega
            return dp[len1][len2] = minDistance_Memo(len1 - 1, len2 - 1, s1, s2, dp);    
        }
        else { // Else wale section me hamesha +1 hoga, that means if se ek zyada operation le rahe hai hum
            int insert = minDistance_Memo(len1, len2 - 1, s1, s2, dp);
            int delete = minDistance_Memo(len1 - 1, len2, s1, s2, dp);
            int replace = minDistance_Memo(len1 - 1, len2 - 1, s1, s2, dp);
            return dp[len1][len2] = Math.min(replace, Math.min(insert, delete)) + 1;
        }
    }
    
}

------------------------------------------------------------------------------------------------------------
	
// Tabulation

class Solution {
    
    //NOTE : Target string is s2 => s1 ko s2 me convert karna hai
    
    public int minDistance(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        
        Integer[][] dp = new Integer[len1 + 1][len2 + 1];
        return minDistance_Tab(len1, len2, s1, s2, dp);
    }
    
    public static int minDistance_Tab(int LEN1, int LEN2, String s1, String s2, Integer[][] dp) {
        
        for(int len1 = 0; len1 <= LEN1; len1++) {
            for(int len2 = 0; len2 <= LEN2; len2++) {
                if(len1 == 0 || len2 == 0) { //len1 -> exhaust -> insert | len2 -> exhaust -> delete 

                    // Agar len2 = 0 ho gaya toh len1 me jitne characters bachenge sabko delete kar denge 
                    // => delete krne me jitne number of operations hoga wo len1 k equal hoga

                    // Agar len1 = 0 ho gaya toh len2 me jitne characters bachenge sabko insert kar denge lenge len1 me 
                    // => insert krne me jitne number of operations hoga wo len2 k equal hoga
                    dp[len1][len2] = (len1 == 0 ? len2 : len1);
                    continue;
                }

                if(s1.charAt(len1 - 1) == s2.charAt(len2 - 1)) {
                    dp[len1][len2] = dp[len1 - 1][len2 - 1];   // minDistance_Memo(len1 - 1, len2 - 1, s1, s2, dp);    
                }
                else {
                    int insert = dp[len1][len2 - 1];      // minDistance_Memo(len1, len2 - 1, s1, s2, dp);
                    int delete = dp[len1 - 1][len2];      // minDistance_Memo(len1 - 1, len2, s1, s2, dp);
                    int replace = dp[len1 - 1][len2 - 1]; // minDistance_Memo(len1 - 1, len2 - 1, s1, s2, dp);
                    dp[len1][len2] = Math.min(replace, Math.min(insert, delete)) + 1;
                }
            }
        }
        return dp[LEN1][LEN2];
    }
    
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Follow up qusetion: 
// Minimum cost When each operation of insert, delete, replace costs x rupee]

// Memoization

class Solution {
    
    public int minDistance(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();

        // Folow up : Cost of Insert, Delete, Replace  
        int[] cost = {2, 5, 3}; // {Insert, Delete, Replace}			// Given Cost
        
        Integer[][] dp = new Integer[len1 + 1][len2 + 1];
        return minDistance_Memo(len1, len2, s1, s2, cost, dp);
    }
    
    public static int minDistance_Memo(int len1, int len2, String s1, String s2, int[] cost, Integer[][] dp) {
        
        if(len1 == 0 || len2 == 0) { //len1 -> exhaust -> insert | len2 -> exhaust -> delete 
           
            // Agar len2 = 0 ho gaya toh len1 me jitne characters bachenge sabko delete kar denge 
            // => delete krne me jitne number of operations hoga wo len1 k equal hoga
            
            // Agar len1 = 0 ho gaya toh len2 me jitne characters bachenge sabko insert kar denge lenge len1 me 
            // => insert krne me jitne number of operations hoga wo len2 k equal hoga
            
            // Incase len1 == len2 == 0 hai, 0 * something => 0, so 0 cost 
            return dp[len1][len2] = (len1 == 0 ? len2 * cost[0] : len1 * cost[1]); 	 // Insert OR Delete cost will be "multiplied" accordingly
            
        }
        
        if(dp[len1][len2] != null) return dp[len1][len2];
        
        if(s1.charAt(len1 - 1) == s2.charAt(len2 - 1)) {
            return dp[len1][len2] = minDistance_Memo(len1 - 1, len2 - 1, s1, s2, cost, dp);    
        }
        else {
            int insert = minDistance_Memo(len1, len2 - 1, s1, s2, cost, dp);
            int delete = minDistance_Memo(len1 - 1, len2, s1, s2, cost, dp);
            int replace = minDistance_Memo(len1 - 1, len2 - 1, s1, s2, cost, dp);
            return dp[len1][len2] = Math.min(replace + cost[2], Math.min(insert + cost[0], delete + cost[1])); 		// Now, min will be selected as per the cost 
        }
    }
    
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
