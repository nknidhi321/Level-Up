// https://www.lintcode.com/problem/848/description
// Similar to cow problem, but usme dis max rakhna tha, isme dis min krna h
// A bit hard due to double and identifying ans can lie between mid, mid + 1 or mid - 1, mid

public class Solution {
  
    public double minmaxGasDist(int[] stations, int k) {
        int n = stations.length;
        // Arrays.sort(stations); // If the ip was not sorted, but given in sorted order
        double max = Integer.MIN_VALUE;
        for(int i = 0; i < n - 1; i++) {
            max = Math.max(max, stations[i + 1] - stations[i]);
        }

        double lo = 0;
        double hi = max;
        double ans = 0.0;
        while(lo < hi) { // hi - lo > 1e-6 Agar ye condition use krte toh 1e-6 k precision k baad loop khud he break ho jaata, break lgane ki zaroorat nahi parti
            double  mid = lo + (hi - lo) / 2.0;
            if(!isPossible(stations, mid, k)) {
                //System.out.println(hi +" false " + mid);
                ans = mid; // Possible hai => minimize karna hai => left me jaao 
                if(hi == mid) break;                                            // Infinite loop break rne k liye
                hi = mid; // Possible nahi hai => left me jaao                  NOTE : Yaha [mid, mid - 1] k bich v ans lie kr sakta hai, so make hi = mid
            }
            else {
                //System.out.println(lo + " true " + mid);
                if(lo == mid) break;                                            // Infinite loop break rne k liye
                lo = mid; // right me jaao                                      NOTE : Yaha [mid, mid + 1] k bich v ans lie kr sakta hai, so make lo = mid
            }
        }
        return ans;
    }

    public boolean isPossible(int[] stations, double mid, int k) {
        int count = 0;
        int i = 1;
        double item = stations[0];
        while(i < stations.length) {
            if(item + mid < stations[i]) {
                item = item + mid;
                count++;
            }
            else {
                item = stations[i];
                i++;
            }
        }
        return count <= k ? false : true; // Kam bnde place ho paaye, dis ghatao, left me jaao
    }

}
