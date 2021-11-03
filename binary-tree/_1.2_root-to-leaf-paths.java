//https://practice.geeksforgeeks.org/problems/root-to-leaf-paths/1#

//NOTE: All paths questions require backtracking 

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
