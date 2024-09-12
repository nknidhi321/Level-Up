/*
Given a string containing only letters (a-z), find the sum of all the numbers which are also present as words.
Do this in linear time, O(n) where n is the length of the string.

Eg : rwffonewofnwthreeonefourfrnwnminusonesix = 1 + 314 - 16 = 299
Note the numbers in the string : rwffonewofnwthreeonefourfrnwnminusonesix
*/

public class NumberWordSum {
    public static void main(String[] args) {
        String s = "rwffonewofnwthreeonefourfrnwnminusonesix";
        int idx = 0, sum = 0, digit = 0;
        boolean minus = false;

        while (idx < s.length()) {
            if (idx + 3 <= s.length() && s.substring(idx, idx + 3).equals("one")) {
                digit = digit * 10 + 1;
                idx += 2;
            } else if (idx + 3 <= s.length() && s.substring(idx, idx + 3).equals("two")) {
                digit = digit * 10 + 2;
                idx += 2;
            } else if (idx + 5 <= s.length() && s.substring(idx, idx + 5).equals("three")) {
                digit = digit * 10 + 3;
                idx += 4;
            } else if (idx + 4 <= s.length() && s.substring(idx, idx + 4).equals("four")) {
                digit = digit * 10 + 4;
                idx += 3;
            } else if (idx + 4 <= s.length() && s.substring(idx, idx + 4).equals("five")) {
                digit = digit * 10 + 5;
                idx += 3;
            } else if (idx + 3 <= s.length() && s.substring(idx, idx + 3).equals("six")) {
                digit = digit * 10 + 6;
                idx += 2;
            } else if (idx + 5 <= s.length() && s.substring(idx, idx + 5).equals("seven")) {
                digit = digit * 10 + 7;
                idx += 4;
            } else if (idx + 5 <= s.length() && s.substring(idx, idx + 5).equals("eight")) {
                digit = digit * 10 + 8;
                idx += 4;
            } else if (idx + 4 <= s.length() && s.substring(idx, idx + 4).equals("nine")) {
                digit = digit * 10 + 9;
                idx += 3;
            } else if (idx + 4 <= s.length() && s.substring(idx, idx + 4).equals("zero")) {
                digit = digit * 10 + 0;
                idx += 3;
            } else if (idx + 5 <= s.length() && s.substring(idx, idx + 5).equals("minus")) {
                if (minus) {
                    digit = -digit;
                }
                sum += digit;
                digit = 0;
                minus = true;
                idx += 4;
            } else {
                if (minus) {
                    digit = -digit;
                }
                sum += digit;
                digit = 0;
                minus = false;
            }
            idx++;
        }

        if (digit != 0) {
            if (minus) {
                digit = -digit;
            }
            sum += digit;
        }

        System.out.println(sum);
    }
}
