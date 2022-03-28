// https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/

class Solution {
    
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 0) return null;
        return constructFromInOrder(nums, 0, nums.length-1);
    }
    
    public static TreeNode constructFromInOrder(int[] nums, int low, int high){
        if(low > high) return null;
        
        int mid = (low + high) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = constructFromInOrder(nums, low, mid - 1);
        root.right = constructFromInOrder(nums, mid + 1, high);
        return root;
    }
    
}
