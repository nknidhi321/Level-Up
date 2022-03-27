// https://leetcode.com/problems/copy-list-with-random-pointer/

// TC : O(3N), SC : O(1)
// Best Approach

```
class Solution {

    public Node copyRandomList(Node head) {
        
        // Every Original node's next will have CopyNodes just next to it
        // And copy nodes next will point to initial original nodes next 
        insertCopyNodesInOriginalList(head);
        
        // create random pointers link in the copyNodes
        addRandomPointersInCopyNodes(head);
        
        // Extract all the copied nodes
        return extractOnlyCopyNodesFromOriginalList(head);
    }
    
    
    // Copy or Duplicate node will be inserted right after the original node
    public static void insertCopyNodesInOriginalList(Node head) {
        Node curr = head;
        while(curr != null) {
            Node forw = curr.next;
            Node copyNode = new Node(curr.val);
            curr.next = copyNode;
            copyNode.next = forw;
            
            curr = forw;
        }
    }
        
    
    // Original's next is CopyNodes
    // Find Say xth original nodes random pointer address
    // Now, your (x+1)th copyNode random pointer will point to just after the original nodes random pointer 
    public static void addRandomPointersInCopyNodes(Node head) {
        Node curr = head;
        while(curr != null) {
            Node copyNode = curr.next;
            if(curr.random == null) copyNode.random = null; //curr ka random ptr null ko point kar raha tha 
            else {
                Node currRandom = curr.random;
                copyNode.random = currRandom.next;
            }
            curr = curr.next.next;
        }
    }
        
    
    // Segreating the copyNodes from the original List
    // And creating another list of those segregated copyNodes
    public static Node extractOnlyCopyNodesFromOriginalList(Node head) {
        Node curr = head;
        Node dummy = new Node(-1);
        Node prev = dummy;
        while(curr != null) {
            Node forwardsForw = curr.next.next;
            Node copyNode = curr.next;
            prev.next = copyNode;
            curr.next = forwardsForw;
            
            prev = prev.next;
            curr = forwardsForw;
        }
        return dummy.next;       
    }
    
}
```
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// TC : O(N), SC : O(N)
// Map will contain <OriginalNodeAddress, CopyNodeAddress>
// Original list ki duplicate pointer jisko point kar raha hai, uske corresponding konsi node hai CopyList me wo easily find out ho jaaegi through map.

```
class Solution {

    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>(); //{OriginalNodeAddress, CopyNodeAddess}
        Node dummy = new Node(-1);
        Node prev = dummy;
        
        Node curr = head;
        while(curr != null) {
            Node node = new Node(curr.val);
            prev.next = node;
            map.put(curr, node);
            
            prev = prev.next;
            curr = curr.next;
        }
        
        Node c1 = head;
        Node c2 = dummy.next;
        while(c1 != null) {
            if(c1.random == null) c2.random = null; //c1 ka random ptr null ko point kar raha tha
            else c2.random = map.get(c1.random);
            
            c1 = c1.next;
            c2 = c2.next;
        }
        return dummy.next;       
    }
}
```

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
