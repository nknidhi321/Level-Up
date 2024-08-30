// https://www.geeksforgeeks.org/problems/power-of-numbers-1587115620/1

// Find N ^ R

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
