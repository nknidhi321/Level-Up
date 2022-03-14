//https://leetcode.com/problems/house-robber-iii/

class Solution {
    
    class Pair{
        int withRob;
        int withoutRob;
        
        public Pair(int withRob, int withoutRob){
            this.withRob = withRob;
            this.withoutRob = withoutRob;
        }
    }
    
    public int rob(TreeNode root) {
         Pair obj = robHelper(root);
         return Math.max(obj.withRob, obj.withoutRob);
    }
    
    public Pair robHelper(TreeNode root){
        if(root == null)
            return new Pair(0, 0);
        
        Pair leftObj = robHelper(root.left);
        Pair rightObj = robHelper(root.right);
        
        int withRob = leftObj.withoutRob + rightObj.withoutRob + root.val;
        int withoutRob = Math.max(leftObj.withRob, leftObj.withoutRob) + Math.max(rightObj.withRob, rightObj.withoutRob);
        
        return new Pair(withRob, withoutRob);
    }
    
}
