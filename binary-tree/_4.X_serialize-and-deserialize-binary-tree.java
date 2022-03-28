// https://leetcode.com/problems/serialize-and-deserialize-binary-tree/

//Using StringBuilder
//Faster

```
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }
    
    public void serializeHelper(TreeNode root, StringBuilder sb){
        if(root == null){
            sb.append("null").append(",");
            return;
        }
        sb.append(root.val).append(",");
        serializeHelper(root.left, sb);
        serializeHelper(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strArr = data.split(",");
        return deserializeHelper(strArr);
    }
    
    int idx = 0;
    public TreeNode deserializeHelper(String[] strArr){
        if(idx == strArr.length || strArr[idx].equals("null")){
            idx++;
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(strArr[idx++]));
        root.left = deserializeHelper(strArr);
        root.right = deserializeHelper(strArr);
        return root;
    }
}
```

----------------------------------------------------------------------------------

// Using String
// Slower

```
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return serializeHelper(root, "");
    }
    
    public String serializeHelper(TreeNode root, String ssf){
        if(root == null)
            return ssf + "null,";
        ssf += root.val + ",";
        ssf = serializeHelper(root.left, ssf);
        ssf = serializeHelper(root.right, ssf);
        return ssf;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strArr = data.split(",");
        return deserializeHelper(strArr);
    }
    
    int idx = 0;
    public TreeNode deserializeHelper(String[] strArr){
        if(idx == strArr.length || strArr[idx].equals("null")){
            idx++;
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(strArr[idx++]));
        root.left = deserializeHelper(strArr);
        root.right = deserializeHelper(strArr);
        return root;
    }
}
```

----------------------------------------------------------------------------------------
