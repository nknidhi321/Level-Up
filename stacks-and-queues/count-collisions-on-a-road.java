// https://leetcode.com/problems/count-collisions-on-a-road/

// Without stack, just logically, non intuitive solution
// TC : O(n), SC : O(1)
class Solution {
    
    public int countCollisions(String directions) {
        int n = directions.length();
        int start = 0;
        int end = n - 1;
        
        // Remove all the 'L' cars at the beginning and all the 'R' cars at the end
        // Because they will never collide
        while(start < n) {
            char dir = directions.charAt(start);
            if(dir == 'L') start++;
            else break;
        }
        while(end >= 0) {
            char dir = directions.charAt(end);
            if(dir == 'R') end--;
            else break;
        }
        
        // Now, Count the remaining cars in between
        // Given :
        // When two cars moving in opposite directions collide with each other, 
        // the number of collisions increases by 2 => Count both the cars, so +1 +1
        // When a moving car collides with a stationary car, 
        // the number of collisions increases by 1 => Count the colliding car, so +1
        int collision = 0;
        for(int i = start; i <= end; i++) {
            char dir = directions.charAt(i);
            if(dir != 'S') {  // Skip the stationary, and count the rest
                collision++;
            }
        }
        return collision;
    }
    
}

// -----------------------------------------------------------------------------------
// Using Stack, Intuitive
// TC : O(n), SC : O(n)

/*
In the case LLRR : 
<- <- -> ->
 L  L  R  R 
 
there will be no collisions since two cars are moving towards Left and two cars moving towards right


In the case RRRRLLL : 
->  ->  -> ->   <- <- <- 
R    R   R  R   L  L  L
0    1   2  3   4  5  6

 Cars 3 and 4 collide and become stationary (S), since cars 0, 1, 2 are moving towards this stationary car so they will all 
 collide and car 5 and 6 are moving towards the same stationary card from the left direction so even they will collide and 
 become stationary (S). We will store cars 0,1,2,3 in stack and when current car is driving towards left we pop 3 and change 
 the current car’s state to stationary and pop rest previous cars driving towards it from the right direction and calculate the 
 collision value. 


In the case RSLRL: 

-> |  <-  -> <-
R  S  L    R  L 
0  1  2    3  4

Car 0 collides with the stationary car 1, car 2 collides with the stationary car 1 and cars 3 and 4 collide with each other. 

*/


class Solution {
    
    public int countCollisions(String directions) {
        int n = directions.length();
        Stack<Character> stack = new Stack<>();
        int collision = 0;
        
        for(int i = 0; i < n; i++) {
            char currDirection = directions.charAt(i);
            if(i == 0) {
                stack.push(currDirection);
                continue;
            }
            
            // There are only 2 colliding conditions (R-> <-L) (S <-L)
            if(!stack.isEmpty() && stack.peek() == 'R' && currDirection == 'L') {
                collision += 2;
                currDirection = 'S';
                stack.pop();
            }
            else if(!stack.isEmpty() && stack.peek() == 'S' && currDirection == 'L') {
                collision += 1;
                currDirection = 'S';
            }
            
            // Now, since you have collied all the R's before you if exists will collide, (R->R->R->S)
            // Note : This case (S<-L<-L<-L) will be handled in elseif above on each iteration
            while(!stack.isEmpty() && stack.peek() == 'R' && currDirection == 'S') {
                collision += 1;
                stack.pop();
            }
            
            stack.push(currDirection);
        }
        return collision;
    }
    
}
