// https://leetcode.com/problems/x-of-a-kind-in-a-deck-of-cards/
// https://www.youtube.com/watch?v=UvpXInRkZ3Q
// https://www.youtube.com/watch?v=ZtDeDD1VYLk

// GCD will be the total count of individual group member
class Solution {
    
    public boolean hasGroupsSizeX(int[] deck) {
        int[] freq = new int[10001];
        
        for(int i = 0; i < deck.length; i++) {
            freq[deck[i]]++;
        }
        
        int ans = 0; // gcd of any number with 0 is that number itself
        for(int i = 0; i <= 1000; i++) {
            if(freq[i] != 0) {
                ans = gcd(freq[i], ans);
            }
        }
        return ans > 1 ? true : false;
    }
    
    // public int gcd(int n1, int n2) {
    //     while(n2 % n1 != 0) {
    //         int rem = n2 % n1;
    //         n2 = n1;
    //         n1 = rem;
    //     }
    //     return n1;
    // }
    
    public int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a % b);
    }
    
}
