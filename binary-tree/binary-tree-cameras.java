// https://leetcode.com/problems/binary-tree-cameras/

// With comments
// Every node will return it's own state

class Solution {
    
    public int minCameraCover(TreeNode root) {
		  // Edge case, when root will need camera
       return minCameraCoverHelper(root) == -1 ? ++count : count;
    }
    
    public int count = 0;
    
    // 1 = Covered by Child(Do not need Camera)
    // 0 = Itself a camera(Has Camera)
    // -1 = Covered by None(Need Camera)
    
    public int minCameraCoverHelper(TreeNode root){
        if(root == null){
            return 1;
        }
        
        int left = minCameraCoverHelper(root.left);
        int right = minCameraCoverHelper(root.right);
        
        if(left == -1 || right == -1){
            count++;
            return 0;                   //Root becoming the camera since any or both of his child needs a camera
        }
        else if(left == 0 || right == 0){
            return 1;                   //I'm covered by my child and parent you see for yourself
        }
        else{                           //left == 1 && right == 1
            return -1;                  //Asking parent to cover him, since both of his child are covered by their child
        }
    }
}

------------------------------------------------------------------------------------------------------------------------------------
  
//Same code 
//Without comments

class Solution {
    
    public int minCameraCover(TreeNode root) {
       return minCameraCoverHelper(root) == -1 ? ++count : count;
    }
    
    public int count = 0;
    
    //1 = Covered by Child(Do not need Camera)
    //0 = Itself a camera(Has Camera)
    //-1 = Covered by None(Need Camera)
    
    public int minCameraCoverHelper(TreeNode root){
        if(root == null){
            return 1;
        }
        
        int left = minCameraCoverHelper(root.left);
        int right = minCameraCoverHelper(root.right);
        
        if(left == -1 || right == -1){
            count++;
            return 0;                  
        }
        else if(left == 0 || right == 0){
            return 1;                   
        }
        else{                           
            return -1;                 
        }
    }
}

------------------------------------------------------------------------------------------------------------			
