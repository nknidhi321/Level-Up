// https://leetcode.com/problems/minimum-speed-to-arrive-on-time/

class Solution {
    
    public int minSpeedOnTime(int[] piles, double hours) {
        int n = piles.length;
        int low = 1; // Min speed can be one
        int high = (int)1e7; // Given in ques.
        int probableAns = -1;
            
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(isPossible(mid, hours, piles)) {
                high = mid - 1;
                probableAns = mid;
            }
            else {
                low = mid + 1;
            }
        }
        return probableAns;
    }
    
    public static boolean isPossible(int fixedSpeed, double hours, int[] piles) {
        int actualHoursSum = 0; // set actualHoursSum at 0, kuki yaha sum nikal rahe hai, count nahi, so don't set at 1
        for(int i = 0; i < piles.length - 1; i++) {
            int hour = (int)Math.ceil(piles[i] / (fixedSpeed * 1.0)); //Time = Distance/Speed //Remember to take ceil val
            if(actualHoursSum + hour <= hours) {
                actualHoursSum += hour;
            }
            else return false; // Jitni fixedSpeed pe actualHoursSum lag rahe hai, wo given hours se zyada ho gaya
        }
        
        double hour = piles[piles.length - 1] / (fixedSpeed * 1.0); //Time = Distance/Speed //Remember to take ceil val
        return actualHoursSum + hour <= hours;
    }
    
}
