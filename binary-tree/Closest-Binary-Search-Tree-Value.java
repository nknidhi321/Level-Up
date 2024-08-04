// https://www.lintcode.com/problem/900/

public class Solution {

    double min;
    int ans;

    public int closestValue(TreeNode root, double target) {
        min = Double.MAX_VALUE;
        ans = Integer.MAX_VALUE;
        closestValueHelper(root, target);
        return ans;
    }

    public void closestValueHelper(TreeNode root, double target) {
        if(root == null) return;

        double diff = Math.abs(root.val - target);
        if(diff < min) {
            min = diff;
            ans = root.val;
        }
        
        if(root.val > target) {
            closestValueHelper(root.left, target);
        }
        else if(root.val < target) {
            closestValueHelper(root.right, target);
        }
        return;
    }

}
