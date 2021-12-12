//https://leetcode.com/problems/koko-eating-bananas/

/*  
    IMPORTANT :
    ---------
    Low = 1 pe he set hoga and actualHoursSum calculation me ceil val lena hai 
*/

class Solution {
    
    public int minEatingSpeed(int[] piles, int hours) {
        int n = piles.length;
        
        // Finding max
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            max = Math.max(max, piles[i]);
        }
        
        // low = 1 hoga, don't make the mistake to set it at min of piles, 
        // Because tum speed ka low set kar rahe ho, distance(piles) ka minimum nahi.
        int low = 1; 
        
        // Test case which failed if you set low = min,
        // piles = [312884470], hours = 312884469 => expectedSpeed = 2, output = 312884470(Because you had set low at min)
        // So set low Speed at 1;
        
        
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

/*
    Same as above except for Calculating ceil by Ourself, instead of Math.ceil
    NOTE:  Make sure to set low at 1 and not at 0, else you might get / by 0 exception in isPossible() method
*/

class Solution {
    
    public int minEatingSpeed(int[] piles, int hours) {
        int n = piles.length;
        
        // Finding max
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            max = Math.max(max, piles[i]);
        }
        
        // low = 1 hoga, don't make the mistake to set it at min of piles, 
        // Because tum speed ka low set kar rahe ho, distance(piles) ka minimum nahi.
        int low = 1; 
        
        // Test case which failed if you set low = min,
        // piles = [312884470], hours = 312884469 => expectedSpeed = 2, output = 312884470(Because you had set low at min)
        // So set low Speed at 1;
        
        
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
        
         //Time = Distance/Speed //Remember to take ceil val
        for(int i = 0; i < piles.length; i++) {
            int rem = piles[i] % fixedSpeed;
            int quo = piles[i] / fixedSpeed;
            
            // quo + 1 => Kuch bananas bach gaye uske liye total 1 hour spend karoge
            // Ex : 22 bananas / 4 speed => totalTime = 5 + 1 
            // because 22 % 4 = 2, ye jo 2 banana bach gaye usko khane me extra 1 hour lagega toh +1  
            int hour = (rem == 0) ? quo : quo + 1; 
            
            if(actualHoursSum + hour <= hours) {
                actualHoursSum += hour;
            }
            else return false; // Jitni fixedSpeed pe actualHoursSum lag rahe hai, wo given hours se zyada ho gaya
        }
        
        return true;
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
