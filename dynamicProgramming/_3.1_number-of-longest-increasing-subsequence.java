// https://leetcode.com/problems/number-of-longest-increasing-subsequence/

class Solution {
    
    public int findNumberOfLIS(int[] arr) {
        int n = arr.length;
        
        int[] dp = new int[n]; // Mere pe khatam hone wala longest LIS ka length
        int maxLIS_len = 0;
        
        int[] count = new int[n]; // Mere pe khatam hone wala longest LIS's ka count
        int maxLIS_count = 0;
        
        for(int i = 0; i < n; i++) {
            dp[i] = count[i] = 1;
            for(int j = i - 1; j >= 0; j--) {
                if(arr[j] < arr[i]) { // Khud se chote elements pe jaao
                    // Mere se zyada len ka mil raha then replace
                    if(dp[j] + 1 > dp[i]) { // Check mere se chote ele's k dp pe, kya apne pe khatm hone wala +1 mere naam ka kar k, mere dp se zyda len bna paa rahe hai   
                        dp[i] = dp[j] + 1; // Agar aisa hai toh mai max se max len bnana chahti hu, so update your dp
                        count[i] = count[j]; // Unke len k he piche lgoge so, wo jitna count bna rahe hai, unke pice lg k tum v utna he bnaoge
                        // Jo mere se chote honge unke piche chipak k mai utna he LIS count bna paaungi
                    }
                    // Mere se "chote len wale +1" kar k, mere len ka mil raha then update *
                    else if(dp[j] + 1 == dp[i]) { // Suppose i pe 5 len hai, and j pe 4 len hai, so kya 4 + 1 == 5 hai 
                        count[i] += count[j]; // Kya mai 4 len k piche lg sakti hu ?? yess then update your count
                        // j aur ghata, suppose aur mil gaya 4 len ka jo 4 + 1 == 5 bna raha ho, so upadte your count
                        // Tum pe khatam hone wala 5 len ka kitna LIS bna paa rahe ho ?? That's why count[i] += count[j] kar k update kar rahe 
                    }
                }
            }
            
            // Cal. overall maxLIS_len and maxLIS_count of that length
            if(dp[i] > maxLIS_len) {
                maxLIS_len = dp[i];
                maxLIS_count = count[i];
            }
            else if(dp[i] == maxLIS_len) { // * "Exactly mere len k" 
                maxLIS_count += count[i];  // Kisi aur ele pe 5 len pe khatam hone wala ka sum rakhi hu, 
                // Ab ek aur nayaa element aaya joki 5 len he bna raha, so that's why jo pehle tha usi me += kar k add karo 
            }
            
        }
        return maxLIS_count;        
    }
    
}
