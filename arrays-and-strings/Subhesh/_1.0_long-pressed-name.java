// https://leetcode.com/problems/long-pressed-name/ 

/*
    The idea is to check if the current ith and jth charcacter is equal then move to check for the next ith and jth character,
    if characters are different then check if it is mistyped due to faulty keyboard,
    to check for mistyped characters you need to check if the jth character is equal to (j-1)th  character,
    if it is equal then just increase j (beacause i is already pointing to the next valid character of the name).

    Space Complexity : O(1)
    Time Complexity : O(N), where N is typed string's length
*/


class Solution {
    
    public boolean isLongPressedName(String name, String typed) {
        
        // Pehle char k liye manually check kar lo 
        // kuki typed hamesha 1st idx se he check hoga while loop me
        if(name.charAt(0) != typed.charAt(0)) return false;  
        
        int start1 = 1; int start2 = 1; // 0th pos ka kaam ho gaya, ab 1st pos se kaam shuru karo
        
        while(start1 < name.length() && start2 < typed.length()) {
            int ch1 = name.charAt(start1);
            int ch2 = typed.charAt(start2);
            
            // Agar dono char equal hai toh dono aage badh jaao
            if(ch1 == ch2) {
                start1++; start2++;
            }
            else { // Agar equal nahi hai toh typed se bolo apne pichle char ko check kare
                if(start2 != 0 && typed.charAt(start2 - 1) == ch2) start2++; // Agar equal nikla toh start2 ko aage badhao
                else return false; // Agar equal nahi pichle char k mtlb kuch nahi ho sakta ab tumhara
            }
        }
        
        if(start1 < name.length()) return false; // Agar typed he khatam ho gaya toh koi scene nai h answer milne ka
        
        while(start2 < typed.length()) { // Agar typed bacha h aur wo pichle chars k equal hai tvi answer bn paaega otherwise nai
            if(start2 != 0 && typed.charAt(start2 - 1) != typed.charAt(start2)) return false;
            else start2++;
        }
        
        return true;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
