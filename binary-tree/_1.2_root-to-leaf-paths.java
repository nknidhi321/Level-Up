//https://practice.geeksforgeeks.org/problems/root-to-leaf-paths/1#

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
            ArrayList<Integer> base = new ArrayList<>(smallAns);
            base.add(root.data);
            ans.add(base);
            return;
        }
        
        smallAns.add(root.data);
        rootToAllLeafPath(root.left, smallAns, ans);
        rootToAllLeafPath(root.right, smallAns, ans);
        smallAns.remove(smallAns.size() - 1);
    }
    
}
