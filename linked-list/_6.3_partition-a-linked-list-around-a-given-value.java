// https://practice.geeksforgeeks.org/problems/partition-a-linked-list-around-a-given-value/1/#
// Pivot element can be multiple, So you will have to take 3 ptrs. Ex : abcdxxxxxxxxxxxijklm

class Solution {
  
    public static Node partition(Node head, int pivot) {
        Node dummy0 = new Node(-1);  // Represents < x
        Node dummy1 = new Node(-1);  // Represents == x
        Node dummy2 = new Node(-1);  // Represents > x
        
        Node p0 = dummy0;
        Node p1 = dummy1;
        Node p2 = dummy2;
        
        Node curr = head;
        while(curr != null) {
            if(curr.data < pivot) {  // Only change from segregate 012
                p0.next = curr;
                p0 = p0.next;
            }
            else if (curr.data == pivot) {  // Only change from segregate 012
                p1.next = curr;
                p1 = p1.next;
            }
            else {
                p2.next = curr;
                p2 = p2.next;
            }
            curr = curr.next;
        }
        
        p0.next = p1.next = p2.next = null; // Make sure to make it null, otherwise it will form a cycle
        
        // Jab ek v x nahi mila, then p0 k last node ko p2 k head se jor do
        if(dummy1.next == null) p0.next = dummy2.next;
        else {
            p0.next = dummy1.next;  // Saare 0 k baad saare 1 should come as per question
            p1.next = dummy2.next;  // Saare 1 k baad saare 2 should come as per question
        }
        return dummy0.next;
    }
}
