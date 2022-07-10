// https://www.geeksforgeeks.org/minimum-maximum-values-expression/

// Works for all digits number
// Separated opertor and operand and used cut strategy
// arr = [1,2,3,4,5]
// op = "+*+*"

// Consider only 2 numbers and observe cuts on operator
// Here code is only for max, return pair for both min && max

class HelloWorld {
    public static void main(String[] args) {
        String s = “1+2*3+4*5”;
        System.out.println(MM(s));
    }
    
    public static int MM(String s) {
        String[] arr = s.split("[*+]"); // Segregating numbers
        int n = arr.length;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '*' || s.charAt(i) == '+') sb.append(s.charAt(i)); // Segregating operators
        }
        Integer[][] dp = new Integer[n][n];
        return MM(0, n - 1, arr, sb.toString(), dp);
    }
    
    public static int MM(int si, int ei, String[] arr, String op, Integer[][] dp) {
        if(si == ei) return dp[si][ei] = Integer.parseInt(arr[si]);
        
        if(dp[si][ei] != null) return dp[si][ei];
        
        int max = Integer.MIN_VALUE;
        for(int cut = si; cut < ei; cut++) { // Draw Tree diagram
            int lc = MM(si, cut, arr, op, dp);
            int rc = MM(cut + 1, ei, arr, op, dp);
            
            int myAns = 0;
            if(op.charAt(cut) == '*') myAns = lc * rc; 
            else myAns = lc + rc;
            
            max = Math.max(max, myAns);
        }
        return max;
    } 
    
}

//===================================================================================================================================================================
// This code works only when there is single digit number/operand
// Applied cut strategy on every operator without segregating operator and operand

    public static class pairmm {
        int min = (int) 1e9;
        int max = 0;

        pairmm(int val) {
            this.min = this.max = val;
        }
    }

    public static pairmm evaluateMinMax(pairmm leftRes, pairmm rightRes, char operator) {
        pairmm pair = new pairmm();
        if (operator == '+') {
            pair.min = leftRes.min + rightRes.min;
            pair.max = leftRes.max + rightRes.max;
        } else if (operator == '*') {
            pair.min = leftRes.min * rightRes.min;
            pair.max = leftRes.max * rightRes.max;
        }
        return pair;
    }

    public static pairmm minMax(String str, int si, int ei, pairmm[][] dp) {
        if (si == ei) {
            return dp[si][ei] = new pairmm((str.charAt(si) - '0'));
        }

        if (dp[si][ei] != null)
            return dp[si][ei];

        pairmm myRes = new pairmm();
        for (int cut = si + 1; cut < ei; cut += 2) {
            pairmm leftRes = minMax(str, si, cut - 1, dp);
            pairmm rightRes = minMax(str, cut + 1, ei, dp);
            pairmm pair = evaluateMinMax(leftRes, rightRes, str.charAt(cut));

            myRes.min = Math.min(myRes.min, pair.min);
            myRes.max = Math.max(myRes.max, pair.max);
        }

        return dp[si][ei] = myRes;
    }

    public static void minMax() {
        String str = "1+2*3+4*5";
        int n = str.length();
        pairmm[][] dp = new pairmm[n][n];

        pairmm res = minMax(str, 0, n - 1, dp);

        System.out.println("Min value: " + res.min);
        System.out.println("Max value: " + res.max);
    }

//===================================================================================================================================================================
