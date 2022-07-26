// https://leetcode.com/problems/rectangle-overlap/

class Solution {
    
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        
        // Bottom left
        int firstX1 = rec1[0];
        int firstY1 = rec1[1];
        
        // Top left
        int firstX2 = rec1[0];
        int firstY2 = rec1[3];
        
        // Top right
        int firstX3 = rec1[2];
        int firstY3 = rec1[3];
        
        // Bottom right
        int firstX4 = rec1[2];
        int firstY4 = rec1[1];
        
        //================================
        
        // Bottom left
        int secX1 = rec2[0];
        int secY1 = rec2[1];
        
        // Top left
        int secX2 = rec2[0];
        int secY2 = rec2[3];
        
        // Top right
        int secX3 = rec2[2];
        int secY3 = rec2[3];
        
        // Bottom right
        int secX4 = rec2[2];
        int secY4 = rec2[1];
        
        /*
            Cases :-
            Compare y-coordinates
            Case 1 : overlappingFromTop(firstY1, secY2); // First upar, sec niche
            Case 2 : overlappingFromBottom(firstY2, secY1); // First niche, sec upar

            Compare x-coordinates
            Case 3 : overlappingFromLeft(firstX3, secX1); // First left, sec right
            Case 4 : overlappingFromRight(firstX1, secX3); // First right, sec left 
        */
        
        return isOverlapping(firstY1, secY2, firstY2, secY1, firstX3, secX1, firstX1, secX3);
        
    }
    
    public boolean isOverlapping(int firstY1, int secY2, int firstY2, int secY1, int firstX3, int secX1, int firstX1, int secX3) {
        // non overlapping 
        // First upar, sec niche || First niche, sec upar || First left, sec right || First right, sec left 
        if(firstY1 >= secY2 || firstY2 <= secY1 || firstX3 <= secX1 || firstX1 >= secX3) return false;
        
        return true; // Overlapping
    }
        
}
