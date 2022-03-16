// https://leetcode.com/problems/path-sum-ii/

class Solution {
    
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if(root == null) return new ArrayList<>();
        
        List<List<Integer>> ans = new ArrayList<>();
        pathSum_Util(root, targetSum, new ArrayList<>(), ans);
        return ans;
    }
    
    public static void pathSum_Util(TreeNode root, int targetSum, List<Integer> smallAns, List<List<Integer>> ans){
        if(root == null) return;   
        if(root.left == null && root.right == null) {
            if(targetSum - root.val == 0) {
                smallAns.add(root.val);
                ans.add(new ArrayList<>(smallAns));
                smallAns.remove(smallAns.size() - 1);
            }
            return;
        }            
        
        smallAns.add(root.val); 
        pathSum_Util(root.left, targetSum - root.val, smallAns, ans);
        pathSum_Util(root.right, targetSum - root.val, smallAns, ans);
        smallAns.remove(smallAns.size() - 1);
    }
}    
