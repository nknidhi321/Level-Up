//https://www.lintcode.com/problem/779/description

public class Solution {
  
    public List<String> generateAbbreviations(String word) {
        // Write your code here
        List<String> list = new ArrayList<>();
        generateAbbreviations_Util(0, 0, word, "", list);
        return list;
    }

    public static void generateAbbreviations_Util(int idx, int count, String word, String ans, List<String> list) {
        if(idx == word.length()) {
            if(count > 0) {
                //Anaa chahta h
                //ans + lagatar kitno ne na kaha uska count
                //Yaha sirf abtak nahi aane wala count include hoga
                list.add(ans + count);
                return;
            }
            //Jinko aana tha wo pichle call me he khud ko add kar chuke hai
            list.add(ans);
            return;
        }

        //Anaa chahta h
        //ans + lagatar kitno ne na kaha uska count + hum khud will be the op
        String wantsToCome = "";
        if(count > 0) wantsToCome = ans + count + word.charAt(idx);

        //Agar humse pehle 0 logo ne aane k liye mana kiya h toh count include nahi karenge op me 
        else wantsToCome = ans + word.charAt(idx);
        generateAbbreviations_Util(idx + 1, 0, word, wantsToCome, list);

        //Lagatar na kehne walo me sirf count increase hoga
        //Jo log na kehte h uske character ko include nahi karenge,
        //uske jagah count badha k bhejenge.
        generateAbbreviations_Util(idx + 1, count + 1, word, ans, list);
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

//Same code with ternary operator

public class Solution {

    public List<String> generateAbbreviations(String word) {
        List<String> list = new ArrayList<>();
        generateAbbreviations_Util(0, 0, word, "", list);
        return list;
    }

    public static void generateAbbreviations_Util(int idx, int count, String word, String ans, List<String> list) {
        if(idx == word.length()) {
            if(count > 0) {
                list.add(ans + count);
                return;
            }
            list.add(ans);
            return;
        }

        char ch = word.charAt(idx);
        generateAbbreviations_Util(idx + 1, 0, word, ans + ((count == 0) ? "" : count) + ch, list);
        generateAbbreviations_Util(idx + 1, count + 1, word, ans, list);
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
