//https://leetcode.com/problems/koko-eating-bananas/

/*  
    IMPORTANT :
    ---------
    Low = 0 pe he set hoga and actualHoursSum calculation me ceil val lena hai 
*/

class Solution {
    
    public int minEatingSpeed(int[] piles, int hours) {
        int n = piles.length;
        
        // Finding max
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            max = Math.max(max, piles[i]);
        }
        
        // low = 0 hoga, don't make the mistake to set it at min of piles, 
        // Because tum speed ka low set kar rahe ho, distance(piles) ka minimum nahi.
        int low = 0; 
        
        // Test case which failed if you set low = min,
        // piles = [312884470], hours = 312884469 => expectedSpeed = 2, output = 312884470(Because you had set low at min)
        // So set low Speed at 0;
        
        
        // Maximum speed max of piles hoga,
        // Agar max of piles pe maxSpeed hoga toh max se kam piles ko us maxSpeed pe toh kha he loge
        // say hours = 1 => speed = max of piles
        int high = max; 
        
        int probableAns = -1;
        
        // Ab within given x hours me minimum speed nikalo
        
        // Speed pe binary search lagega.
        // Mid == fixedSpeed, Question is fixedSpeed = mid rakh k kya aap given x hours me apna kaam kar loge ? 
        
        while(low <= high) {
            int mid = low + (high - low) / 2;
 
            // Agar kisi speed pe tum apna kaam kar le rahe ho,
            // Toh aur lalach dikhao, left me chale jaao, 
            // Kya pta aur kam speed pe tum apna kaam kar paao, 
            // Make sure to store prev probableAns
            if(isPossible(mid, hours, piles)) {
                high = mid - 1;
                probableAns = mid;
            }
        
            // Agar tum apna kaam nahi kar paa rahe ho toh apni speed badahao, go to right
            else {
                low = mid + 1;
            }
        }
        return probableAns;
    }
    
    
    public static boolean isPossible(int fixedSpeed, int hours, int[] piles) {
        
        int actualHoursSum = 0; // set actualHoursSum at 0, kuki yaha sum nikal rahe hai, count nahi, so don't set at 1
        for(int i = 0; i < piles.length; i++) {
            int hour = (int)Math.ceil(piles[i] / (fixedSpeed * 1.0)); //Time = Distance/Speed //Remember to take ceil val
            if(actualHoursSum + hour <= hours) {
                actualHoursSum += hour;
            }
            else return false; // Jitni fixedSpeed pe actualHoursSum lag rahe hai, wo given hours se zyada ho gaya
        }
        
        return true;
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
