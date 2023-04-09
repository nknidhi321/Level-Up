// https://practice.geeksforgeeks.org/problems/the-celebrity-problem/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article

// Put all N people in stack and find out the potential celebrity by elimination approach
// 2 bande pop karo, and eliminate it by celeb rule, jo v potential celeb h usko wapas stack me daal do
// Ab jo last me bachega wo potential celebrity hai.
// Ab isko confirm krne k liye, celeb k liye row and col. pe iterate kar k celeb k rule ko confirm karo
// If all is application return 1(potential celeb is the real celeb), else return -1(No celeb)


/*
    Definition :-
    ----------
    Celebrity kisi ko nahi janta
    Celebrity ko sab jaante hai
    
    
    Observation :-
    -----------
    1)  Ek se zyada celebrity nahi ho saktey. Why ??
        
        Suppose 2 celebrity hai, mtlb celebrity v ek dusre ko jante honge
        Par celebrity ki definition he ye hai ki celebrity kisi ko nahi jante,
        toh wo ek dusre ko kaise jaan saktey hai, violating rule.
        Hence, there cannot be more than 1 celebrity.
        
    2)  There can be 0 celebrity, "atleast" koi ek banda hai jo celebrity ko nahi jaanta

    
    Intuition :-
    ---------
    
    Elimination game khelo, kon kon celebrity nahi ho sakta.
    At last stack will consist of only potential celebrity.
    
    Sabko stack me daalo, 2 jan ko pop karo(say a & b), a se poocho kya tum b ko jantey ho ?
    If yes, b can be the potential celebrity, put b back to stack.
    If no, b can never be celebrity, put a back to stack.
    
    At last, jo v bachega, uske row & column pe jaake check kar lo
    ki wo potential celebrity, actual celebrity hai ya nahi ?
*/

class Solution { 
 
    int celebrity(int M[][], int n) {
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < n; i++) { // Push everyone in stack, sab potentialCelebrity hai
            stack.push(i);
        }
        
        while(stack.size() != 1) { // Tab tak check karo jab tak sirf 1 potentialCelebrity nahi bach jaata
            int a = stack.pop();
            int b = stack.pop();
            
            if(M[a][b] == 1) stack.push(b); // a knows b, b can be potential celebrity
            if(M[a][b] == 0) stack.push(a); // a do not know b, a can be potential celebrity
        }
        
        // Ab sirf 1 potentialCelebrity bacha hai, uske row and column pe jaake check kar lo
        // If it is actually a Celebrity or not ??
 
        int pC = stack.pop(); // pC => potentialCelebrity
        
        // Check columns, saara 0 hona chahiye
        // Check rows, saare 1 hone chahiye except diagonal crossing cell
        for(int idx = 0; idx < n; idx++) {
            if((idx != pC) && (M[pC][idx] != 0 || M[idx][pC] != 1)) {
                return -1;
            }
        }
        
        return pC;
    }
}
