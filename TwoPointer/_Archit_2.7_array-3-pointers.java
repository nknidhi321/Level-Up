// https://www.interviewbit.com/problems/array-3-pointers/

// TC : O(len A + len B + len C)

public class Solution {
     
    public int minimize(final List<Integer> A, final List<Integer> B, final List<Integer> C) {
        int min = Integer.MAX_VALUE;
        int i = 0, j = 0, k = 0;
        
        while(i < A.size() && j < B.size() && k < C.size()) {
            int a = A.get(i);
            int b = B.get(j);
            int c = C.get(k);

            // a, b , c tino me se jiska v value sbse chota hoga wahi har bar maximum difference create kar raha hoga
            // So, iss difference ko reduce karne k liye ya toh a, b, c me se sbse minimum ko increase karo ya fir sbse maximum ko decrement karo
          
            // Since i, j, k ko 0 se start kar rahe h islye yaha minimum(a, b, c) ko badahaya jaega.
 
            // If you intialize i,j,k with 0 and still want to decrement the maximum(a, b, c) what happens ??
            // i, j, k tino 0th idx pe hai and max(a, b , c) ko decrement karne jaaoge, toh ek he baar me out of boundary chala jaaega, so poora array iterate nai ho paaya.
            // Hence, if you want to decrement the max element start the loop from n - 1 not from 0
          
            int first = Math.abs(a - b);
            int sec = Math.abs(b - c);
            int third = Math.abs(c - a);

            min = Math.min(min, Math.max(first, Math.max(sec, third)));
            
            // a, b, c me se jo chota h usko aage badha do, kuki difference tvi minimum aagega jab 2 numbers kaafi karib honge
            // Ans since array is sorted in ascending order so we are moving towards right
            
            if(a <= b && a <= c) i++;
            else if(b <= a && b <= c) j++;
            else k++;
        } 
        return min;
    }   
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Brute Force
// TLE
// O(N ^ 3)

public class Solution {
     
    public int minimize(final List<Integer> A, final List<Integer> B, final List<Integer> C) {
        
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < A.size(); i++) {
            for(int j = 0; j < B.size(); j++) {
                for(int k = 0; k < C.size(); k++) {
                    int first = Math.abs(A.get(i) - B.get(j));
                    int sec = Math.abs(B.get(j) - C.get(k));
                    int third = Math.abs(C.get(k) - A.get(i));
                    min = Math.min(min, Math.max(first, Math.max(sec, third)));
                }
            }
        }      
        return min;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
