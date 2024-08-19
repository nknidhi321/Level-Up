/*
    Problem
    
    Move ^ is north, move > is east, move v is south and move < is west.
    we have to return whether the given move will form a rectangle or not.
*/

import java.util.HashSet;
import java.util.Set;

public class RectangleCheck {

    public static boolean doesFormRectangle(String moves) {
        // Set to track visited positions
        Set<String> visitedPositions = new HashSet<>();

        // Initial position (0, 0)
        int x = 0, y = 0;
        visitedPositions.add(x + "," + y);

        for (int i = 0; i < moves.length(); i++) {
            char move = moves.charAt(i);

            if (move == '^') {
                y++;
            } else if (move == 'v') {
                y--;
            } else if (move == '>') {
                x++;
            } else if (move == '<') {
                x--;
            } else {
                return false; // Invalid move character
            }
            
            String currentPosition = x + "," + y;

            // Rectangle is detected if the position has been visited before
            if (visitedPositions.contains(currentPosition)) return true;
            visitedPositions.add(currentPosition);
        }

        // If we finish and no rectangle is detected
        return false;
    }

    public static void main(String[] args) {
        String moves = "^>v<";
        System.out.println(doesFormRectangle(moves)); // true
        
        moves = "^>v>>^<<v";
        System.out.println(doesFormRectangle(moves)); // true
        
        moves = "^^>>vv<<";
        System.out.println(doesFormRectangle(moves)); // false
    }
  
}

