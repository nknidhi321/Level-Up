// https://www.lintcode.com/problem/901/description

public class Solution {
  
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        LinkedList<Integer> queue = new LinkedList<>();
        dfsInOrder(root, target, k, queue);
        return new ArrayList<>(queue);
    }

    public void dfsInOrder(TreeNode root, double target, int k, LinkedList<Integer> queue) {
        if(root == null) return;

        dfsInOrder(root.left, target, k, queue);

        if(queue.size() < k) {
            queue.add(root.val);
        }
        else { 
            // Since travelling in InOrder so we only need to check the firstDiff and upcomingLastDiff,
            // because it will already be in sorted order
            double firstDiff = Math.abs(queue.getFirst() - target);
            double upcomingLastDiff = Math.abs(root.val - target);
            if(firstDiff > upcomingLastDiff) {
                queue.removeFirst();
                queue.addLast(root.val);
            }
            else { // If upcomingLastDiff is greater than the firstDiff, no need to check further,
                   // because its inorder and more upcomingLastDiff will obviously be larger than the current
                return; 
            }
        }
        dfsInOrder(root.right, target, k, queue);
    }

}
