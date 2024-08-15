// https://www.geeksforgeeks.org/reduce-the-string-by-removing-k-consecutive-identical-characters/

public class Main {

    public static String reducedString(String s, int k) {
        StringBuilder ans = new StringBuilder();
        
        int i = 0;
        while(i < s.length()) {
            int j = i;
            StringBuilder temp = new StringBuilder();
            while(j < s.length() && s.charAt(i) == s.charAt(j)) {
                temp.append(s.charAt(i));
                j++;
            }
            if(j - i != k) ans.append(temp);
            i = j;
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        int k = 2;
        String str = "geeksforgeeks";
        String reducedString = reducedString(str, k);
        System.out.println("Reduced string: " + reducedString);
    }
}

