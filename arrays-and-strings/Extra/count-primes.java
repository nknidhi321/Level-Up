// https://leetcode.com/problems/count-primes/

class Solution {
    
  public int countPrimes(int n) {
        if(n == 0 || n == 1) return 0;
      
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);                 // Sabko bol do tum prime ho
        isPrime[0] = isPrime[1] = false;            // 0 and 1 prime nahi hai
      
        for(int i = 2; i * i <= n; i++) {           // 2 se sqrt. n tak he check karo kuki f1 * f2 <= n
            if(isPrime[i]) {                        // Check if the number is prime, usko chor k, 
                for(int j = 2 * i; j < n; j+=i) {   // uske saare factors ko,
                    isPrime[j] = false;             // non Prime mark kar k aa jaao
                }
            }
        }
      
        int count = 0; 
        for(int i = 0; i < n; i++) {                // Ab boolean array pe iterate karenge 
            if(isPrime[i]) {                        // And jo v True hoga => Prime hoga
                count++;                            // sabko count karte jaaenge
            }
        }
        return count;                               // return count
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
