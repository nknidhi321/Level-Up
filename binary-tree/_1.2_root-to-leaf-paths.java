//https://practice.geeksforgeeks.org/problems/root-to-leaf-paths/1#

/*
1st Approach
============
The approach is to keep on forming the ans while moving up to the leaf node(Bottom-up)
Requires backtracking since we are forming the answer while moving up, [All paths question]
*/

class Tree{
    public ArrayList<ArrayList<Integer>> Paths(Node root){
        // Code here
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> smallAns = new ArrayList<>();
        rootToAllLeafPath(root, smallAns, ans);
        return ans;
    }
    
    public void rootToAllLeafPath(Node root, ArrayList<Integer> smallAns, ArrayList<ArrayList<Integer>> ans){
        if(root == null) return;
        if(root.left == null && root.right == null){
            ArrayList<Integer> base = new ArrayList<>(smallAns);  //deep copying the path, since backtracking is to be done in smallAns
            base.add(root.data);  //adding leaf node in base //Note: If you do this step in smallAns, also make sure to backtrack
            ans.add(base);  //creating our final ans list
            return;
        }
        
        smallAns.add(root.data);  //making it part of smallAns
        rootToAllLeafPath(root.left, smallAns, ans);
        rootToAllLeafPath(root.right, smallAns, ans);
        smallAns.remove(smallAns.size() - 1);  //backtracking //for another path
    }
    
}


//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
//$$$$$$$$$$$$$$$===============================$$$$$$$$$$$$$$$$$$$$$$$$$
//$$$$$$$$$$$$$$$==========IMPORTANT============$$$$$$$$$$$$$$$$$$$$$$$$$ 
//$$$$$$$$$$$$$$$===============================$$$$$$$$$$$$$$$$$$$$$$$$$
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

/*
2nd Approach
============
The faith is we will get all paths from left and right, and to form our answer we will add ourself in each of the path and return.

NOTE: Here the question demands root to leaf path, and if we add ourself at the last, then we have to reverse each of the smallAns of the final ans list,
this would require another helper function to reverse the final ans list.
So, to escape the above problem and inorder to solve the question without any helper function, we can add ourself at the 0th index of the left and right smallAns
But if we do so there will be shifting of O(n) already added elements in the list. Therefore, this Solution increases the complxity of the algorithm.
So, the 1st Solution will be a better approach for this question.
*/

class Tree{
    public ArrayList<ArrayList<Integer>> Paths(Node root){
        // Code here
        if(root == null) return new ArrayList<>();
        if(root.left == null && root.right == null){
            ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
            ArrayList<Integer> smallAns = new ArrayList<>();
            smallAns.add(root.data);
            ans.add(smallAns);
            return ans;
        }
        
        ArrayList<ArrayList<Integer>> ans = new ArrayList();
        
        ArrayList<ArrayList<Integer>> left = Paths(root.left);
        for(ArrayList<Integer> smallAns : left){
            smallAns.add(0, root.data);
            ans.add(smallAns);
        }
        
        ArrayList<ArrayList<Integer>> right = Paths(root.right);
        for(ArrayList<Integer> smallAns : right){
            smallAns.add(0, root.data);
            ans.add(smallAns);
        }
        return ans;
    }
    
}

