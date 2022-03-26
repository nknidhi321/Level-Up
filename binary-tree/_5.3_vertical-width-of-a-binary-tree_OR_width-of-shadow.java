// https://practice.geeksforgeeks.org/problems/vertical-width-of-a-binary-tree/1/#

// Root ko 0 idx maan lo, left me idx - 1 bhejo and right me idx + 1 bhejo  
// Kisi v level pe maxLeft kitna gaya - kisi v level pe maxRight kitna gaya + 1    [ Why + 1 ? Mere ko include kar k ]

class Tree {
   
    public static int verticalWidth(Node root) {
	   if(root == null) return 0;
        
        int[] minMax = new int[2]; // {maxVerticalLeftSideWidth, maxVerticalRightSideWidth}
        widthOfShadow(root, minMax, 0);
        return minMax[1] - minMax[0] + 1;
    }
    
    public static void widthOfShadow(Node root, int[] minMax, int idx) {
        if(root == null) return;
        
        minMax[0] = Math.min(minMax[0], idx);
        minMax[1] = Math.max(minMax[1], idx);
        widthOfShadow(root.left, minMax, idx - 1);
        widthOfShadow(root.right, minMax, idx + 1);  
    }
	
}
