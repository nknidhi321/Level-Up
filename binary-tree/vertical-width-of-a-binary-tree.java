// https://practice.geeksforgeeks.org/problems/vertical-width-of-a-binary-tree/1/#

// Root ko 0 idx maan lo, left me idx - 1 bhejo and right me idx + 1 bhejo  
// Kisi v level pe maxLeft kitna gaya - kisi v level pe maxRight kitna gaya + 1    [ Why + 1 ? Mere ko include kar k ]

class Tree {
   
    public static int verticalWidth(Node root) {
	   if(root == null) return 0;
        
        int[] arr = new int[2]; // {maxHorizonatlLeftWidth, maxHorizontalRightWidth}
        verticalWidth_Util(root, arr, 0);
        return arr[1] - arr[0] + 1;
    }
    
    public static void verticalWidth_Util(Node root, int[] arr, int idx) {
        if(root == null) return;
        
        arr[0] = Math.min(arr[0], idx);
        arr[1] = Math.max(arr[1], idx);
        verticalWidth_Util(root.left, arr, idx - 1);
        verticalWidth_Util(root.right, arr, idx + 1);  
    }
	
}
