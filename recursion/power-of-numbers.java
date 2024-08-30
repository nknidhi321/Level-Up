// https://www.geeksforgeeks.org/problems/power-of-numbers-1587115620/1

// Find N ^ R 

// If b is
// Even : a^b = a^b/2 * a^b/2
// Odd : a^b = a^b/2 * a^b/2 * a

// Example : 
// a^32 = a^16 * a^16
// a^16 = a^8 * a^8
// a^8 = a^4 * a^4
// a^4 = a^2 * a^2
// a^2 = a^1 * a^1
// a^1 = a^0 * a^0 * a


class Solution {
    
    long mod = 1000000007;
        
    long power(int N,int R) {
        if(R == 0) return 1;
        
        long val = power(N, R/2);
        if(R % 2 == 0) {
            return (val * val) % mod;
        }
        else {
            return ((val * val) % mod * N) % mod;     
        }
    }

}
