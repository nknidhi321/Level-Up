// https://leetcode.com/problems/count-collisions-on-a-road/

// Without stack, just logically

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
