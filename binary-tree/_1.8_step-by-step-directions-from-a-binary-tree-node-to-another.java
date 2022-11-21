// https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/

class Solution {
    
    public String getDirections(TreeNode root, int startValue, int destValue) {
        StringBuilder sourcePath = new StringBuilder();
        StringBuilder destPath = new StringBuilder();
        modifiedLCA(root, startValue, destValue, sourcePath, destPath);
        return sourcePath.append(destPath.reverse()).toString();    
    }
    
    public char modifiedLCA(TreeNode root, int startValue, int destValue, StringBuilder sourcePath, StringBuilder destPath) {
        if(root == null) return '#';
        
        // Poora tree traversal k baad he return marna, because dest tumhare niche v ho skta h
        char left = modifiedLCA(root.left, startValue, destValue, sourcePath, destPath);
        char right = modifiedLCA(root.right, startValue, destValue, sourcePath, destPath);
        
        // s => source k taraf wala node h
        // d => dest k traraf wala node h
        
        // Being parent add your childs path 
        if(left == 's') sourcePath.append('U'); 
        else if(left == 'd') destPath.append('L'); 
        
        if(right == 's') sourcePath.append('U'); 
        else if(right == 'd') destPath.append('R');
        
        // LCA ka logic
        if(left != '#' && right != '#') { // pehle he dono node source && dest mil chuka hai
            return '#'; // Kuch add nahi krna bs return kr jaao
        }
        else if(left != '#') { // left me ek node(source/dest) mil gya h
            if(root.val == startValue || root.val == destValue) return '#'; // Dusra node tum khud ho, so kuch add nahi krna
            else return left; // Dusra node milna baaki hai, so add your path
        }
        else if(right != '#') { // right me ek node(source/dest) mil gya h
            if(root.val == startValue || root.val == destValue) return '#'; // Dusra node tum khud ho, so kuch add nahi krna
            else return right; // Dusra node milna baaki hai, so add your path
        }        

        if(root.val == startValue) return 's'; // tumhara parent, tumse aur parent k bich ka path add krega
        else if(root.val == destValue) return 'd'; // tumhara parent, tumse aur parent k bich ka path add krega
        
        return '#'; // Kuch add nahi krna bs return kr jaao
    }
    
}
