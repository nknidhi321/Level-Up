// https://practice.geeksforgeeks.org/problems/box-stacking/1#
// Do checkout the optimization at below

// All 6 permutatations

class Solution {
    
    public static class Box implements Comparable<Box> {
        public int h, w, l;
        
        public Box(int h, int w, int l) {
            this.h = h;
            this.w = w;
            this.l = l;
        }
        
        // Sort on asc. width, if width is same then,
        // Sort on desc. length, just like Russian doll
        public int compareTo(Box o) {  
            if(this.w - o.w == 0) return o.l - this.l;
            else return this.w - o.w;
        }
        
    }
    
    public static int maxHeight(int[] height, int[] width, int[] length, int n) {

        // Each box will form 6 instances given in ques.
        Box[] boxes = new Box[n * 6]; // {h, w, l}
        
        // NOTE : Each box will form 3 effective valid instances is an optimization
        // Even if you take all the 3! = 6 permutations of a box, and generate LIS, that would also work  
        
        for(int i = 0; i < n; i++) { // Keeping all the 6 instances in boxes array of Box type
            boxes[(6 * i) + 0] = new Box(height[i], width[i], length[i]);
            boxes[(6 * i) + 1] = new Box(height[i], length[i], width[i]);
            boxes[(6 * i) + 2] = new Box(width[i], height[i], length[i]);
            boxes[(6 * i) + 3] = new Box(width[i], length[i], height[i]);
            boxes[(6 * i) + 4] = new Box(length[i], width[i], height[i]);
            boxes[(6 * i) + 5] = new Box(length[i], height[i], width[i]);
        }
        
        Arrays.sort(boxes);
        
        // for(int i = 0; i < boxes.length; i++) { // Printing all the effective valid boxes
        //     Box box = boxes[i];
        //     System.out.println(box.h + " " + box.w + " " + box.l);
        // }
        
        
        // NOTE : Here you cannot use NlogN solution, 
        // because that is only used to find the "LIS in terms of length" 
        
        // If you make all the heights as 1 instead of some x height, then this ques. is exactly russian doll 
        
        // Now, perform LIS on box.l => box length 
        // And dp will store mere pe kahatam hone wala LIS ka "max height" 
        int overAllMaxHeight = 0;
        int[] dp = new int[boxes.length]; // Mere pe kahatam hone wala LIS ka max. height  
        for(int i = 0; i < boxes.length; i++) {
            Box currBox = boxes[i];
            
            int maxPrevHeight = 0;
            for(int j = i - 1; j >= 0; j--) { // Apne se piche 
                Box prevBox = boxes[j];
                if(prevBox.l < currBox.l) { // apne se chote length waale pe jaao
                    maxPrevHeight = Math.max(maxPrevHeight, dp[j]); // Max of all the heights find karo
                }
            }
            dp[i] = maxPrevHeight + currBox.h; // Aur unke piche chipak jaao
            
            // dp pe last me iterate k bajay, yahi overAllMaxHeight nikalte chalo
            overAllMaxHeight = Math.max(overAllMaxHeight, dp[i]);
        }
        return overAllMaxHeight;
    }
    
}

//---------------------------------------------------------------------------------------------------------------------------------------

// Optimization 
// Keeping only 3 effective valid instances

class Solution {
    
    public static class Box implements Comparable<Box> {
        public int h, w, l;
        
        public Box(int h, int w, int l) {
            this.h = h;
            this.w = w;
            this.l = l;
        }
        
        // Sort on asc. width, if width is same then,
        // Sort on desc. length, just like Russian doll
        public int compareTo(Box o) {  
            if(this.w - o.w == 0) return o.l - this.l;
            else return this.w - o.w;
        }
        
    }
    
    public static int maxHeight(int[] height, int[] width, int[] length, int n) {

        // Each box will form 3 effective valid instances
        Box[] boxes = new Box[n * 3]; // {h, w, l}
        
        // NOTE : Each box will form 3 effective valid instances is an optimization
        // Even if you take all the 3! = 6 permutations of a box, and generate LIS, that would also work  
        
        for(int i = 0; i < n; i++) { // Keeping all effective valid instances in boxes array of Box type
            boxes[(3 * i) + 0] = new Box(height[i], Math.min(width[i], length[i]), Math.max(width[i], length[i]));
            boxes[(3 * i) + 1] = new Box(length[i], Math.min(width[i], height[i]), Math.max(width[i], height[i]));
            boxes[(3 * i) + 2] = new Box(width[i], Math.min(height[i], length[i]), Math.max(height[i], length[i]));
        }
        
        Arrays.sort(boxes);
        
        // for(int i = 0; i < boxes.length; i++) { // Printing all the effective valid boxes
        //     Box box = boxes[i];
        //     System.out.println(box.h + " " + box.w + " " + box.l);
        // }
        
        
        // NOTE : Here you cannot use NlogN solution, 
        // because that is only used to find the "LIS in terms of length" 
        
        // If you make all the heights as 1 instead of some x height, then this ques. is exactly russian doll 
        
        // Now, perform LIS on box.l => box length 
        // And dp will store mere pe kahatam hone wala LIS ka "max height" 
        int overAllMaxHeight = 0;
        int[] dp = new int[boxes.length]; // Mere pe kahatam hone wala LIS ka max. height  
        for(int i = 0; i < boxes.length; i++) {
            Box currBox = boxes[i];
            
            int maxPrevHeight = 0;
            for(int j = i - 1; j >= 0; j--) { // Apne se piche 
                Box prevBox = boxes[j];
                if(prevBox.l < currBox.l) { // apne se chote length waale pe jaao
                    maxPrevHeight = Math.max(maxPrevHeight, dp[j]); // Max of all the heights find karo
                }
            }
            dp[i] = maxPrevHeight + currBox.h; // Aur unke piche chipak jaao
            
            // dp pe last me iterate k bajay, yahi overAllMaxHeight nikalte chalo
            overAllMaxHeight = Math.max(overAllMaxHeight, dp[i]);
        }
        return overAllMaxHeight;
    }
    
}
//---------------------------------------------------------------------------------------------------------------------------------
