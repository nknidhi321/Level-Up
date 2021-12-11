//https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
//Binary Search on Answer

//Each day, we load the ship with packages on the conveyor belt => Kisi v din tum khali nahi jaaoge

class Solution {
    
    public int shipWithinDays(int[] weights, int days) {
        
        int n = weights.length;
        
        // Min capacity max of weights hoga he kuki kisi din us max weight of array ko toh carry karna he hai,
        // So set your low at max of weights and max capacity can be sum of all weights so set hight at sum of weights
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for(int i = 0; i < n; i++) {
            max = Math.max(max, weights[i]);
            sum += weights[i];
        }
        
        int low = max;
        int high = sum;
        int probableAns = -1;
        
        // Capacity pe binary search lagega.
        // Mid == capacity, Question is capacity = mid rakh k kya aap x days me apna kaam kar loge ? 
        
        while(low <= high) {
            int mid = low + (high - low) / 2;
            
            // Agar din kam ya equal hai toh => Ship ki capacity ghata do taaki zyada din lage => move towards left
            if(isPossible(mid, days, weights)) {
                high = mid - 1;
                probableAns = mid;
            }
        
            // Agar din zyada lg raha hai toh => Ship ki capacity badha do taaki din kam lage => move towards right
            else {
                low = mid + 1;
            }
        }
        return probableAns;
    }
    
    public static boolean isPossible(int capacity, int days, int[] weights) {
        int sum = 0;
        int actualDays = 1; // set actualDays at 1, kuki 1 element toh hoga he array me
        for(int i = 0; i < weights.length; i++) {
            if(sum + weights[i] <= capacity) {
                sum += weights[i];
            }
            else { // Agar sum exceed ho gaya capacity se toh actualDays ko +1 kar do
                sum = weights[i];
                actualDays++;
            }
            
            if(actualDays > days) return false; //Jitne days tum soch kr aaye the, us se zyada din lg gaye us set kiye huye capacity pe
        }
        return true;
    }
    
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
