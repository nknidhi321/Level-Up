// https://leetcode.com/problems/unique-binary-search-trees-ii/ 
// https://www.youtube.com/watch?v=qOItdXuTZGo

// NOTE : Agar sirf count poocha hota tb catalan lga lete
// Not a DP question

```
class Solution {
    
    public List<TreeNode> generateTrees(int n) {
        return genertateSubtree(1, n);
    }

    public List<TreeNode> genertateSubtree(int si, int ei) {
        if(si > ei) {
            List<TreeNode> base = new ArrayList<>();
            base.add(null);
            return base;
        }        
        
        List<TreeNode> myAns = new ArrayList<>();
        for(int cut = si; cut <= ei; cut++) { // Saare nodes ek ek baar root bnega
            
            // Tumse jo left me hai, wo left subtree he bnenge to hold BST property
            List<TreeNode> leftList = genertateSubtree(si, cut - 1); 
            
            // Tumse jo right me hai, wo right subtree he bnenge to hold BST property
            List<TreeNode> rightList = genertateSubtree(cut + 1, ei);
         
            // You have to generate all the "combinations", 
            // So, leftList ya rightList kisi ko v inner outer loop bna lo, no matter
            for(TreeNode l : leftList) {
                for(TreeNode r : rightList) {                
                    TreeNode root = new TreeNode(cut); // root andar he bnega kuki sabka individual address hona chahiye           
                    root.left = l; // Jo left subtree hai usko left me daal do
                    root.right = r; // Jo right subtree hai usko right me daal do
                    myAns.add(root); // Apne aap ko answer me add kar lo.
                }
            }
        }
        return myAns;
    }
    
}
```
-------------------------------------------------------------------------------------------------------------------------------------
