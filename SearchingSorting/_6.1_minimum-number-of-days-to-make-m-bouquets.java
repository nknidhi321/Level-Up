// https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/
// Similar to Book Allocation

class Solution {
    
    public int minDays(int[] arr, int m, int k) {
        int n = arr.length;
        int min = (int)1e9;
        int max = -(int)1e9;
        for(int i = 0; i < n; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }
        
        // si : min sbse min day lgega flower ko bloom hone me
        // ei : max sbse max day lgega flower ko bloom hone me
        int si = min, ei = max, probAns = -1;
        while(si <= ei) {
            int probDays = si + (ei - si) / 2;
            if(isPossible(arr, probDays, m, k)) {
                System.out.println(probDays);
                probAns = probDays;
                ei = probDays - 1;
            }
            else {
                si = probDays + 1;
            }
        }
        return probAns;
    }
    
    public static boolean isPossible(int[] arr, int probDays, int m, int k) {
        int n = arr.length;
        int ei = 0, count = 0;
        while(ei < n) {
            int tempk = k;
            while(ei < n && tempk > 0) {
                if(arr[ei] > probDays) { // Agar probDays se zyada ho tum 
                    ei++; // toh tumhare baad se ans bnana shuru karo, 
                    break; // kuki tumko lekar kvi v koi answer nai bna paaega
                }
                tempk--; // k different type of flowers le rahi hu ensuring that  
                ei++;
            }
            if(tempk == 0) count++; // k different type of flowers le liye, so dusra bouquet bn k tayar h
            if(count >= m) return true; // m bouquets bnane hai, kya bn gaye ??
        }
        return false;
    } 
    
}
