// https://leetcode.com/problems/serialize-and-deserialize-binary-tree/

// Idea : Travel in preOrder and add all the nodes + null nodes in stringBuilder to serialize
// Now, to deserialize you have an array of preOrder, you just have to convert it to BST using the same range [min, max] ki kahani

// Using StringBuilder
// Faster

```
public class Codec {

    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }
    
    public void serializeHelper(TreeNode root, StringBuilder sb) {
        if(root == null) {
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
        int[] idx = new int[] {0};
        return deserializeHelper(strArr, idx);
    }
    
    public TreeNode deserializeHelper(String[] strArr, int[] idx) {
        if(idx[0] == strArr.length || strArr[idx[0]].equals("null")) {
            idx[0]++; // Pehle condition k wazah se loop kvi break nai hoga
            return null; // Kuki "tree k nodes + null nodes" == "strArr me jo nodes hai"
        }
        TreeNode root = new TreeNode(Integer.parseInt(strArr[idx[0]++]));
        root.left = deserializeHelper(strArr, idx);
        root.right = deserializeHelper(strArr, idx);
        return root;
    }
    
    
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));```

--------------------------------------------------------------------------------------------------

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

----------------------------------------------------------------------------------------------
