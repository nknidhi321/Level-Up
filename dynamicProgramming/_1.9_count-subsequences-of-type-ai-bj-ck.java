// https://practice.geeksforgeeks.org/problems/count-subsequences-of-type-ai-bj-ck4425/1

class Solution {
    
    public int fun(String s) {
        int n = s.length(), mod = (int)1e9 + 7;
        int aCount = 0, bCount = 0, cCount = 0, emptyCount = 1;

        for(int i = 0; i < n; i++) {  // Bich k state me reh k socho
            if(s.charAt(i) == 'a') {
                aCount = ((aCount + emptyCount) % mod + aCount) % mod; // nahiAaungi + emptyKPiche + aaungi
            }
            else if(s.charAt(i) == 'b') {
                bCount = ((bCount + aCount) % mod + bCount) % mod; // nahiAaungi + aKPiche + aaungi
            }
            else {
                cCount = ((cCount + bCount) % mod + cCount) % mod; // nahiAaungi + bKPiche + aaungi
            }
        }
        return cCount;
    }
    
}
