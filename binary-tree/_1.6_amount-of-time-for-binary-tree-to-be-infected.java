// https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/

// Create adjacency graph
// Now traversal on the graph from source using bfs
// Not Making an extra DFS call because adjacency list is already created

```
class Solution {

    public int amountOfTime(TreeNode root, int start) {
        if (root == null) return 0;

        // Convert the tree to a graph (adjacency list)
        Map<Integer, List<Integer>> graph = new HashMap<>();
        buildGraph(root, null, graph);

        // Perform BFS from the start node
        return bfs(start, graph);
    }

    private void buildGraph(TreeNode node, TreeNode parent, Map<Integer, List<Integer>> graph) {
        if (node == null) return;

        graph.putIfAbsent(node.val, new ArrayList<>());
        if (parent != null) {
            graph.putIfAbsent(parent.val, new ArrayList<>());
            
            graph.get(node.val).add(parent.val); // create birectional edge
            graph.get(parent.val).add(node.val); // create birectional edge
        }

        buildGraph(node.left, node, graph);
        buildGraph(node.right, node, graph);
    }

    private int bfs(int start, Map<Integer, List<Integer>> graph) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);

        int minutes = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int node = queue.poll();
                for (int neighbor : graph.get(node)) {
                    if (!visited.contains(neighbor)) {
                        queue.offer(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
            if (!queue.isEmpty()) {
                minutes++;
            }
        }

        return minutes;
    }

}

```

// ============================================================================================================================

// Create parent Map
// Make dfs to find start node
// Make bfs call on tree find the start node
// Making an extra DFS call as start is given in int and not TreeNode

```
class Solution {
    
    static class Pair {
        TreeNode node;
        int level;
        
        Pair(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }
    
    public int amountOfTime(TreeNode root, int start) {
        Map<Integer, TreeNode> map = new HashMap<>();
        createParentMap(root, null, map);
        return bfs(root, start, map);
    }
    
    private void createParentMap(TreeNode node, TreeNode parent, Map<Integer, TreeNode> map) {
        if (node == null) return;
        if (parent != null) {
            map.put(node.val, parent);
        }
        createParentMap(node.left, node, map);
        createParentMap(node.right, node, map);
    }
    
     private TreeNode findNode(TreeNode root, int value) {
        if (root == null) return null;
        if (root.val == value) return root;
        TreeNode leftResult = findNode(root.left, value);
        if (leftResult != null) return leftResult;
        return findNode(root.right, value);
    }
    
    private int bfs(TreeNode root, int start, Map<Integer, TreeNode> parentMap) {
        TreeNode startNode = findNode(root, start);
        if (startNode == null) return 0;
        
        Queue<Pair> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        
        queue.offer(new Pair(startNode, 0));
        visited.add(startNode.val);
        
        int maxLevel = 0;
        
        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            TreeNode node = current.node;
            int level = current.level;
            
            maxLevel = Math.max(maxLevel, level);
            
            // Visit left child
            if (node.left != null && !visited.contains(node.left.val)) {
                queue.offer(new Pair(node.left, level + 1));
                visited.add(node.left.val);
            }
            
            // Visit right child
            if (node.right != null && !visited.contains(node.right.val)) {
                queue.offer(new Pair(node.right, level + 1));
                visited.add(node.right.val);
            }
            
            // Visit parent
            if (parentMap.containsKey(node.val)) {
                TreeNode parent = parentMap.get(node.val);
                if (!visited.contains(parent.val)) {
                    queue.offer(new Pair(parent, level + 1));
                    visited.add(parent.val);
                }
            }
        }
        
        return maxLevel;
    }
    
}

```
// ======================================================================================================================================

